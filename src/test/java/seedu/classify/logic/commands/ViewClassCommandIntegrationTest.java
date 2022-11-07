package seedu.classify.logic.commands;

import static seedu.classify.commons.core.Messages.MESSAGE_PERSONS_LISTED_IN_CLASS;
import static seedu.classify.commons.core.Messages.MESSAGE_SINGLE_PERSON_LISTED_IN_CLASS;
import static seedu.classify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.classify.testutil.TypicalStudents.ALICE;
import static seedu.classify.testutil.TypicalStudents.getTypicalStudentRecord;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.classify.model.Model;
import seedu.classify.model.ModelManager;
import seedu.classify.model.UserPrefs;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.ClassPredicate;
import seedu.classify.model.student.Student;
import seedu.classify.testutil.StudentBuilder;


/**
 * Contains integration tests (interaction with the Model) for {@code ViewClassCommand}.
 */
public class ViewClassCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalStudentRecord(), new UserPrefs());
    }

    @Test
    public void execute_viewClass_success() {

        Model expectedModel = new ModelManager(model.getStudentRecord(), new UserPrefs());

        ClassPredicate predicate = preparePredicate("XXX");
        expectedModel.updateFilteredStudentList(predicate);

        assertCommandSuccess(new ViewClassCommand(predicate), model,
                String.format(MESSAGE_PERSONS_LISTED_IN_CLASS, 0), expectedModel);
    }

    @Test
    public void execute_viewClassAfterAddStud_success() {
        //Initially there are no students with class "XXX"
        Model expectedModel = new ModelManager(model.getStudentRecord(), new UserPrefs());
        ClassPredicate predicate = preparePredicate("XXX");
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(new ViewClassCommand(predicate), model,
                String.format(MESSAGE_PERSONS_LISTED_IN_CLASS, 0), expectedModel);

        //After adding a student with class "XXX"
        Student validStudent = new StudentBuilder().withClassName("XXX").build();
        model.addStudent(validStudent);
        expectedModel.addStudent(validStudent);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(new ViewClassCommand(predicate), model,
                String.format(MESSAGE_SINGLE_PERSON_LISTED_IN_CLASS, 1), expectedModel);
    }

    @Test
    public void execute_viewClassAfterDeleteStud_success() {
        //Initially there is one student ALICE with class "4A1"
        Model expectedModel = new ModelManager(model.getStudentRecord(), new UserPrefs());
        ClassPredicate predicate = preparePredicate("4A1");
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(new ViewClassCommand(predicate), model,
                String.format(MESSAGE_SINGLE_PERSON_LISTED_IN_CLASS, 1), expectedModel);

        //After deleting the student ALICE with class "4A1"
        model.deleteStudent(ALICE);
        expectedModel.deleteStudent(ALICE);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(new ViewClassCommand(predicate), model,
                String.format(MESSAGE_PERSONS_LISTED_IN_CLASS, 0), expectedModel);
    }

    /**
     * Parses {@code userInput} into a {@code ClassPredicate}.
     */
    private ClassPredicate preparePredicate(String userInput) {
        return new ClassPredicate(new Class(userInput));
    }

}

