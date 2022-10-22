package seedu.rc4hdb.model.resident.predicates;

import java.util.Collections;
import java.util.HashSet;
import java.util.function.Predicate;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentDescriptor;
import seedu.rc4hdb.model.resident.fields.Tag;

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
        assert resident != null : "Resident object is null";
        return descriptor.getName().map(name -> resident.getName().containsIgnoreCase(name)).orElse(false)
                || descriptor.getPhone().map(phone -> resident.getPhone().containsIgnoreCase(phone)).orElse(false)
                || descriptor.getEmail().map(email -> resident.getEmail().containsIgnoreCase(email)).orElse(false)
                || descriptor.getRoom().map(room -> resident.getRoom().containsIgnoreCase(room)).orElse(false)
                || descriptor.getGender().map(gender -> resident.getGender().containsIgnoreCase(gender)).orElse(false)
                || descriptor.getHouse().map(house -> resident.getHouse().containsIgnoreCase(house)).orElse(false)
                || descriptor.getMatricNumber().map(matric -> resident.getMatricNumber().containsIgnoreCase(matric))
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
