package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.OTHER_DATE_TIME;
import static seedu.address.logic.commands.CommandTestUtil.OTHER_DATE_TIME_FIVE;
import static seedu.address.logic.commands.CommandTestUtil.OTHER_DATE_TIME_FOUR;
import static seedu.address.logic.commands.CommandTestUtil.OTHER_DATE_TIME_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.OTHER_DATE_TIME_INDEX_THREE;
import static seedu.address.logic.commands.CommandTestUtil.OTHER_DATE_TIME_INDEX_TWO;
import static seedu.address.logic.commands.CommandTestUtil.OTHER_DATE_TIME_THREE;
import static seedu.address.logic.commands.CommandTestUtil.OTHER_DATE_TIME_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Uid;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * EditCommand.
 */
public class EditCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Uid outOfBoundsUid = new Uid(99998L);

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder().withUid(firstPerson.getUid().toString()).build();
        System.out.println("First person datetime: " + ((Patient) firstPerson).getDatesTimes().toString());
        System.out.println("Edited person datetime: " + ((Patient) editedPerson).getDatesTimes().toString());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson)
                .withDateTimeIndexes("1").build();
        EditCommand editCommand = new EditCommand(firstPerson.getUid(), descriptor);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                editedPerson.getCategoryIndicator(), editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        System.out.println("First person UID: " + firstPerson.getUid().toString());
        System.out.println("Edited person UID: " + editedPerson.getUid().toString());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withGender(VALID_GENDER_BOB).withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withGender(VALID_GENDER_BOB).withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(lastPerson.getUid(), descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                editedPerson.getCategoryIndicator(), editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_dateTimeFieldSpecifiedUnfilteredList_success() {
        //Case 1 (null dateTime only)
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PersonBuilder personToBeEdited = new PersonBuilder(firstPerson);
        Person editedPerson = personToBeEdited.withDatesTimes().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withDatesTimes().build();
        EditCommand editCommand = new EditCommand(firstPerson.getUid(), descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                editedPerson.getCategoryIndicator(), editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        //Case 4 (non-null dateTime and not empty dateTime and null dateTimeIndex)
        editedPerson = personToBeEdited.withDatesTimes(OTHER_DATE_TIME, OTHER_DATE_TIME_TWO,
                OTHER_DATE_TIME_THREE).build();
        descriptor = new EditPersonDescriptorBuilder().withDatesTimes(OTHER_DATE_TIME, OTHER_DATE_TIME_TWO,
                OTHER_DATE_TIME_THREE).build();
        editCommand = new EditCommand(firstPerson.getUid(), descriptor);

        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                editedPerson.getCategoryIndicator(), editedPerson);

        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        //Case 5 (non-null dateTime and not empty dateTime and non-null dateTimeIndex and not empty dateTimeIndex)
        editedPerson = personToBeEdited.withDatesTimes(OTHER_DATE_TIME_FOUR, OTHER_DATE_TIME_FIVE,
                OTHER_DATE_TIME_THREE).build();
        descriptor = new EditPersonDescriptorBuilder().withDatesTimes(OTHER_DATE_TIME_FOUR, OTHER_DATE_TIME_FIVE)
                .withDateTimeIndexes(OTHER_DATE_TIME_INDEX, OTHER_DATE_TIME_INDEX_TWO).build();
        editCommand = new EditCommand(firstPerson.getUid(), descriptor);

        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                editedPerson.getCategoryIndicator(), editedPerson);

        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        //Case 2 (non-null dateTimeIndex and not empty and null dateTime)
        editedPerson = personToBeEdited.withDatesTimes(OTHER_DATE_TIME_FIVE).build();
        descriptor = new EditPersonDescriptorBuilder().withDateTimeIndexes(OTHER_DATE_TIME_INDEX,
                        OTHER_DATE_TIME_INDEX_THREE).build();
        editCommand = new EditCommand(firstPerson.getUid(), descriptor);

        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                editedPerson.getCategoryIndicator(), editedPerson);

        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        //Case 1 (null dateTimeIndex only)
        editedPerson = personToBeEdited.withDatesTimes().build();
        descriptor = new EditPersonDescriptorBuilder().withDateTimeIndexes().build();
        editCommand = new EditCommand(firstPerson.getUid(), descriptor);

        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                editedPerson.getCategoryIndicator(), editedPerson);

        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        //Case 4 (non-null dateTime and not empty dateTime and non-null dateTimeIndex but empty)
        editedPerson = personToBeEdited.withDatesTimes(OTHER_DATE_TIME,
                OTHER_DATE_TIME_TWO, OTHER_DATE_TIME_THREE).build();
        descriptor = new EditPersonDescriptorBuilder().withDateTimeIndexes()
                .withDatesTimes(OTHER_DATE_TIME, OTHER_DATE_TIME_TWO, OTHER_DATE_TIME_THREE).build();
        editCommand = new EditCommand(firstPerson.getUid(), descriptor);

        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                editedPerson.getCategoryIndicator(), editedPerson);

        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        //Case 2 (non-null dateTimeIndex and not empty and non-null dateTime but empty)
        editedPerson = personToBeEdited.withDatesTimes(OTHER_DATE_TIME_TWO).build();
        descriptor = new EditPersonDescriptorBuilder().withDateTimeIndexes(OTHER_DATE_TIME_INDEX,
                OTHER_DATE_TIME_INDEX_THREE).withDatesTimes().build();
        editCommand = new EditCommand(firstPerson.getUid(), descriptor);

        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                editedPerson.getCategoryIndicator(), editedPerson);

        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        //Case 1 (non-null dateTime but empty and non-null dateTimeIndex but empty)
        editedPerson = personToBeEdited.withDatesTimes().build();
        descriptor = new EditPersonDescriptorBuilder().withDateTimeIndexes().withDatesTimes().build();
        editCommand = new EditCommand(firstPerson.getUid(), descriptor);

        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                editedPerson.getCategoryIndicator(), editedPerson);

        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        EditCommand editCommand = new EditCommand(editedPerson.getUid(), new EditPersonDescriptor());
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                editedPerson.getCategoryIndicator(), editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(personInFilteredList.getUid(),
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                editedPerson.getCategoryIndicator(), editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(secondPerson.getUid(), descriptor);
        String expectedMessage = String.format(EditCommand.MESSAGE_DUPLICATE_PERSON,
                firstPerson.getCategoryIndicator());
        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(firstPerson.getUid(),
                new EditPersonDescriptorBuilder(personInList).build());
        String expectedMessage = String.format(EditCommand.MESSAGE_DUPLICATE_PERSON,
                personInList.getCategoryIndicator());
        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidUidUnfilteredList_throwsCommandException() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand deleteCommand = new EditCommand(outOfBoundsUid, descriptor);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_UID);
    }

    public void execute_invalidUidFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        Person onlyPerson = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(new Uid(onlyPerson.getUid().uid + 1L), descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_UID);
    }

    @Test
    public void execute_invalidDateTimeSpecificField_throwsCommandException() {
        // dateTimeIndex outofbound
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withDateTimeIndexes(OTHER_DATE_TIME_INDEX_THREE).build();

        EditCommand editCommand = new EditCommand(firstPerson.getUid(), descriptor);

        String expectedMessage = EditCommand.MESSAGE_OUT_OF_BOUND_DATETIMEINDEX;

        assertCommandFailure(editCommand, model, expectedMessage);

        // testing whether it give the exception when dateTime less than dateTimeIndex
        descriptor = new EditPersonDescriptorBuilder().withDatesTimes(OTHER_DATE_TIME_FOUR)
                .withDateTimeIndexes(OTHER_DATE_TIME_INDEX, OTHER_DATE_TIME_INDEX_TWO)
                .build();

        editCommand = new EditCommand(new Uid("3"), descriptor); //the uid 3 is from TypicalPersons.java [Carl]

        expectedMessage = EditCommand.MESSAGE_INVALID_NUMBERS_OF_DATETIME_AND_DATETIMEINDEX;

        assertCommandFailure(editCommand, model, expectedMessage);

    }

    @Test
    public void equals() {
        Person firstPerson = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPerson = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        final EditCommand standardCommand = new EditCommand(firstPerson.getUid(), DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(firstPerson.getUid(), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(secondPerson.getUid(), DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(firstPerson.getUid(), DESC_BOB)));
    }

}
