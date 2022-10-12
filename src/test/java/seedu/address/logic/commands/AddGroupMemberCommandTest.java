package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.testutil.PersonBuilder;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.person.testutil.Assert.assertThrows;
import static seedu.address.model.person.testutil.TypicalPersons.CARL;
import static seedu.address.model.person.testutil.TypicalPersons.getTypicalAddressBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddMemberCommand}.
 */
public class AddGroupMemberCommandTest {

    private Model model = new ModelManager(new AddressBook(getTypicalAddressBook()), new UserPrefs());
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGroupMemberCommand("CS2101", null));
    }

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGroupMemberCommand(null, "nameHere"));
    }

//    @Test
//    public void execute_addPersonGroup_addSuccessful() throws Exception {
//
//        Person personToAddPersonGroup = model.getPersonWithName(CARL.getName()).get(0);
//        Person editedPerson = new PersonBuilder(personToAddPersonGroup).build();
//
//        String group = ;
//        String name = "Roger";
//        AddGroupMemberCommand addGroupMemberCommand = new AddGroupMemberCommand(group, name);
//
//        String expectedMessage = String.format(AddGroupMemberCommand.MESSAGE_ASSIGN_GROUP_SUCCESS,
//                personToAddPersonGroup, name, group);
//        System.out.println(expectedMessage);
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        model.setPerson(personToAddPersonGroup, editedPerson);
//
//        assertCommandSuccess(addGroupMemberCommand, model, expectedMessage, expectedModel);
//    }

}
