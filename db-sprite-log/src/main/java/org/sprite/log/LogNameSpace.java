package org.sprite.log;

public enum LogNameSpace {

    Init("db-sprite-init"),
    Trace("db-sprite-trace"),
    close("db-sprite-close"),
    monitor("db-sprite-monitor");

    private String desc;

    LogNameSpace(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
