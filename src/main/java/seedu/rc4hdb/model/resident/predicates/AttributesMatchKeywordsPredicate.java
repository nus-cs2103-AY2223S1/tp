package seedu.rc4hdb.model.resident.predicates;

import static java.util.Objects.isNull;

import java.util.function.Predicate;

import seedu.rc4hdb.logic.commands.modelcommands.FilterCommand;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentDescriptor;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class AttributesMatchKeywordsPredicate implements Predicate<Resident> {
    private final ResidentDescriptor descriptor;

    public AttributesMatchKeywordsPredicate(ResidentDescriptor keywords) {
        this.descriptor = keywords;
    }

    @Override
    public boolean test(Resident resident) {
        boolean predicate = true;
        if (!isNull(descriptor.getName())) {
            predicate = predicate && descriptor.getName().equals(resident.getName());
        }
        if (!isNull(descriptor.getRoom())) {
            predicate = predicate && resident.getRoom().equals(descriptor.getRoom());
        }
        if (!isNull(descriptor.getPhone())) {
            predicate = predicate && resident.getPhone().equals(descriptor.getPhone());
        }
        if (!isNull(descriptor.getEmail())) {
            predicate = predicate && resident.getEmail().equals(descriptor.getEmail());
        }
        if (!isNull(descriptor.getGender())) {
            predicate = predicate && resident.getGender().equals(descriptor.getGender());
        }
        if (!isNull(descriptor.getHouse())) {
            predicate = predicate && resident.getHouse().equals(descriptor.getHouse());
        }
        if (!isNull(descriptor.getMatricNumber())) {
            predicate = predicate && resident.getMatricNumber().equals(descriptor.getMatricNumber());
        }
        if (!isNull(descriptor.getTags())) {
            predicate = predicate && resident.getTags().equals(descriptor.getTags());
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
