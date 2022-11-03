package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.message.Message;
import seedu.address.model.person.Person;


/**
 * Generates a message based on a template and a person in the address book.
 */
public class GenerateMessageCommand extends MessageCommandGroup {
    public static final String COMMAND_SPECIFIER = "generate";
    public static final String COMMAND_SPECIFIER_ALIAS = "g";
    public static final String COMMAND_WORD = COMMAND_GROUP + " " + COMMAND_SPECIFIER;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Generates a customised message for the person. "
            + "\nParameters: [PERSON_INDEX] [MESSAGE_INDEX]"
            + "\nExample: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_SUCCESS = "Message generated and copied to clipboard:\n%1$s";

    private final Index personIndex;
    private final Index messageIndex;

    /**
     * @param personIndex person to generate for
     * @param messageIndex message template to generate on
     */
    public GenerateMessageCommand(Index personIndex, Index messageIndex) {
        requireNonNull(personIndex);
        requireNonNull(messageIndex);
        this.personIndex = personIndex;
        this.messageIndex = messageIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        if (personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person person = lastShownList.get(personIndex.getZeroBased());

        List<Message> messages = model.getMessages();
        if (messageIndex.getZeroBased() >= messages.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MESSAGE_INDEX);
        }
        Message message = messages.get(messageIndex.getZeroBased());

        String generatedMessage = message.generate(person);

        StringSelection stringSelection = new StringSelection(generatedMessage);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        return new CommandResult(String.format(MESSAGE_SUCCESS, generatedMessage));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GenerateMessageCommand)) {
            return false;
        }

        // state check
        GenerateMessageCommand e = (GenerateMessageCommand) other;
        return messageIndex.equals(e.messageIndex) && personIndex.equals(e.personIndex);
    }
}
