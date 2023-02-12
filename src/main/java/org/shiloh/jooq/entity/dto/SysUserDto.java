package org.shiloh.jooq.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.shiloh.jooq.codegen.tables.pojos.SysUser;

/**
 * 系统用户信息 dto
 * <p>
 * 可直接继承 jOOQ 生成的 Pojo，获得表中的字段
 *
 * @author shiloh
 * @date 2023/2/11 17:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserDto extends SysUser {
    /**
     * 所在部门 ID
     */
    private Long deptId;

    /**
     * 所在部门名称
     */
    private String deptName;
}
