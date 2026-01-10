package com.session2.model;

import java.sql.Timestamp;

public class Enrollment {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Timestamp registerAt;
    private String status;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Timestamp getRegisterAt() {
        return this.registerAt;
    }

    public void setRegisterAt(Timestamp registerAt) {
        this.registerAt = registerAt;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
