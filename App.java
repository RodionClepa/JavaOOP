import java.util.Scanner;

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

            if(operation[0].equals("df")){
                if(operation.length==1){
                    LoggingSystem.log("INFO", "generalOperations -> operation<df>");
                    university.printAllFaculties();
                }
                else if(operation.length==2){
                    LoggingSystem.log("INFO", "generalOperations -> operation<df>");
                    university.displayFacultiesOfField(operation[1]);
                }
                else{
                    LoggingSystem.log("WARN", "generalOperations -> operation<df> -> Invalid number of attributes");
                    System.out.println("Invalid number of attributes");
                }
            }
            else if(operation[0].equals("ss")){
                LoggingSystem.log("INFO", "generalOperations -> operation<ss>");
                if(operation.length==2){
                    university.searchStudentFaculty(operation[1]);
                }
                else{
                    LoggingSystem.log("WARN", "generalOperations -> operation<ss> -> Invalid number of attributes");
                    System.out.println("Not enough attributes!");
                }
            }
            else if(operation[0].equals("nf")){
                LoggingSystem.log("INFO", "generalOperations -> operation<nf>");
                if(operation.length==4){
                    university.createFaculty(operation[1], operation[2], operation[3]);
                }
                else{
                    LoggingSystem.log("WARN", "generalOperations -> operation<nf> -> Invalid number of attributes");
                    System.out.println("Not enough attributes!");
                }
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
            if(operation[0].equals("ns")){
                LoggingSystem.log("INFO", "facultyOperations -> operation<ns>");
                if(operation.length==7){
                    university.createStudent(operation[1],operation[2], operation[3], operation[4], operation[5], operation[6]);
                }
                else{
                    LoggingSystem.log("WARN", "facultyOperations -> operation<ns> -> Invalid number of attributes");
                    System.out.println("Invalid number of attributes");
                }
            }
            else if(operation[0].equals("gs")){
                LoggingSystem.log("INFO", "facultyOperations -> operation<gs>");
                if(operation.length == 2){
                    university.graduateStudent(operation[1]);
                }
                else{
                    LoggingSystem.log("WARN", "facultyOperations -> operation<gs> -> Invalid number of attributes");
                    System.out.println("Invalid number of attributes");
                }
            }
            else if(operation[0].equals("ds")){
                LoggingSystem.log("INFO", "facultyOperations -> operation<ns>");
                if(operation.length == 2){
                    university.printAllStudentsFacultyByStatus(operation[1], false);
                }
                else{
                    LoggingSystem.log("WARN", "facultyOperations -> operation<ds> -> Invalid number of attributes");
                    System.out.println("Invalid number of attributes");
                }
            }
            else if(operation[0].equals("dg")){
                LoggingSystem.log("INFO", "facultyOperations -> operation<dg>");
                if(operation.length == 2){
                    university.printAllStudentsFacultyByStatus(operation[1], true);
                }
                else{
                    LoggingSystem.log("WARN", "facultyOperations -> operation<dg> -> Invalid number of attributes");
                    System.out.println("Invalid number of attributes");
                }
            }
            else if(operation[0].equals("bf")){
                LoggingSystem.log("INFO", "facultyOperations -> operation<bf>");
                if(operation.length == 3){
                    university.checkStudentBelongs(operation[1], operation[2]);
                }
                else{
                    LoggingSystem.log("WARN", "facultyOperations -> operation<bf> -> Invalid number of attributes");
                    System.out.println("Invalid number of attributes");
                }
            }
        } while(!operation[0].equals("b") && !operation[0].equals("q"));
    }

    private void runStudentOperations(){
        String cmd = "";
        do {
            System.out.println(cmd);
            System.out.println("Student Operations");
            System.out.println("cf/<email>/<first name> - change first name\ncl/<email>/<last name>\nb - go back\n");
            cmd = takeUserInput();
            operation = cmd.split("/");

            if(operation[0].equals("cf")){
                LoggingSystem.log("INFO", "studentOperations -> operation<cf>");
                if(operation.length==3){
                    university.changeStudentFirstName(operation[1], operation[2]);
                }
                else{
                    LoggingSystem.log("WARN", "studentOperations -> operation<cf> -> Not enough attributes");
                    System.out.println("Not enough attributes!");
                }
            }
            if(operation[0].equals("cl")){
                LoggingSystem.log("INFO", "studentOperations -> operation<cl>");
                if(operation.length==3){
                    university.changeStudentLastName(operation[1], operation[2]);
                }
                else{
                    LoggingSystem.log("WARN", "studentOperations -> operation<cl> -> Not enough attributes");
                    System.out.println("Not enough attributes!");
                }
            }
        } while(!operation[0].equals("b") && !operation[0].equals("q"));
    }

    public void run(){
        university.addListOfFaculties(FileManager.readDataFromFile());
        String cmd = "";
        while(!cmd.equals("q")){
            System.out.println("--------------------");
            LoggingSystem.log("INFO", "function readDataFromFile -> Proccessing...");
            System.out.println("f - Faculty operations\ng - General operations\ns - Student Operations\nq - Quit Program\n");

            cmd = takeUserInput();

            LoggingSystem.log("INFO", "Reading command VALUE("+cmd+")");
            if(cmd.equals("f")){
                LoggingSystem.log("INFO", "Calling facultyOperations");
                runFacultyOperations();
            }
            else if(cmd.equals("g")){
                LoggingSystem.log("INFO", "Calling generalOperation");
                runGeneralOperations();
            }
            else if(cmd.equals("s")){
                LoggingSystem.log("INFO", "Calling studentOperations");
                runStudentOperations();
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