package seedu.rc4hdb.model.resident.predicates;

import java.util.function.Predicate;

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
        return descriptor.getName().map(name -> name.equals(resident.getName())).orElse(true)
                && descriptor.getPhone().map(phone -> phone.equals(resident.getPhone())).orElse(true)
                && descriptor.getEmail().map(email -> email.equals(resident.getEmail())).orElse(true)
                && descriptor.getRoom().map(room -> room.equals(resident.getRoom())).orElse(true)
                && descriptor.getGender().map(gender -> gender.equals(resident.getGender())).orElse(true)
                && descriptor.getHouse().map(house -> house.equals(resident.getHouse())).orElse(true)
                && descriptor.getMatricNumber().map(matric -> matric.equals(resident.getMatricNumber())).orElse(true)
                && descriptor.getTags().map(tags -> tags.equals(resident.getTags())).orElse(true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttributesMatchKeywordsPredicate // instanceof handles nulls
                && descriptor.equals(((AttributesMatchKeywordsPredicate) other).descriptor)); // state check
    }

}
