package com.binghe.demo.common.bean.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor="of")
@Accessors(chain=true)
public class LoginUserVo {
	private String username;// 用户名
	private String password;// 密码
}
