package org.example;

public class Grade {

    // Course Name
    private String course;

    // Number of credits for that course
    private float weight;

    // Letter Grade Received for the course
    private String grade;

    // GPAs for Various Scales
    private float fourGPA;
    private float yorkGPA;
    private float ryersonGPA;
    private float uoftGPA;

    public Grade(String course, float weight, String grade) {
        this.course = course;
        this.weight = weight;
        this.grade = grade;
        this.fourGPA = Converter.fourScale(grade);
        this.yorkGPA = Converter.yorkScale(grade);
        this.ryersonGPA = Converter.ryersonScale(grade);
        this.uoftGPA = Converter.uoftScale(grade);
    }

    // Getters & Setters for javaFX
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) { this.grade = grade; }

    public float getRyersonGPA() { return ryersonGPA; }

    public void setRyersonGPA(float ryersonGPA) { this.ryersonGPA = ryersonGPA; }

    public float getUoftGPA() { return uoftGPA; }

    public void setUoftGPA(float uoftGPA) { this.uoftGPA = uoftGPA; }

    public float getFourGPA() { return fourGPA; }

    public void setFourGPA(float fourGPA) {
        this.fourGPA = fourGPA;
    }

    public float getYorkGPA() {
        return yorkGPA;
    }

    public void setYorkGPA(float yorkGPA) {
        this.yorkGPA = yorkGPA;
    }
}
