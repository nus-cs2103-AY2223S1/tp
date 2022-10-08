package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.*;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AttendanceCommandTest {

    private static final String ATTENDANCE_STUB = "2022-01-07";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addAttendanceUnfilteredList_success() {
        Person thirdPerson = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(thirdPerson).withAttendance(ATTENDANCE_STUB).build();

        AttendanceCommand attendanceCommand = new AttendanceCommand(INDEX_THIRD_PERSON,
                new Attendance(ATTENDANCE_STUB));

        String expectedMessage = String.format(AttendanceCommand.MESSAGE_ADD_ATTENDANCE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(thirdPerson, editedPerson);

        assertCommandSuccess(attendanceCommand, model, expectedMessage, expectedModel);

    }
}
