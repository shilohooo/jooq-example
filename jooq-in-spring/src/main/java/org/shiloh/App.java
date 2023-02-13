package org.shiloh;

import org.shiloh.jooq.codegen.tables.daos.SysUserDao;
import org.shiloh.jooq.codegen.tables.pojos.SysUserPojo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author shiloh
 * @date 2023/2/13 17:55
 */
@ComponentScan
public class App {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        final SysUserDao sysUserDao = context.getBean(SysUserDao.class);
        final List<SysUserPojo> sysUsers = sysUserDao.findAll();
        assertThat(sysUsers).isNotEmpty();
    }
}
