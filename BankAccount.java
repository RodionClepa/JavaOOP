public class BankAccount {
     String accountNumber;
     String accountHolder;
     double balance;
     public BankAccount(String accountNumber, String accountHolder){
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0;
     }
     public void printAllData(){
        System.out.println("Account Number:" + this.accountNumber);
        System.out.println("Account Holder:" + this.accountHolder);
        System.out.println("Account Holder:" + this.balance);
     }
     public void deposit(double amount){
        if(amount>0){
            this.balance+=amount;
        }
        else{
            System.out.println("Invalid input!");
        }
     }
     public void takeOff(double amount){
        if(amount<balance){
            this.balance-=amount;
        }
        else{
            System.out.println("Invalid input!");
        }
     }
}
