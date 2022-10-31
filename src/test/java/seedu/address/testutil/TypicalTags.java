package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.tag.Tag;

/**
 * A utility class containing a list of {@code Tag} objects to be used in tests.
 */
public class TypicalTags {

    public static final Tag BIOLOGY_PROJECT = new TagBuilder()
            .withName("biology")
            .withCount(2)
            .build();
    public static final Tag FAMILY_MEMBER = new TagBuilder()
            .withName("family")
            .withCount(5)
            .build();

    private TypicalTags() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Tag task : getTypicalTags()) {
            ab.addTag(task);
        }
        return ab;
    }

    public static List<Tag> getTypicalTags() {
        return new ArrayList<>(Arrays.asList(BIOLOGY_PROJECT, FAMILY_MEMBER));
    }
}
