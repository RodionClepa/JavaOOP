import java.util.Scanner;

public class App {
    private String[] operation;

    private void generalOperations(Univer uni, Scanner scanner){
        String cmd = "";
        do{
            System.out.println("General Operations");
            System.out.println("nf/<faculty name>/<faculty abbreviation>/<field> - create faculty\nss/<student email> - search student and show faculty\ndf - display all falcuties\ndf/<field> - display all faculties of a field\nb - go back\n");
            System.out.print("->");
            cmd = scanner.nextLine();
            operation = cmd.split("/");

            if(operation[0].equals("df")){
                if(operation.length==1){
                    uni.log("INFO", "generalOperations -> operation<df>");
                    uni.printAllFaculties();
                }
                else if(operation.length==2){
                    uni.log("INFO", "generalOperations -> operation<df>");
                    uni.displayFacultiesOfField(operation[1]);
                }
                else{
                    uni.log("WARN", "generalOperations -> operation<df> -> Invalid number of attributes");
                    System.out.println("Invalid number of attributes");
                }
            }
            else if(operation[0].equals("ss")){
                uni.log("INFO", "generalOperations -> operation<ss>");
                if(operation.length==2){
                    uni.searchStudentFaculty(operation[1]);
                }
                else{
                    uni.log("WARN", "generalOperations -> operation<ss> -> Invalid number of attributes");
                    System.out.println("Not enough attributes!");
                }
            }
            else if(operation[0].equals("nf")){
                uni.log("INFO", "generalOperations -> operation<nf>");
                if(operation.length==4){
                    uni.createFaculty(operation[1], operation[2], operation[3]);
                }
                else{
                    uni.log("WARN", "generalOperations -> operation<nf> -> Invalid number of attributes");
                    System.out.println("Not enough attributes!");
                }
            }
            else if(operation[0].equals("s")){
                uni.log("INFO", "generalOperations -> operation<s>");
                uni.readDataFromFile();
            }
        }while(!operation[0].equals("b") && !operation[0].equals("q"));
    }
    private void facultyOperations(Univer uni, Scanner scanner){
        String cmd = "";
        do {
            System.out.println("Faculty Operations");
            System.out.println("ns/<faculty abbreviation>/<first name>/<last name>/<email>/<year-month-day birth>/<year-month-day enrollment> - create student\ngs/<email> - graduate student\nds/<faculty abreviation> - display enrolled student\ndg/<faculty> - display graduated students\nbf/<faculty abbreviation>/<email> - check if student belongs to faculty\nb - go back\n");
            System.out.print("->");
            cmd = scanner.nextLine();
            operation = cmd.split("/");
            if(operation[0].equals("ns")){
                uni.log("INFO", "facultyOperations -> operation<ns>");
                if(operation.length==7){
                    uni.createStudent(operation[1],operation[2], operation[3], operation[4], operation[5], operation[6]);
                }
                else{
                    uni.log("WARN", "facultyOperations -> operation<ns> -> Invalid number of attributes");
                    System.out.println("Invalid number of attributes");
                }
            }
            else if(operation[0].equals("gs")){
                uni.log("INFO", "facultyOperations -> operation<gs>");
                if(operation.length == 2){
                    uni.graduateStudent(operation[1]);
                }
                else{
                    uni.log("WARN", "facultyOperations -> operation<gs> -> Invalid number of attributes");
                    System.out.println("Invalid number of attributes");
                }
            }
            else if(operation[0].equals("ds")){
                uni.log("INFO", "facultyOperations -> operation<ns>");
                if(operation.length == 2){
                    uni.printAllStudentsFacultyByStatus(operation[1], false);
                }
                else{
                    uni.log("WARN", "facultyOperations -> operation<ds> -> Invalid number of attributes");
                    System.out.println("Invalid number of attributes");
                }
            }
            else if(operation[0].equals("dg")){
                uni.log("INFO", "facultyOperations -> operation<dg>");
                if(operation.length == 2){
                    uni.printAllStudentsFacultyByStatus(operation[1], true);
                }
                else{
                    uni.log("WARN", "facultyOperations -> operation<dg> -> Invalid number of attributes");
                    System.out.println("Invalid number of attributes");
                }
            }
            else if(operation[0].equals("bf")){
                uni.log("INFO", "facultyOperations -> operation<bf>");
                if(operation.length == 3){
                    uni.checkStudentBelongs(operation[1], operation[2]);
                }
                else{
                    uni.log("WARN", "facultyOperations -> operation<bf> -> Invalid number of attributes");
                    System.out.println("Invalid number of attributes");
                }
            }
        } while(!operation[0].equals("b") && !operation[0].equals("q"));
    }

    private void studentOperations(Univer uni, Scanner scanner){
        String cmd = "";
        do {
            System.out.println(cmd);
            System.out.println("Student Operations");
            System.out.println("cf/<email>/<first name> - change first name\ncl/<email>/<last name>\nb - go back\n");
            System.out.print("->");
            cmd = scanner.nextLine();
            operation = cmd.split("/");

            if(operation[0].equals("cf")){
                uni.log("INFO", "studentOperations -> operation<cf>");
                if(operation.length==3){
                    uni.changeStudentFirstName(operation[1], operation[2]);
                }
                else{
                    uni.log("WARN", "studentOperations -> operation<cf> -> Not enough attributes");
                    System.out.println("Not enough attributes!");
                }
            }
            if(operation[0].equals("cl")){
                uni.log("INFO", "studentOperations -> operation<cl>");
                if(operation.length==3){
                    uni.changeStudentLastName(operation[1], operation[2]);
                }
                else{
                    uni.log("WARN", "studentOperations -> operation<cl> -> Not enough attributes");
                    System.out.println("Not enough attributes!");
                }
            }
        } while(!operation[0].equals("b") && !operation[0].equals("q"));
    }

    public static void main(String[] args) {
        App app = new App();
        Univer uni = new Univer();
        uni.log("INFO", "function changeStudentFirstName -> Succesfully updated");
        uni.readDataFromFile();
        String cmd = "";
        Scanner scanner = new Scanner(System.in);
        while(!cmd.equals("q")){
            System.out.println("--------------------");
            uni.log("INFO", "function readDataFromFile -> Proccessing...");
            System.out.println("f - Faculty operations\ng - General operations\ns - Student Operations\nq - Quit Program\n");
            System.out.print("->");
            cmd = scanner.nextLine();
            uni.log("INFO", "Reading command VALUE("+cmd+")");
            if(cmd.equals("f")){
                uni.log("INFO", "Calling facultyOperations");
                app.facultyOperations(uni, scanner);
            }
            else if(cmd.equals("g")){
                uni.log("INFO", "Calling generalOperation");
                app.generalOperations(uni, scanner);
            }
            else if(cmd.equals("s")){
                uni.log("INFO", "Calling studentOperations");
                app.studentOperations(uni, scanner);
            }
        }
        uni.log("INFO", "Closing programm");
        scanner.close();
    }
}

