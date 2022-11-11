package longtimenosee.model.person.predicate;

import java.util.function.Predicate;

import longtimenosee.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Phone} number contains the given number.
 */
public class PhoneContainsNumberPredicate implements Predicate<Person> {
    private final String number;

    /**
     * Constructs a PhoneMatchesNumberPredicate object, which consists of a number input.
     *
     * @param number is the input by the user to be compared.
     */
    public PhoneContainsNumberPredicate(String number) {
        assert number.length() >= 3;
        this.number = number;
    }

    @Override
    public boolean test(Person person) {
        return person.getPhone().value.contains(number);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof PhoneContainsNumberPredicate) {
                return number.equals(((PhoneContainsNumberPredicate) other).number);
            } else {
                return false;
            }
        }
    }
}
