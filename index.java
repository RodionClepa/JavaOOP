import java.util.Scanner;

public class index {
    public static void main(String[] args){
        BankAccount newAccount = new BankAccount("124rafafa3", "ClepaRodion");
        newAccount.printAllData();
        Scanner scanner = new Scanner(System.in);
        double enteredAmount;
        byte command=-1;
        while(command!=0){
            System.out.println("1-Deposit, 2-Print All Data, 3-Take off, 0-Exit");
            command = scanner.nextByte();
            if(command==1){
                System.out.println("Enter the amount of money you want to deposit");
                enteredAmount = scanner.nextDouble();
                newAccount.deposit(enteredAmount);
            }
            if(command==2) newAccount.printAllData();
            if(command==3){
                System.out.println("Enter the amount of money you want to take from deposit");
                enteredAmount = scanner.nextDouble();
                newAccount.takeOff(enteredAmount);
            }
        }
        scanner.close();
    }
}
