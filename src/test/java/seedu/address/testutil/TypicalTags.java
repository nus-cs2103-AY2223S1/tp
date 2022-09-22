package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class TypicalTags {
    public static final Tag FRIENDS = new Tag("friends");

    public static final Tag OWES_MONEY = new Tag("owesMoney");

    public static Set<Tag> getTypicalTags() {
        Set<Tag> tags = new HashSet<>();
        tags.add(FRIENDS);
        tags.add(OWES_MONEY);
        return tags;
    }
}
