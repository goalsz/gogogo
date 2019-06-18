package xyz.zhanglin.entity;

public class Student {

    private String studentid;

    private String studentname;

    private String studentage;

    private String studentsex;

    private String classid;

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentage() {
        return studentage;
    }

    public void setStudentage(String studentage) {
        this.studentage = studentage;
    }

    public String getStudentsex() {
        return studentsex;
    }

    public void setStudentsex(String studentsex) {
        this.studentsex = studentsex;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentid='" + studentid + '\'' +
                ", studentname='" + studentname + '\'' +
                ", studentage='" + studentage + '\'' +
                ", studentsex='" + studentsex + '\'' +
                ", classid='" + classid + '\'' +
                '}';
    }
}
