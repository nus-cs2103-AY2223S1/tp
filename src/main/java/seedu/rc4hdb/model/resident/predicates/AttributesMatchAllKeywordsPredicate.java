package seedu.rc4hdb.model.resident.predicates;

import java.util.function.Predicate;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentDescriptor;

/**
 * Tests that a {@code Person}'s {@code Name} matches all of the keywords given.
 */
public class AttributesMatchAllKeywordsPredicate implements Predicate<Resident> {
    private final ResidentDescriptor descriptor;

    /**
     * Constructor to create predicate object for filter with an all specifier.
     * @param keywords the description to filter the residents with
     */
    public AttributesMatchAllKeywordsPredicate(ResidentDescriptor keywords) {
        this.descriptor = keywords;
    }

    @Override
    public boolean test(Resident resident) {
        assert resident != null : "Resident object is null";
        return descriptor.getName().map(name -> resident.getName().containsIgnoreCase(name)).orElse(true)
                && descriptor.getPhone().map(phone -> resident.getPhone().containsIgnoreCase(phone)).orElse(true)
                && descriptor.getEmail().map(email -> resident.getEmail().containsIgnoreCase(email)).orElse(true)
                && descriptor.getRoom().map(room -> resident.getRoom().containsIgnoreCase(room)).orElse(true)
                && descriptor.getGender().map(gender -> resident.getGender().containsIgnoreCase(gender)).orElse(true)
                && descriptor.getHouse().map(house -> resident.getHouse().containsIgnoreCase(house)).orElse(true)
                && descriptor.getMatricNumber().map(matric -> resident.getMatricNumber().containsIgnoreCase(matric))
                .orElse(true) && resident.getTags().containsAll(descriptor.getTags().orElse(resident.getTags()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttributesMatchAllKeywordsPredicate // instanceof handles nulls
                && descriptor.equals(((AttributesMatchAllKeywordsPredicate) other).descriptor)); // state check
    }

}

