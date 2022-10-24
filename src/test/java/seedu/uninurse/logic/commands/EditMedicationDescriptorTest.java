package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_MED_AMOXICILLIN;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_MED_DOSAGE_AMOXICILLIN;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_MED_TYPE_AMOXICILLIN;

import org.junit.jupiter.api.Test;

import seedu.uninurse.logic.commands.EditMedicationCommand.EditMedicationDescriptor;

public class EditMedicationDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditMedicationDescriptor descriptorWithSameValues = new EditMedicationDescriptor(
                DESC_MED_AMOXICILLIN.getType(), DESC_MED_AMOXICILLIN.getDosage());
        assertEquals(DESC_MED_AMOXICILLIN, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_MED_AMOXICILLIN, DESC_MED_AMOXICILLIN);

        // null -> returns false
        assertNotEquals(null, DESC_MED_AMOXICILLIN);

        // different types -> returns false
        assertNotEquals(5, DESC_MED_AMOXICILLIN);

        // different values -> returns false
        assertNotEquals(DESC_MED_TYPE_AMOXICILLIN, DESC_MED_AMOXICILLIN);

        // different medication type, same medication dosage -> returns false
        assertNotEquals(DESC_MED_AMOXICILLIN, DESC_MED_DOSAGE_AMOXICILLIN);

        // different medication dosage, same medication type -> returns false
        assertNotEquals(DESC_MED_AMOXICILLIN, DESC_MED_TYPE_AMOXICILLIN);

        // different medication dosage, different medication type -> returns false
        assertNotEquals(DESC_MED_DOSAGE_AMOXICILLIN, DESC_MED_TYPE_AMOXICILLIN);
    }
}
