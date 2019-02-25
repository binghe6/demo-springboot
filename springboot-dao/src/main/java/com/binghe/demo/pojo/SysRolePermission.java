package com.binghe.demo.pojo;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor="of")
@Accessors(chain=true)
public class SysRolePermission {
    private Integer roleId;

    private Integer permissionId;

    private Date createDate;

}