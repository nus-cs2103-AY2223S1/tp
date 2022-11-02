package seedu.clinkedin.logic.commands;

import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.testutil.TypicalPersons.getTypicalAddressBook;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;
import seedu.clinkedin.model.UserPrefs;
import seedu.clinkedin.model.link.Link;
import seedu.clinkedin.model.person.Person;

public class AddLinkCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Set<Link> emptySet = new HashSet<>();

    @Test
    public void constructor_nullLink_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddLinkCommand(Index.fromZeroBased(0), null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddLinkCommand(null, emptySet));
    }

    @Test
    public void constructor_validParams_success() {
        assertDoesNotThrow(() -> new AddLinkCommand(Index.fromZeroBased(0), emptySet));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        AddLinkCommand addLinkCommand = new AddLinkCommand(Index.fromOneBased(100), emptySet);
        assertThrows(CommandException.class, () -> addLinkCommand.execute(model));
    }

    @Test
    public void execute_noChangeInValue_success() {
        Person personToEdit = model.getFilteredPersonList().get(0);
        AddLinkCommand addLinkCommand = new AddLinkCommand(Index.fromOneBased(1), personToEdit.getLinks());
        String expectedMessage = String.format(AddLinkCommand.MESSAGE_ADD_LINKS_SUCCESS, personToEdit);
        assertCommandSuccess(addLinkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndex_success() throws MalformedURLException {
        Set<Link> linksToAdd = new HashSet<>();
        linksToAdd.add(new Link(new URL("https://www.dummylink.com")));
        Person personToEdit = model.getFilteredPersonList().get(0);
        AddLinkCommand addLinkCommand = new AddLinkCommand(Index.fromOneBased(1), linksToAdd);
        linksToAdd.addAll(personToEdit.getLinks());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTagTypeMap(), personToEdit.getStatus(),
                personToEdit.getNote(),
                personToEdit.getRating(), linksToAdd);
        String expectedMessage = String.format(AddLinkCommand.MESSAGE_ADD_LINKS_SUCCESS, editedPerson);
        expectedModel.setPerson(personToEdit, editedPerson);
        assertCommandSuccess(addLinkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals_sameObject() throws MalformedURLException {
        Set<Link> linksToAdd = new HashSet<>();
        linksToAdd.add(new Link(new URL("https://www.dummylink.com")));
        AddLinkCommand command1 = new AddLinkCommand(Index.fromOneBased(1), linksToAdd);
        assertTrue(command1.equals(command1));

    }

    @Test
    public void equals_diffObjectSameParameters() throws MalformedURLException {
        Set<Link> linksToAdd = new HashSet<>();
        linksToAdd.add(new Link(new URL("https://www.dummylink.com")));
        AddLinkCommand command1 = new AddLinkCommand(Index.fromOneBased(1), linksToAdd);
        AddLinkCommand command2 = new AddLinkCommand(Index.fromOneBased(1), linksToAdd);
        assertTrue(command1.equals(command2));
    }

    @Test
    public void notEqual_null() throws MalformedURLException {
        Set<Link> linksToAdd = new HashSet<>();
        linksToAdd.add(new Link(new URL("https://www.dummylink.com")));
        AddLinkCommand command1 = new AddLinkCommand(Index.fromOneBased(1), linksToAdd);
        assertFalse(command1.equals(null));
    }

    @Test
    public void notEqual_differentType() throws MalformedURLException {
        Set<Link> linksToAdd = new HashSet<>();
        linksToAdd.add(new Link(new URL("https://www.dummylink.com")));
        AddLinkCommand command1 = new AddLinkCommand(Index.fromOneBased(1), linksToAdd);
        assertFalse(command1.equals(5));
    }
}
