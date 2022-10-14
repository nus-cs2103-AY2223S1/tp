package seedu.address.model.person;

import seedu.address.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} is contained inside the current list of hidden persons.
 */
public class HiddenPersonPredicateSingleton implements Predicate<Person> {
    private static HiddenPersonPredicateSingleton instance = new HiddenPersonPredicateSingleton();
    public static List<Person> hiddenPersons = new ArrayList<>();
    public static Predicate<Person> currentPredicate = Model.PREDICATE_SHOW_ALL_PERSONS;
    private HiddenPersonPredicateSingleton(){}

    @Override
    public boolean test(Person person) {
        if (hiddenPersons.contains(person)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HiddenPersonPredicateSingleton // instanceof handles nulls
                && hiddenPersons.equals(((HiddenPersonPredicateSingleton) other).hiddenPersons)); // state check
    }

    public static void addToHiddenList(Person p) {
        hiddenPersons.add(p);
    }

    public static HiddenPersonPredicateSingleton getInstance() {
        return instance;
    }

    public static Predicate<Person> combineWithHiddenPredicate(Predicate<Person> p) {
        currentPredicate = currentPredicate.and(Predicate.not(p));
        return currentPredicate;
    }

    public static void clearHiddenList() {
        hiddenPersons.clear();
    }

}
