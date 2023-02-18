package org.shiloh.extend.dao.impl;

import org.jooq.*;
import org.jooq.impl.DAOImpl;
import org.jooq.impl.DSL;
import org.shiloh.extend.dao.BaseDao;
import org.shiloh.extend.page.PageResult;

import java.util.List;
import java.util.Optional;

import static org.jooq.conf.ParamType.INLINED;

/**
 * jOOQ {@link org.jooq.impl.DAOImpl} 扩展
 *
 * @author shiloh
 * @date 2023/2/18 11:16
 */
public abstract class BaseDaoImpl<R extends UpdatableRecord<R>, P, T> extends DAOImpl<R, P, T>
        implements BaseDao<R, P, T> {

    public static final String SELECT = "select";

    protected BaseDaoImpl(Table<R> table, Class<P> type) {
        super(table, type);
    }

    protected BaseDaoImpl(Table<R> table, Class<P> type, Configuration configuration) {
        super(table, type, configuration);
    }

    /**
     * 获取 {@link DSLContext}
     *
     * @return {@link DSLContext}
     * @author shiloh
     * @date 2023/2/18 12:20
     */
    @Override
    public DSLContext create() {
        return DSL.using(super.configuration());
    }

    /**
     * 根据查询条件查询单条记录
     *
     * @param condition 查询条件
     * @return 泛型 P 对应的 Pojo 实例
     * @author shiloh
     * @date 2023/2/18 12:20
     */
    @Override
    public P fetchOne(Condition condition) {
        return this.create().selectFrom(super.getTable())
                .where(condition)
                .orderBy(super.getTable().getPrimaryKey().getFields())
                .fetchOne(this.mapper());
    }

    /**
     * 根据查询条件获取包含单挑记录的 {@link Optional<P>} 实例
     *
     * @param condition 查询条件
     * @return {@link Optional<P>}
     * @author shiloh
     * @date 2023/2/18 12:22
     */
    @Override
    public Optional<P> fetchOneOptional(Condition condition) {
        return Optional.ofNullable(this.fetchOne(condition));
    }

    /**
     * 根据条件查询所有记录，并按给定排序字段进行排序
     *
     * @param condition  查询条件
     * @param sortFields 排序字段，一个或多个
     * @return 泛型 P 对应的 Pojo 列表
     * @author shiloh
     * @date 2023/2/18 12:23
     */
    @Override
    public List<P> fetch(Condition condition, SortField<?>... sortFields) {
        return this.create().selectFrom(super.getTable())
                .where(condition)
                .orderBy(sortFields)
                .fetch(super.mapper());
    }

    /**
     * 根据查询条件查询分页数据，并按给定的排序字段进行排序
     *
     * @param pageResult 分页查询参数，在此对象中设置分页参数
     * @param condition  查询条件
     * @param sortFields 排序字段，一个或多个
     * @return 分页查询结果集
     * @author shiloh
     * @date 2023/2/18 12:41
     */
    @Override
    public PageResult<P> fetchPage(PageResult<P> pageResult, Condition condition, SortField<?>... sortFields) {
        return this.fetchPage(pageResult, this.create().selectFrom(super.getTable()).where(condition).orderBy(sortFields));
    }

    /**
     * 根据给定的查询语句查询分页数据
     *
     * @param pageResult      分页查询参数
     * @param selectLimitStep 查询语句
     * @return 分页查询结果集
     * @author shiloh
     * @date 2023/2/18 12:43
     */
    @Override
    public PageResult<P> fetchPage(PageResult<P> pageResult, SelectLimitStep<?> selectLimitStep) {
        return this.fetchPage(pageResult, selectLimitStep, record -> record.into(super.getType()));
    }

    /**
     * 根据给定的查询语句获取任意 Pojo 类型的分页数据
     *
     * @param pageResult      分页查询参数
     * @param selectLimitStep 查询语句
     * @param pojoType        Pojo 类型
     * @return 分页查询结果集
     * @author shiloh
     * @date 2023/2/18 12:49
     */
    @Override
    public <O> PageResult<O> fetchPage(PageResult<O> pageResult, SelectLimitStep<?> selectLimitStep, Class<O> pojoType) {
        return this.fetchPage(pageResult, selectLimitStep, record -> record.into(pojoType));
    }

    /**
     * 根据给定的查询语句获取任意 {@link Record} 类型的分页数据
     *
     * @param pageResult      分页查询参数
     * @param selectLimitStep 查询语句
     * @param mapper          {@link Record} 类型的 mapper
     * @return 分页查询结果集
     * @author shiloh
     * @date 2023/2/18 12:46
     */
    @Override
    public <O> PageResult<O> fetchPage(
            PageResult<O> pageResult,
            SelectLimitStep<?> selectLimitStep,
            RecordMapper<? super Record, O> mapper
    ) {
        // 每页记录数为 0，不发起查询
        if (pageResult.getPageSize() == 0) {
            return PageResult.empty((pageResult.getPageIndex() - 1) - pageResult.getPageSize());
        }

        // 这里分页使用的是 MySQL 的 SQL_CALC_FOUND_ROWS 关键字和 SELECT FOUND_ROWS() 语句。
        // 在查询之前，获得原始SQL语句，并且在 select 后拼接上 SQL_CALC_FOUND_ROWS 关键字，
        // 在SQL执行完毕后通过 SELECT FOUND_ROWS() 可以查询出总行数
        String pageSql = selectLimitStep.getSQL(INLINED);
        pageSql = String.format("%s SQL_CALC_FOUND_ROWS %s limit ?, ?",
                SELECT, pageSql.substring(pageSql.indexOf(SELECT) + SELECT.length())
        );
        final List<O> data = this.create().fetch(
                        pageSql,
                        // 绑定参数
                        (pageResult.getPageIndex() - 1) * pageResult.getPageSize(),
                        pageResult.getPageSize()
                )
                .map(mapper);
        final long totalElements = this.create().fetchOne("SELECT FOUND_ROWS()")
                .into(Long.class);
        final PageResult<O> result = pageResult.into(new PageResult<>());
        result.setData(data);
        result.setTotalElements(totalElements);
        // 计算总页数
        result.calcTotalPages();
        return result;
    }
}
