package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientRebuilder;
import seedu.address.model.client.UniqueClientList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.NoConflictMeetingList;
import seedu.address.model.meeting.exceptions.ConflictingMeetingException;
import seedu.address.model.product.Product;
import seedu.address.model.product.UniqueProductList;

/**
 * Wraps all data at the client-book level
 * Duplicates are not allowed (by .isSameClient comparison)
 */
public class MyInsuRec implements ReadOnlyMyInsuRec {

    private final UniqueClientList clients;
    private final NoConflictMeetingList meetings;
    private final UniqueProductList products;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        clients = new UniqueClientList();
        meetings = new NoConflictMeetingList();
        products = new UniqueProductList();
    }

    public MyInsuRec() {}

    /**
     * Creates a MyInsuRec using the Clients in the {@code toBeCopied}
     */
    public MyInsuRec(ReadOnlyMyInsuRec toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the client list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setClients(List<Client> clients) {
        this.clients.setClients(clients);
    }

    /**
     * Replaces the contents of the client list with {@code meetings}.
     * {@code meetings} must not contain meetings with timing conflicts.
     */
    public void setMeetings(List<Meeting> meetings) {
        this.meetings.setMeetings(meetings);
    }

    /**
     * Replaces the contents of the product list with {@code products}.
     * {@code products} must not contain duplicate products.
     */
    public void setProducts(List<Product> products) {
        this.products.setProducts(products);
    }

    /**
     * Resets the existing data of this {@code MyInsuRec} with {@code newData}.
     */
    public void resetData(ReadOnlyMyInsuRec newData) {
        requireNonNull(newData);

        setClients(newData.getClientList());
        setMeetings(newData.getMeetingList());
        setProducts(newData.getProductList());
    }

    //// client-level operations

    /**
     * Returns true if a client with the same identity as {@code client} exists in the client book.
     */
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return clients.contains(client);
    }

    /**
     * Adds a client to the client book.
     * The client must not already exist in the client book.
     */
    public void addClient(Client p) {
        clients.add(p);
    }

    /**
     * Replaces the given client {@code target} in the list with {@code editedClient}.
     * {@code target} must exist in the client book.
     * The client identity of {@code editedClient} must not be the same as another existing client in the client book.
     */
    public void setClient(Client target, Client editedClient) {
        requireNonNull(editedClient);

        clients.setClient(target, editedClient);
        target.getMeetings().forEach(meetings::remove);
        editedClient.getMeetings().forEach(meetings::add);
    }

    /**
     * Removes {@code client} from this {@code MyInsuRec}. This includes any associated meetings with the client.
     * {@code key} must exist in MyInsuRec.
     */
    public void removeClient(Client client) {
        clients.remove(client);
        client.getMeetings().forEach(meetingToRemove -> {
            if (meetings.containsSpecific(meetingToRemove)) {
                meetings.remove(meetingToRemove);
            }
        });
    }

    //// meeting-level operations

    /**
     * Adds a meeting to the meeting list.
     * There must not be any timing conflicts with any other meeting on the list.
     */
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    /**
     * Removes meeting.
     * The meeting must exist in MyInsuRec.
     * @param meeting
     */
    public void removeMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    /**
     * Returns true if a meeting with the same date and time as {@code meeting} exists in the meetings book.
     */
    public boolean hasMeeting(Meeting meeting) {
        return meetings.contains(meeting);
    }

    /**
     * Returns true if a meeting with the same date, time, and description
     * as {@code meeting} exists in the meetings book.
     * This is stronger version of hasMeeting.
     */
    public boolean hasSpecificMeeting(Meeting meeting) {
        return meetings.containsSpecific(meeting);
    }

    /**
     * Replaces the given meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in MyInsuRec.
     * The Meeting identity of {@code editedMeeting} must not be the same as
     * another existing Meeting in MyInsuRec.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) throws ConflictingMeetingException {
        requireNonNull(editedMeeting);

        meetings.setMeeting(target, editedMeeting);
    }

    //// product-level operations

    /**
     * Adds a product to the product list.
     * There must not be any naming conflicts with any other product on the list.
     */
    public void addProduct(Product product) {
        requireNonNull(product);
        products.add(product);
    }

    /**
     * Removes product from MyInsuRec. This includes any association of a client to the product.
     * The meeting must exist in the product list in MyInsuRec.
     */
    public void removeProduct(Product product) {
        requireNonNull(product);
        products.remove(product);
        clients.forEach(client -> {
            if (client.hasProduct(product)) {
                ClientRebuilder rb = new ClientRebuilder(client);
                rb = rb.removeProduct(product);
                Client editedClient = rb.build();
                clients.setClient(client, editedClient);
            }
        });
    }

    /**
     * Returns true if a product with the same identity as {@code product} exists in the products book.
     */
    public boolean hasProduct(Product product) {
        requireNonNull(product);
        return products.contains(product);
    }

    //// util methods

    @Override
    public String toString() {
        return clients.asUnmodifiableObservableList().size() + " clients";
        // TODO: refine later
    }

    @Override
    public ObservableList<Client> getClientList() {
        return clients.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Meeting> getMeetingList() {
        return meetings.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Product> getProductList() {
        return products.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MyInsuRec // instanceof handles nulls
                && clients.equals(((MyInsuRec) other).clients)
                && meetings.equals(((MyInsuRec) other).meetings)
                && products.equals(((MyInsuRec) other).products));
    }

    @Override
    public int hashCode() {
        return clients.hashCode();
    }
}
