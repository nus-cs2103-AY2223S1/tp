package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.SocialTestUtil.INVALID_SOCIALS;
import static seedu.address.logic.commands.SocialTestUtil.VALID_WHATSAPP;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests and unit test for OpenCommand.
 */
public class OpenCommandTest {

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        OpenCommand excludeCommand = new OpenCommand(outOfBoundIndex, VALID_WHATSAPP);

        assertCommandFailure(excludeCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validSocials() throws CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        OpenCommand openCommand = new OpenCommand(INDEX_FIRST_PERSON, INVALID_SOCIALS);
        assertCommandFailure(openCommand, model, OpenCommand.MESSAGE_WRONG_SOCIAL);
    }
}
