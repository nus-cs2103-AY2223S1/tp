package seedu.condonery.model.client;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.condonery.model.client.exceptions.UniqueClientNotFoundException;

/**
 * Wraps all data at the Condonery level
 * Duplicates are not allowed (by .isSameClient comparison)
 */
public class ClientDirectory implements ReadOnlyClientDirectory {

    private final UniqueClientList clients;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        clients = new UniqueClientList();
    }

    public ClientDirectory() {}

    /**
     * Creates an ClientDirectory using the Clients in the {@code toBeCopied}
     */
    public ClientDirectory(ReadOnlyClientDirectory toBeCopied, Path imageDirectoryPath) {
        this();
        resetData(toBeCopied);

        for (Client client : this.clients) {
            client.setImageDirectoryPath(imageDirectoryPath);
        }
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
     * Resets the existing data of this {@code ClientDirectory} with {@code newData}.
     */
    public void resetData(ReadOnlyClientDirectory newData) {
        requireNonNull(newData);

        setClients(newData.getClientList());
    }

    //// client-level operations

    /**
     * Returns true if a client with the same identity as {@code client} exists in the address book.
     */
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return clients.contains(client);
    }

    /**
     * Adds a client to the address book.
     * The client must not already exist in the address book.
     */
    public void addClient(Client p) {
        clients.add(p);
    }

    /**
     * Replaces the given client {@code target} in the list with {@code editedClient}.
     * {@code target} must exist in the address book.
     * The client identity of {@code editedClient} must not be the same
     * as another existing client in the address book.
     */
    public void setClient(Client target, Client editedClient) {
        requireNonNull(editedClient);

        clients.setClient(target, editedClient);
    }

    /**
     * Removes {@code key} from this {@code ClientDirectory}.
     * {@code key} must exist in the address book.
     */
    public void removeClient(Client key) {
        clients.remove(key);
    }

    //// util methods

    /**
     * Returns true if a client whos name contains the given String exists in the client directory.
     */
    public boolean hasClientName(String substring) {
        return clients.hasClientName(substring);
    }

    /**
     * Returns true if only one unique client whos name contains the given String exists in the client directory.
     */
    public boolean hasUniqueClientName(String substring) {
        return clients.hasUniqueClientName(substring);
    }

    /**
     * Returns a unique client whos name contains the given string.
     *
     * @throws UniqueClientNotFoundException if the substring does not match to a unique
     *                                 client.
     */
    public Client getUniqueClientByName(String substring) throws UniqueClientNotFoundException {
        return clients.getUniqueClientByName(substring);
    }

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
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientDirectory // instanceof handles nulls
                && clients.equals(((ClientDirectory) other).clients));
    }

    @Override
    public int hashCode() {
        return clients.hashCode();
    }
}
