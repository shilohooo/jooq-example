package org.shiloh.jooq.test;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.shiloh.extend.page.PageResult;
import org.shiloh.jooq.codegen.tables.daos.SysUserDao;
import org.shiloh.jooq.codegen.tables.pojos.SysUserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.shiloh.jooq.codegen.tables.TSysUser.SYS_USER;

/**
 * @author shiloh
 * @date 2023/2/18 18:21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback
public class SysUserDaoTests {
    @Autowired
    private SysUserDao sysUserDao;

    @Test
    public void test() {
        Assertions.assertThat(this.sysUserDao).isNotNull();
    }

    @Test
    public void testFindAllUsers() {
        final List<SysUserPojo> sysUsers = this.sysUserDao.fetch(SYS_USER.AGE.lt(20));
        Assertions.assertThat(sysUsers).isNotEmpty();
    }

    @Test
    public void testFindAllByPage() {
        PageResult<SysUserPojo> pageResult = new PageResult<>();
        pageResult.setPageIndex(1);
        pageResult.setPageSize(5);
        pageResult = this.sysUserDao.fetchPage(pageResult, SYS_USER.AGE.lt(20), SYS_USER.AGE.asc());
        Assertions.assertThat(pageResult.getTotalPages()).isGreaterThan(0);
        Assertions.assertThat(pageResult.getTotalElements()).isGreaterThan(0);
        Assertions.assertThat(pageResult.getData()).isNotEmpty();
    }
}
