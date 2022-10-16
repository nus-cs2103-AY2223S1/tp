package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Next Of Kin Data} matches any of the keywords given.
 */
public class NextOfKinPredicate implements Predicate<Person> {
    private final List<String> patientName;

    public NextOfKinPredicate(List<String> patientName) {
        this.patientName = patientName;
    }

    @Override
    public boolean test(Person person) {
        return patientName.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NextOfKinPredicate// instanceof handles nulls
                && patientName.equals(((NextOfKinPredicate) other).patientName)); // state check
    }
}
