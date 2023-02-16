/*
 * This file is generated by jOOQ.
 */
package org.shiloh.jooq.codegen;


import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;
import org.shiloh.jooq.codegen.tables.TSysDept;
import org.shiloh.jooq.codegen.tables.TSysUser;


/**
 * A class modelling indexes of tables of the <code>learn_jooq</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index SYS_DEPT_PRIMARY = Indexes0.SYS_DEPT_PRIMARY;
    public static final Index SYS_USER_PRIMARY = Indexes0.SYS_USER_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index SYS_DEPT_PRIMARY = Internal.createIndex("PRIMARY", TSysDept.SYS_DEPT, new OrderField[] { TSysDept.SYS_DEPT.ID }, true);
        public static Index SYS_USER_PRIMARY = Internal.createIndex("PRIMARY", TSysUser.SYS_USER, new OrderField[] { TSysUser.SYS_USER.ID }, true);
    }
}
