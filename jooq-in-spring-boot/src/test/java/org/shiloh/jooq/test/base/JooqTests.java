package org.shiloh.jooq.test.base;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.shiloh.jooq.App;
import org.shiloh.jooq.codegen.tables.TSysUser;
import org.shiloh.jooq.codegen.tables.pojos.SysUserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author shiloh
 * @date 2023/2/16 18:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@Transactional
@Rollback
public class JooqTests {
    @Autowired
    protected DSLContext dslContext;

    @Autowired
    protected Configuration configuration;

    @Autowired
    protected TransactionManager transactionManager;

    @Test
    public void testJooqBeanNotNull() {
        assertThat(this.dslContext).isNotNull();
        assertThat(this.configuration).isNotNull();
        assertThat(this.transactionManager).isNotNull();
    }

    @Test
    public void testFindAllUser() {
        final List<SysUserPojo> sysUsers = this.dslContext.select()
                .from(TSysUser.SYS_USER)
                .fetchInto(SysUserPojo.class);
        assertThat(sysUsers).isNotEmpty();
    }
}
