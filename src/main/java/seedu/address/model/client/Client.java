package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyClient;
import seedu.address.model.remark.Remark;
import seedu.address.model.remark.UniqueRemarkList;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionLog;

/**
 * Represents a Client in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client implements ReadOnlyClient {

    // Identity fields
    private final Name name;

    // Data fields
    private final Address address;
    private final ClientPhone phone;
    private final ClientEmail email;
    private final Set<Tag> tags = new HashSet<>();
    private final UniqueRemarkList remarks;
    private final TransactionLog transactions;

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Address address, ClientPhone phone, ClientEmail email, Set<Tag> tags) {
        this(name, address, phone, email, tags, new UniqueRemarkList(), new TransactionLog());
    }

    /**
     * Overloaded Constructor take in all fields.
     * @param name client name.
     * @param address address of client.
     * @param phone number of client.
     * @param email email of client.
     * @param tags tags of client.
     * @param remarks list of unique remarks.
     * @param transactions list of transactions.
     */
    public Client(Name name, Address address, ClientPhone phone, ClientEmail email,
                  Set<Tag> tags, UniqueRemarkList remarks, TransactionLog transactions) {
        requireAllNonNull(name, address, tags, remarks);
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.tags.addAll(tags);
        this.remarks = remarks;
        this.transactions = transactions;
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public ClientPhone getPhone() {
        return phone;
    }

    public ClientEmail getEmail() {
        return email;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public UniqueRemarkList getRemarks() {
        return remarks;
    }

    public TransactionLog getTransactions() {
        return transactions;
    }

    /**
     * Adds a remark to the unique list in the client.
     * @param remark to be added to the list.
     */
    public void addRemark(Remark remark) {
        this.remarks.add(remark);
    }

    /**
     * Returns true if remark is in the list of remarks
     * @param remark to be checked
     * @return true if contains
     */
    public boolean hasRemark(Remark remark) {
        requireNonNull(remark);
        return remarks.contains(remark);
    }

    public double getTotalTransacted() {
        return transactions.calculateNetTransacted();
    }

    /**
     * Adds a transaction to the transaction log in the client.
     * @param transaction to be added to the list.
     */
    public void addTransaction(Transaction transaction) {
        this.transactions.addTransaction(transaction);
    }

    /**
     * Returns true if both client have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return otherClient.getName().equals(getName())
                && otherClient.getAddress().equals(getAddress())
                && otherClient.getPhone().equals(getPhone())
                && otherClient.getEmail().equals(getEmail())
                && otherClient.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Address: ")
                .append(getAddress())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        UniqueRemarkList remarks = getRemarks();
        builder.append("; Remarks: ");
        Iterator<Remark> itr = remarks.iterator();
        String prefix = "";
        while (itr.hasNext()) {
            Remark remark = itr.next();
            builder.append(prefix);
            prefix = ", ";
            builder.append(remark.getName());
        }

        TransactionLog transactions = getTransactions();
        builder.append("; Total transactions: ");

        if (transactions == null || transactions.isEmpty()) {
            builder.append("$0");
            return builder.toString();
        }

        double netTransaction = transactions.calculateNetTransacted();
        builder.append(netTransaction < 0 ? "-$" : "$").append(Math.abs(netTransaction));

        return builder.toString();
    }

    @Override
    public ObservableList<Remark> getRemarkList() {
        return remarks.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Transaction> getTransactionList() {
        return transactions.asUnmodifiableObservableList();
    }

    /**
     * Returns an unmodifiable view of the sell transactions list.
     */
    public ObservableList<Transaction> getSellTransactionList() {
        return transactions.getSellTransactionList();
    }

    /**
     * Returns an unmodifiable view of the buy transactions list.
     */
    public ObservableList<Transaction> getBuyTransactionList() {
        return transactions.getBuyTransactionList();
    }

}
