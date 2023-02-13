package org.shiloh.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.shiloh.jooq.codegen.tables.pojos.SysUserPojo;
import org.shiloh.test.base.JooqSpringTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link org.shiloh.jooq.codegen.tables.daos.SysUserDao} 单元测试
 *
 * @author shiloh
 * @date 2023/2/13 18:11
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SysUserDaoTests extends JooqSpringTest {
    /**
     * 新增用户的 ID
     */
    private Long newUserId;

    /**
     * 测试查询所有用户信息
     *
     * @author shiloh
     * @date 2023/2/13 18:12
     */
    @Test
    public void test01FindAll() {
        final List<SysUserPojo> sysUsers = this.sysUserDao.findAll();
        assertThat(sysUsers).isNotEmpty();
    }

    /**
     * 测试插入用户信息自动回滚
     *
     * @author shiloh
     * @date 2023/2/13 18:13
     */
    @Test
    public void test02InsertAutoRollback() {
        final SysUserPojo sysUserPojo = new SysUserPojo();
        sysUserPojo.setUsername("shiloh-spring");
        sysUserPojo.setPassword("pass-spring");
        sysUserPojo.setAge(18);
        this.sysUserDao.insert(sysUserPojo);
        assertThat(sysUserPojo.getId()).isNotNull();
        this.newUserId = sysUserPojo.getId();
    }

    /**
     * 测试根据 ID 查询用户信息
     *
     * @author shiloh
     * @date 2023/2/13 18:14
     */
    @Test
    public void test03FindById() {
        final SysUserPojo sysUserPojo = this.sysUserDao.findById(1L);
        assertThat(sysUserPojo).isNotNull();

        if (this.newUserId == null) {
            return;
        }

        final SysUserPojo rollback = this.sysUserDao.findById(this.newUserId);
        assertThat(rollback).isNull();
    }
}
