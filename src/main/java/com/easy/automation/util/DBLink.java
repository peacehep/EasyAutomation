package com.easy.automation.util;

/**
 * 以枚举类的方式定义可连接的不同数据库
 *
 */

public enum DBLink {
    SIT("SIT环境"),TEST("本地测试环境");

    private String description;

    DBLink(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
