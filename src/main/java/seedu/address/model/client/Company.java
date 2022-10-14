package seedu.address.model.client;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyCompany;
import seedu.address.model.poc.Poc;
import seedu.address.model.poc.UniquePocList;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionLog;

/**
 * Represents a Company in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Company implements ReadOnlyCompany {

    // Identity fields
    private final Name name;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final UniquePocList pocs;
    private final TransactionLog transactions;

    /**
     * Every field must be present and not null.
     */
    public Company(Name name, Address address, Set<Tag> tags) {
        this(name, address, tags, new UniquePocList(), new TransactionLog());
    }

    /**
     * Overloaded Constructor take in all fields.
     * @param name company name.
     * @param address address of company.
     * @param tags tags of company.
     * @param pocs list of unique pocs.
     * @param transactions list of transactions.
     */
    public Company(Name name, Address address, Set<Tag> tags, UniquePocList pocs, TransactionLog transactions) {
        requireAllNonNull(name, address, tags, pocs);
        this.name = name;
        this.address = address;
        this.tags.addAll(tags);
        this.pocs = pocs;
        this.transactions = transactions;
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public UniquePocList getPocs() {
        return pocs;
    }

    public TransactionLog getTransactions() {
        return transactions;
    }

    /**
     * Adds a poc to the unique list in the company.
     * @param poc to be added to the list.
     */
    public void addPoc(Poc poc) {
        this.pocs.add(poc);
    }

    /**
     * Returns true if poc is in the list of pocs
     * @param poc to be checked
     * @return true if contains
     */
    public boolean hasPoc(Poc poc) {
        requireNonNull(poc);
        return pocs.contains(poc);
    }

    public double getTotalTransacted() {
        return transactions.calculateNetTransacted();
    }

    /**
     * Adds a transaction to the transaction log in the company.
     * @param transaction to be added to the list.
     */
    public void addTransaction(Transaction transaction) {
        this.transactions.addTransaction(transaction);
    }

    /**
     * Returns true if both companies have the same name.
     * This defines a weaker notion of equality between two companies.
     */
    public boolean isSameCompany(Company otherCompany) {
        if (otherCompany == this) {
            return true;
        }

        return otherCompany != null
                && otherCompany.getName().equals(getName());
    }

    /**
     * Returns true if both companies have the same identity and data fields.
     * This defines a stronger notion of equality between two companies.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Company)) {
            return false;
        }

        Company otherCompany = (Company) other;
        return otherCompany.getName().equals(getName())
                && otherCompany.getAddress().equals(getAddress())
                && otherCompany.getTags().equals(getTags());
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
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        UniquePocList pocs = getPocs();
        builder.append("; POCs: ");
        Iterator<Poc> itr = pocs.iterator();
        String prefix = "";
        while (itr.hasNext()) {
            Poc poc = itr.next();
            builder.append(prefix);
            prefix = ", ";
            builder.append(poc.getName());
        }

        TransactionLog transactions = getTransactions();

        builder.append("; Total transactions: $");
        if (!isNull(transactions) && !transactions.isEmpty()) {
            builder.append(transactions.calculateNetTransacted());
        } else {
            builder.append("0");
        }
        return builder.toString();
    }

    @Override
    public ObservableList<Poc> getPocList() {
        return pocs.asUnmodifiableObservableList();
    }

}
