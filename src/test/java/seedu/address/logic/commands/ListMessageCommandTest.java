package seedu.address.logic.commands;

import static seedu.address.logic.commands.ListMessageCommand.MESSAGE_NO_MESSAGES;
import static seedu.address.logic.commands.ListMessageCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalMessages.VALID_MESSAGE_HAPPY_BIRTHDAY;
import static seedu.address.testutil.TypicalMessages.VALID_MESSAGE_RECOMMEND_PRODUCT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.message.Message;

class ListMessageCommandTest {
    @Test
    public void execute_emptyList_noMessages() throws CommandException {
        ListMessageCommandTest.ModelStubListCommand
                modelStub = new ListMessageCommandTest.ModelStubListCommand();

        CommandResult commandResult = new ListMessageCommand().execute(modelStub);

        Assertions.assertEquals(MESSAGE_NO_MESSAGES, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_nonEmptyList_listOfMessages() throws CommandException {
        ListMessageCommandTest.ModelStubListCommand
                modelStub = new ListMessageCommandTest.ModelStubListCommand();

        modelStub.createMessage(VALID_MESSAGE_HAPPY_BIRTHDAY);
        modelStub.createMessage(VALID_MESSAGE_RECOMMEND_PRODUCT);

        CommandResult commandResult = new ListMessageCommand().execute(modelStub);

        String output = "\n1. " + modelStub.getMessages().get(0) + "\n"
                + "2. " + modelStub.getMessages().get(1) + "\n";

        Assertions.assertEquals(String.format(MESSAGE_SUCCESS, output), commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        ListMessageCommand listMessageCommand1 = new ListMessageCommand();
        ListMessageCommand listMessageCommand2 = new ListMessageCommand();

        // same object -> return true
        Assertions.assertTrue(listMessageCommand1.equals(listMessageCommand1));

        // same type, different object -> return true
        Assertions.assertTrue(listMessageCommand1.equals(listMessageCommand2));

        // null -> return false
        Assertions.assertFalse(listMessageCommand1.equals(null));
    }

    private class ModelStubListCommand extends CommandTestUtil.ModelStub {
        private ObservableList<Message> messagesAdded = FXCollections.observableArrayList();

        @Override
        public void createMessage(Message message) {
            messagesAdded.add(message);
        }

        @Override
        public ObservableList<Message> getMessages() {
            return messagesAdded;
        }
    }
}
