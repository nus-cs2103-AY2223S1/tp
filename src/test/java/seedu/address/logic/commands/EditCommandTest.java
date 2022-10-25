package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.AddCommandParser;
import seedu.address.logic.parser.AddNoteCommandParser;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.EditCommandParser;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.NoteBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditPersonDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_editPersonWithTag_addsTagIntoTagMapping() throws Exception {
        Model model = new ModelManager();
        String tagName = "Operations";
        model.addPerson(new PersonBuilder().build());

        assertFalse(model.getTagMapping().containsKey(tagName));

        assertAll(() -> new EditCommandParser(model).parse(" "
                        + "1" + " "
                        + CliSyntax.PREFIX_TAG + tagName + " ")
                .execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));

        assertEquals(1,
                model.getTagMapping()
                        .get(tagName)
                        .getDeepCopiedPersonList()
                        .stream()
                        .filter(p -> p.getName().fullName.equals(PersonBuilder.DEFAULT_NAME))
                        .count());
    }

    // Ensure that if a Person is edited with a Tag that already exists in the address book, the
    // Tag that the Person points to is the same object as the Tag that exists in the address book.
    @Test
    public void execute_editPersonWithTag_tagAlreadyExistsInTagMapping() throws Exception {
        Model model = new ModelManager();
        String tagName = "Operations";
        model.addPerson(new PersonBuilder().build());
        model.addTag(new Tag(tagName));

        assertAll(() -> new EditCommandParser(model).parse(" "
                        + "1" + " "
                        + CliSyntax.PREFIX_TAG + tagName + " ")
                .execute(model));

        List<Tag> listOfTagsFromPerson = new ArrayList<>(model.getAddressBook().getPersonList().get(0).getTags());
        Tag tagFromPerson = listOfTagsFromPerson.get(0);
        Tag tagFromTagMapping = model.getTagMapping().get(tagName);

        assertSame(tagFromTagMapping, tagFromPerson);
    }

    @Test
    public void execute_editPersonWithTag_deletesTagFromTagMapping() {
        Model model = new ModelManager();
        String tagName = "Operations";

        assertAll(() -> new AddCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NAME + PersonBuilder.DEFAULT_NAME + " "
                        + CliSyntax.PREFIX_PHONE + PersonBuilder.DEFAULT_PHONE + " "
                        + CliSyntax.PREFIX_ADDRESS + PersonBuilder.DEFAULT_ADDRESS + " "
                        + CliSyntax.PREFIX_EMAIL + PersonBuilder.DEFAULT_EMAIL + " "
                        + CliSyntax.PREFIX_TAG + tagName)
                .execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));

        assertAll(() -> new EditCommandParser(model).parse(" "
                        + "1" + " "
                        + CliSyntax.PREFIX_TAG + " ")
                .execute(model));

        assertFalse(model.getTagMapping().containsKey(tagName));
    }

    // Tag being added already exists in UniqueTagMapping because a Note has it
    @Test
    public void execute_editPersonWithTag_tagAlreadyExistsInTagMappingDueToNote() throws Exception {
        Model model = new ModelManager();
        model.addPerson(new PersonBuilder().build());
        String tagName = "Operations";

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + NoteBuilder.DEFAULT_TITLE + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + NoteBuilder.DEFAULT_CONTENT + " "
                        + CliSyntax.PREFIX_NOTES_TAG + tagName)
                .execute(model));

        assertAll(() -> new EditCommandParser(model).parse(" "
                        + "1" + " "
                        + CliSyntax.PREFIX_TAG + tagName + " ")
                .execute(model));

        List<Tag> listOfTagsFromPerson = new ArrayList<>(model.getAddressBook().getPersonList().get(0).getTags());
        Tag tagFromPerson = listOfTagsFromPerson.get(0);
        Tag tagFromTagMapping = model.getTagMapping().get(tagName);

        assertSame(tagFromTagMapping, tagFromPerson);
    }

    // Tag is not deleted from UniqueTagMapping when there still exists a Note with that Tag
    @Test
    public void execute_editPersonWithTag_doesNotDeletesTagFromTagMapping() {
        Model model = new ModelManager();
        String tagName = "Operations";

        assertAll(() -> new AddNoteCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NOTES_TITLE + NoteBuilder.DEFAULT_TITLE + " "
                        + CliSyntax.PREFIX_NOTES_CONTENT + NoteBuilder.DEFAULT_CONTENT + " "
                        + CliSyntax.PREFIX_NOTES_TAG + tagName)
                .execute(model));

        assertAll(() -> new AddCommandParser(model).parse(" "
                        + CliSyntax.PREFIX_NAME + PersonBuilder.DEFAULT_NAME + " "
                        + CliSyntax.PREFIX_PHONE + PersonBuilder.DEFAULT_PHONE + " "
                        + CliSyntax.PREFIX_ADDRESS + PersonBuilder.DEFAULT_ADDRESS + " "
                        + CliSyntax.PREFIX_EMAIL + PersonBuilder.DEFAULT_EMAIL + " "
                        + CliSyntax.PREFIX_TAG + tagName)
                .execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));

        assertAll(() -> new EditCommandParser(model).parse(" "
                        + "1" + " "
                        + CliSyntax.PREFIX_TAG + " ")
                .execute(model));

        assertTrue(model.getTagMapping().containsKey(tagName));
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

}
