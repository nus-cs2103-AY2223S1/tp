package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Name;
import seedu.address.model.poc.Poc;
import seedu.address.model.poc.UniquePocList;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionLog;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Client objects.
 */
public class ClientBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Address address;
    private Set<Tag> tags;
    private UniquePocList pocs;
    private TransactionLog transactions;

    /**
     * Creates a {@code ClientBuilder} with the default details.
     */
    public ClientBuilder() {
        name = new Name(DEFAULT_NAME);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        pocs = new UniquePocList();
        transactions = new TransactionLog();
    }

    /**
     * Initializes the ClientBuilder with the data of {@code clientToCopy}.
     */
    public ClientBuilder(Client clientToCopy) {
        name = clientToCopy.getName();
        address = clientToCopy.getAddress();
        tags = new HashSet<>(clientToCopy.getTags());
        pocs = clientToCopy.getPocs();
        transactions = clientToCopy.getTransactions();
    }

    /**
     * Sets the {@code Name} of the {@code Client} that we are building.
     */
    public ClientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Client} that we are building.
     */
    public ClientBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Client} that we are building.
     */
    public ClientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code UniquePocList} of the {@code Client} that we are building.
     */
    public ClientBuilder withUniquePocList(UniquePocList pocs) {
        this.pocs = pocs;
        return this;
    }

    /**
     * Sets the {@code TransactionLog} of the {@code Client} that we are building.
     */
    public ClientBuilder withTransactionLog(TransactionLog transactions) {
        this.transactions = transactions;
        return this;
    }

    /**
     * Adds poc to the {@code UniquePocList} of the {@code Client} that we are building.
     */
    public ClientBuilder withAddedPoc(Poc poc) {
        this.pocs.add(poc);
        return this;
    }

    /**
     * Adds transaction to the {@code Transactions} of the {@code Client} that we are building.
     */
    public ClientBuilder withTransaction(Transaction transaction) {
        this.transactions.addTransaction(transaction);
        return this;
    }

    public Client build() {
        return new Client(name, address, tags, pocs, transactions);
    }

}
