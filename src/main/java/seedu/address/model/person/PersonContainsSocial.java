package seedu.address.model.person;

import java.util.Locale;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Preferred Social} matches or contains the input.
 */
public class PersonContainsSocial implements Predicate<Person> {

    private final String social;

    /**
     * Constructor for PersonContainsSocial to set the given input to lowercase.
     *
     * @param input preferred social
     */
    public PersonContainsSocial(String input) {
        this.social = input.toLowerCase(Locale.ROOT);
    }

    @Override
    public boolean test(Person person) {
        String preferred = person.getSocial().getPreferred().toLowerCase(Locale.ROOT);
        return preferred.contains(social);
    }
}
