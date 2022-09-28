package seedu.address.model.person;

import java.util.function.Predicate;

public class AlwaysTruePredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
        return true;
    }

}

