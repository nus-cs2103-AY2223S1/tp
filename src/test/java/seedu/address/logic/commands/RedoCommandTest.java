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

public class RedoCommandTest {
    private final Model model = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());


    @Test
    public void execute_redoNothing_failure() {
        assertCommandFailure(
                "Redo cannot be done as there is no previous change in data to be restored",
                new RedoCommand(), model);
    }

    @Test
    public void execute_redoAddPatient_success() {
        try {
            new AddPatientCommand(new PatientBuilder().build()).execute(model);
            new AddPatientCommand(new PatientBuilder().withName("Charlie").build()).execute(model);
            new AddPatientCommand(new PatientBuilder().build()).execute(expectedModel);
            new AddPatientCommand(new PatientBuilder().withName("Charlie").build()).execute(expectedModel);
            new UndoCommand().execute(model);
            new UndoCommand().execute(expectedModel);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        try {
            assertCommandSuccess(new RedoCommand(), model, new RedoCommand().execute(expectedModel), expectedModel);
            assertCommandSuccess(new RedoCommand(), model, new RedoCommand().execute(expectedModel), expectedModel);
            assertCommandFailure(
                    "Redo cannot be done as there is no previous change in data to be restored",
                    new RedoCommand(), model);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void execute_redoAddAppointment_success() {
        try {
            new AddAppointmentCommand(new AppointmentBuilder().build()).execute(model);
            new AddAppointmentCommand(new AppointmentBuilder().withName("Charlie").build()).execute(model);
            new AddAppointmentCommand(new AppointmentBuilder().build()).execute(expectedModel);
            new AddAppointmentCommand(new AppointmentBuilder().withName("Charlie").build()).execute(expectedModel);
            new UndoCommand().execute(model);
            new UndoCommand().execute(expectedModel);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        try {
            assertCommandSuccess(new RedoCommand(), model, new RedoCommand().execute(expectedModel), expectedModel);
            assertCommandSuccess(new RedoCommand(), model, new RedoCommand().execute(expectedModel), expectedModel);
            assertCommandFailure(
                    "Redo cannot be done as there is no previous change in data to be restored",
                    new RedoCommand(), model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void execute_redoAddBill_success() {
        try {
            new AddBillCommand(new BillBuilder().withAppointment(new AppointmentBuilder()
                    .build()).build()).execute(model);
            new AddBillCommand(new BillBuilder().withAppointment(new AppointmentBuilder()
                    .withName("Charlie").build()).build()).execute(model);
            new AddBillCommand(new BillBuilder().withAppointment(new AppointmentBuilder()
                    .build()).build()).execute(expectedModel);
            new AddBillCommand(new BillBuilder().withAppointment(new AppointmentBuilder()
                    .withName("Charlie").build()).build()).execute(expectedModel);
            new UndoCommand().execute(model);
            new UndoCommand().execute(expectedModel);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        try {
            assertCommandSuccess(new RedoCommand(), model, new RedoCommand().execute(expectedModel), expectedModel);
            assertCommandSuccess(new RedoCommand(), model, new RedoCommand().execute(expectedModel), expectedModel);
            assertCommandFailure(
                    "Redo cannot be done as there is no previous change in data to be restored",
                    new RedoCommand(), model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void execute_redoSortBill_success() {
        try {
            new SortBillCommand("name", true).execute(model);
            new UndoCommand().execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        try {
            assertCommandSuccess(new RedoCommand(), model, new RedoCommand().execute(expectedModel), expectedModel);
            assertCommandFailure(
                    "Redo cannot be done as there is no previous change in data to be restored",
                    new RedoCommand(), model);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void execute_redoSortPatient_success() {
        try {
            new SortPatientCommand("name", true).execute(model);
            new UndoCommand().execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        try {
            assertCommandSuccess(new RedoCommand(), model, new RedoCommand().execute(expectedModel), expectedModel);
            assertCommandFailure(
                    "Redo cannot be done as there is no previous change in data to be restored",
                    new RedoCommand(), model);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void execute_redoSortAppointment_success() {
        try {
            new SortAppointmentCommand("name", true).execute(model);
            new UndoCommand().execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        try {
            assertCommandSuccess(new RedoCommand(), model, new RedoCommand().execute(expectedModel), expectedModel);
            assertCommandFailure(
                    "Redo cannot be done as there is no previous change in data to be restored",
                    new RedoCommand(), model);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void execute_redoNoUndo_success() {
        try {
            new AddPatientCommand(new PatientBuilder().build()).execute(model);
            new ListCommand().execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }
        assertCommandFailure(
                "Redo cannot be done as there is no previous change in data to be restored",
                new RedoCommand(), model);
    }

}

