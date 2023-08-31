import java.util.ArrayList;
import java.util.List;

public class TransactionController {

    private static final List<Transaction> transactionList = new ArrayList<>();

    static {
        transactionList.add(new Transaction(ClientsController.findByEmail("test"),
                            ClientsController.findByEmail("rafalprzybyl17@gmail.com"),
                            Cryptocurrency.BTC,
                            10));
        transactionList.add(new Transaction(ClientsController.findByEmail("rafalprzybyl17@gmail.com"),
                            ClientsController.findByEmail("thommy@gmail.com"),
                            Cryptocurrency.ETH,
                            20));
    }

    public static List<Transaction> getTransactionList() {
        return transactionList;
    }
    public static void addNewTransaction(User from, User to, Cryptocurrency cryptocurrency, double amount){
        transactionList.add(new Transaction(from, to, cryptocurrency, amount));
    }
}
