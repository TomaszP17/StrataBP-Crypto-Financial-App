import java.time.LocalDate;

public class Client extends User {
    private double accountBalance;

    public Client(LocalDate dateOfBirth, String name, String lastname, String pesel, String email, String password) {
        super(dateOfBirth, name, lastname, pesel, email, password);
        this.accountBalance = 0;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
