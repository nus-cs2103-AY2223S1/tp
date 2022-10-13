package seedu.foodrem.testutil;

import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_TAG_NAME_FRUITS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_TAG_NAME_FRUITS_WITH_WHITESPACES;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_TAG_NAME_NUMBERS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_TAG_NAME_VEGETABLES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.foodrem.model.tag.Tag;

/**
 * A utility class containing a list of {@code tag} objects to be used in tests.
 */
public class TypicalTags {
    public static final Tag FRUITS = new TagBuilder().withTagName(VALID_TAG_NAME_FRUITS).build();
    public static final Tag FRUITS_WITH_WHITESPACE = new TagBuilder()
            .withTagName(VALID_TAG_NAME_FRUITS_WITH_WHITESPACES).build();
    public static final Tag NUMBERS = new TagBuilder().withTagName(VALID_TAG_NAME_NUMBERS).build();
    public static final Tag VEGETABLES = new TagBuilder().withTagName(VALID_TAG_NAME_VEGETABLES).build();

    private TypicalTags() {
    } // prevents instantiation

    public static List<Tag> getTypicalTags() {
        return new ArrayList<>(Arrays.asList(FRUITS, FRUITS_WITH_WHITESPACE, NUMBERS, VEGETABLES));
    }
}
