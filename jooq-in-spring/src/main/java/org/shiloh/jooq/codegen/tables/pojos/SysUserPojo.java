/*
 * This file is generated by jOOQ.
 */
package org.shiloh.jooq.codegen.tables.pojos;


import javax.annotation.Generated;

import org.shiloh.jooq.codegen.tables.interfaces.ISysUser;


/**
 * 系统用户信息表
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SysUserPojo implements ISysUser {

    private static final long serialVersionUID = -1821419735;

    private Long    id;
    private String  username;
    private String  password;
    private Integer age;
    private String  sex;
    private String  email;
    private Long    deptId;

    public SysUserPojo() {}

    public SysUserPojo(ISysUser value) {
        this.id = value.getId();
        this.username = value.getUsername();
        this.password = value.getPassword();
        this.age = value.getAge();
        this.sex = value.getSex();
        this.email = value.getEmail();
        this.deptId = value.getDeptId();
    }

    public SysUserPojo(
        Long    id,
        String  username,
        String  password,
        Integer age,
        String  sex,
        String  email,
        Long    deptId
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.deptId = deptId;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Integer getAge() {
        return this.age;
    }

    @Override
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String getSex() {
        return this.sex;
    }

    @Override
    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Long getDeptId() {
        return this.deptId;
    }

    @Override
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SysUserPojo (");

        sb.append(id);
        sb.append(", ").append(username);
        sb.append(", ").append(password);
        sb.append(", ").append(age);
        sb.append(", ").append(sex);
        sb.append(", ").append(email);
        sb.append(", ").append(deptId);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ISysUser from) {
        setId(from.getId());
        setUsername(from.getUsername());
        setPassword(from.getPassword());
        setAge(from.getAge());
        setSex(from.getSex());
        setEmail(from.getEmail());
        setDeptId(from.getDeptId());
    }

    @Override
    public <E extends ISysUser> E into(E into) {
        into.from(this);
        return into;
    }
}
