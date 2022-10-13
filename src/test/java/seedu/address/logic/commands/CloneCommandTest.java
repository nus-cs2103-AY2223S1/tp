package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.ClonePersonBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class CloneCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToClone = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        CloneCommand cloneCommand = new CloneCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(CloneCommand.MESSAGE_CLONE_PERSON_SUCCESS, personToClone);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModel.addPerson(personToClone);

        assertCommandSuccess(cloneCommand, model, expectedMessage, expectedModel);
    }
//    @Test
//    public void execute_allFieldsSpecifiedUnfilteredList_success() {
//        Person personToClone = new PersonBuilder().build();
//        Person clonedPerson = new ClonePersonBuilder(personToClone).build();
//        CloneCommand cloneCommand = new CloneCommand(INDEX_FIRST_PERSON);
////        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
////        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
//
//        String expectedMessage = String.format(cloneCommand.MESSAGE_CLONE_PERSON_SUCCESS, clonedPerson);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
//
//        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
//    }
}
