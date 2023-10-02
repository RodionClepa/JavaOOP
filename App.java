import java.util.Scanner;
import java.util.List;

public class App {
    private String[] operation;
    public University university;
    Scanner scanner = new Scanner(System.in);

    public App(){
        this.university = new University();
    }

    private String takeUserInput(){
        String cmd;
        System.out.print("->");
        cmd = this.scanner.nextLine();
        return cmd;
    }
    private void runGeneralOperations(){
        String cmd = "";
        do{
            System.out.println("General Operations");
            System.out.println("nf/<faculty name>/<faculty abbreviation>/<field> - create faculty\nss/<student email> - search student and show faculty\ndf - display all falcuties\ndf/<field> - display all faculties of a field\nb - go back\n");
            cmd = takeUserInput();
            operation = cmd.split("/");

            switch (operation[0]) {
                case "df":
                    if (operation.length == 1) {
                        LoggingSystem.log("INFO", "runGeneralOperations -> operation<df>");
                        university.printAllFaculties();
                    } else if (operation.length == 2) {
                        LoggingSystem.log("INFO", "runGeneralOperations -> operation<df>");
                        university.displayFacultiesOfField(operation[1]);
                    } else {
                        LoggingSystem.log("WARN", "runGeneralOperations -> operation<df> -> Invalid number of attributes");
                        System.out.println("Invalid number of attributes");
                    }
                    break;
            
                case "ss":
                    LoggingSystem.log("INFO", "runGeneralOperations -> operation<ss>");
                    if (operation.length == 2) {
                        university.searchStudentFaculty(operation[1]);
                    } else {
                        LoggingSystem.log("WARN", "runGeneralOperations -> operation<ss> -> Invalid number of attributes");
                        System.out.println("Not enough attributes!");
                    }
                    break;
            
                case "nf":
                    LoggingSystem.log("INFO", "runGeneralOperations -> operation<nf>");
                    if (operation.length == 4) {
                        university.createFaculty(operation[1], operation[2], operation[3]);
                    } else {
                        LoggingSystem.log("WARN", "runGeneralOperations -> operation<nf> -> Invalid number of attributes");
                        System.out.println("Not enough attributes!");
                    }
                    break;
                
                case "b":
                    break;
            
                default:
                    System.out.println("Invalid command");
                    break;
            }            
        }while(!operation[0].equals("b") && !operation[0].equals("q"));
    }
    private void runFacultyOperations(){
        String cmd = "";
        do {
            System.out.println("Faculty Operations");
            System.out.println("ns/<faculty abbreviation>/<first name>/<last name>/<email>/<year-month-day birth>/<year-month-day enrollment> - create student\ngs/<email> - graduate student\nds/<faculty abreviation> - display enrolled student\ndg/<faculty> - display graduated students\nbf/<faculty abbreviation>/<email> - check if student belongs to faculty\nb - go back\n");
            cmd = takeUserInput();
            operation = cmd.split("/");
            switch (operation[0]) {
                case "ns":
                    LoggingSystem.log("INFO", "runFacultyOperations -> operation<ns>");
                    if (operation.length == 7) {
                        university.createStudent(operation[1], operation[2], operation[3], operation[4], operation[5], operation[6]);
                    } else {
                        LoggingSystem.log("WARN", "runFacultyOperations -> operation<ns> -> Invalid number of attributes");
                        System.out.println("Invalid number of attributes");
                    }
                    break;
            
                case "gs":
                    LoggingSystem.log("INFO", "runFacultyOperations -> operation<gs>");
                    if (operation.length == 2) {
                        university.graduateStudent(operation[1]);
                    } else {
                        LoggingSystem.log("WARN", "runFacultyOperations -> operation<gs> -> Invalid number of attributes");
                        System.out.println("Invalid number of attributes");
                    }
                    break;
            
                case "ds":
                    LoggingSystem.log("INFO", "runFacultyOperations -> operation<ds>");
                    if (operation.length == 2) {
                        university.printAllStudentsFacultyByStatus(operation[1], false);
                    } else {
                        LoggingSystem.log("WARN", "runFacultyOperations -> operation<ds> -> Invalid number of attributes");
                        System.out.println("Invalid number of attributes");
                    }
                    break;
            
                case "dg":
                    LoggingSystem.log("INFO", "runFacultyOperations -> operation<dg>");
                    if (operation.length == 2) {
                        university.printAllStudentsFacultyByStatus(operation[1], true);
                    } else {
                        LoggingSystem.log("WARN", "runFacultyOperations -> operation<dg> -> Invalid number of attributes");
                        System.out.println("Invalid number of attributes");
                    }
                    break;
            
                case "bf":
                    LoggingSystem.log("INFO", "runFacultyOperations -> operation<bf>");
                    if (operation.length == 3) {
                        university.checkStudentBelongs(operation[1], operation[2]);
                    } else {
                        LoggingSystem.log("WARN", "runFacultyOperations -> operation<bf> -> Invalid number of attributes");
                        System.out.println("Invalid number of attributes");
                    }
                    break;
                
                case "b":
                    break;
            
                default:
                    System.out.println("Invalid command!");
                    break;
            }
            
        } while(!operation[0].equals("b") && !operation[0].equals("q"));
    }

