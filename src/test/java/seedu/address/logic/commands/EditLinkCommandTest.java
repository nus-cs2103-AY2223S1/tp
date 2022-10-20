package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditLinkCommand.EditLinkDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.team.Link;
import seedu.address.model.team.Url;

class EditLinkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_linkNothingEdited_throwsCommandException() {
        Link googleLink = new Link(new Name("Google"), new Url("https://google.com"));
        model.addLink(googleLink);

        Link facebookLink = new Link(new Name("Facebook"), new Url("https://facebook.com"));
        model.addLink(facebookLink);

        EditLinkCommand editLinkCommand = new EditLinkCommand(INDEX_SECOND_PERSON, DESC_GOOGLE);
        assertCommandFailure(editLinkCommand, model, editLinkCommand.MESSAGE_DUPLICATE_LINK);
    }

    @Test
    void testEquals() {
        EditLinkCommand standardCommand = new EditLinkCommand(INDEX_FIRST_PERSON, DESC_GOOGLE);

        EditLinkCommand commandWithSameValues = new EditLinkCommand(INDEX_FIRST_PERSON,
                new EditLinkDescriptor(DESC_GOOGLE));
        EditLinkCommand commandWithDifferentIndex = new EditLinkCommand(INDEX_SECOND_PERSON, DESC_GOOGLE);
        EditLinkCommand commandWithDifferentDescriptor = new EditLinkCommand(INDEX_FIRST_PERSON, DESC_FACEBOOK);

        //Same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        //Same values -> returns true
        assertTrue(standardCommand.equals(commandWithSameValues));

        //Different index -> returns false
        assertFalse(standardCommand.equals(commandWithDifferentIndex));

        //Different descriptor -> returns false
        assertFalse(standardCommand.equals(commandWithDifferentDescriptor));

        //null -> returns false
        assertFalse(standardCommand.equals(null));

        //different type -> returns false
        assertFalse(standardCommand.equals(5));
    }
}
