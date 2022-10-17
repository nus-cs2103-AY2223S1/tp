package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.message.Message;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.ListMessageCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.ListMessageCommand.MESSAGE_NO_MESSAGES;
import static seedu.address.testutil.TypicalMessages.VALID_MESSAGE_HAPPY_BIRTHDAY;
import static seedu.address.testutil.TypicalMessages.VALID_MESSAGE_RECOMMEND_PRODUCT;

class ListMessageCommandTest {
    @Test
    public void execute_emptyList_NoMessages() throws CommandException {
        ListMessageCommandTest.ModelStubListCommand
                modelStub = new ListMessageCommandTest.ModelStubListCommand();

        CommandResult commandResult = new ListMessageCommand().execute(modelStub);

        assertEquals(MESSAGE_NO_MESSAGES, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_nonEmptyList_ListOfMessages() throws CommandException{
        ListMessageCommandTest.ModelStubListCommand
                modelStub = new ListMessageCommandTest.ModelStubListCommand();

        modelStub.addMessage(VALID_MESSAGE_HAPPY_BIRTHDAY);
        modelStub.addMessage(VALID_MESSAGE_RECOMMEND_PRODUCT);

        CommandResult commandResult = new ListMessageCommand().execute(modelStub);

        String output = "\n1. " + modelStub.getMessages().get(0) + "\n" +
                "2. " + modelStub.getMessages().get(1) + "\n";

        assertEquals(String.format(MESSAGE_SUCCESS, output), commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        ListMessageCommand listMessageCommand1 = new ListMessageCommand();
        ListMessageCommand listMessageCommand2 = new ListMessageCommand();

        // same object -> return true
        assertTrue(listMessageCommand1.equals(listMessageCommand1));

        // same type, different object -> return true
        assertTrue(listMessageCommand1.equals(listMessageCommand2));

        // null -> return false
        assertFalse(listMessageCommand1.equals(null));
    }

    private class ModelStubListCommand extends CommandTestUtil.ModelStub {
        private List<Message> messagesAdded = new ArrayList<>();

        @Override
        public void addMessage(Message message) {
            messagesAdded.add(message);
        }

        @Override
        public List<Message> getMessages() {
            return messagesAdded;
        }
    }
}