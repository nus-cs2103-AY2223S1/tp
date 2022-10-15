package longtimenosee.model.person.predicate;

import java.time.LocalDate;
import java.util.function.Predicate;

import longtimenosee.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Birthday} matches the input given.
 */
public class BirthdayMatchesInputPredicate implements Predicate<Person> {
    private final String birthday;

    public BirthdayMatchesInputPredicate(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean test(Person person) {
        LocalDate personBirthday = person.getBirthday().getBirthday();
        LocalDate inputDate = LocalDate.parse(birthday);
        return personBirthday.equals(inputDate);
    }
}
