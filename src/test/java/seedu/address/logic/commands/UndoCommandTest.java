package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientsHealthContact;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.BillBuilder;
import seedu.address.testutil.PatientBuilder;

public class UndoCommandTest {

    private final Model model = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());



    @Test
    public void execute_undoNothing_failure() {
        assertCommandFailure("Undo cannot be done as there was no previous action", new UndoCommand(), model);
    }

    @Test
    public void execute_undoAddPatient_success() {
        try {
            new AddPatientCommand(new PatientBuilder().build()).execute(model);
            new AddPatientCommand(new PatientBuilder().withName("Charlie").build()).execute(model);
            new AddPatientCommand(new PatientBuilder().build()).execute(expectedModel);
            new AddPatientCommand(new PatientBuilder().withName("Charlie").build()).execute(expectedModel);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        try {
            assertCommandSuccess(new UndoCommand(), model, new UndoCommand().execute(expectedModel), expectedModel);
            assertCommandSuccess(new UndoCommand(), model, new UndoCommand().execute(expectedModel), expectedModel);
            assertCommandFailure("Undo cannot be done as there was no previous action", new UndoCommand(), model);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void execute_undoAddAppointment_success() {
        try {
            new AddAppointmentCommand(new AppointmentBuilder().build()).execute(model);
            new AddAppointmentCommand(new AppointmentBuilder().withName("Charlie").build()).execute(model);
            new AddAppointmentCommand(new AppointmentBuilder().build()).execute(expectedModel);
            new AddAppointmentCommand(new AppointmentBuilder().withName("Charlie").build()).execute(expectedModel);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        try {
            assertCommandSuccess(new UndoCommand(), model, new UndoCommand().execute(expectedModel), expectedModel);
            assertCommandSuccess(new UndoCommand(), model, new UndoCommand().execute(expectedModel), expectedModel);
            assertCommandFailure("Undo cannot be done as there was no previous action", new UndoCommand(), model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void execute_undoAddBill_success() {
        try {
            new AddBillCommand(new BillBuilder().withAppointment(new AppointmentBuilder()
                    .build()).build()).execute(model);
            new AddBillCommand(new BillBuilder().withAppointment(new AppointmentBuilder()
                    .withName("Charlie").build()).build()).execute(model);
            new AddBillCommand(new BillBuilder().withAppointment(new AppointmentBuilder()
                    .build()).build()).execute(expectedModel);
            new AddBillCommand(new BillBuilder().withAppointment(new AppointmentBuilder()
                    .withName("Charlie").build()).build()).execute(expectedModel);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        try {
            assertCommandSuccess(new UndoCommand(), model, new UndoCommand().execute(expectedModel), expectedModel);
            assertCommandSuccess(new UndoCommand(), model, new UndoCommand().execute(expectedModel), expectedModel);
            assertCommandFailure("Undo cannot be done as there was no previous action", new UndoCommand(), model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void execute_undoSortBill_success() {
        try {
            new SortBillCommand("name", true).execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        try {
            assertCommandSuccess(new UndoCommand(), model, new UndoCommand().execute(expectedModel), expectedModel);
            assertCommandFailure("Undo cannot be done as there was no previous action", new UndoCommand(), model);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void execute_undoSortPatient_success() {
        try {
            new SortPatientCommand("name", true).execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        try {
            assertCommandSuccess(new UndoCommand(), model, new UndoCommand().execute(expectedModel), expectedModel);
            assertCommandFailure("Undo cannot be done as there was no previous action", new UndoCommand(), model);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void execute_undoSortAppointment_success() {
        try {
            new SortAppointmentCommand("name", true).execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        try {
            assertCommandSuccess(new UndoCommand(), model, new UndoCommand().execute(expectedModel), expectedModel);
            assertCommandFailure("Undo cannot be done as there was no previous action", new UndoCommand(), model);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void execute_undoListPatient_fail() {
        new ListCommand().execute(model);
        assertCommandFailure("Undo cannot be done as there was no previous action", new UndoCommand(), model);
    }


    @Test
    public void execute_undoAddThenList_success() {
        try {
            new AddPatientCommand(new PatientBuilder().build()).execute(model);
            new ListCommand().execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        try {
            assertCommandSuccess(new UndoCommand(), model, new UndoCommand().execute(expectedModel), expectedModel);
            assertCommandFailure("Undo cannot be done as there was no previous action", new UndoCommand(), model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

    }

}
