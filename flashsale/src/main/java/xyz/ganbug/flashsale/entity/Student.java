package xyz.ganbug.flashsale.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


public class Student {
    private String schoolName;

    public Student(String schoolName){
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    
    public static void main(String[] args){
        System.out.println(Character.isUpperCase('_'));
    }
}
