import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    public static void saveDataInFile(List<Faculty> faculties){
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
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            LoggingSystem.log("ERROR", "function readDataFromFile -> File not found");
            System.out.println("File not found!");
        }
    }
    public static List<Faculty> readDataFromFile(){
        List<Faculty> faculties = new ArrayList<>();
        LoggingSystem.log("INFO", "function readDataFromFile -> Proccessing...");
        try {
            File myFile = new File("Data.txt");
            if(myFile.exists() && myFile.length()==0){
                return null;
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

                        student = new Student(firstName, lastName, email, LocalDate.parse(dateOfBirth), LocalDate.parse(enrollmentDate), Boolean.parseBoolean(graduated));
                        faculties.get(index).addStudent(student);
                        checkStudent = myReader.nextLine();
                    }
                }
                index+=1;
            }
            myReader.close();
            LoggingSystem.log("INFO", "function readDataFromFile -> Successfully Read Data");
            return faculties;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            LoggingSystem.log("ERROR", "function readDataFromFile -> File not found");
            System.out.println("File not found!");
        }
        return null;
    }

    public static List<String> readListOfStudentsEmail(String filename){
        LoggingSystem.log("INFO", "function readListOfStudentsEmail -> Proccessing...");
        try {
            List<String> emailList = new ArrayList<>();
            File myFile = new File(filename+".txt");
            if(myFile.exists() && myFile.length()==0){
                return null;
            }
            Scanner myReader = new Scanner(myFile);
            String takeLine;
            while (myReader.hasNextLine()){
                takeLine = myReader.nextLine();
                if(takeLine!="") emailList.add(takeLine);
            }
            myReader.close();
            LoggingSystem.log("INFO", "function readDataFromFile -> Successfully Read Data");
            return emailList;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            LoggingSystem.log("ERROR", "function readDataFromFile -> File not found");
            System.out.println("File not found!");
        }
        return null;
    }
    public static void readListOfStudents(String filename, int indexOfFaculty,List<Faculty> faculties){
        LoggingSystem.log("INFO", "function readListOfStudentsEmail -> Proccessing...");
        try{
            File myFile = new File(filename+".txt");
            if(myFile.exists() && myFile.length()==0){
                System.out.println("File doesn't exists");
                return;
            }
            Scanner myReader = new Scanner(myFile);
            String takeLine;
            String firstName, lastName, email, enrollmentDate, dateOfBirth;
            Student student = null;
            boolean foundCoincidence;
            while (myReader.hasNextLine()){
                takeLine = myReader.nextLine();
                foundCoincidence = false;
                if(takeLine!="") {
                    firstName = takeLine;
                    lastName = myReader.nextLine();
                    email = myReader.nextLine();
                    enrollmentDate = myReader.nextLine();
                    dateOfBirth = myReader.nextLine();
                    for (int i = 0; i < faculties.size(); i++) {
                        student = faculties.get(i).findStudentInFacultyByEmail(email);
                        if(student != null){
                            foundCoincidence = true;
                            break;
                        }
                    }
                    if(foundCoincidence == true){
                        System.out.println("Email was already taken - " + email);
                    }
                    else{
                        student = new Student(firstName, lastName, email, LocalDate.parse(dateOfBirth), LocalDate.parse(enrollmentDate));
                        faculties.get(indexOfFaculty).addStudent(student);
                    }
                };
            }
            saveDataInFile(faculties);
            myReader.close();
            LoggingSystem.log("INFO", "function readDataFromFile -> Successfully Read Data");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            LoggingSystem.log("ERROR", "function readDataFromFile -> File not found");
            System.out.println("File not found!");
        }
    }
}
