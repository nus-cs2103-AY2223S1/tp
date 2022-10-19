package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.team.Link;
import seedu.address.model.team.Url;

class AddLinkCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_linkAlreadyExist_throwsCommandException() {
        Link googleLink = new Link(new Name("Google"), new Url("https://google.com"));
        model.addLink(googleLink);
        AddLinkCommand addLinkCommand = new AddLinkCommand(googleLink);
        assertCommandFailure(addLinkCommand, model, addLinkCommand.MESSAGE_DUPLICATE_LINK);
    }

    @Test
    void testEquals() {
        Link googleLink = new Link(new Name("Google"), new Url("https://google.com"));
        Link facebookLink = new Link(new Name("Facebook"), new Url("https://facebook.com"));

        AddLinkCommand googleAddLinkCommand = new AddLinkCommand(googleLink);
        AddLinkCommand googleAddLinkCommandDuplicate = new AddLinkCommand(googleLink);
        AddLinkCommand facebookAddLinkCommand = new AddLinkCommand(facebookLink);

        //Same object -> returns true
        assertTrue(googleAddLinkCommand.equals(googleAddLinkCommand));

        //Same values -> returns true
        assertTrue(googleAddLinkCommand.equals(googleAddLinkCommandDuplicate));

        //Different values -> returns false
        assertFalse(googleAddLinkCommand.equals(facebookAddLinkCommand));

        //null -> returns false
        assertFalse(googleAddLinkCommand.equals(null));

        //different type -> returns false
        assertFalse(googleAddLinkCommand.equals(5));
    }
}
