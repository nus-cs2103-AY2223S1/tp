package seedu.uninurse.logic.commands;

import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.testutil.TestUtil.getCurrentDate;
import static seedu.uninurse.testutil.TypicalPatients.getTypicalUninurseBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.UserPrefs;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.DateTime;
import seedu.uninurse.model.task.NonRecurringTask;
import seedu.uninurse.testutil.PatientBuilder;

class PatientsTodayCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getUninurseBook(), new UserPrefs());
    }

    @Test
    public void execute_noPatientsToday_showsNoPatients() {
        assertCommandSuccess(new PatientsTodayCommand(), model, PatientsTodayCommand.MESSAGE_FAILURE,
                CommandType.EMPTY, expectedModel);
    }

    @Test
    public void execute_onePatientToday_showsOnePatient() throws Exception {
        Patient patientForToday = new PatientBuilder()
                                        .withTasks(new NonRecurringTask("test",
                                                new DateTime(getCurrentDate()))).build();

        CommandResult addCommand = new AddPatientCommand(patientForToday).execute(model);
        CommandResult addCommand2 = new AddPatientCommand(patientForToday).execute(expectedModel);
        expectedModel.updateFilteredPatientList(Model.PREDICATE_SHOW_PATIENTS_FOR_TODAY);
        assertCommandSuccess(new PatientsTodayCommand(), model, PatientsTodayCommand.MESSAGE_SUCCESS,
                PatientsTodayCommand.COMMAND_TYPE, expectedModel);
    }
}
