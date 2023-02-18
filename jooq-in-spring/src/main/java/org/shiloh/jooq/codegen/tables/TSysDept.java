/*
 * This file is generated by jOOQ.
 */
package org.shiloh.jooq.codegen.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.shiloh.jooq.codegen.Indexes;
import org.shiloh.jooq.codegen.Keys;
import org.shiloh.jooq.codegen.LearnJooq;
import org.shiloh.jooq.codegen.tables.records.SysDeptRecord;


/**
 * 系统部门信息表
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TSysDept extends TableImpl<SysDeptRecord> {

    private static final long serialVersionUID = -617431999;

    /**
     * The reference instance of <code>learn_jooq.sys_dept</code>
     */
    public static final TSysDept SYS_DEPT = new TSysDept();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SysDeptRecord> getRecordType() {
        return SysDeptRecord.class;
    }

    /**
     * The column <code>learn_jooq.sys_dept.id</code>. ID，自增主键
     */
    public final TableField<SysDeptRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "ID，自增主键");

    /**
     * The column <code>learn_jooq.sys_dept.name</code>. 部门名称
     */
    public final TableField<SysDeptRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(50), this, "部门名称");

    /**
     * The column <code>learn_jooq.sys_dept.tel</code>. 电话号码
     */
    public final TableField<SysDeptRecord, String> TEL = createField(DSL.name("tel"), org.jooq.impl.SQLDataType.VARCHAR(50), this, "电话号码");

    /**
     * Create a <code>learn_jooq.sys_dept</code> table reference
     */
    public TSysDept() {
        this(DSL.name("sys_dept"), null);
    }

    /**
     * Create an aliased <code>learn_jooq.sys_dept</code> table reference
     */
    public TSysDept(String alias) {
        this(DSL.name(alias), SYS_DEPT);
    }

    /**
     * Create an aliased <code>learn_jooq.sys_dept</code> table reference
     */
    public TSysDept(Name alias) {
        this(alias, SYS_DEPT);
    }

    private TSysDept(Name alias, Table<SysDeptRecord> aliased) {
        this(alias, aliased, null);
    }

    private TSysDept(Name alias, Table<SysDeptRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("系统部门信息表"));
    }

    public <O extends Record> TSysDept(Table<O> child, ForeignKey<O, SysDeptRecord> key) {
        super(child, key, SYS_DEPT);
    }

    @Override
    public Schema getSchema() {
        return LearnJooq.LEARN_JOOQ;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.SYS_DEPT_PRIMARY);
    }

    @Override
    public Identity<SysDeptRecord, Long> getIdentity() {
        return Keys.IDENTITY_SYS_DEPT;
    }

    @Override
    public UniqueKey<SysDeptRecord> getPrimaryKey() {
        return Keys.KEY_SYS_DEPT_PRIMARY;
    }

    @Override
    public List<UniqueKey<SysDeptRecord>> getKeys() {
        return Arrays.<UniqueKey<SysDeptRecord>>asList(Keys.KEY_SYS_DEPT_PRIMARY);
    }

    @Override
    public TSysDept as(String alias) {
        return new TSysDept(DSL.name(alias), this);
    }

    @Override
    public TSysDept as(Name alias) {
        return new TSysDept(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TSysDept rename(String name) {
        return new TSysDept(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TSysDept rename(Name name) {
        return new TSysDept(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}