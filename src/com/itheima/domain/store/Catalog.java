package com.itheima.domain.store;

public class Catalog {
    private String id;
    private String name; //名称
    private String remark; //描述
    private String state; //状态
    private String courseId; //学科id

    private Course course;  //一个目录(题目类型)属于某一个学科

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", state='" + state + '\'' +
                ", courseId='" + courseId + '\'' +
                ", course=" + course +
                '}';
    }
}