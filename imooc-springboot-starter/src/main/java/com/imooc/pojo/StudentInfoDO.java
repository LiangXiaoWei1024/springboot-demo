package com.imooc.pojo;

import java.io.Serializable;

import javax.persistence.Id;


public class StudentInfoDO implements Serializable {
    
    String studentNo;
    
    String studentName;
    
    String studentSex;
    
    String picPath;
 
    public String getStudentNo() {
        return studentNo;
    }
 
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }
 
    public String getStudentName() {
        return studentName;
    }
 
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
 
    public String getStudentSex() {
        return studentSex;
    }
 
    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }
 
    public String getPicPath() {
        return picPath;
    }
 
    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}
