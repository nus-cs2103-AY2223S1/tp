package longtimenosee.model.person.predicate;

import java.util.function.Predicate;

import longtimenosee.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Address} contains the input given.
 */
public class AddressContainsInputPredicate implements Predicate<Person> {
    private final String address;

    /**
     * Constructs a AddressMatchesInputPredicate object, which consists of an address input.
     *
     * @param address is the input by the user to be compared.
     */
    public AddressContainsInputPredicate(String address) {
        assert address.length() != 0;
        this.address = address;
    }

    @Override
    public boolean test(Person person) {
        return person.getAddress().value.toLowerCase().contains(address.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof AddressContainsInputPredicate) {
                return address.equals(((AddressContainsInputPredicate) other).address);
            } else {
                return false;
            }
        }
    }
}
