package seedu.address.model.client;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Tests that a {@code Client} is within a list of clients.
 */
public class ClientInListPredicate implements Predicate<Client> {
    private final List<String> clientNames;

    public ClientInListPredicate(List<Client> clients) {
        clientNames = clients.stream().map(client -> client.getName().toString()).collect(Collectors.toList());
    }

    public void addToList(Client client) {
        clientNames.add(client.getName().toString());
    }

    @Override
    public boolean test(Client client) {
        return clientNames.contains(client.getName().toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientInListPredicate // instanceof handles nulls
                && clientNames.equals(((ClientInListPredicate) other).clientNames)); // state check
    }
}
