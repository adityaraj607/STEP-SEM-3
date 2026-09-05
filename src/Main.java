void deposit(Account ac, double amount){
    if (ac.deposit(500)){
        System.out.println("SUCCESS");
        System.out.println("New balance: ₹"+ac.getBalance());
    }else{
        System.out.println("FAILED (Invalid amount)");
    }
}

void withdraw(Account ac, double amount){
    if (ac.withdraw(amount)){
        System.out.println("SUCCESS");
        System.out.println("New balance: ₹"+ac.getBalance());
    }else{
        System.out.println("FAILED (Insufficient balance)");
    }
}

void display(Account ac){
    System.out.println("Account #"+ac.getAccountNumber()+" | "+ac.getName()+" ("+ac.getAge()+") | "+ac.getAccountType()+" | ₹"+ac.getBalance()+" | "+ac.getStatus());
}

void main() {
    System.out.println("==================================================");
    System.out.println("  GLOBAL DIGITAL BANK - ACCOUNT TEST");
    System.out.println("==================================================");
    System.out.println(">>> 1. Creating Account");
    Account ac1 = new Account(1001,"John Doe",25, 1000,"Savings");
    System.out.println("Account created!");
    display(ac1);

    System.out.println(">>> 2. Deposit Money");
    System.out.print("Depositing ₹500.0: ");
    deposit(ac1,500);

    System.out.print("Depositing ₹-100.0: ");
    deposit(ac1,-100);

    System.out.println(">>> 3. Withdraw Money");
    System.out.println("Withdrawing ₹200.0: ");
    withdraw(ac1, 200);

    System.out.println("Withdrawing ₹2000.0: ");
    withdraw(ac1, 200);

    System.out.println(">>> 4. Creating Another Account");
    Account ac2 = new Account(1002,"Jane Smith",30, 2000,"Current");
    display(ac2);

    System.out.println(">>> 5. All Accounts");
    display(ac1);
    display(ac2);

    System.out.println("==================================================");
    System.out.println("  TEST COMPLETED!");
    System.out.println("==================================================");
}
