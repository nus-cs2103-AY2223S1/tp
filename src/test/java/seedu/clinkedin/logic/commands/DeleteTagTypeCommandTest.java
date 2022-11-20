package seedu.clinkedin.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.logic.parser.Prefix;
import seedu.clinkedin.model.tag.TagType;

public class DeleteTagTypeCommandTest {


    @Test
    public void constructor_nullPrefixNullTagType_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTagTypeCommand(null));
    }

    @Test
    public void equals() {
        Prefix firstPrefix = new Prefix("abc/");
        Prefix secondPrefix = new Prefix("def/");
        TagType firstTagType = new TagType("abc", firstPrefix);
        TagType secondTagType = new TagType("def", secondPrefix);

        DeleteTagTypeCommand deleteFirstTagTypeCommand = new DeleteTagTypeCommand(firstTagType);
        DeleteTagTypeCommand deleteSecondTagTypeCommand = new DeleteTagTypeCommand(secondTagType);

        // same object -> returns true
        assertTrue(deleteFirstTagTypeCommand.equals(deleteFirstTagTypeCommand));

        // same values -> returns true
        DeleteTagTypeCommand deleteFirstTagTypeCommandCopy = new DeleteTagTypeCommand(firstTagType);

        assertTrue(deleteFirstTagTypeCommand.equals(deleteFirstTagTypeCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstTagTypeCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstTagTypeCommand.equals(null));

        // different add tag -> returns false
        assertFalse(deleteFirstTagTypeCommand.equals(deleteSecondTagTypeCommand));
    }


}
