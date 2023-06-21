package com.itheima.domain.system;

public class Role {
  private String id;
  private String name; //名称
  private String remark; //描述

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Override
  public String toString() {
    return "Role{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", remark='" + remark + '\'' +
            '}';
  }
}