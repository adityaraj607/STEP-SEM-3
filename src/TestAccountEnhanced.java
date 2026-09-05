public class TestAccountEnhanced {
    void display(EnhancedAccount ac){
        System.out.println("Account #"+ac.getAccountNumber()+" | "+ac.getName()+" ("+ac.getAge()+") | "+ac.getAccountType()+" | ₹"+ac.getBalance()+" | "+ac.getStatus()+" | Pin:"+ac.hasPin());
    }
    void withdraw(EnhancedAccount ac, double amount){
        if (ac.withdraw(amount)){
            System.out.println("SUCCESS");
            System.out.println("New balance: ₹"+ac.getBalance());
        }else{
            System.out.println("FAILED (Account Inactive)");
        }
    }
    void deposit(EnhancedAccount ac, double amount){
        if (ac.deposit(amount)){
            System.out.println("SUCCESS");
            System.out.println("New balance: ₹"+ac.getBalance());
        }else{
            System.out.println("FAILED (Account Inactive)");
        }
    }
    void close(EnhancedAccount ac){
        if (ac.closeAccount()){
            System.out.println("Closing account: SUCCESS");
            System.out.print("After closing: ");
            display(ac);
        }
        else System.out.println("Closing account: FAILED");
    }

    void reopen(EnhancedAccount ac){
        if (ac.reopenAccount()){
            System.out.println("Reopening account: SUCCESS");
            System.out.print("After Reopening: ");
            display(ac);
        }
        else System.out.println("Reopening account: FAILED");
    }
    void setpin(EnhancedAccount ac, int pin){
        if (ac.setPin(pin)){
            System.out.println("SUCCESS");
        }else{
            System.out.println("FAILED");
        }
    }
    public void main(String[] args){
        System.out.println("============================================================");
        System.out.println("ENHANCED ACCOUNT TEST (BOOLEAN RETURNS)");
        System.out.println("============================================================");

        System.out.println("\n>>> Test 1: Valid Account Creation");
        EnhancedAccount ac1= new EnhancedAccount(1001,"John Doe",25,1000,"Savings");
        display(ac1);

        System.out.println("\n>>> Test 2: Invalid Age (under 18)");
        System.out.println("Creating account with age 16");
        EnhancedAccount ac2= new EnhancedAccount(1002,"Young Kid",16,500,"Savings");
        display(ac2);

        System.out.println("\n>>> Test 3: Invalid Account Type");
        System.out.println("Creating account with type \"Invalid\"");
        EnhancedAccount ac3= new EnhancedAccount(1003,"Test User",25,500,"Invalid");
        System.out.println("Account type defaulted to: "+ ac3.getAccountType());
        display(ac3);

        System.out.println("\n>>>Test 4: Minimum Balance Enforcement on Creation");
        System.out.println("Creating Savings account with ₹300 (below minimum)");
        EnhancedAccount ac4= new EnhancedAccount(1004,"Bob Wilson",25,300,"Savings");
        System.out.println("Balance auto-corrected to minimum: ₹"+ac4.getBalance());
        display(ac4);

        System.out.println("\n>>> Test 5: Withdrawal with Minimum Balance");
        EnhancedAccount ac5= new EnhancedAccount(1005,"Alice Brown",30,1000,"Current");
        System.out.print("Initial: ");
        display(ac5);
        System.out.println("Withdrawing 200");
        withdraw(ac5,200);
        System.out.print("After withdrawal:");
        display(ac5);
        System.out.println("Withdrawing 900");
        withdraw(ac5,900);
        display(ac5);

        System.out.println("\n>>> Test 6: Account Status Management");
        EnhancedAccount ac6= new EnhancedAccount(1006,"Charlie Green",35,2000,"Savings");
        close(ac6);
        System.out.print("Depositing ₹500.0 to closed account:");
        deposit(ac6,500);
        reopen(ac6);

        System.out.println("\n>>> Test 7: PIN Protection");
        EnhancedAccount ac7= new EnhancedAccount(1007,"Diana Prince",28,1300,"Savings");
        System.out.print("Setting PIN 1234: ");
        ac7.setPin(1234);

        System.out.print("Withdrawing ₹200.0 with correct PIN (1234):");
        if (ac7.withdraw(200,1234)){
            System.out.println("SUCCESS");
            System.out.println("New balance: "+ac7.getBalance());
        }else{
            System.out.println("FAILED (Incorrect pin)");
        }

        System.out.print("Withdrawing ₹100.0 with incorrect PIN (9999):");
        if (ac7.withdraw(100,9999)){
            System.out.println("SUCCESS");
            System.out.println("New balance: "+ac7.getBalance());
        }else{
            System.out.println("FAILED (Incorrect pin)");
        }
        System.out.print("Withdrawing ₹100.0 with PIN not set: ");
        EnhancedAccount ac8= new EnhancedAccount(1007,"Diana Prince",28,1300,"Savings");
        if (ac8.withdraw(100,9999)){
            System.out.println("SUCCESS");
            System.out.println("New balance: "+ac7.getBalance());
        }else{
            System.out.println("FAILED (Pin Not Set)");
        }

        System.out.println("\n>>> Test 8: All Accounts Summary");
        display(ac1);
        display(ac2);
        display(ac3);
        display(ac4);
        display(ac5);
        display(ac6);
        display(ac7);
    }
}
