package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.DeleteMessageCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMessages.VALID_MESSAGE_HAPPY_BIRTHDAY;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.message.Message;

class DeleteMessageCommandTest {
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteMessageCommand(null));
    }

    @Test
    public void execute_indexDoesNotExist_throwsCommandException() {
        DeleteMessageCommandTest.ModelStubAcceptingMessagesAddedAndDeleted
                modelStub = new DeleteMessageCommandTest.ModelStubAcceptingMessagesAddedAndDeleted();
        assertThrows(CommandException.class, () -> new DeleteMessageCommand(Index.fromZeroBased(0)).execute(modelStub));
    }

    @Test
    public void execute_messageExist_success() throws Exception {
        DeleteMessageCommandTest.ModelStubAcceptingMessagesAddedAndDeleted
                modelStub = new DeleteMessageCommandTest.ModelStubAcceptingMessagesAddedAndDeleted();
        modelStub.createMessage(VALID_MESSAGE_HAPPY_BIRTHDAY);

        List<Message> messagesToRemove = new ArrayList<>();
        messagesToRemove.add(VALID_MESSAGE_HAPPY_BIRTHDAY);

        CommandResult commandResult =
                new DeleteMessageCommand(Index.fromZeroBased(0)).execute(modelStub);
        assertEquals(String.format(MESSAGE_SUCCESS, VALID_MESSAGE_HAPPY_BIRTHDAY), commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        Index index0 = Index.fromZeroBased(0);
        Index index1 = Index.fromZeroBased(1);

        DeleteMessageCommand deleteMessageCommand1 = new DeleteMessageCommand(index0);
        DeleteMessageCommand deleteMessageCommand2 = new DeleteMessageCommand(index0);
        DeleteMessageCommand deleteMessageCommand3 = new DeleteMessageCommand(index1);

        // same index -> return true
        assertTrue(deleteMessageCommand1.equals(deleteMessageCommand2));

        // different indices -> return false
        assertFalse(deleteMessageCommand1.equals(deleteMessageCommand3));

        // null -> return false
        assertFalse(deleteMessageCommand1.equals(null));
    }

    private class ModelStubAcceptingMessagesAddedAndDeleted extends CommandTestUtil.ModelStub {
        private ObservableList<Message> messagesAdded = FXCollections.observableArrayList();

        @Override
        public boolean hasMessage(Message message) {
            return messagesAdded.contains(message);
        }

        @Override
        public void createMessage(Message message) {
            messagesAdded.add(message);
        }

        @Override
        public void deleteMessage(Message message) {
            messagesAdded.remove(message);
        }

        @Override
        public ObservableList<Message> getMessages() {
            return messagesAdded;
        }
    }
}
