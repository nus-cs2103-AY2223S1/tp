package seedu.rc4hdb.model.resident.predicates;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentStringDescriptor;
import seedu.rc4hdb.model.tag.Tag;

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
        return descriptor.getName().map(name -> resident.getName().contains(name)).orElse(false)
                || descriptor.getPhone().map(phone -> resident.getPhone().contains(phone)).orElse(false)
                || descriptor.getEmail().map(email -> resident.getEmail().contains(email)).orElse(false)
                || descriptor.getRoom().map(room -> resident.getRoom().contains(room)).orElse(false)
                || descriptor.getGender().map(gender -> resident.getGender().contains(gender)).orElse(false)
                || descriptor.getHouse().map(house -> resident.getHouse().contains(house)).orElse(false)
                || descriptor.getMatricNumber().map(matric -> resident.getMatricNumber().contains(matric))
                .orElse(false)
                || !Collections.disjoint(resident.getTags(), makeTag(descriptor.getTags().orElse(new HashSet<>())));
    }

    /**
     * Converts a set of Strings to a set of tags
     * @param tags the set of strings
     * @return a set of tags
     */
    public Set<Tag> makeTag(Set<String> tags) {
        HashSet<Tag> newTags = new HashSet<>();
        for (String tag : tags) {
            Tag newTag = new Tag(tag);
            newTags.add(newTag);
        }
        return newTags;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttributesMatchAnyKeywordPredicate // instanceof handles nulls
                && descriptor.equals(((AttributesMatchAnyKeywordPredicate) other).descriptor)); // state check
    }
}
