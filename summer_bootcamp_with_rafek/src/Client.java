import java.time.LocalDate;

public class Client extends User {



    private double accountBalance;
    public Client( String name, String lastname, String dateOfBirth, String email,String pesel, String password) {
        super(name, lastname, dateOfBirth, email, pesel, password);
        this.accountBalance = 0;

    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }




}
