package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.student.TagContainsKeywordsPredicate;
import seedu.address.testutil.StudentBuilder;


public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    public Model mockDatabase() {
        Student john = new StudentBuilder().withName("John").withTags("friends").build();
        Student cena = new StudentBuilder().withName("Cena").withTags("friends").build();
        AddressBook ab = new AddressBook();
        ab.addStudent(john);
        ab.addStudent(cena);
        Model model = new ModelManager(ab, new UserPrefs());
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
        Student john = outputModel.getAddressBook().getStudentList().get(0);
        Student cena = outputModel.getAddressBook().getStudentList().get(1);
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
