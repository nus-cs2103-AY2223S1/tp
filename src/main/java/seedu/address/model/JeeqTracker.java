package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.client.UniqueClientList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameCompany comparison)
 */
public class JeeqTracker implements ReadOnlyJeeqTracker {

    private final UniqueClientList companies;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        companies = new UniqueClientList();
    }

    public JeeqTracker() {}

    /**
     * Creates an JeeqTracker using the Companies in the {@code toBeCopied}
     */
    public JeeqTracker(ReadOnlyJeeqTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the company list with {@code companies}.
     * {@code companies} must not contain duplicate companies.
     */
    public void setCompanies(List<Client> companies) {
        this.companies.setClients(companies);
    }

    /**
     * Resets the existing data of this {@code JeeqTracker} with {@code newData}.
     */
    public void resetData(ReadOnlyJeeqTracker newData) {
        requireNonNull(newData);

        setCompanies(newData.getCompanyList());
    }

    //// company-level operations

    /**
     * Returns true if a company with the same identity as {@code company} exists in the address book.
     */
    public boolean hasCompany(Client client) {
        requireNonNull(client);
        return companies.contains(client);
    }

    /**
     * Adds a company to the address book.
     * The company must not already exist in the address book.
     */
    public void addCompany(Client p) {
        companies.add(p);
    }

    /**
     * Replaces the given company {@code target} in the list with {@code editedCompany}.
     * {@code target} must exist in the address book.
     * The identity of {@code editedCompany} must not be the same as another existing company in the address book.
     */
    public void setCompany(Client target, Client editedClient) {
        requireNonNull(editedClient);

        companies.setClient(target, editedClient);
    }

    /**
     * Removes {@code key} from this {@code JeeqTracker}.
     * {@code key} must exist in the address book.
     */
    public void removeCompany(Client key) {
        companies.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return companies.asUnmodifiableObservableList().size() + " companies";
        // TODO: refine later
    }

    @Override
    public ObservableList<Client> getCompanyList() {
        return companies.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JeeqTracker // instanceof handles nulls
                && companies.equals(((JeeqTracker) other).companies));
    }

    @Override
    public int hashCode() {
        return companies.hashCode();
    }
}
