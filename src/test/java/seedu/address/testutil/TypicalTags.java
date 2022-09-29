package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * A utility class containing a set of {@code Tag} objects to be used in tests
 */
public class TypicalTags {
    public static final Tag VALID_TAG_FRIENDS = new Tag("friends");

    public static final Tag VALID_TAG_OWES_MONEY = new Tag("owesMoney");

    public static Set<Tag> getTypicalTags() {
        Set<Tag> tags = new HashSet<>();
        tags.add(VALID_TAG_FRIENDS);
        tags.add(VALID_TAG_OWES_MONEY);
        return tags;
    }
}
