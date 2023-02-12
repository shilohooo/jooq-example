package org.shiloh.jooq.entity.dto;

import lombok.Data;

/**
 * 系统用户信息 dto
 *
 * @author shiloh
 * @date 2023/2/11 17:44
 */
@Data
public class SysUserDto {
    /**
     * ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 所在部门 ID
     */
    private Long deptId;

    /**
     * 所在部门名称
     */
    private String deptName;
}
