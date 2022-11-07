package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_INSURANCE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.InsuranceCommand.EditInsuranceDescriptor;
import seedu.address.testutil.EditInsuranceDescriptorBuilder;

public class EditInsuranceDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditInsuranceDescriptor descriptorWithSameValues = new EditInsuranceDescriptor(DESC_AMY_INSURANCE);
        assertTrue(DESC_AMY_INSURANCE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY_INSURANCE.equals(DESC_AMY_INSURANCE));

        // null -> returns false
        assertFalse(DESC_AMY_INSURANCE.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY_INSURANCE.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY_INSURANCE.equals(DESC_BOB_INSURANCE));

        // different health insurance -> returns false
        EditInsuranceDescriptor editedAmy = new EditInsuranceDescriptorBuilder(DESC_AMY_INSURANCE)
                .withHealthInsurance(false).build();
        assertFalse(DESC_AMY_INSURANCE.equals(editedAmy));

        // different disability insurance -> returns false
        editedAmy = new EditInsuranceDescriptorBuilder(DESC_AMY_INSURANCE).withDisabilityInsurance(false).build();
        assertFalse(DESC_AMY_INSURANCE.equals(editedAmy));

        // different critical illness insurance -> returns false
        editedAmy = new EditInsuranceDescriptorBuilder(DESC_AMY_INSURANCE).withCriticalIllnessInsurance(true).build();
        assertFalse(DESC_AMY_INSURANCE.equals(editedAmy));

        // different life insurance -> returns false
        editedAmy = new EditInsuranceDescriptorBuilder(DESC_AMY_INSURANCE).withLifeInsurance(true).build();
        assertFalse(DESC_AMY_INSURANCE.equals(editedAmy));;
    }
}
