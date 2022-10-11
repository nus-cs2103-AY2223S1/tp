package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Phone} number matches the given number.
 */
public class PhoneMatchesNumberPredicate implements Predicate<Person> {
    private final String number;

    public PhoneMatchesNumberPredicate(String number) {
        this.number = number;
    }

    @Override
    public boolean test(Person person) {
        return person.getPhone().value.equals(number);
    }
}
