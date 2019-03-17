package com.binghe.demo.pojo.db1;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor="of")
@Accessors(chain=true)
public class SysUser {
    private Integer id;

    private String username;

    private String password;

    private String mobile;

    private String email;

    private String salt;

    private Integer state;

    private Date createDate;

    private Date updateDate;
    
    public enum State {
    	STATE_OFF(0,"不可用"),
    	STATE_ON(1,"可用");
    	
    	public Integer value;
    	public String desc;
    	
    	private State(Integer value, String desc) {
    		this.value = value;
    		this.desc = desc;
    	}
    	
    	public Integer getValue() {
    		return value;
    	}
    	
    	public String getDesc() {
    		return desc;
    	}
    }

}