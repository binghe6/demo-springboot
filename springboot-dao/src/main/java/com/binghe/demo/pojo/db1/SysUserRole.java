package com.binghe.demo.pojo.db1;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor="of")
@Accessors(chain=true)
public class SysUserRole {
    private Integer roleId;

    private Integer userId;

    private Date createDate;

}