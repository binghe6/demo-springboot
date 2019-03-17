package com.binghe.demo.pojo.db1;

import java.util.Date;

public class SysPermission {
    private Integer id;

    private String permCode;

    private String permName;

    private String permDesc;

    private Integer pid;

    private Boolean permType;

    private Short menuIndex;

    private String url;

    private Boolean state;

    private Date createDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermCode() {
        return permCode;
    }

    public void setPermCode(String permCode) {
        this.permCode = permCode;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getPermDesc() {
        return permDesc;
    }

    public void setPermDesc(String permDesc) {
        this.permDesc = permDesc;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Boolean getPermType() {
        return permType;
    }

    public void setPermType(Boolean permType) {
        this.permType = permType;
    }

    public Short getMenuIndex() {
        return menuIndex;
    }

    public void setMenuIndex(Short menuIndex) {
        this.menuIndex = menuIndex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}