    private void runStudentOperations(){
        String cmd = "";
        do {
            System.out.println(cmd);
            System.out.println("Student Operations");
            System.out.println("cf/<email>/<first name> - change first name\ncl/<email>/<last name> - change last name\nb - go back\n");
            cmd = takeUserInput();
            operation = cmd.split("/");

            switch (operation[0]) {
                case "cf":
                    LoggingSystem.log("INFO", "runStudentOperations -> operation<cf>");
                    if (operation.length == 3) {
                        university.changeStudentFirstName(operation[1], operation[2]);
                    } else {
                        LoggingSystem.log("WARN", "runStudentOperations -> operation<cf> -> Not enough attributes");
                        System.out.println("Not enough attributes!");
                    }
                    break;
            
                case "cl":
                    LoggingSystem.log("INFO", "runStudentOperations -> operation<cl>");
                    if (operation.length == 3) {
                        university.changeStudentLastName(operation[1], operation[2]);
                    } else {
                        LoggingSystem.log("WARN", "runStudentOperations -> operation<cl> -> Not enough attributes");
                        System.out.println("Not enough attributes!");
                    }
                    break;
                
                case "b":
                    break;
                    
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        } while(!operation[0].equals("b") && !operation[0].equals("q"));
    }

    private void runBatchOperations(){
        String cmd = "";
        int indexOfFaculty;
        do {
            System.out.println(cmd);
            System.out.println("Batch Operations");
            System.out.println("gs/<filename> - graduate students via txt file\nes/<filename>/<faculty abbreviation> - enroll students via txt file\nb - go back\n");
            cmd = takeUserInput();
            operation = cmd.split("/");

            switch (operation[0]) {
                case "gs":
                    LoggingSystem.log("INFO", "runBatchOperations -> operation<gs>");
                    if (operation.length == 2) {
                        List<String> emailList = FileManager.readListOfStudentsEmail(operation[1]);
                        if (emailList != null) {
                            for (int i = 0; i < emailList.size(); i++) {
                                university.graduateStudent(emailList.get(i));
                            }
                        }
                    } else {
                        LoggingSystem.log("WARN", "runBatchOperations -> operation<gs> -> Not enough attributes");
                        System.out.println("Not enough attributes!");
                    }
                    break;
            
                case "es":
                    LoggingSystem.log("INFO", "runBatchOperations -> operation<es>");
                    if (operation.length == 3) {
                        indexOfFaculty = university.getFacultyIndex(operation[2]);
                        if(indexOfFaculty!=-1){
                            FileManager.readListOfStudents(operation[1], indexOfFaculty, university.getFacultiesList());
                        }
                        else{
                            LoggingSystem.log("WARN", "runBatchOperations -> operation<es> -> No such faculty");
                            System.out.println("No such faculty!");
                        }
                    } else {
                        LoggingSystem.log("WARN", "runBatchOperations -> operation<es> -> Not enough attributes");
                        System.out.println("Not enough attributes!");
                    }
                    break;
                case "b":
                    break;

                default:
                    System.out.println("Invalid command!");
                    break;
            }
        } while(!operation[0].equals("b") && !operation[0].equals("q"));
    }

    public void run(){
        university.addListOfFaculties(FileManager.readDataFromFile());
        String cmd = "";
        while(!cmd.equals("q")){
            System.out.println("--------------------");
            LoggingSystem.log("INFO", "function readDataFromFile -> Proccessing...");
            System.out.println("f - Faculty operations\ng - General operations\ns - Student Operations\nb - Batch Operations\nq - Quit Program\n");

            cmd = takeUserInput();

            LoggingSystem.log("INFO", "Reading command VALUE("+cmd+")");
            switch (cmd) {
                case "f":
                    LoggingSystem.log("INFO", "Calling runFacultyOperations");
                    runFacultyOperations();
                    break;
                case "g":
                    LoggingSystem.log("INFO", "Calling runGeneralOperation");
                    runGeneralOperations();
                    break;
                case "s":
                    LoggingSystem.log("INFO", "Calling runStudentOperations");
                    runStudentOperations();
                    break;
                case "b":
                    LoggingSystem.log("INFO", "Calling runBatchOperations");
                    runBatchOperations();
                    break;

                case "q":
                    break;

                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
        this.scanner.close();
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
        LoggingSystem.log("INFO", "Closing programm");
    }
}