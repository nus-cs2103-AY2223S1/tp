package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.question.Question;

/**
 * Adds a question to the address book.
 */
public class AddQCommand extends Command {

    public static final String COMMAND_WORD = "addq";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a question to the question list. " + "\nExample"
            + ": addq What is the purpose of UML diagrams?";

    public static final String MESSAGE_EMPTY_DESCRIPTION =
            "The question description cannot be empty.\n" + MESSAGE_USAGE;
    public static final String MESSAGE_SUCCESS = "New question added: %1$s";
    public static final String MESSAGE_DUPLICATE_QUESTION = "This question already exists in the address book";

    private final Question toAdd;

    /**
     * Creates an AddQCommand to add the specified {@code Question}
     */
    public AddQCommand(Question question) {
        requireNonNull(question);
        toAdd = question;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasQuestion(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_QUESTION);
        }

        model.addQuestion(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddQCommand // instanceof handles nulls
                && toAdd.equals(((AddQCommand) other).toAdd));
    }
}
