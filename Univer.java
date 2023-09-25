import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Univer{
    private List<Faculty> faculties;
    
    public Univer(){
        this.faculties = new ArrayList<>();
    }

    private void createFaculty(String nameFac, String abbreviation, String field){
        log("INFO", "calling function createFaculty VALUES("+nameFac+","+abbreviation+","+field+")");
        StudyField studyFields = null;
        boolean foundCoincidence = false;
        try {
            studyFields = StudyField.valueOf(field);
        } catch (IllegalArgumentException e) {
            log("ERROR", "function createFaculty -> Invalid Study Field");
            System.out.println("Invalid Study Field");
        }
        if(studyFields!=null){
            for(int i=0; i<faculties.size(); i++){
                if(faculties.get(i).getName().equals(nameFac)){
                    foundCoincidence=true;
                    log("WARN", "function createFaculty -> A coincidence with faculties name");
                    System.out.println("Sorry we found a coincidence with faculties name");
                    break;
                }
                if(faculties.get(i).getAbbreviation().equals(abbreviation)){
                    foundCoincidence=true;
                    log("WARN", "function createFaculty -> A coincidence with faculties abbreviation");
                    System.out.println("Sorry we found a coincidence with faculties abbreviation");
                    break;
                }
            }
            if(foundCoincidence==false) {
                faculties.add(new Faculty(nameFac,abbreviation, studyFields));
                System.out.println("New faculty added");
                log("INFO", "function createFaculty -> New faculty added.");
                saveDataInFile();
            }
        }
    }
    private void printAllFaculties(){
        log("INFO", "calling function printAllFaculties");
        for(int i=0; i<faculties.size(); i++){
            faculties.get(i).printFaculty();
            System.out.println("--------------------------------");
        }
    }
    private void displayFacultiesOfField(String fieldName){
        log("INFO", "calling function printAllFaculties VALUES("+fieldName+")");
        StudyField studyFields = null;
        try {
            studyFields = StudyField.valueOf(fieldName.toUpperCase());
        } catch (IllegalArgumentException e) {
            log("ERROR", "function createFaculty -> Invalid Study Field");
            System.out.println("Invalid Study Field");
        }
        if(studyFields!=null){
            for (int i = 0; i < faculties.size(); i++) {
                if(faculties.get(i).getField()==studyFields){
                    faculties.get(i).printFaculty();
                    System.out.println("--------------------------------");
                }
            }
        }
        // if(studyFields)
    }
    private void createStudent(String abbreviation, String firstName, String lastName, String email, String dateBirth, String dateEnroll){
        log("INFO", "calling function createStudent VALUES("+abbreviation+", "+firstName+", "+lastName+", "+email+", "+dateBirth+", "+dateEnroll+")");
        List<Student> students = new ArrayList<>();
        Student student = null;
        LocalDate dateBirthParsed;
        LocalDate dateEnrollParsed;
        try {
            dateBirthParsed = LocalDate.parse(dateBirth);
            dateEnrollParsed = LocalDate.parse(dateEnroll);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format please use yy-mm-dd");
            log("ERROR", "function createStudent -> Invalid date format");
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
            log("WARN", "function createStudent -> No such faculty");
            return;
        }
        foundCoincidence=false;
        students = faculties.get(positionOfFaculty).getStudentListFaculty();
        for (int i = 0; i < students.size(); i++) {
            student = students.get(i);
            if(student.getEmail().equals(email)){
                foundCoincidence = true;
                break;
            }
        }
        if(foundCoincidence){
            System.out.println("Email was already taken or Student is already enrolled");
            log("WARN", "function createStudent -> Email was already taken or Student is already enrolled");
            return;
        }
        for (int i = 0; i < faculties.size(); i++) {
            students = faculties.get(i).getStudentListFaculty();
            for (int j = 0; j < students.size(); j++) {
                student = students.get(j);
                if(student.getEmail().equals(email)){
                    foundCoincidence = true;
                    if(i == positionOfFaculty){
                        System.out.println("Email was already taken or Student is already enrolled");
                        log("WARN", "function createStudent -> Email was already taken or Student is already enrolled");
                    }
                    else{
                        System.out.println("Email is already taken or student is enrolled in another Faculty");
                        log("WARN", "function createStudent -> Email was already taken or Student is already enrolled in another Faculty");
                    }
                    break;
                }
            }
        }
        if(foundCoincidence){
            return;
        }

        Student newStudent = new Student(firstName, lastName, email, dateBirthParsed, dateEnrollParsed);
        faculties.get(positionOfFaculty).addStudent(newStudent);
        System.out.println("New student added");
        log("WARN", "function createStudent -> New student added");
        saveDataInFile();
    }
    private void graduateStudent(String email){
        log("INFO", "calling function graduateStudent VALUES("+email+")");
        List<Student> students = new ArrayList<>();
        boolean studentStatus;
        Student student = null;
        for (int i = 0; i < faculties.size(); i++) {
            students = faculties.get(i).getStudentListFaculty();
            for (int j = 0; j < students.size(); j++){
                student = students.get(j);
                if(student.getEmail().equals(email)){
                    studentStatus = student.getGraduated();
                    if(studentStatus){
                        System.out.println("Student already graduated");
                        log("WARN", "function graduateStudent -> Student already graduated");
                    }
                    else{
                        student.gradute();
                        System.out.println("Student graduated");
                        log("WARN", "function graduateStudent -> Student graduated");
                        saveDataInFile();
                    }
                    return;
                }
            }
        }
        System.out.println("No such email!");
        log("WARN", "function graduateStudent -> No such email");
    }
    private void printAllStudentsFacultyByStatus(String abbreviation, boolean graduated){
        log("INFO", "calling function printAllStudentsFacultyByStatus VALUES("+abbreviation+", "+graduated+")");
        abbreviation = abbreviation.toUpperCase();
        for(int i=0; i<faculties.size(); i++){
            if(faculties.get(i).getAbbreviation().equals(abbreviation)){
                faculties.get(i).printAllStudents(graduated);
                return;
            }
        }
        System.out.println("Invalid abbreviation name");
        log("WARN", "function printAllStudentsFacultyByStatus -> Invalid abbreviation");
    }
    private void searchStudentFaculty(String email){
        List<Student> students = new ArrayList<>();
        Student student = null;
        log("INFO", "calling function searchStudentFaculty VALUES("+email+")");
        for (int i = 0; i < faculties.size(); i++) {
            students = faculties.get(i).getStudentListFaculty();
            for (int j = 0; j < students.size(); j++) {
                student = students.get(j);
                if(student.getEmail().equals(email)){
                    System.out.println("First Name: "+student.getFirstName());
                    System.out.println("Last Name: "+student.getLastName());
                    System.out.println("Email: "+student.getEmail());
                    System.out.println("Date of birth: "+student.getDateOfBirth());
                    System.out.println("Enrollment Date: "+student.getEnrollmentDate());
                    System.out.println("Abbreviation: "+faculties.get(i).getAbbreviation());
                    log("INFO", "calling function searchStudentFaculty -> Student Founded");
                    return;
                }
            }
        }
        System.out.println("No such student or abbreviation");
        log("WARN", "function searchStudentFaculty -> No such student or abbreviation");
    }
    private void checkStudentBelongs(String abbreviation, String email){
        log("INFO", "calling function checkStudentBelongs VALUES("+abbreviation+","+email+")");
        List<Student> students = new ArrayList<>();
        Student student = null;
        abbreviation = abbreviation.toUpperCase();
        for (int i = 0; i < faculties.size(); i++) {
            if(faculties.get(i).getAbbreviation().equals(abbreviation)){
                students = faculties.get(i).getStudentListFaculty();
                for (int j = 0; j < students.size(); j++) {
                    student = students.get(j);
                    if(student.getEmail().equals(email)){
                        System.out.println("\n!!!Student belongs to this faculty!!!\n");
                        log("INFO", "function checkStudentBelongs -> Student belongs");
                        return;
                    }
                }
                System.out.println("\n!!!Student doesn't belongs to this faculty or doesn't exists!!!\n");
                log("WARN", "function checkStudentBelongs -> Student doesn't belongs or doesn't exists");
                return;
            }
        }
        System.out.println("\n!!!Such faculty doesnt exists!!!\n");
        log("WARN", "function checkStudentBelongs -> Faculty doesn't exists");
    }

    private void changeStudentFirstName(String email, String firstName){
        log("INFO", "calling function changeStudentFirstName VALUES("+email+","+firstName+")");
        List<Student> students = new ArrayList<>();
        Student student = null;
        for (int i = 0; i < faculties.size(); i++) {
            students = faculties.get(i).getStudentListFaculty();
            for (int j = 0; j < students.size(); j++) {
                student = students.get(j);
                if(student.getEmail().equals(email)){
                    student.changeFirstName(firstName);
                    System.out.println("Successfully changed First name");
                    log("INFO", "function changeStudentFirstName -> Succesfully updated");
                    saveDataInFile();
                    return;
                }
            }
        }
        log("WARN", "function changeStudentFirstName -> No such email");
        System.out.println("No such email!");
    }

    private void changeStudentLastName(String email, String lastName){
        log("INFO", "calling function changeStudentLastName VALUES("+email+","+lastName+")");
        List<Student> students = new ArrayList<>();
        Student student = null;
        for (int i = 0; i < faculties.size(); i++) {
            students = faculties.get(i).getStudentListFaculty();
            for (int j = 0; j < students.size(); j++) {
                student = students.get(j);
                if(student.getEmail().equals(email)){
                    student.changeLastName(lastName);
                    System.out.println("Successfully changed Last name");
                    log("INFO", "function changeStudentLastName -> Succesfully updated");
                    saveDataInFile();
                    return;
                }
            }
        }
        log("WARN", "function changeStudentLastName -> No such email");
        System.out.println("No such email!");
    }
    private void saveDataInFile(){
        Faculty faculty = null;
        List<Student> students = new ArrayList<>();
        Student student = null;
        try {
            FileWriter myWriter = new FileWriter("Data.txt");
            for (int i = 0; i < faculties.size(); i++) {
                faculty = faculties.get(i);
                myWriter.write(faculty.getName()+"\n");
                myWriter.write(faculty.getAbbreviation()+"\n");
                myWriter.write(faculty.getField()+"\n");
                students = faculty.getStudentListFaculty();
                if(students.size()==0){
                    myWriter.write("[]\n");
                }
                else{
                    myWriter.write("[\n");
                    for (int j = 0; j < students.size(); j++) {
                        student = students.get(j);
                        myWriter.write(student.getFirstName()+"\n");
                        myWriter.write(student.getLastName()+"\n");
                        myWriter.write(student.getEmail()+"\n");
                        myWriter.write(student.getEnrollmentDate()+"\n");
                        myWriter.write(student.getDateOfBirth()+"\n");
                        myWriter.write(student.getGraduated()+"\n");
                    }
                    myWriter.write("]\n");
                }
                //Save students
            }
            // myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private void readDataFromFile(){
        try {
            File myFile = new File("Data.txt");
            if(myFile.exists() && myFile.length()==0){
                return;
            }
            Scanner myReader = new Scanner(myFile);
            String nameFaculty, abbreviation, studyField, checkStudent;
            String firstName, lastName, email, enrollmentDate, dateOfBirth, graduated;
            Student student = null;
            int index = 0;
            while (myReader.hasNextLine()) {
                nameFaculty = myReader.nextLine();
                abbreviation = myReader.nextLine();
                studyField = myReader.nextLine();
                checkStudent = myReader.nextLine();
                if(checkStudent.equals("[]")){
                    faculties.add(new Faculty(nameFaculty,abbreviation, StudyField.valueOf(studyField)));
                }
                else{
                    checkStudent = myReader.nextLine();
                    faculties.add(new Faculty(nameFaculty,abbreviation, StudyField.valueOf(studyField)));
                    while(!checkStudent.equals("]")){
                        firstName = checkStudent;
                        lastName = myReader.nextLine();
                        email = myReader.nextLine();
                        enrollmentDate = myReader.nextLine();
                        dateOfBirth = myReader.nextLine();
                        graduated = myReader.nextLine();
                        LocalDate.parse(dateOfBirth);
                        student = new Student(firstName, lastName, email, LocalDate.parse(dateOfBirth), LocalDate.parse(enrollmentDate), Boolean.parseBoolean(graduated));
                        faculties.get(index).addStudent(student);
                        checkStudent = myReader.nextLine();
                    }
                }
                index+=1;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private void log(String type, String message){
        try {
            FileWriter logWriter = new FileWriter("logs.txt", true);
            LocalDateTime timeNow = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = timeNow.format(formatter);
            String logFormat = "[%s] %7s %s%n";

            logWriter.write(String.format(logFormat, formattedDateTime, type, message));
            logWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Univer uni = new Univer();
        uni.readDataFromFile();
        String cmd = "";
        String[] operation;
        Scanner scanner = new Scanner(System.in);
        while(!cmd.equals("q")){
            System.out.println("f - Faculty operations\ng - General operations\ns - Student Operations\nq - Quit Program\n");
            System.out.print("->");
            cmd = scanner.nextLine();
            if(cmd.equals("f")){
                do {

                    System.out.println("Faculty Operations");
                    System.out.println("ns/<faculty abbreviation>/<first name>/<last name>/<email>/<year_month_day_birth>/<year_month_day_enrollment> - create student\ngs/<email> - graduate student\nds/<faculty abreviation> - display enrolled student\ndg/<faculty> - display graduated students\nbf/<faculty abbreviation>/<email> - check if student belongs to faculty\nb - go back\nq - Quit Program\n");
                    System.out.print("->");
                    cmd = scanner.nextLine();
                    operation = cmd.split("/");

                    if(operation[0].equals("ns")){
                        if(operation.length==7){
                            uni.createStudent(operation[1],operation[2], operation[3], operation[4], operation[5], operation[6]);
                        }
                        else{
                            System.out.println("Invalid number of attributes");
                        }
                    }
                    else if(operation[0].equals("gs")){
                        if(operation.length == 2){
                            uni.graduateStudent(operation[1]);
                        }
                        else{
                            System.out.println("Invalid number of attributes");
                        }
                    }
                    else if(operation[0].equals("ds")){
                        if(operation.length == 2){
                            uni.printAllStudentsFacultyByStatus(operation[1], false);
                        }
                        else{
                            System.out.println("Invalid number of attributes");
                        }
                    }
                    else if(operation[0].equals("dg")){
                        if(operation.length == 2){
                            uni.printAllStudentsFacultyByStatus(operation[1], true);
                        }
                        else{
                            System.out.println("Invalid number of attributes");
                        }
                    }
                    else if(operation[0].equals("bf")){
                        if(operation.length == 3){
                            uni.checkStudentBelongs(operation[1], operation[2]);
                        }
                        else{
                            System.out.println("Invalid number of attributes");
                        }
                    }
                } while(!operation[0].equals("b") && !operation[0].equals("q"));
            }
            // nf/Facultatea Calculatoare, Informatica si Microelectronica/FCIM/SOFTWARE_ENGINEERING
            // ns/FCGC/Rodion/Clepa/rodion@gmail.com/2002-05-24/2022-09-01
            else if(cmd.equals("g")){
                do{
                
                System.out.println("General Operations");
                System.out.println("nf/<faculty name>/<faculty abbreviation>/<field> - create faculty\nss/<student email> - search student and show faculty\ndf - display all falcuties\ndf/<field> - display all faculties of a field\nb - go back\nq - Quit Program\n");
                System.out.print("->");
                cmd = scanner.nextLine();
                operation = cmd.split("/");

                if(operation[0].equals("df")){
                    if(operation.length==1){
                        uni.printAllFaculties();
                    }
                    else if(operation.length==2){
                        uni.displayFacultiesOfField(operation[1]);
                    }
                    else{
                        System.out.println("Invalid number of attributes");
                    }
                }
                else if(operation[0].equals("ss")){
                    if(operation.length==2){
                        uni.searchStudentFaculty(operation[1]);
                    }
                    else{
                        System.out.println("Not enough attributes!");
                    }
                }
                else if(operation[0].equals("nf")){
                    if(operation.length==4){
                        uni.createFaculty(operation[1], operation[2], operation[3]);
                    }
                    else{
                        System.out.println("Not enough attributes!");
                    }
                }
                else if(operation[0].equals("s")){
                    uni.readDataFromFile();
                }
            }while(!operation[0].equals("b") && !operation[0].equals("q"));
            }
            else if(cmd.equals("s")){
                do {

                    System.out.println("Student Operations");
                    System.out.println("cf/<email>/<first name> - change first name\ncl/<email>/<last name>\nb - go back\nq - Quit Program\n");
                    System.out.print("->");
                    cmd = scanner.nextLine();
                    operation = cmd.split("/");

                    if(operation[0].equals("cf")){
                        if(operation.length==3){
                            if(operation[2].isEmpty()){
                                System.out.println("First Name is Empty");
                            }
                            else{
                                uni.changeStudentFirstName(operation[1], operation[2]);
                            }
                        }
                        else{
                            System.out.println("Not enough attributes!");
                        }
                    }
                    if(operation[0].equals("cl")){
                        if(operation.length==3){
                            if(operation[2].isEmpty()){
                                System.out.println("Last Name is Empty");
                            }
                            else{
                                uni.changeStudentLastName(operation[1], operation[2]);
                            }
                        }
                        else{
                            System.out.println("Not enough attributes!");
                        }
                    }
                } while(!operation[0].equals("b") && !operation[0].equals("q"));
            }
        }
        System.out.println();
        scanner.close();
    }
}