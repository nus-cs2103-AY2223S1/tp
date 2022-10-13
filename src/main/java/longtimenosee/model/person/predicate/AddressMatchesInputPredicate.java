package longtimenosee.model.person.predicate;

import java.util.function.Predicate;

import longtimenosee.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Address} matches the input given.
 */
public class AddressMatchesInputPredicate implements Predicate<Person> {
    private final String address;

    public AddressMatchesInputPredicate(String address) {
        this.address = address;
    }

    @Override
    public boolean test(Person person) {
        return person.getAddress().value.equals(address);
    }
}
