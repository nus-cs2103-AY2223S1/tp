package seedu.address.logic.commands.consultation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CONSULTATION1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CONSULTATION2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CONSULT1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CONSULT1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VENUE_CONSULT1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_CONSULT1_START;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_CONSULT1_END;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CONSULT1;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalConsultations.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.*;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.consultation.Consultation;
import seedu.address.testutil.EditConsultationDescriptorBuilder;
import seedu.address.testutil.ConsultationBuilder;
/**
 * Contains integration tests (interaction with the Model) and unit tests for EditConsultationCommand.
 */
public class EditConsultationCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Consultation editedConsultation = new ConsultationBuilder().build();
        EditConsultationCommand.EditConsultDescriptor descriptor = new EditConsultationDescriptorBuilder(editedConsultation).build();
        EditConsultationCommand editConsultationCommand = new EditConsultationCommand(INDEX_FIRST_CONSULTATION, descriptor);

        String expectedMessage = String.format(EditConsultationCommand.MESSAGE_EDIT_CONSULTATION_SUCCESS, editedConsultation);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setConsultation(model.getFilteredConsultationList().get(0), editedConsultation);

        assertCommandSuccess(editConsultationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastConsultation = Index.fromOneBased(model.getFilteredConsultationList().size());
        Consultation lastConsultation = model.getFilteredConsultationList().get(indexLastConsultation.getZeroBased());

        ConsultationBuilder ConsultationInList = new ConsultationBuilder(lastConsultation);
        Consultation editedConsultation = ConsultationInList.withName(VALID_NAME_CONSULT1)
                .withModule(VALID_MODULE_CONSULT1)
                .withVenue(VALID_VENUE_CONSULT1)
                .withTimeslot(VALID_TIMESLOT_CONSULT1_START, VALID_TIMESLOT_CONSULT1_END)
                .withDescription(VALID_DESCRIPTION_CONSULT1)
                .build();

        EditConsultationCommand.EditConsultDescriptor descriptor = new EditConsultationDescriptorBuilder().withName(VALID_NAME_CONSULT1)
                .withModuleCode(VALID_MODULE_CONSULT1)
                .withVenue(VALID_VENUE_CONSULT1)
                .withTimeSlot(VALID_TIMESLOT_CONSULT1_START, VALID_TIMESLOT_CONSULT1_END)
                .withDescription(VALID_DESCRIPTION_CONSULT1)
                .build();

        EditConsultationCommand editConsultationCommand = new EditConsultationCommand(indexLastConsultation, descriptor);

        String expectedMessage = String.format(EditConsultationCommand.MESSAGE_EDIT_CONSULTATION_SUCCESS, editedConsultation);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setConsultation(lastConsultation, editedConsultation);

        assertCommandSuccess(editConsultationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditConsultationCommand editConsultationCommand = new EditConsultationCommand(INDEX_FIRST_CONSULTATION,
                new EditConsultationCommand.EditConsultDescriptor());

        assertCommandFailure(editConsultationCommand, model, Messages.MESSAGE_UNCHANGED_FIELD);
    }

    @Test
    public void execute_duplicateConsultationUnfilteredList_failure() {
        Consultation firstConsultation = model.getFilteredConsultationList()
                .get(INDEX_FIRST_CONSULTATION.getZeroBased());
        EditConsultationCommand.EditConsultDescriptor descriptor =
                new EditConsultationDescriptorBuilder(firstConsultation).build();
        EditConsultationCommand editConsultationCommand =
                new EditConsultationCommand(INDEX_SECOND_CONSULTATION, descriptor);

        assertCommandFailure(editConsultationCommand, model, EditConsultationCommand.MESSAGE_DUPLICATE_CONSULTATION);
    }

    @Test
    public void execute_invalidConsultationIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredConsultationList().size() + 1);
        EditConsultationCommand.EditConsultDescriptor descriptor = new EditConsultationDescriptorBuilder()
                .withName(VALID_NAME_CONSULT1).build();
        EditConsultationCommand editConsultationCommand = new EditConsultationCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editConsultationCommand, model, Messages.MESSAGE_INVALID_CONSULTATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidConsultationModuleNotExist_failure() {
        Consultation firstConsultation = model.getFilteredConsultationList()
                .get(INDEX_FIRST_CONSULTATION.getZeroBased());
        EditConsultationCommand.EditConsultDescriptor descriptor =
                new EditConsultationDescriptorBuilder(firstConsultation).withModuleCode("CS1101S").build();
        EditConsultationCommand editConsultationCommand =
                new EditConsultationCommand(INDEX_FIRST_CONSULTATION, descriptor);

        assertCommandFailure(editConsultationCommand, model, EditConsultationCommand.MESSAGE_NON_EXISTING_MODULE);
    }

    @Test
    public void equals() {
        final EditConsultationCommand standardCommand =
                new EditConsultationCommand(INDEX_FIRST_CONSULTATION, DESC_CONSULTATION1);

        // same values -> returns true
        EditConsultationCommand.EditConsultDescriptor copyDescriptor =
                new EditConsultationCommand.EditConsultDescriptor(DESC_CONSULTATION1);
        EditConsultationCommand commandWithSameValues =
                new EditConsultationCommand(INDEX_FIRST_CONSULTATION, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand("all")));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditConsultationCommand(INDEX_SECOND_CONSULTATION, DESC_CONSULTATION1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditConsultationCommand(INDEX_FIRST_CONSULTATION, DESC_CONSULTATION2)));
    }
}
