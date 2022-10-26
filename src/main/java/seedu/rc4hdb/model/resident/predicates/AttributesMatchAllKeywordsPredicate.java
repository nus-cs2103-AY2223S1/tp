package seedu.rc4hdb.model.resident.predicates;

import java.util.function.Predicate;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentStringDescriptor;
import seedu.rc4hdb.model.resident.fields.Tag;

/**
 * Tests that a {@code Resident}'s fields matches all the keywords given.
 */
public class AttributesMatchAllKeywordsPredicate implements Predicate<Resident> {
    private final ResidentStringDescriptor descriptor;

    /**
     * Constructor to create predicate object for filter with an all specifier.
     * @param keywords the description to filter the residents with
     */
    public AttributesMatchAllKeywordsPredicate(ResidentStringDescriptor keywords) {
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
                .orElse(true)
                && resident.getTags().stream().allMatch(this::allTagMatch);
    }

    /**
     * Returns true if {@code tag} matches with the all the tags in {@code descriptor}.
     */
    private boolean allTagMatch(Tag tag) {
        if (descriptor.getTags().isEmpty()) {
            return false;
        }
        return descriptor.getTags().get().stream()
                .allMatch(tag::containsIgnoreCase);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttributesMatchAllKeywordsPredicate // instanceof handles nulls
                && descriptor.equals(((AttributesMatchAllKeywordsPredicate) other).descriptor)); // state check
    }

}

