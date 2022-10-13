package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.TypicalStudents.getTypicalStudents;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.student.StudentListCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;




/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class StudentListCommandTest {

    private Model model;
    private Model expectedModel;
    private List<Student> students;
    private String studentString;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        students = getTypicalStudents();
        for (int i = 0; i < students.size(); i++) {
            studentString += students.get(i) + "\n";
        }
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new StudentListCommand(), model, StudentListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new StudentListCommand(), model, StudentListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
