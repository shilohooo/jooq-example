package org.shiloh.extend.dao;

import org.jooq.*;
import org.shiloh.extend.page.PageResult;

import java.util.List;
import java.util.Optional;

/**
 * Base DAO
 *
 * @author shiloh
 * @date 2023/2/18 12:19
 */
public interface BaseDao<R extends UpdatableRecord<R>, P, T> extends DAO<R, P, T> {
    /**
     * 获取 {@link DSLContext}
     *
     * @return {@link DSLContext}
     * @author shiloh
     * @date 2023/2/18 12:20
     */
    DSLContext create();

    /**
     * 根据查询条件查询单条记录
     *
     * @param condition 查询条件
     * @return 泛型 P 对应的 Pojo 实例
     * @author shiloh
     * @date 2023/2/18 12:20
     */
    P fetchOne(Condition condition);

    /**
     * 根据查询条件获取包含单挑记录的 {@link Optional<P>} 实例
     *
     * @param condition 查询条件
     * @return {@link Optional<P>}
     * @author shiloh
     * @date 2023/2/18 12:22
     */
    Optional<P> fetchOneOptional(Condition condition);

    /**
     * 根据条件查询所有记录，并按给定排序字段进行排序
     *
     * @param condition  查询条件
     * @param sortFields 排序字段，一个或多个
     * @return 泛型 P 对应的 Pojo 列表
     * @author shiloh
     * @date 2023/2/18 12:23
     */
    List<P> fetch(Condition condition, SortField<?>... sortFields);

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
    PageResult<P> fetchPage(PageResult<P> pageResult, Condition condition, SortField<?>... sortFields);

    /**
     * 根据给定的查询语句查询分页数据
     *
     * @param pageResult      分页查询参数
     * @param selectLimitStep 查询语句
     * @return 分页查询结果集
     * @author shiloh
     * @date 2023/2/18 12:43
     */
    PageResult<P> fetchPage(PageResult<P> pageResult, SelectLimitStep<?> selectLimitStep);

    /**
     * 根据给定的查询语句获取任意 {@link Record} 类型的分页数据
     *
     * @param pageResult      分页查询参数
     * @param selectLimitStep 查询语句
     * @param mapper          {@link Record} 类型的 mapper
     * @param <O>             返回值类型的泛型
     * @return 分页查询结果集
     * @author shiloh
     * @date 2023/2/18 12:46
     */
    <O> PageResult<O> fetchPage(
            PageResult<O> pageResult,
            SelectLimitStep<?> selectLimitStep,
            RecordMapper<? super Record, O> mapper
    );

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
    <O> PageResult<O> fetchPage(PageResult<O> pageResult, SelectLimitStep<?> selectLimitStep, Class<O> pojoType);
}
