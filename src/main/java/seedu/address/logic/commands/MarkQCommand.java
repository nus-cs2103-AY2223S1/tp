package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.question.ImportantTag;
import seedu.address.model.question.Question;

/**
 * Marks an existing question as important in SETA.
 */
public class MarkQCommand extends Command {

    public static final String COMMAND_WORD = "markq";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a question as important"
            + " by the index number used in the question list.\n"
            + "Example: "
            + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_QUESTION_SUCCESS = "Marked %1$s as important";

    private final Index index;

    public MarkQCommand(Index index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof MarkQCommand)) {
            return false;
        }

        MarkQCommand e = (MarkQCommand) other;
        return index.equals(e.index);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Question> lastShownList = model.getFilteredQuestionList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
        }

        Question questionToEdit = lastShownList.get(index.getZeroBased());
        Question editedQuestion = new Question(
                questionToEdit.getDescription(),
                new ImportantTag(true));

        model.setQuestion(questionToEdit, editedQuestion);
        model.updateFilteredQuestionList(Model.PREDICATE_SHOW_ALL_QUESTIONS);

        return new CommandResult(String.format(MESSAGE_MARK_QUESTION_SUCCESS, editedQuestion));
    }
}
