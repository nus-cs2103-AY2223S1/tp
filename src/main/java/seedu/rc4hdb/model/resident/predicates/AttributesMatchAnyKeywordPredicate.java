package seedu.rc4hdb.model.resident.predicates;

import java.util.function.Predicate;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentDescriptor;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class AttributesMatchAnyKeywordPredicate implements Predicate<Resident> {
    private ResidentDescriptor descriptor;

    /**
     * Constructor to create predicate object for filter
     *
     * @param keywords the description to filter the residents with any specifier
     */
    public AttributesMatchAnyKeywordPredicate(ResidentDescriptor keywords) {
        this.descriptor = keywords;
    }

    @Override
    public boolean test(Resident resident) {
        return descriptor.getName().map(name -> name.equals(resident.getName())).orElse(false)
                || descriptor.getPhone().map(phone -> phone.equals(resident.getPhone())).orElse(false)
                || descriptor.getEmail().map(email -> email.equals(resident.getEmail())).orElse(false)
                || descriptor.getRoom().map(room -> room.equals(resident.getRoom())).orElse(false)
                || descriptor.getGender().map(gender -> gender.equals(resident.getGender())).orElse(false)
                || descriptor.getHouse().map(house -> house.equals(resident.getHouse())).orElse(false)
                || descriptor.getMatricNumber().map(matric -> matric.equals(resident.getMatricNumber())).orElse(false)
                || descriptor.getTags().map(tags -> tags.equals(resident.getTags())).orElse(false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttributesMatchAnyKeywordPredicate // instanceof handles nulls
                && descriptor.equals(((AttributesMatchAnyKeywordPredicate) other).descriptor)); // state check
    }
}
