package bookface.logic.commands.edit;

import static bookface.logic.commands.CommandTestUtil.assertCommandSuccess;
import static bookface.testutil.TypicalPersons.getTypicalBookFaceData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bookface.commons.core.Messages;
import bookface.commons.core.index.Index;
import bookface.logic.commands.ClearCommand;
import bookface.logic.commands.CommandTestUtil;
import bookface.logic.commands.edit.EditUserCommand.EditPersonDescriptor;
import bookface.model.BookFace;
import bookface.model.Model;
import bookface.model.ModelManager;
import bookface.model.UserPrefs;
import bookface.model.person.Person;
import bookface.testutil.EditPersonDescriptorBuilder;
import bookface.testutil.PersonBuilder;
import bookface.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditUserCommand.
 */
public class EditUserCommandTest {

    private final Model model = new ModelManager(getTypicalBookFaceData(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditUserCommand editUserCommand = new EditUserCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditUserCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new BookFace(model.getBookFace()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editUserCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(CommandTestUtil.VALID_NAME_BOB)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB).withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        EditUserCommand editUserCommand = new EditUserCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditUserCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new BookFace(model.getBookFace()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editUserCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditUserCommand editUserCommand = new EditUserCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                new EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditUserCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new BookFace(model.getBookFace()), new UserPrefs());

        assertCommandSuccess(editUserCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList()
                .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditUserCommand editUserCommand = new EditUserCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditUserCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new BookFace(model.getBookFace()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editUserCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditUserCommand editUserCommand = new EditUserCommand(TypicalIndexes.INDEX_SECOND_PERSON, descriptor);

        CommandTestUtil.assertCommandFailure(editUserCommand, model, Messages.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getBookFace().getPersonList()
                .get(TypicalIndexes.INDEX_SECOND_PERSON.getZeroBased());
        EditUserCommand editUserCommand = new EditUserCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder(personInList).build());

        CommandTestUtil.assertCommandFailure(editUserCommand, model, Messages.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditUserCommand editUserCommand = new EditUserCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editUserCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of BookFace
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getBookFace().getPersonList().size());

        EditUserCommand editUserCommand = new EditUserCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        CommandTestUtil.assertCommandFailure(editUserCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditUserCommand standardCommand = new EditUserCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                CommandTestUtil.DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(CommandTestUtil.DESC_AMY);
        EditUserCommand commandWithSameValues = new EditUserCommand(TypicalIndexes.INDEX_FIRST_PERSON, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditUserCommand(TypicalIndexes.INDEX_SECOND_PERSON,
                CommandTestUtil.DESC_AMY));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditUserCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                CommandTestUtil.DESC_BOB));
    }

}
