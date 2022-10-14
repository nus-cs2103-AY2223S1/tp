package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class HiddenPersonPredicateSingleton implements Predicate<Person> {
    private static HiddenPersonPredicateSingleton instance = new HiddenPersonPredicateSingleton();
    public static List<Person> hiddenPersons = new ArrayList<>();

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
        return p.and(Predicate.not(instance));
    }

    public static void clearHiddenList() {
        hiddenPersons.clear();
    }
}
