package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.GenerateMessageCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMessages.VALID_MESSAGE_HAPPY_BIRTHDAY;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.message.Message;
import seedu.address.model.person.Person;
import seedu.address.model.person.TargetPerson;

class GenerateMessageCommandTest {
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        Index index = Index.fromZeroBased(0);
        assertThrows(NullPointerException.class, () -> new GenerateMessageCommand(null, null));
        assertThrows(NullPointerException.class, () -> new GenerateMessageCommand(index, null));
        assertThrows(NullPointerException.class, () -> new GenerateMessageCommand(null, index));
    }

    @Test
    public void execute_personIndexDoesNotExist_throwsCommandException() {
        GenerateMessageCommandTest.ModelStubMessagesGenerating
                modelStub = new GenerateMessageCommandTest.ModelStubMessagesGenerating();
        Index index = Index.fromZeroBased(0);
        modelStub.createMessage(VALID_MESSAGE_HAPPY_BIRTHDAY);
        assertThrows(CommandException.class, () -> new GenerateMessageCommand(index, index).execute(modelStub));
    }

    @Test
    public void execute_messageIndexDoesNotExist_throwsCommandException() {
        GenerateMessageCommandTest.ModelStubMessagesGenerating
                modelStub = new GenerateMessageCommandTest.ModelStubMessagesGenerating();
        Index index = Index.fromZeroBased(0);
        modelStub.addPerson(ALICE);
        assertThrows(CommandException.class, () -> new GenerateMessageCommand(index, index).execute(modelStub));
    }

    @Test
    public void execute_messageExistAndPersonExist_success() throws Exception {
        GenerateMessageCommandTest.ModelStubMessagesGenerating
                modelStub = new GenerateMessageCommandTest.ModelStubMessagesGenerating();

        Index index = Index.fromZeroBased(0);
        modelStub.addPerson(ALICE);
        modelStub.createMessage(VALID_MESSAGE_HAPPY_BIRTHDAY);

        CommandResult commandResult =
                new GenerateMessageCommand(index, index).execute(modelStub);

        String generatedMessage = VALID_MESSAGE_HAPPY_BIRTHDAY.generate(ALICE);
        assertEquals(String.format(MESSAGE_SUCCESS, generatedMessage), commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        Index index0 = Index.fromZeroBased(0);
        Index index1 = Index.fromZeroBased(1);

        GenerateMessageCommand generateMessageCommand1 = new GenerateMessageCommand(index0, index0);
        GenerateMessageCommand generateMessageCommand2 = new GenerateMessageCommand(index0, index0);
        GenerateMessageCommand generateMessageCommand3 = new GenerateMessageCommand(index0, index1);
        GenerateMessageCommand generateMessageCommand4 = new GenerateMessageCommand(index1, index0);
        GenerateMessageCommand generateMessageCommand5 = new GenerateMessageCommand(index1, index1);

        // same index -> return true
        assertTrue(generateMessageCommand1.equals(generateMessageCommand2));

        // different indices -> return false
        assertFalse(generateMessageCommand1.equals(generateMessageCommand3));
        assertFalse(generateMessageCommand1.equals(generateMessageCommand4));
        assertFalse(generateMessageCommand1.equals(generateMessageCommand5));

        // null -> return false
        assertFalse(generateMessageCommand1.equals(null));
    }

    private class ModelStubMessagesGenerating extends CommandTestUtil.ModelStub {
        private ObservableList<Message> messagesAdded = FXCollections.observableArrayList();
        private ObservableList<Person> personList = FXCollections.observableArrayList();
        private TargetPerson targetPerson = new TargetPerson();

        @Override
        public void addPerson(Person person) {
            personList.add(person);
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            if (personList.contains(target)) {
                int i = personList.indexOf(target);
                personList.set(i, editedPerson);
            }

            if (isTargetPerson(target)) {
                setTargetPerson(editedPerson);
            }
        }

        @Override
        public void clearFiltersInFilteredPersonList() {
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return personList;
        }

        @Override
        public boolean hasMessage(Message message) {
            return messagesAdded.contains(message);
        }

        @Override
        public void createMessage(Message message) {
            messagesAdded.add(message);
        }

        @Override
        public ObservableList<Message> getMessages() {
            return messagesAdded;
        }

        @Override
        public void setTargetPerson(Person person) {
            targetPerson.set(person);
        }

        @Override
        public void clearTargetPerson() {
            targetPerson.clear();
        }

        @Override
        public boolean isTargetPerson(Person person) {
            return targetPerson.isSamePerson(person);
        }

        @Override
        public boolean hasTargetPerson() {
            return targetPerson.isPresent();
        }

        @Override
        public Person getTargetPerson() {
            return targetPerson.get();
        }
    }
}
