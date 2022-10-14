package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.client.Address;
import seedu.address.model.client.Company;
import seedu.address.model.client.Name;
import seedu.address.model.poc.Poc;
import seedu.address.model.poc.UniquePocList;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionLog;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Company objects.
 */
public class CompanyBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Address address;
    private Set<Tag> tags;
    private UniquePocList pocs;
    private TransactionLog transactions;

    /**
     * Creates a {@code CompanyBuilder} with the default details.
     */
    public CompanyBuilder() {
        name = new Name(DEFAULT_NAME);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        pocs = new UniquePocList();
        transactions = new TransactionLog();
    }

    /**
     * Initializes the CompanyBuilder with the data of {@code companyToCopy}.
     */
    public CompanyBuilder(Company companyToCopy) {
        name = companyToCopy.getName();
        address = companyToCopy.getAddress();
        tags = new HashSet<>(companyToCopy.getTags());
        pocs = companyToCopy.getPocs();
        transactions = companyToCopy.getTransactions();
    }

    /**
     * Sets the {@code Name} of the {@code Company} that we are building.
     */
    public CompanyBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Company} that we are building.
     */
    public CompanyBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Company} that we are building.
     */
    public CompanyBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code UniquePocList} of the {@code Company} that we are building.
     */
    public CompanyBuilder withUniquePocList(UniquePocList pocs) {
        this.pocs = pocs;
        return this;
    }

    /**
     * Sets the {@code TransactionLog} of the {@code Company} that we are building.
     */
    public CompanyBuilder withTransactionLog(TransactionLog transactions) {
        this.transactions = transactions;
        return this;
    }

    /**
     * Adds poc to the {@code UniquePocList} of the {@code Company} that we are building.
     */
    public CompanyBuilder withAddedPoc(Poc poc) {
        this.pocs.add(poc);
        return this;
    }

    /**
     * Adds transaction to the {@code Transactions} of the {@code Company} that we are building.
     */
    public CompanyBuilder withTransaction(Transaction transaction) {
        this.transactions.addTransaction(transaction);
        return this;
    }

    public Company build() {
        return new Company(name, address, tags, pocs, transactions);
    }

}
