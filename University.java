import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

public class University{
    private List<Faculty> faculties; 
    
    public University(){
        this.faculties = new ArrayList<>();
    }

    public void createFaculty(String nameFac, String abbreviation, String field){
        LoggingSystem.log("INFO", "calling function createFaculty VALUES("+nameFac+","+abbreviation+","+field+")");
        StudyField studyFields = null;
        boolean foundCoincidence = false;
        try {
            studyFields = StudyField.valueOf(field);
        } catch (IllegalArgumentException e) {
            LoggingSystem.log("ERROR", "function createFaculty -> Invalid Study Field");
            System.out.println("Invalid Study Field");
            return;
        }
        for(int i=0; i<faculties.size(); i++){
            if(faculties.get(i).getName().equals(nameFac)){
                foundCoincidence=true;
                LoggingSystem.log("WARN", "function createFaculty -> A coincidence with faculties name");
                System.out.println("Sorry we found a coincidence with faculties name");
                break;
            }
            if(faculties.get(i).getAbbreviation().equals(abbreviation)){
                foundCoincidence=true;
                LoggingSystem.log("WARN", "function createFaculty -> A coincidence with faculties abbreviation");
                System.out.println("Sorry we found a coincidence with faculties abbreviation");
                break;
            }
        }
        if(foundCoincidence==false) {
            faculties.add(new Faculty(nameFac,abbreviation, studyFields));
            System.out.println("New faculty added");
            LoggingSystem.log("INFO", "function createFaculty -> New faculty added.");
            FileManager.saveDataInFile(faculties);
        }
    }
    public void printAllFaculties(){
        LoggingSystem.log("INFO", "calling function printAllFaculties");
        for(int i=0; i<faculties.size(); i++){
            faculties.get(i).printFaculty();
            System.out.println("--------------------------------");
        }
    }
    public void displayFacultiesOfField(String fieldName){
        LoggingSystem.log("INFO", "calling function printAllFaculties VALUES("+fieldName+")");
        StudyField studyFields = null;
        try {
            studyFields = StudyField.valueOf(fieldName.toUpperCase());
        } catch (IllegalArgumentException e) {
            LoggingSystem.log("ERROR", "function createFaculty -> Invalid Study Field");
            System.out.println("Invalid Study Field");
            return;
        }
        for (int i = 0; i < faculties.size(); i++) {
            if(faculties.get(i).getField()==studyFields){
                faculties.get(i).printFaculty();
                System.out.println("--------------------------------");
            }
        }
        // if(studyFields)
    }
    public void createStudent(String abbreviation, String firstName, String lastName, String email, String dateBirth, String dateEnroll){
        LoggingSystem.log("INFO", "calling function createStudent VALUES("+abbreviation+", "+firstName+", "+lastName+", "+email+", "+dateBirth+", "+dateEnroll+")");
        List<Student> students = new ArrayList<>();
        Student student = null;
        LocalDate dateBirthParsed;
        LocalDate dateEnrollParsed;
        try {
            dateBirthParsed = LocalDate.parse(dateBirth);
            dateEnrollParsed = LocalDate.parse(dateEnroll);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format please use yy-mm-dd");
            LoggingSystem.log("ERROR", "function createStudent -> Invalid date format");
            return;
        }
        abbreviation=abbreviation.toUpperCase();
        boolean foundCoincidence = false;
        int positionOfFaculty = -1;
        for(int i=0; i<faculties.size(); i++){
            if(faculties.get(i).getAbbreviation().equals(abbreviation)){
                foundCoincidence=true;
                positionOfFaculty=i;
                break;
            }
        }
        if(!foundCoincidence){
            System.out.println("No such faculty");
            LoggingSystem.log("WARN", "function createStudent -> No such faculty");
            return;
        }
        foundCoincidence=false;
        students = faculties.get(positionOfFaculty).getStudentListFaculty();
        for (int i = 0; i < students.size(); i++) {
            student = faculties.get(i).findStudentInFacultyByEmail(email);
            if(!student.equals(null)) {
                foundCoincidence = true;
                break;
            }
        }
        if(foundCoincidence){
            System.out.println("Email was already taken or Student is already enrolled");
            LoggingSystem.log("WARN", "function createStudent -> Email was already taken or Student is already enrolled");
            return;
        }
        for (int i = 0; i < faculties.size(); i++) {
            student = faculties.get(i).findStudentInFacultyByEmail(email);
            if(!student.equals(null)) {
                foundCoincidence = true;
                if(i == positionOfFaculty){
                    System.out.println("Email was already taken or Student is already enrolled");
                    LoggingSystem.log("WARN", "function createStudent -> Email was already taken or Student is already enrolled");
                }
                else{
                    System.out.println("Email is already taken or student is enrolled in another Faculty");
                    LoggingSystem.log("WARN", "function createStudent -> Email was already taken or Student is already enrolled in another Faculty");
                }
                break;
            }
        }
        if(foundCoincidence){
            return;
        }

        Student newStudent = new Student(firstName, lastName, email, dateBirthParsed, dateEnrollParsed);
        faculties.get(positionOfFaculty).addStudent(newStudent);
        System.out.println("New student added");
        LoggingSystem.log("WARN", "function createStudent -> New student added");
        FileManager.saveDataInFile(faculties);
    }
    public void graduateStudent(String email){
        LoggingSystem.log("INFO", "calling function graduateStudent VALUES("+email+")");
        boolean studentStatus;
        Student student = null;
        for (int i = 0; i < faculties.size(); i++) {
            student = faculties.get(i).findStudentInFacultyByEmail(email);
            if(!student.equals(null)) {
                studentStatus = student.getGraduated();
                if(studentStatus){
                    System.out.println("Student already graduated");
                    LoggingSystem.log("WARN", "function graduateStudent -> Student already graduated");
                }
                else{
                    student.gradute();
                    System.out.println("Student graduated");
                    LoggingSystem.log("WARN", "function graduateStudent -> Student graduated");
                    FileManager.saveDataInFile(faculties);
                }
                return;
            }
        }
        System.out.println("No such email!");
        LoggingSystem.log("WARN", "function graduateStudent -> No such email");
    }
    public void printAllStudentsFacultyByStatus(String abbreviation, boolean graduated){
        LoggingSystem.log("INFO", "calling function printAllStudentsFacultyByStatus VALUES("+abbreviation+", "+graduated+")");
        abbreviation = abbreviation.toUpperCase();
        for(int i=0; i<faculties.size(); i++){
            if(faculties.get(i).getAbbreviation().equals(abbreviation)){
                faculties.get(i).printAllStudents(graduated);
                return;
            }
        }
        System.out.println("Invalid abbreviation name");
        LoggingSystem.log("WARN", "function printAllStudentsFacultyByStatus -> Invalid abbreviation");
    }
    public void searchStudentFaculty(String email){
        Student student = null;
        LoggingSystem.log("INFO", "calling function searchStudentFaculty VALUES("+email+")");
        for (int i = 0; i < faculties.size(); i++) {
            student = faculties.get(i).findStudentInFacultyByEmail(email);
            if(!student.equals(null)) {
                student.studentInfo();
                System.out.println("Abbreviation: "+faculties.get(i).getAbbreviation());
                LoggingSystem.log("INFO", "calling function searchStudentFaculty -> Student Founded");
                return;
            }
        }
        System.out.println("No such student or abbreviation");
        LoggingSystem.log("WARN", "function searchStudentFaculty -> No such student or abbreviation");
    }
    public void checkStudentBelongs(String abbreviation, String email){
        LoggingSystem.log("INFO", "calling function checkStudentBelongs VALUES("+abbreviation+","+email+")");
        Student student = null;
        abbreviation = abbreviation.toUpperCase();
        for (int i = 0; i < faculties.size(); i++) {
            if(faculties.get(i).getAbbreviation().equals(abbreviation)){
                student = faculties.get(i).findStudentInFacultyByEmail(email);
                if(!student.equals(null)) {
                    System.out.println("\n!!!Student belongs to this faculty!!!\n");
                    LoggingSystem.log("INFO", "function checkStudentBelongs -> Student belongs");
                    return;
                }
                System.out.println("\n!!!Student doesn't belongs to this faculty or doesn't exists!!!\n");
                LoggingSystem.log("WARN", "function checkStudentBelongs -> Student doesn't belongs or doesn't exists");
                return;
            }
        }
        System.out.println("\n!!!Such faculty doesnt exists!!!\n");
        LoggingSystem.log("WARN", "function checkStudentBelongs -> Faculty doesn't exists");
    }

