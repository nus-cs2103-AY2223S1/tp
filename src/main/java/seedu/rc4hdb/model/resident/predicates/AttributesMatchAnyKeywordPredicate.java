package seedu.rc4hdb.model.resident.predicates;

import java.util.Collections;
import java.util.HashSet;
import java.util.function.Predicate;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentDescriptor;
import seedu.rc4hdb.model.tag.Tag;

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
        return descriptor.getName().map(name -> resident.getName().contains(name)).orElse(false)
                || descriptor.getPhone().map(phone -> resident.getPhone().contains(phone)).orElse(false)
                || descriptor.getEmail().map(email -> resident.getEmail().contains(email)).orElse(false)
                || descriptor.getRoom().map(room -> resident.getRoom().contains(room)).orElse(false)
                || descriptor.getGender().map(gender -> resident.getGender().contains(gender)).orElse(false)
                || descriptor.getHouse().map(house -> resident.getHouse().contains(house)).orElse(false)
                || descriptor.getMatricNumber().map(matric -> resident.getMatricNumber().contains(matric))
                .orElse(false)
                || !Collections.disjoint(resident.getTags(), descriptor.getTags().orElse(new HashSet<Tag>()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttributesMatchAnyKeywordPredicate // instanceof handles nulls
                && descriptor.equals(((AttributesMatchAnyKeywordPredicate) other).descriptor)); // state check
    }
}
