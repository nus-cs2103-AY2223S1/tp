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
        assert resident != null: "Resident object is null";
        return descriptor.getName().map(name -> resident.getName().contains(name)).orElse(true)
                && descriptor.getPhone().map(phone -> resident.getPhone().contains(phone)).orElse(true)
                && descriptor.getEmail().map(email -> resident.getEmail().contains(email)).orElse(true)
                && descriptor.getRoom().map(room -> resident.getRoom().contains(room)).orElse(true)
                && descriptor.getGender().map(gender -> resident.getGender().contains(gender)).orElse(true)
                && descriptor.getHouse().map(house -> resident.getHouse().contains(house)).orElse(true)
                && descriptor.getMatricNumber().map(matric -> resident.getMatricNumber().contains(matric))
                .orElse(true) && resident.getTags().containsAll(descriptor.getTags().orElse(resident.getTags()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttributesMatchAllKeywordsPredicate // instanceof handles nulls
                && descriptor.equals(((AttributesMatchAllKeywordsPredicate) other).descriptor)); // state check
    }

}

