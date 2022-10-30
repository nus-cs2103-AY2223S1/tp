package seedu.clinkedin.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.logic.parser.Prefix;
import seedu.clinkedin.model.tag.TagType;

public class EditTagTypeCommandTest {


    @Test
    public void constructor_nullPrefixNullTagType_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditTagTypeCommand(null, null,
                null, null));
    }

    @Test
    public void constructor_nullPrefix_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditTagTypeCommand(null, new TagType("abc",
                new Prefix("")), null, new TagType("def", new Prefix(""))));
    }

    @Test
    public void constructor_nullTagType_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditTagTypeCommand(new Prefix(""), null,
                new Prefix(""), null));
    }

    @Test
    public void constructor_nullToEdit_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditTagTypeCommand(null, null,
                new Prefix(""), new TagType("abc", new Prefix(""))));
    }

    @Test
    public void constructor_nullEditTo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditTagTypeCommand(new Prefix(""),
                new TagType("abc", new Prefix("")), null, null));
    }

    @Test
    public void constructor_tagTypeIsEmptyString_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new EditTagTypeCommand(new Prefix(""), new TagType("",
                new Prefix("")), null, null));
    }

    @Test
    public void equals() {
        Prefix firstPrefix = new Prefix("abc/");
        Prefix secondPrefix = new Prefix("def/");
        TagType firstTagType = new TagType("abc", firstPrefix);
        TagType secondTagType = new TagType("def", secondPrefix);

        EditTagTypeCommand editFirstTagCommand = new EditTagTypeCommand(firstPrefix, firstTagType, firstPrefix,
                firstTagType);
        EditTagTypeCommand editSecondTagCommand = new EditTagTypeCommand(secondPrefix, secondTagType, secondPrefix,
                secondTagType);

        // same object -> returns true
        assertTrue(editFirstTagCommand.equals(editFirstTagCommand));

        // same values -> returns true
        EditTagTypeCommand editFirstTagCommandCopy = new EditTagTypeCommand(firstPrefix, firstTagType, firstPrefix,
                firstTagType);
        assertTrue(editFirstTagCommand.equals(editFirstTagCommandCopy));

        // different types -> returns false
        assertFalse(editFirstTagCommand.equals(1));

        // null -> returns false
        assertFalse(editFirstTagCommand.equals(null));

        // different add tag -> returns false
        assertFalse(editFirstTagCommand.equals(editSecondTagCommand));
    }

}
