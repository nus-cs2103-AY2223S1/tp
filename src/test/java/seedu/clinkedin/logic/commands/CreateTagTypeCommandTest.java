package seedu.clinkedin.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.logic.parser.Prefix;
import seedu.clinkedin.model.tag.TagType;

public class CreateTagTypeCommandTest {


    @Test
    public void constructor_nullPrefixNullTagType_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateTagTypeCommand(null, null));
    }

    @Test
    public void constructor_nullPrefix_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateTagTypeCommand(new TagType("abc", null),
                null));
    }

    @Test
    public void constructor_nullTagType_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateTagTypeCommand(null, new Prefix("")));
    }


    @Test
    public void constructor_tagTypeIsEmptyString_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new CreateTagTypeCommand(new TagType("",
                new Prefix("")), null));
    }

    @Test
    public void equals() {
        Prefix firstPrefix = new Prefix("abc/");
        Prefix secondPrefix = new Prefix("def/");
        TagType firstTagType = new TagType("abc", firstPrefix);
        TagType secondTagType = new TagType("def", secondPrefix);

        CreateTagTypeCommand createFirstTagTypeCommand = new CreateTagTypeCommand(firstTagType, firstPrefix);
        CreateTagTypeCommand createSecondTagTypeCommand = new CreateTagTypeCommand(secondTagType, secondPrefix);

        // same object -> returns true
        assertTrue(createFirstTagTypeCommand.equals(createFirstTagTypeCommand));

        // same values -> returns true
        CreateTagTypeCommand createFirstTagTypeCommandCopy = new CreateTagTypeCommand(firstTagType, firstPrefix);
        assertTrue(createFirstTagTypeCommand.equals(createFirstTagTypeCommandCopy));

        // different types -> returns false
        assertFalse(createFirstTagTypeCommand.equals(1));

        // null -> returns false
        assertFalse(createFirstTagTypeCommand.equals(null));

        // different add tag -> returns false
        assertFalse(createFirstTagTypeCommand.equals(createSecondTagTypeCommand));
    }


}

