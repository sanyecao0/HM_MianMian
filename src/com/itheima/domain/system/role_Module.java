package com.itheima.domain.system;

public class role_Module {

    private  String id;
    private  String pId;
    private  String name;
    public   boolean checked;
    public role_Module() {}

    public role_Module(String id, String pId, String name, boolean check) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.checked = check;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return checked;
    }

    public void setCheck(boolean checked) {
        this.checked = checked;
    }

}
