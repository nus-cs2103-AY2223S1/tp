package seedu.address.model.meeting;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.client.Client;

/**
 * Tests that a {@code Meeting}'s {@code Client} matches the Client given.
 */
public class MeetingContainsClientPredicate implements Predicate<Meeting> {
    private final List<Client> clients;

    public MeetingContainsClientPredicate(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean test(Meeting meeting) {
        return clients.stream()
                .anyMatch(client -> client.equals(meeting.getClient()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MeetingContainsClientPredicate
                && clients.equals(((MeetingContainsClientPredicate) other).clients));
    }
}
