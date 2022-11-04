package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.contact.ContactType;
import seedu.address.testutil.PersonBuilder;

public class DeleteAttributeCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullAttribute_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteAttributeCommand(null));
    }

    @Test
    public void execute_deleteNonContactAttribute_success() {
        // Construct Person Before applying Delete Attribute Command.
        Person toDeleteAttribute = model.getFilteredPersonList().get(0);
        PersonBuilder personToDeleteAttribute = new PersonBuilder(toDeleteAttribute);
        Person personBeforeDeletion = personToDeleteAttribute
                .withName(VALID_NAME_AMY)
                .withRole(VALID_ROLE_AMY)
                .build();
        model.setSelectedPerson(toDeleteAttribute);
        model.setPerson(toDeleteAttribute, personBeforeDeletion);

        // Construct Person after applying delete attribute command.
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person afterDeleteAttribute = expectedModel.getFilteredPersonList().get(0);
        PersonBuilder personAfterDeleteAttribute = new PersonBuilder(afterDeleteAttribute);
        Person personAfterDeletion = personAfterDeleteAttribute
                .withName(VALID_NAME_AMY)
                .withRole(null)
                .build();
        expectedModel.setSelectedPerson(afterDeleteAttribute);
        expectedModel.setPerson(afterDeleteAttribute, personAfterDeletion);

        // Create Delete Attribute Command
        DeleteAttributeCommand deleteRole = new DeleteAttributeCommand(PREFIX_ROLE);

        //Assert Statement to ensure both model after deletion doesn't contain role.
        assertCommandSuccess(deleteRole, model, DeleteAttributeCommand.MESSAGE_DELETE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_deleteContactAttribute_success() {
        // Construct Person Before applying Delete Attribute Command.
        Person toDeleteAttribute = model.getFilteredPersonList().get(0);
        PersonBuilder personToDeleteAttribute = new PersonBuilder(toDeleteAttribute);
        Person personBeforeDeletion = personToDeleteAttribute
                .withName(VALID_NAME_AMY)
                .withRole(VALID_ROLE_AMY)
                .withContact(ContactType.EMAIL, VALID_EMAIL_AMY)
                .build();
        model.setSelectedPerson(toDeleteAttribute);
        model.setPerson(toDeleteAttribute, personBeforeDeletion);

        // Construct Person after applying delete attribute command.
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person afterDeleteAttribute = expectedModel.getFilteredPersonList().get(0);
        PersonBuilder personAfterDeleteAttribute = new PersonBuilder(afterDeleteAttribute);
        Person personAfterDeletion = personAfterDeleteAttribute
                .withName(VALID_NAME_AMY)
                .withRole(VALID_ROLE_AMY)
                .withoutContact(ContactType.EMAIL)
                .build();
        expectedModel.setSelectedPerson(afterDeleteAttribute);
        expectedModel.setPerson(afterDeleteAttribute, personAfterDeletion);

        // Create Delete Attribute Command
        DeleteAttributeCommand deleteRole = new DeleteAttributeCommand(PREFIX_EMAIL);

        //Assert Statement to ensure both model after deletion doesn't contain role.
        assertCommandSuccess(deleteRole, model, DeleteAttributeCommand.MESSAGE_DELETE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_deleteNonExistentAttribute_failure() {
        // Construct Person Before applying Delete Attribute Command.
        Person toDeleteAttribute = model.getFilteredPersonList().get(0);
        PersonBuilder personToDeleteAttribute = new PersonBuilder(toDeleteAttribute);
        Person personBeforeDeletion = personToDeleteAttribute
                .withName(VALID_NAME_AMY)
                .withRole(VALID_ROLE_AMY)
                .withContact(ContactType.EMAIL, VALID_EMAIL_AMY)
                .build();
        model.setSelectedPerson(toDeleteAttribute);
        model.setPerson(toDeleteAttribute, personBeforeDeletion);

        DeleteAttributeCommand deleteNonExistentAttribute = new DeleteAttributeCommand(new Prefix("gh/"));

        assertCommandFailure(deleteNonExistentAttribute, model, DeleteAttributeCommand.MESSAGE_NO_SUCH_ATTRIBUTE);
    }

    @Test
    public void execute_deleteName_failure() {
        // Construct Person Before applying Delete Attribute Command.
        Person toDeleteAttribute = model.getFilteredPersonList().get(0);
        PersonBuilder personToDeleteAttribute = new PersonBuilder(toDeleteAttribute);
        Person personBeforeDeletion = personToDeleteAttribute
                .withName(VALID_NAME_AMY)
                .withRole(VALID_ROLE_AMY)
                .withContact(ContactType.EMAIL, VALID_EMAIL_AMY)
                .build();
        model.setSelectedPerson(toDeleteAttribute);
        model.setPerson(toDeleteAttribute, personBeforeDeletion);

        DeleteAttributeCommand deleteNonExistentAttribute = new DeleteAttributeCommand(PREFIX_NAME);
        assertCommandFailure(deleteNonExistentAttribute, model, DeleteAttributeCommand.MESSAGE_CANNOT_DELETE_NAME);
    }
}
