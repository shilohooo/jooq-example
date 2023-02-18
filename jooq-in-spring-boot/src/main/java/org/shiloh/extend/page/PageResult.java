package org.shiloh.extend.page;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * 通用分页查询结果封装
 *
 * @param <T> 查询结果类型
 * @author shiloh
 * @date 2023/2/18 12:27
 */
@Data
@NoArgsConstructor
public class PageResult<T> {
    /**
     * 当前页码
     */
    private Integer pageIndex;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Long totalPages;

    /**
     * 总记录数
     */
    private Long totalElements;

    /**
     * 数据列表
     */
    private List<T> data;

    public PageResult(Integer pageIndex, Integer pageSize, Long totalElements, List<T> data) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.data = data;

        if (this.totalElements == 0) {
            this.totalPages = 0L;
            return;
        }

        this.calcTotalPages();
    }

    /**
     * 计算总页数
     *
     * @author shiloh
     * @date 2023/2/18 13:23
     */
    public void calcTotalPages() {
        if (this.totalElements == null || this.totalElements == 0L) {
            this.totalPages = 0L;
            return;
        }
        
        this.totalPages = this.totalElements % this.pageSize == 0
                ? this.totalElements / this.pageSize
                : this.totalElements / this.pageSize + 1;
    }

    /**
     * 构建查询数据为空的分页查询结果集
     *
     * @param pageIndex 当前页码
     * @return 分页查询结果集
     * @author shiloh
     * @date 2023/2/18 13:11
     */
    public static <T> PageResult<T> empty(Integer pageIndex) {
        return new PageResult<>(pageIndex, 0, 0L, Collections.emptyList());
    }

    /**
     * 从给定的数据中拷贝数据
     *
     * @param source 源数据
     * @author shiloh
     * @date 2023/2/18 12:38
     */
    public void from(PageResult<T> source) {
        this.setPageIndex(source.getPageIndex());
        this.setPageSize(source.getPageSize());
        this.setTotalElements(source.getTotalElements());
        this.setData(source.getData());

        this.calcTotalPages();
    }

    /**
     * 将数据转换到给定目标中
     *
     * @param target 目标对象
     * @return 目标对象
     * @author shiloh
     * @date 2023/2/18 12:39
     */
    public PageResult<T> into(PageResult<T> target) {
        target.from(this);
        return target;
    }
}
