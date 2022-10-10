package seedu.rc4hdb.model.person;

import static java.util.Objects.isNull;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.rc4hdb.logic.commands.modelcommands.FilterCommand;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class AttributesMatchKeywordsPredicate implements Predicate<Person> {
    private final FilterCommand.FilterPersonDescriptor descriptor;

    public AttributesMatchKeywordsPredicate(FilterCommand.FilterPersonDescriptor keywords) {
        this.descriptor = keywords;
    }

    @Override
    public boolean test(Person person) {
        boolean predicate = true;
        if (!descriptor.getName().isEmpty()) {
            predicate = predicate && descriptor.getName().equals(Optional.of(person.getName()));
        }
        if (!descriptor.getAddress().isEmpty()) {
            predicate = predicate && descriptor.getAddress().equals(Optional.of(person.getAddress()));
        }
        if (!descriptor.getPhone().isEmpty()) {
            predicate = predicate && descriptor.getPhone().equals(Optional.of(person.getPhone()));
        }
        if (!descriptor.getEmail().isEmpty()) {
            predicate = predicate && descriptor.getEmail().equals(Optional.of(person.getEmail()));
        }
        if (!descriptor.getTags().isEmpty()) {
            predicate = predicate && descriptor.getTags().equals(Optional.of(person.getTags()));
        }
        return predicate;

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttributesMatchKeywordsPredicate // instanceof handles nulls
                && descriptor.equals(((AttributesMatchKeywordsPredicate) other).descriptor)); // state check
    }

}
