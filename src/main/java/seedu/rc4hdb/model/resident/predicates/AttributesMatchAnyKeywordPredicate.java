package seedu.rc4hdb.model.resident.predicates;

import java.util.function.Predicate;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentStringDescriptor;
import seedu.rc4hdb.model.resident.fields.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class AttributesMatchAnyKeywordPredicate implements Predicate<Resident> {
    private ResidentStringDescriptor descriptor;

    /**
     * Constructor to create predicate object for filter
     *
     * @param keywords the description to filter the residents with any specifier
     */
    public AttributesMatchAnyKeywordPredicate(ResidentStringDescriptor keywords) {
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
                || resident.getTags().stream().anyMatch(this::anyTagMatch);
    }

    /**
     * Returns true if there is any match between the {@code tag} and the tags in {@code descriptor}.
     */
    private boolean anyTagMatch(Tag tag) {
        if (descriptor.getTags().isEmpty()) {
            return false;
        }
        return descriptor.getTags().get().stream()
                .anyMatch(tag::containsIgnoreCase);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttributesMatchAnyKeywordPredicate // instanceof handles nulls
                && descriptor.equals(((AttributesMatchAnyKeywordPredicate) other).descriptor)); // state check
    }
}
