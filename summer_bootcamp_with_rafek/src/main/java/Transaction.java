import java.time.LocalDate;

public class Transaction {
    private final int id;
    private final User from;
    private final User to;
    private final Cryptocurrency cryptocurrency;
    private final double amount;
    private final LocalDate date;
    private static int counter = 1;

    public Transaction(User from, User to, Cryptocurrency cryptocurrency, double amount) {
        this.id = counter++;
        this.from = from;
        this.to = to;
        this.cryptocurrency = cryptocurrency;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public Cryptocurrency getCryptocurrency() {
        return cryptocurrency;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
