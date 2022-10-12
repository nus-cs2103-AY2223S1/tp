package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DINNER;
import static seedu.address.logic.commands.CommandTestUtil.LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMT_DINNER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_DINNER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_DINNER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_INCOME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditEntryDescriptor;
import seedu.address.testutil.EditEntryDescriptorBuilder;

public class EditEntryDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEntryDescriptor descriptorWithSameValues = new EditEntryDescriptor(LUNCH);
        assertTrue(LUNCH.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(LUNCH.equals(LUNCH));

        // null -> returns false
        assertFalse(LUNCH.equals(null));

        // different types -> returns false
        assertFalse(LUNCH.equals(5));

        // different values -> returns false
        assertFalse(LUNCH.equals(DINNER));

        // different description -> returns false
        EditEntryDescriptor editedLunch = new EditEntryDescriptorBuilder(LUNCH)
                .withDescription(VALID_DESC_DINNER).build();
        assertFalse(LUNCH.equals(editedLunch));

        // different type -> returns false
        editedLunch = new EditEntryDescriptorBuilder(LUNCH).withType(VALID_TYPE_INCOME).build();
        assertFalse(LUNCH.equals(editedLunch));

        // different amount -> returns false
        editedLunch = new EditEntryDescriptorBuilder(LUNCH).withAmount(VALID_AMT_DINNER).build();
        assertFalse(LUNCH.equals(editedLunch));

        // different date -> returns false
        editedLunch = new EditEntryDescriptorBuilder(LUNCH).withDate(VALID_DATE_DINNER).build();
        assertFalse(LUNCH.equals(editedLunch));

        // different tags -> returns false
        editedLunch = new EditEntryDescriptorBuilder(LUNCH).withTags(VALID_TAG_MEAL).build();
        assertFalse(LUNCH.equals(editedLunch));
    }
}
