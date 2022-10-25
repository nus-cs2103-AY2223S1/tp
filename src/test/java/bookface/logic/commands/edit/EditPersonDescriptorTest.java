package bookface.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import bookface.logic.commands.CommandTestUtil;
import bookface.logic.commands.edit.EditUserCommand.EditPersonDescriptor;
import bookface.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(CommandTestUtil.DESC_AMY);
        assertEquals(CommandTestUtil.DESC_AMY, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(CommandTestUtil.DESC_AMY, CommandTestUtil.DESC_AMY);

        // null -> returns false
        assertNotEquals(null, CommandTestUtil.DESC_AMY);

        // different types -> returns false
        assertNotEquals(5, CommandTestUtil.DESC_AMY);

        // different values -> returns false
        assertNotEquals(CommandTestUtil.DESC_AMY, CommandTestUtil.DESC_BOB);

        // different name -> returns false
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withName(CommandTestUtil.VALID_NAME_BOB).build();
        assertNotEquals(CommandTestUtil.DESC_AMY, editedAmy);

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB).build();
        assertNotEquals(CommandTestUtil.DESC_AMY, editedAmy);

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withEmail(CommandTestUtil.VALID_EMAIL_BOB).build();
        assertNotEquals(CommandTestUtil.DESC_AMY, editedAmy);

        // different tags -> returns false
        editedAmy = new EditPersonDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        assertNotEquals(CommandTestUtil.DESC_AMY, editedAmy);
    }
}
