import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private String name;
    private String abbreviation;
    private List<Student> students;
    private StudyField studyField;

    public Faculty(String name, String abbreviation, StudyField studyField){
        this.name = name;
        this.abbreviation = abbreviation;
        this.studyField = studyField;
        this.students = new ArrayList<>();
    }
    public void printFaculty(){
        System.out.println("Name: " + name);
        System.out.println("Abbreviation: " + abbreviation);
        System.out.println("Study Field: " + studyField);
    }

    public String getName(){
        return name;
    }
    public String getAbbreviation(){
        return abbreviation;
    }
    public StudyField getField(){
        return studyField;
    }
    public void addStudent(Student newStudent){
        students.add(newStudent);
    }
    public List<Student> getStudentListFaculty(){
        return students;
    }
    public void printAllStudents(boolean grat){
        Student student = null;
        for (int i = 0; i < students.size(); i++) {
            student = students.get(i);
            if(student.getGraduated()==grat){
                students.get(i).studentInfo();
                System.out.println("-------------------------------------");
            }
        }
    }
}
