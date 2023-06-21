package com.itheima.domain.system;

import java.util.Date;

public class SysLog {
    private String id;  //唯一标识id
    private String username; //操作人用户名
    private String ip; //操作人id地址
    private Date time; //操作时间
    private String method; //操作的方法名
    private String action; //访问的地址

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", time=" + time +
                ", method='" + method + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
