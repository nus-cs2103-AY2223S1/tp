package seedu.condonery.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.condonery.model.client.exceptions.ClientNotFoundException;
import seedu.condonery.model.client.exceptions.DuplicateClientException;
import seedu.condonery.model.client.exceptions.UniqueClientNotFoundException;

/**
 * A list of clients that enforces uniqueness between its elements and does not allow nulls.
 * A client is considered unique by comparing using {@code Client#isSameClient(Client)}.
 * As such, adding and updating of clients uses Client#isSameClient(Client) for equality
 * so as to ensure that the client being added or updated is unique in terms of identity in the
 * UniqueClientList. However, the removal of a client uses Client#equals(Object) so
 * as to ensure that the client with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Client#isSameClient(Client)
 */
public class UniqueClientList implements Iterable<Client> {

    private final ObservableList<Client> internalList = FXCollections.observableArrayList();
    private final ObservableList<Client> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent client as the given argument.
     */
    public boolean contains(Client toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameClient);
    }

    /**
     * Adds a client to the list.
     * The client must not already exist in the list.
     */
    public void add(Client toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateClientException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the client {@code target} in the list with {@code editedClient}.
     * {@code target} must exist in the list.
     * The client identity of {@code editedClient} must not be the same as another existing client in the list.
     */
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ClientNotFoundException();
        }

        if (!target.isSameClient(editedClient) && contains(editedClient)) {
            throw new DuplicateClientException();
        }

        internalList.set(index, editedClient);
    }

    /**
     * Removes the equivalent client from the list.
     * The client must exist in the list.
     */
    public void remove(Client toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ClientNotFoundException();
        }
    }

    public void setClients(UniqueClientList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setClients(List<Client> clients) {
        requireAllNonNull(clients);
        if (!clientsAreUnique(clients)) {
            throw new DuplicateClientException();
        }

        internalList.setAll(clients);
    }

    /**
     * Returns true if a client whos name contains the given String exists in the client directory.
     */
    public boolean hasClientName(String substring) {
        for (Client client : internalList) {
            if (client.getName().toString().contains(substring)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if only one unique client whos name contains the given String exists in the client directory.
     */
    public boolean hasUniqueClientName(String substring) {
        int containCount = 0;
        for (Client client : internalList) {
            if (client.getName().toString().contains(substring)) {
                containCount += 1;
            }
        }
        if (containCount == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a unique client whos name contains the given string.
     *
     * @throws UniqueClientNotFoundException if the substring does not match to a unique
     *                                 client.
     */
    public Client getUniqueClientByName(String substring) throws UniqueClientNotFoundException {
        Client uniqueClient = null;
        for (Client client : internalList) {
            if (client.getName().containsSubstring(substring)) {
                if (uniqueClient == null) {
                    uniqueClient = client;
                } else {
                    throw new UniqueClientNotFoundException();
                }
            }
        }
        if (uniqueClient == null) {
            throw new UniqueClientNotFoundException();
        } else {
            return uniqueClient;
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Client> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Client> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueClientList // instanceof handles nulls
                        && internalList.equals(((UniqueClientList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code clients} contains only unique clients.
     */
    private boolean clientsAreUnique(List<Client> clients) {
        for (int i = 0; i < clients.size() - 1; i++) {
            for (int j = i + 1; j < clients.size(); j++) {
                if (clients.get(i).isSameClient(clients.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
