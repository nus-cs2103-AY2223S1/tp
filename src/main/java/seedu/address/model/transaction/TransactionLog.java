package seedu.address.model.transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Store all transactions in a list.
 */
public class TransactionLog {
    private final static double INITIAL_NET_TRANSACTION = 0;
    List<Transaction> transactionList;
    double netTransacted;

    public TransactionLog(List<Transaction> transactionList, double netTransacted) {
        this.transactionList = transactionList;
        this.netTransacted = netTransacted;
    }

    public TransactionLog() {
        this(new ArrayList<Transaction>(), INITIAL_NET_TRANSACTION);
    }

}
