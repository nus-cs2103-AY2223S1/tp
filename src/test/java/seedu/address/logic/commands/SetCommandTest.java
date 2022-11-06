package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SLACK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMEZONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetCommand.SetPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.contact.ContactType;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.SetPersonDescriptorBuilder;

public class SetCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person toSet = model.getSortedFilteredPersonList().get(0);
        model.setSelectedPerson(toSet);
        Person editedPerson = new PersonBuilder()
                .withName(VALID_NAME_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withRole(VALID_ROLE_AMY)
                .withTimezone(VALID_TIMEZONE_AMY)
                .withContact(ContactType.SLACK, VALID_SLACK_AMY)
                .withContact(ContactType.EMAIL, VALID_EMAIL_AMY)
                .withContact(ContactType.TELEGRAM, VALID_TELEGRAM_AMY)
                .withContact(ContactType.PHONE, VALID_PHONE_AMY)
                .build();
        SetPersonDescriptor descriptor = new SetPersonDescriptorBuilder(editedPerson).build();
        SetCommand setCommand = new SetCommand(descriptor);

        String expectedMessage = SetCommand.MESSAGE_UPDATE_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSelectedPerson(toSet);
        expectedModel.setPerson(toSet, editedPerson);

        assertCommandSuccess(setCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Person toSet = model.getSortedFilteredPersonList().get(0);
        model.setSelectedPerson(toSet);

        PersonBuilder personInList = new PersonBuilder(toSet);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withTags(VALID_TAG_HUSBAND).build();

        SetPersonDescriptor descriptor = new SetPersonDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();

        SetCommand editCommand = new SetCommand(descriptor);

        String expectedMessage = SetCommand.MESSAGE_UPDATE_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(toSet, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        SetCommand setCommand = new SetCommand(new SetPersonDescriptor());
        Person toSet = model.getSortedFilteredPersonList().get(0);
        model.setSelectedPerson(toSet);

        String expectedMessage = SetCommand.MESSAGE_UPDATE_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(setCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Person personInFilteredList = model.getSortedFilteredPersonList().get(0);
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        model.setSelectedPerson(personInFilteredList);
        SetCommand setCommand = new SetCommand(new SetPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = SetCommand.MESSAGE_UPDATE_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getSortedFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(setCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getSortedFilteredPersonList().get(0);
        Person secondPerson = model.getSortedFilteredPersonList().get(1);
        model.setSelectedPerson(secondPerson);
        SetPersonDescriptor descriptor = new SetPersonDescriptorBuilder(firstPerson).build();
        SetCommand setCommand = new SetCommand(descriptor);

        assertCommandFailure(setCommand, model, SetCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        Person personInList = model.getAddressBook().getPersonList().get(1);
        model.setSelectedPerson(model.getAddressBook().getPersonList().get(0));
        SetCommand editCommand = new SetCommand(new SetPersonDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, SetCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void equals() {
        final SetCommand standardCommand = new SetCommand(DESC_AMY);

        // same values -> returns true
        SetPersonDescriptor copyDescriptor = new SetPersonDescriptor(DESC_AMY);
        SetCommand commandWithSameValues = new SetCommand(copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new SetCommand(DESC_BOB)));
    }

}
