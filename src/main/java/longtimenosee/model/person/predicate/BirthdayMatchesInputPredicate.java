package longtimenosee.model.person.predicate;

import java.time.LocalDate;
import java.util.function.Predicate;

import longtimenosee.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Birthday} matches the input given.
 */
public class BirthdayMatchesInputPredicate implements Predicate<Person> {
    private final String birthday;

    /**
     * Constructs a BirthdayMatchesInputPredicate object, which consists of a birthday input.
     *
     * @param birthday is the input by the user to be compared.
     */
    public BirthdayMatchesInputPredicate(String birthday) {
        assert birthday.length() != 0;
        assert birthday.length() == 10;
        this.birthday = birthday;
    }

    @Override
    public boolean test(Person person) {
        LocalDate personBirthday = person.getBirthday().getBirthday();
        LocalDate inputDate = LocalDate.parse(birthday);
        return personBirthday.equals(inputDate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof BirthdayMatchesInputPredicate) {
                return birthday.equals(((BirthdayMatchesInputPredicate) other).birthday);
            } else {
                return false;
            }
        }
    }
}
