package tuthub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.testutil.Assert.assertThrows;
import static tuthub.testutil.TypicalIndexes.INDEX_FIRST_TUTOR;
import static tuthub.testutil.TypicalIndexes.INDEX_SECOND_TUTOR;
import static tuthub.testutil.TypicalTutors.getTypicalTuthub;

import org.junit.jupiter.api.Test;

import tuthub.commons.core.Messages;
import tuthub.commons.core.index.Index;
import tuthub.logic.commands.exceptions.CommandException;
import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.UserPrefs;

public class MailCommandTest {

    private Model model = new ModelManager(getTypicalTuthub(), new UserPrefs());

    @Test
    public void constructor_nullTarget_throwsNullPointerException() {
        String target = null;
        assertThrows(NullPointerException.class, () -> new MailCommand(target));
    }

    @Test
    public void constructor_nullTargetIndex_throwsNullPointerException() {
        Index targetIndex = null;
        assertThrows(NullPointerException.class, () -> new MailCommand(targetIndex));
    }

    @Test
    public void checkValidTarget() {
        // Invalid word
        String invalidWord = "a";
        MailCommand mailCommand1 = new MailCommand(invalidWord);
        String expectedMessage = String.format(MailCommand.MESSAGE_INVALID_WORD, invalidWord);
        assertThrows(CommandException.class, expectedMessage, mailCommand1::checkValidTarget);

        // Valid word
        String validWord = "all";
        MailCommand mailCommand2 = new MailCommand(validWord);
        assertAll(mailCommand2::checkValidTarget);
    }

    @Test
    public void checkValidIndex() {
        // Invalid index
        Index invalidIndex = Index.fromZeroBased(200);
        MailCommand mailCommand1 = new MailCommand(invalidIndex);
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX, () -> mailCommand1.checkValidIndex(
                        model.getSortedFilteredTutorLis()));

        // Valid index
        Index validIndex = Index.fromZeroBased(1);
        MailCommand mailCommand2 = new MailCommand(validIndex);
        assertAll(() -> mailCommand2.checkValidIndex(model.getSortedFilteredTutorLis()));
    }

    @Test
    public void equals() {
        MailCommand mailCommand1 = new MailCommand(INDEX_FIRST_TUTOR);
        MailCommand mailCommand2 = new MailCommand(INDEX_SECOND_TUTOR);
        MailCommand mailCommand3 = new MailCommand("all");
        MailCommand mailCommand4 = new MailCommand("a");

        // same object -> returns true
        assertTrue(mailCommand1.equals(mailCommand1));
        assertTrue(mailCommand3.equals(mailCommand3));

        // same values -> returns true
        MailCommand mailCommand1Copy = new MailCommand(INDEX_FIRST_TUTOR);
        assertTrue(mailCommand1.equals(mailCommand1Copy));
        MailCommand mailCommand3Copy = new MailCommand("all");
        assertTrue(mailCommand3.equals(mailCommand3));

        // different types -> returns false
        assertFalse(mailCommand1.equals(1));
        assertFalse(mailCommand3.equals(1));

        // null -> returns false
        assertFalse(mailCommand1.equals(null));
        assertFalse(mailCommand3.equals(null));

        // different tutor -> returns false
        assertFalse(mailCommand1.equals(mailCommand2));
        assertFalse(mailCommand3.equals(mailCommand4));
    }
}
