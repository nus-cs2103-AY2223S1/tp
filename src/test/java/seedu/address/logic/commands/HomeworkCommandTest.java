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
import seedu.address.model.person.Homework;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class HomeworkCommandTest {

    private static final String HOMEWORK_STUB = "Some homework";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addHomeworkUnfilteredList_success() {
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson).withHomework(HOMEWORK_STUB).build();

        HomeworkCommand homeworkCommand = new HomeworkCommand(INDEX_SECOND_PERSON,
                new Homework(HOMEWORK_STUB));

        String expectedMessage = String.format(HomeworkCommand.MESSAGE_ADD_HOMEWORK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(secondPerson, editedPerson);

        assertCommandSuccess(homeworkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addHomeworkFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withHomework(HOMEWORK_STUB).build();
        HomeworkCommand homeworkCommand = new HomeworkCommand(INDEX_FIRST_PERSON,
                new Homework(HOMEWORK_STUB));

        String expectedMessage = String.format(HomeworkCommand.MESSAGE_ADD_HOMEWORK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(homeworkCommand, model, expectedMessage, expectedModel);
    }
}
