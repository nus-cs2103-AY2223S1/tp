package seedu.studmap.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.studmap.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.studmap.testutil.TypicalStudents.getTypicalStudMap;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.studmap.model.StudMap;
import seedu.studmap.model.Model;
import seedu.studmap.model.ModelManager;
import seedu.studmap.model.UserPrefs;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.TagContainsKeywordsPredicate;
import seedu.studmap.testutil.StudentBuilder;


public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalStudMap(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStudMap(), new UserPrefs());

    public Model mockDatabase() {
        Student john = new StudentBuilder().withName("John").withTags("friends").build();
        Student cena = new StudentBuilder().withName("Cena").withTags("friends").build();
        StudMap sm = new StudMap();
        sm.addStudent(john);
        sm.addStudent(cena);
        Model model = new ModelManager(sm, new UserPrefs());
        return model;
    }

    @Test
    public void execute_zeroKeywords_nostudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        TagContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.filterStudentListWithTag(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_singleKeyword_multiplestudentsFound() {
        Model initialModel = mockDatabase();
        Model outputModel = mockDatabase();
        Student john = outputModel.getStudMap().getStudentList().get(0);
        Student cena = outputModel.getStudMap().getStudentList().get(1);
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 2);
        TagContainsKeywordsPredicate predicate = preparePredicate("friends");
        FilterCommand command = new FilterCommand(predicate);
        outputModel.filterStudentListWithTag(predicate);
        assertCommandSuccess(command, initialModel, expectedMessage, outputModel);
        assertEquals(Arrays.asList(john, cena), outputModel.getFilteredStudentList());


    }

    private TagContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
