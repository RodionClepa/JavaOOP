import java.time.LocalDate;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate enrollmentDate;
    private LocalDate dateOfBirth;
    boolean graduated;

    public Student(String firstName, String lastName, String email, LocalDate dateOfBirth, LocalDate enrollmentDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.dateOfBirth = dateOfBirth;
        this.graduated = false;
    }

    public Student(String firstName, String lastName, String email, LocalDate dateOfBirth, LocalDate enrollmentDate, boolean graduated){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.dateOfBirth = dateOfBirth;
        this.graduated = graduated;
    }

    public void studentInfo(){
        System.out.println("First Name: "+firstName);
        System.out.println("Last Name: "+lastName);
        System.out.println("Email: "+email);
        System.out.println("Date of birth: "+dateOfBirth);
        System.out.println("Enrollment Date: "+enrollmentDate);
    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }
    public boolean getGraduated(){
        return graduated;
    }

    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }

    public LocalDate getEnrollmentDate(){
        return enrollmentDate;
    }

    public void gradute(){
        graduated=true;
    }
    public void changeFirstName(String firstName){
        this.firstName = firstName;
    }
    public void changeLastName(String lastName){
        this.lastName = lastName;
    }
}
