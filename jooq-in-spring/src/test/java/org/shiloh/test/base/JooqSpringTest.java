package org.shiloh.test.base;

import org.jooq.DSLContext;
import org.junit.runner.RunWith;
import org.shiloh.App;
import org.shiloh.jooq.codegen.tables.daos.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shiloh
 * @date 2023/2/13 18:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
@Transactional
@Rollback
public class JooqSpringTest {
    @Autowired
    protected DSLContext dslContext;

    @Autowired
    protected SysUserDao sysUserDao;
}
