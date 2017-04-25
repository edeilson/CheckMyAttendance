package com.edge.checkmyattendance;

import java.io.Serializable;

/**
 * Created by Edeilson Silva on 24/04/2017.
 */

public class Student implements Serializable {

    private String idStudent;
    private String nameStudent;
    private String overalAttendance;
    private String birthDate;
    private String startCourseDate;
    private String endCourseDate;


    public Student(String idStudent, String birthDate) {
        this.idStudent = idStudent;
        this.birthDate = birthDate;
    }

    public Student(String nameStudent, String overalAttendance,  String startCourseDate,  String endCourseDate) {
        this.nameStudent = nameStudent;
        this.overalAttendance = overalAttendance;
        this.startCourseDate = startCourseDate;
        this.endCourseDate = endCourseDate;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getOveralAttendance() {
        return overalAttendance;
    }

    public void setOveralAttendance(String overalAttendance) {
        this.overalAttendance = overalAttendance;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEndCourseDate() {
        return endCourseDate;
    }

    public void setEndCourseDate(String endCourseDate) {
        this.endCourseDate = endCourseDate;
    }

    public String getStartCourseDate() {
        return startCourseDate;
    }

    public void setStartCourseDate(String startCourseDate) {
        this.startCourseDate = startCourseDate;
    }
}
