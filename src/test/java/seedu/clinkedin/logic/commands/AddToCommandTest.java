package seedu.clinkedin.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinkedin.testutil.TypicalPersons.getTypicalAddressBook;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.AddToCommand.UpdatePersonDescriptor;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;
import seedu.clinkedin.model.UserPrefs;
import seedu.clinkedin.model.link.Link;
import seedu.clinkedin.model.person.Note;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.Rating;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.person.exceptions.RatingAlreadyExistsException;

public class AddToCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        assertThrows(NullPointerException.class, () -> new AddToCommand(null, updatePersonDescriptor));
    }

    @Test
    public void constructor_nullUpdatePersonDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddToCommand(Index.fromZeroBased(0), null));
    }

    @Test
    public void constructor_nullIndexAndUpdatePersonDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddToCommand(null, null));
    }

    @Test
    public void constructor_validParameters_success() {
        Index index = Index.fromZeroBased(0);
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        assertDoesNotThrow(() -> new AddToCommand(index, updatePersonDescriptor));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Index index = Index.fromZeroBased(0);
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        AddToCommand addToCommand = new AddToCommand(index, updatePersonDescriptor);
        assertThrows(NullPointerException.class, () -> addToCommand.execute(null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(100);
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        AddToCommand addToCommand = new AddToCommand(invalidIndex, updatePersonDescriptor);
        assertThrows(CommandException.class, () -> addToCommand.execute(model));
    }

    @Test
    public void execute_unchangedRating_throwsCommandException() {
        Index index = Index.fromZeroBased(0);
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        Person personToEdit = model.getFilteredPersonList().get(index.getZeroBased());
        updatePersonDescriptor.setRating(personToEdit.getRating());
        AddToCommand addToCommand = new AddToCommand(index, updatePersonDescriptor);
        assertThrows(CommandException.class, () -> addToCommand.execute(model));
    }

    @Test
    public void execute_invalidTagType_throwsCommandException() {
        Index index = Index.fromZeroBased(0);
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        updatePersonDescriptor.setTagTypeMap(new UniqueTagTypeMap().copy());
        updatePersonDescriptor.setNote(new Note("Test Note"));
        AddToCommand addToCommand = new AddToCommand(index, updatePersonDescriptor);
        assertDoesNotThrow(() -> addToCommand.execute(model));
    }

    @Test
    public void execute_duplicateTags_throwsCommandException() {
        Index index = Index.fromZeroBased(0);
        Person personToEdit = model.getFilteredPersonList().get(index.getZeroBased());
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        UniqueTagTypeMap duplicateTagTypeMap = new UniqueTagTypeMap().copy();
        duplicateTagTypeMap.setTagTypeMap(personToEdit.getTags());

        updatePersonDescriptor.setTagTypeMap(duplicateTagTypeMap);
        updatePersonDescriptor.setNote(new Note("Test Note"));
        AddToCommand addToCommand = new AddToCommand(index, updatePersonDescriptor);
        assertThrows(CommandException.class, () -> addToCommand.execute(model));
    }

    @Test
    public void execute_validParams_success() {
        Index index = Index.fromZeroBased(1);
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        updatePersonDescriptor.setRating(new Rating("5"));
        updatePersonDescriptor.setNote(new Note("Test Note"));
        AddToCommand addToCommand = new AddToCommand(index, updatePersonDescriptor);
        Person personToEdit = model.getFilteredPersonList().get(index.getZeroBased());
        Person editedPerson = AddToCommand.createUpdatedPerson(personToEdit, updatePersonDescriptor);
        expectedModel.setPerson(personToEdit, editedPerson);
        String expectedMessage = String.format(AddToCommand.MESSAGE_SUCCESS, editedPerson);
        assertCommandSuccess(addToCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        Index index = Index.fromZeroBased(0);
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        AddToCommand addToCommand = new AddToCommand(index, updatePersonDescriptor);
        assertTrue(addToCommand.equals(addToCommand));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        Index index = Index.fromZeroBased(0);
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        AddToCommand addToCommand = new AddToCommand(index, updatePersonDescriptor);
        AddToCommand addToCommandCopy = new AddToCommand(index, updatePersonDescriptor);
        assertTrue(addToCommand.equals(addToCommandCopy));
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        Index index = Index.fromZeroBased(0);
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        AddToCommand addToCommand = new AddToCommand(index, updatePersonDescriptor);
        assertFalse(addToCommand.equals(1));
    }

    @Test
    public void equals_null_returnsFalse() {
        Index index = Index.fromZeroBased(0);
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        AddToCommand addToCommand = new AddToCommand(index, updatePersonDescriptor);
        assertFalse(addToCommand.equals(null));
    }

    @Test
    public void equals_differentIndex_returnsFalse() {
        Index index = Index.fromZeroBased(0);
        Index indexCopy = Index.fromZeroBased(1);
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        AddToCommand addToCommand = new AddToCommand(index, updatePersonDescriptor);
        AddToCommand addToCommandCopy = new AddToCommand(indexCopy, updatePersonDescriptor);
        assertFalse(addToCommand.equals(addToCommandCopy));
    }

    @Test
    public void equals_differentUpdatePersonDescriptor_returnsFalse() {
        Index index = Index.fromZeroBased(0);
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        UpdatePersonDescriptor updatePersonDescriptorCopy = new UpdatePersonDescriptor();
        updatePersonDescriptorCopy.setRating(new Rating("5"));
        AddToCommand addToCommand = new AddToCommand(index, updatePersonDescriptor);
        AddToCommand addToCommandCopy = new AddToCommand(index, updatePersonDescriptorCopy);
        assertFalse(addToCommand.equals(addToCommandCopy));
    }

    @Test
    public void createUpdatedPerson_nullPerson_throwsAssertionException() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        assertThrows(AssertionError.class, () -> AddToCommand.createUpdatedPerson(null, updatePersonDescriptor));
    }

    @Test
    public void createUpdatedPerson_nullUpdatePersonDescriptor_throwsNullPointerException() {
        Person person = model.getFilteredPersonList().get(0);
        assertThrows(NullPointerException.class, () -> AddToCommand.createUpdatedPerson(person, null));
    }

    @Test
    public void createUpdatedPerson_ratingExists_throwsRatingAlreadyExistsException() {
        Index index = Index.fromZeroBased(0);
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        updatePersonDescriptor.setRating(new Rating("5"));
        Person personToEdit = model.getFilteredPersonList().get(index.getZeroBased());
        assertThrows(RatingAlreadyExistsException.class, () -> AddToCommand.createUpdatedPerson(personToEdit,
                updatePersonDescriptor));
    }

    @Test
    public void createUpdatedPerson_validParams_success() throws MalformedURLException {
        Person person = model.getFilteredPersonList().get(1);
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        Set<Link> links = new HashSet<>();
        links.add(new Link(new URL("https://www.google.com")));
        updatePersonDescriptor.setRating(new Rating("5"));
        updatePersonDescriptor.setNote(new Note("Test Note"));
        updatePersonDescriptor.setLinks(links);
        Person updatedPerson = AddToCommand.createUpdatedPerson(person, updatePersonDescriptor);
        assertEquals(updatedPerson.getRating(), updatePersonDescriptor.getRating().get());
        assertEquals(updatedPerson.getNote(), person.mergeNote(updatePersonDescriptor.getNote().get()));
    }
}
