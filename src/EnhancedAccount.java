public class EnhancedAccount{
    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    int pin=0;
    public EnhancedAccount(int accountNumber, String name, int age, double initialBalance, String accountType){
        this.accountNumber=accountNumber;
        this.name=name;
        if(age<18){
            this.age=18;
        }else{
            this.age=age;
        }
        if (accountType.equals("Savings") || accountType.equals("Current")){
            this.accountType=accountType;
        }else{
            this.accountType="Savings";
        }
        if(this.accountType.equals("Savings")){
            if (initialBalance<500) this.balance=500;
            else this.balance=initialBalance;
        } else {
            if (initialBalance<1000) this.balance=1000;
            else this.balance=initialBalance;
        }
        this.status="Active";
    }
    public boolean deposit(double amount){
        if (status.equals("Inactive")) return false;
        if (amount<=0){
            return false;
        }else{
            balance+=amount;
            return true;
        }
    }
    public boolean withdraw(double amount,int pin){
        if (this.pin!=pin) return false;
        if (status.equals("Inactive")) return false;
        if ((accountType.equals("Savings")) &&(balance-amount<500)){
            return false;
        }else if (accountType.equals("Current") &&(balance-amount<1000)){
            return false;
        }
        balance-=amount;
        return true;
    }
    public boolean withdraw(double amount){
        if (status.equals("Inactive")) return false;
        if ((accountType.equals("Savings")) &&(balance-amount<500)){
            return false;
        }else if (accountType.equals("Current") &&(balance-amount<1000)){
            return false;
        }
        balance-=amount;
        return true;

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
    public boolean closeAccount(){
        status="Inactive";
        return true;
    }
    public boolean reopenAccount(){
        status="Active";
        return true;
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
    public boolean setPin(int pin){
        if (pin<1000 || pin >9999){
            return false;
        }else{
            this.pin=pin;
            return true;
        }
    }
    public boolean verifyPin(int pin){
        return this.pin==pin;
    }
    public boolean hasPin(){
        return this.pin!=0;
    }
}