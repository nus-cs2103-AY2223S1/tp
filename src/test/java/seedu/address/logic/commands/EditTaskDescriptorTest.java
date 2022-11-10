package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.TEST_CATEGORY_FRONTEND;
import static seedu.address.logic.commands.CommandTestUtil.TEST_DEADLINE_TOMORROW;
import static seedu.address.logic.commands.CommandTestUtil.TEST_DESCRIPTION_TWO;
import static seedu.address.logic.commands.CommandTestUtil.TEST_PRIORITY_LOW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Email;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskCommand.EditTaskDescriptor descriptorWithSameValues = new EditTaskCommand.EditTaskDescriptor(DESC_ONE);
        assertTrue(DESC_ONE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ONE.equals(DESC_ONE));

        // null -> returns false
        assertFalse(DESC_ONE.equals(null));

        // different types -> returns false
        assertFalse(DESC_ONE.equals(5));

        // different values -> returns false
        assertFalse(DESC_ONE.equals(DESC_TWO));

        // different name -> returns false
        EditTaskCommand.EditTaskDescriptor editedOne =
                new EditTaskDescriptorBuilder(DESC_ONE).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_ONE.equals(editedOne));

        // different description -> returns false
        editedOne = new EditTaskDescriptorBuilder(DESC_ONE).withDescription(TEST_DESCRIPTION_TWO).build();
        assertFalse(DESC_ONE.equals(editedOne));

        // different category -> returns false
        editedOne = new EditTaskDescriptorBuilder(DESC_ONE).withCategory(TEST_CATEGORY_FRONTEND).build();
        assertFalse(DESC_ONE.equals(editedOne));

        // different priority -> returns false
        editedOne = new EditTaskDescriptorBuilder(DESC_ONE).withPriority(TEST_PRIORITY_LOW).build();
        assertFalse(DESC_ONE.equals(editedOne));

        // different deadline -> returns false
        editedOne = new EditTaskDescriptorBuilder(DESC_ONE).withDeadline(TEST_DEADLINE_TOMORROW).build();
        assertFalse(DESC_ONE.equals(editedOne));

        // different is done -> returns false
        editedOne = new EditTaskDescriptorBuilder(DESC_ONE).withIsDone(true).build();
        assertFalse(DESC_ONE.equals(editedOne));

        // different person email -> returns false
        editedOne = new EditTaskDescriptorBuilder(DESC_ONE).withPersonEmail(new Email(VALID_EMAIL_BOB)).build();
        assertFalse(DESC_ONE.equals(editedOne));
    }
}