    public void changeStudentFirstName(String email, String firstName){
        LoggingSystem.log("INFO", "calling function changeStudentFirstName VALUES("+email+","+firstName+")");
        Student student = null;
        for (int i = 0; i < faculties.size(); i++) {
            student = faculties.get(i).findStudentInFacultyByEmail(email);
            if(!student.equals(null)) {
                student.changeFirstName(firstName);
                System.out.println("Successfully changed First name");
                LoggingSystem.log("INFO", "function changeStudentFirstName -> Succesfully updated");
                FileManager.saveDataInFile(faculties);
                return;
            }
        }
        LoggingSystem.log("WARN", "function changeStudentFirstName -> No such email");
        System.out.println("No such email!");
    }

    public void changeStudentLastName(String email, String lastName){
        LoggingSystem.log("INFO", "calling function changeStudentLastName VALUES("+email+","+lastName+")");
        Student student = null;
        for (int i = 0; i < faculties.size(); i++) {
            student = faculties.get(i).findStudentInFacultyByEmail(email);
            if(!student.equals(null)) {
                student.changeLastName(lastName);
                System.out.println("Successfully changed Last name");
                LoggingSystem.log("INFO", "function changeStudentLastName -> Succesfully updated");
                FileManager.saveDataInFile(faculties);
                return;
            }
        }
        LoggingSystem.log("WARN", "function changeStudentLastName -> No such email");
        System.out.println("No such email!");
    }

    public void addListOfFaculties(List<Faculty> faculties){
        this.faculties = faculties;
    }

}