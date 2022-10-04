package seedu.address.model.person;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.FilterCommand;

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
        if(!isNull(descriptor.getName())) {
            predicate = predicate && descriptor.getName().equals(person.getName());
        }
        if(!isNull(descriptor.getAddress())) {
            predicate = predicate && person.getAddress().equals(descriptor.getAddress());
        }
        if(!isNull(descriptor.getPhone())) {
            predicate = predicate && person.getPhone().equals(descriptor.getPhone());
        }
        if(!isNull(descriptor.getEmail())) {
            predicate = predicate && person.getEmail().equals(descriptor.getEmail());
        }
        if(!isNull(descriptor.getTags())) {
            predicate = predicate && person.getTags().equals(descriptor.getTags());
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
