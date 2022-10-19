package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CreateMessageCommand.MESSAGE_DUPLICATE_MESSAGE;
import static seedu.address.logic.commands.CreateMessageCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMessages.VALID_MESSAGE_HAPPY_BIRTHDAY;
import static seedu.address.testutil.TypicalMessages.VALID_MESSAGE_RECOMMEND_PRODUCT;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.message.Message;

class CreateMessageCommandTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateMessageCommand(null));
    }

    @Test
    public void execute_singleMessages_addSuccessful() throws Exception {
        CreateMessageCommandTest.ModelStubAcceptingMessagesAdded
                modelStub = new CreateMessageCommandTest.ModelStubAcceptingMessagesAdded();

        List<Message> messagesToAdd = new ArrayList<>();
        messagesToAdd.add(VALID_MESSAGE_HAPPY_BIRTHDAY);

        CommandResult commandResult = new CreateMessageCommand(VALID_MESSAGE_HAPPY_BIRTHDAY).execute(modelStub);

        assertEquals(String.format(MESSAGE_SUCCESS, VALID_MESSAGE_HAPPY_BIRTHDAY), commandResult.getFeedbackToUser());
        assertEquals(messagesToAdd, modelStub.messagesAdded);
    }

    @Test
    public void execute_duplicateTags_userNotified() throws Exception {
        CreateMessageCommandTest.ModelStubAcceptingMessagesAdded
                modelStub = new CreateMessageCommandTest.ModelStubAcceptingMessagesAdded();
        modelStub.createMessage(VALID_MESSAGE_HAPPY_BIRTHDAY);

        List<Message> messagesToAdd = new ArrayList<>();
        messagesToAdd.add(VALID_MESSAGE_HAPPY_BIRTHDAY);

        CommandResult commandResult = new CreateMessageCommand(VALID_MESSAGE_HAPPY_BIRTHDAY).execute(modelStub);

        String duplicateMessage = String.format(MESSAGE_DUPLICATE_MESSAGE, VALID_MESSAGE_HAPPY_BIRTHDAY);
        assertEquals(duplicateMessage, commandResult.getFeedbackToUser());
        assertEquals(messagesToAdd, modelStub.messagesAdded);
    }

    @Test
    public void equals() throws Exception {
        CreateMessageCommandTest.ModelStubAcceptingMessagesAdded
                modelStub1 = new CreateMessageCommandTest.ModelStubAcceptingMessagesAdded();
        CreateMessageCommandTest.ModelStubAcceptingMessagesAdded
                modelStub2 = new CreateMessageCommandTest.ModelStubAcceptingMessagesAdded();

        CommandResult commandResult1 = new CreateMessageCommand(VALID_MESSAGE_HAPPY_BIRTHDAY).execute(modelStub1);

        CommandResult commandResult2 = new CreateMessageCommand(VALID_MESSAGE_HAPPY_BIRTHDAY).execute(modelStub2);

        CommandResult commandResult3 = new CreateMessageCommand(VALID_MESSAGE_RECOMMEND_PRODUCT).execute(modelStub1);

        // same messages added once to different stubs -> return true
        assertTrue(commandResult1.equals(commandResult2));

        // different messages added to the same stub-> return false
        assertFalse(commandResult1.equals(commandResult3));

        // null -> return false
        assertFalse(commandResult1.equals(null));
    }

    private class ModelStubAcceptingMessagesAdded extends CommandTestUtil.ModelStub {
        private List<Message> messagesAdded = new ArrayList<>();

        @Override
        public boolean hasMessage(Message message) {
            return messagesAdded.contains(message);
        }

        @Override
        public void createMessage(Message message) {
            messagesAdded.add(message);
        }
    }
}
