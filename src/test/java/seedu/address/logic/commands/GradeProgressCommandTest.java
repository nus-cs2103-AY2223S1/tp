package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.GradeProgress;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class GradeProgressCommandTest {

    private static final String GRADE_PROGRESS_STUB = "MATH: A";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addGradeProgressUnfilteredList_success() {
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson).withGradeProgress(GRADE_PROGRESS_STUB).build();
        secondPerson.clearGradeProgressList();

        GradeProgressCommand gradeProgressCommand = new GradeProgressCommand(INDEX_SECOND_PERSON,
                new GradeProgress(GRADE_PROGRESS_STUB));

        String expectedMessage = String.format(GradeProgressCommand.MESSAGE_ADD_GRADEPROGRESS_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);

        assertCommandSuccess(gradeProgressCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addGradeProgressFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withGradeProgress(GRADE_PROGRESS_STUB).build();
        personInFilteredList.clearGradeProgressList();
        GradeProgressCommand gradeProgressCommand = new GradeProgressCommand(INDEX_FIRST_PERSON,
                new GradeProgress(GRADE_PROGRESS_STUB));

        String expectedMessage = String.format(GradeProgressCommand.MESSAGE_ADD_GRADEPROGRESS_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(gradeProgressCommand, model, expectedMessage, expectedModel);
    }
}
