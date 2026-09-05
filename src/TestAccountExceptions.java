import java.util.ArrayList;
import java.util.List;

public class TestAccountExceptions{
    public static void main(String[] args){
        List<EnhancedAccount> accounts=new ArrayList<>();

        System.out.println("============================================================");
        System.out.println("ACCOUNT TEST WITH EXCEPTIONS");
        System.out.println("============================================================");

        System.out.println(">>> Test 1: Valid Account Creation");
        try{
            EnhancedAccount acc1=new EnhancedAccount(1001,"John Doe",25,1000.0,"Savings");
            accounts.add(acc1);
            System.out.println("SUCCESS: "+accountInfo(acc1));
        }catch(IllegalArgumentException e){
            System.out.println("EXCEPTION: "+e.getMessage());
        }

        System.out.println(">>> Test 2: Invalid Age (under 18)");
        try{
            EnhancedAccount acc2=new EnhancedAccount(1002,"Jane Smith",16,1000.0,"Savings");
            accounts.add(acc2);
            System.out.println("SUCCESS: "+accountInfo(acc2));
        }catch(IllegalArgumentException e){
            System.out.println("EXCEPTION: "+e.getMessage());
        }

        System.out.println(">>> Test 3: Invalid Account Type");
        try{
            EnhancedAccount acc3=new EnhancedAccount(1003,"Bob White",22,1000.0,"Invalid");
            accounts.add(acc3);
            System.out.println("SUCCESS: "+accountInfo(acc3));
        }catch(IllegalArgumentException e){
            System.out.println("EXCEPTION: "+e.getMessage());
        }

        System.out.println(">>> Test 4: Minimum Balance on Creation");
        System.out.println("Creating Savings account with ₹300");
        try{
            EnhancedAccount acc4=new EnhancedAccount(1004,"Karen Black",29,300.0,"Savings");
            accounts.add(acc4);
            System.out.println("SUCCESS: "+accountInfo(acc4));
        }catch(IllegalArgumentException e){
            System.out.println("EXCEPTION: "+e.getMessage());
        }

        System.out.println(">>> Test 5: Valid Deposit and Withdrawal");
        EnhancedAccount acc5=null;
        try{
            acc5=new EnhancedAccount(1005,"Alice Brown",30,1000.0,"Current");
            accounts.add(acc5);
            System.out.println("Account: "+accountInfo(acc5));

            try{
                acc5.setPin(1234);
                System.out.println("Setting PIN 1234: SUCCESS");
            }catch(IllegalArgumentException e){
                System.out.println("EXCEPTION: "+e.getMessage());
            }

            try{
                acc5.deposit(500.0);
                System.out.println("Depositing ₹500.0: SUCCESS");
                System.out.println("Balance after deposit: ₹"+acc5.getBalance());
            }catch(InvalidAmountException | InactiveAccountException e){
                System.out.println("EXCEPTION: "+e.getMessage());
            }

            try{
                acc5.withdraw(200.0,1234);
                System.out.println("Withdrawing ₹200.0: SUCCESS");
                System.out.println("Balance after withdrawal: ₹"+acc5.getBalance());
            }catch(InvalidAmountException | InsufficientBalanceException | MinimumBalanceViolationException | InactiveAccountException | InvalidPinException e){
                System.out.println("EXCEPTION: "+e.getMessage());
            }

            System.out.println(accountInfo(acc5));
        }catch(IllegalArgumentException e){
            System.out.println("EXCEPTION: "+e.getMessage());
        }

        System.out.println(">>> Test 6: Invalid Deposit (Negative Amount)");
        System.out.println("Attempting to deposit ₹-100.0");
        try{
            acc5.deposit(-100.0);
            System.out.println("Depositing ₹-100.0: SUCCESS");
        }catch(InvalidAmountException | InactiveAccountException e){
            System.out.println("EXCEPTION: "+e.getMessage());
        }

        System.out.println(">>> Test 7: Insufficient Balance");
        try{
            EnhancedAccount acc6=new EnhancedAccount(1006,"Charlie Green",35,500.0,"Savings");
            accounts.add(acc6);
            acc6.setPin(1234);
            System.out.println("Account: "+accountInfo(acc6));
            System.out.println("Attempting to withdraw ₹1000.0");
            try{
                acc6.withdraw(1000.0,1234);
                System.out.println("Withdrawing ₹1000.0: SUCCESS");
            }catch(InvalidAmountException | InsufficientBalanceException | MinimumBalanceViolationException | InactiveAccountException | InvalidPinException e){
                System.out.println("EXCEPTION: "+e.getMessage());
            }
        }catch(IllegalArgumentException e){
            System.out.println("EXCEPTION: "+e.getMessage());
        }

        System.out.println(">>> Test 8: Minimum Balance Violation");
        try{
            EnhancedAccount acc7=new EnhancedAccount(1007,"Diana Prince",28,1000.0,"Savings");
            accounts.add(acc7);
            acc7.setPin(1234);
            System.out.println("Account: "+accountInfo(acc7));
            System.out.println("Attempting to withdraw ₹600.0");
            try{
                acc7.withdraw(600.0,1234);
                System.out.println("Withdrawing ₹600.0: SUCCESS");
            }catch(InvalidAmountException | InsufficientBalanceException | MinimumBalanceViolationException | InactiveAccountException | InvalidPinException e){
                System.out.println("EXCEPTION: "+e.getMessage());
            }
        }catch(IllegalArgumentException e){
            System.out.println("EXCEPTION: "+e.getMessage());
        }

        System.out.println(">>> Test 9: Inactive Account Operations");
        try{
            EnhancedAccount acc8=new EnhancedAccount(1008,"Eve Wilson",32,2000.0,"Current");
            accounts.add(acc8);
            System.out.println("Account: "+accountInfo(acc8));

            try{
                acc8.closeAccount();
                System.out.println("Closing account: SUCCESS");
            }catch(IllegalStateException e){
                System.out.println("EXCEPTION: "+e.getMessage());
            }

            System.out.println("Attempting to deposit ₹100.0 on closed account");
            try{
                acc8.deposit(100.0);
                System.out.println("Depositing ₹100.0: SUCCESS");
            }catch(InvalidAmountException | InactiveAccountException e){
                System.out.println("EXCEPTION: "+e.getMessage());
            }

            try{
                acc8.reopenAccount();
                System.out.println("Reopening account: SUCCESS");
            }catch(IllegalStateException e){
                System.out.println("EXCEPTION: "+e.getMessage());
            }

            try{
                acc8.deposit(100.0);
                System.out.println("Depositing ₹100.0 after reopen: SUCCESS");
                System.out.println("Balance after deposit: ₹"+acc8.getBalance());
            }catch(InvalidAmountException | InactiveAccountException e){
                System.out.println("EXCEPTION: "+e.getMessage());
            }
        }catch(IllegalArgumentException e){
            System.out.println("EXCEPTION: "+e.getMessage());
        }

        System.out.println(">>> Test 10: PIN Verification");
        try{
            EnhancedAccount acc9=new EnhancedAccount(1009,"Frank Miller",40,1500.0,"Savings");
            accounts.add(acc9);
            System.out.println("Account: "+accountInfo(acc9));

            try{
                acc9.setPin(1234);
                System.out.println("Setting PIN 1234: SUCCESS");
            }catch(IllegalArgumentException e){
                System.out.println("EXCEPTION: "+e.getMessage());
            }

            try{
                acc9.withdraw(200.0,1234);
                System.out.println("Withdrawing ₹200.0 with correct PIN: SUCCESS");
                System.out.println("Balance: ₹"+acc9.getBalance());
            }catch(InvalidAmountException | InsufficientBalanceException | MinimumBalanceViolationException | InactiveAccountException | InvalidPinException e){
                System.out.println("EXCEPTION: "+e.getMessage());
            }

            System.out.println("Attempting to withdraw ₹100.0 with incorrect PIN (9999)");
            try{
                acc9.withdraw(100.0,9999);
                System.out.println("Withdrawing ₹100.0: SUCCESS");
            }catch(InvalidAmountException | InsufficientBalanceException | MinimumBalanceViolationException | InactiveAccountException | InvalidPinException e){
                System.out.println("EXCEPTION: "+e.getMessage());
            }

            System.out.println("Attempting to withdraw ₹100.0 without PIN set");
            try{
                EnhancedAccount tempAcc=new EnhancedAccount(9999,"Temp User",20,1000.0,"Savings");
                tempAcc.withdraw(100.0,1234);
                System.out.println("Withdrawing ₹100.0: SUCCESS");
            }catch(InvalidAmountException | InsufficientBalanceException | MinimumBalanceViolationException | InactiveAccountException | InvalidPinException e){
                System.out.println("EXCEPTION: "+e.getMessage());
            }
        }catch(IllegalArgumentException e){
            System.out.println("EXCEPTION: "+e.getMessage());
        }

        System.out.println(">>> Test 11: All Accounts Summary");
        for(EnhancedAccount acc:accounts){
            System.out.println(accountInfo(acc));
        }

        System.out.println("============================================================");
        System.out.println("TEST COMPLETED!");
        System.out.println("============================================================");
    }

    private static String accountInfo(EnhancedAccount acc){
        return "Account #"+acc.getAccountNumber()+" | "+acc.getName()+" ("+acc.getAge()+" yrs) | "+acc.getAccountType()+" | ₹"+acc.getBalance()+" | "+acc.getStatus()+" | PIN: "+(acc.hasPin()?"Yes":"No");
    }
}