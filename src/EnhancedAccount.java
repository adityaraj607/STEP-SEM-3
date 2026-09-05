public class EnhancedAccount{
    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    int pin=0;
    public EnhancedAccount(int accountNumber, String name, int age, double initialBalance, String accountType) throws IllegalArgumentException{
        if(age<18){
            throw new IllegalArgumentException("Age must be at least 18");
        }
        if(!accountType.equals("Savings") && !accountType.equals("Current")){
            throw new IllegalArgumentException("Account type must be 'Savings' or 'Current'");
        }
        if(accountType.equals("Savings")){
            if(initialBalance<500){
                throw new IllegalArgumentException("Initial balance must be at least 500 for Savings account");
            }
        }else{
            if(initialBalance<1000){
                throw new IllegalArgumentException("Initial balance must be at least 1000 for Current account");
            }
        }
        this.accountNumber=accountNumber;
        this.name=name;
        this.age=age;
        this.accountType=accountType;
        this.balance=initialBalance;
        this.status="Active";
    }
    public void deposit(double amount) throws InvalidAmountException, InactiveAccountException{
        if(status.equals("Inactive")){
            throw new InactiveAccountException("Account is inactive");
        }
        if(amount<=0){
            throw new InvalidAmountException("Deposit amount must be positive");
        }
        balance+=amount;
    }
    public void withdraw(double amount,int pin) throws InvalidAmountException, InsufficientBalanceException, MinimumBalanceViolationException, InactiveAccountException, InvalidPinException{
        if(status.equals("Inactive")){
            throw new InactiveAccountException("Account is inactive");
        }
        if(!hasPin()){
            throw new InvalidPinException("No PIN has been set for this account");
        }
        if(this.pin!=pin){
            throw new InvalidPinException("Incorrect PIN");
        }
        if(amount<=0){
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }
        if(amount>balance){
            throw new InsufficientBalanceException("Insufficient balance");
        }
        if(accountType.equals("Savings") && (balance-amount<500)){
            throw new MinimumBalanceViolationException("Withdrawal would breach minimum balance of 500");
        }else if(accountType.equals("Current") && (balance-amount<1000)){
            throw new MinimumBalanceViolationException("Withdrawal would breach minimum balance of 1000");
        }
        balance-=amount;
    }
    public void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException, MinimumBalanceViolationException, InactiveAccountException{
        if(status.equals("Inactive")){
            throw new InactiveAccountException("Account is inactive");
        }
        if(amount<=0){
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }
        if(amount>balance){
            throw new InsufficientBalanceException("Insufficient balance");
        }
        if(accountType.equals("Savings") && (balance-amount<500)){
            throw new MinimumBalanceViolationException("Withdrawal would breach minimum balance of 500");
        }else if(accountType.equals("Current") && (balance-amount<1000)){
            throw new MinimumBalanceViolationException("Withdrawal would breach minimum balance of 1000");
        }
        balance-=amount;
    }
    public int getAccountNumber(){
        return accountNumber;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public void closeAccount() throws IllegalStateException{
        if(status.equals("Inactive")){
            throw new IllegalStateException("Account is already closed");
        }
        status="Inactive";
    }
    public void reopenAccount() throws IllegalStateException{
        if(status.equals("Active")){
            throw new IllegalStateException("Account is already active");
        }
        status="Active";
    }
    public double getBalance(){
        return balance;
    }
    public String getAccountType(){
        return accountType;
    }
    public String getStatus(){
        return status;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setAge(int age){
        this.age=age;
    }
    public void setPin(int pin) throws IllegalArgumentException{
        if(pin<1000 || pin>9999){
            throw new IllegalArgumentException("PIN must be a 4-digit number");
        }
        this.pin=pin;
    }
    public boolean verifyPin(int pin){
        return this.pin==pin;
    }
    public boolean hasPin(){
        return this.pin!=0;
    }
}