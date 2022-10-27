package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.question.Question;

/**
 * Deletes a question identified using it's displayed index from the question list.
 */
public class DeleteQCommand extends Command {

    public static final String COMMAND_WORD = "deleteq";

    public static final String MESSAGE_USAGE = "The question index provided is invalid. "
            + "Please use a valid index.\nFor example, if you have 3 questions in the question list, the index can be "
            + "1, 2 or 3.\nExample: deleteq 3";

    public static final String MESSAGE_DELETE_QUESTION_SUCCESS = "Deleted Question: %1$s";

    private final Index targetIndex;

    public DeleteQCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Question> lastShownList = model.getFilteredQuestionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
        }

        Question questionToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteQuestion(questionToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_QUESTION_SUCCESS, questionToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteQCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteQCommand) other).targetIndex)); // state check
    }
}
