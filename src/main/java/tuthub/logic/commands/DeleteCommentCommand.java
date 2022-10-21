package tuthub.logic.commands;

import tuthub.commons.core.Messages;
import tuthub.commons.core.index.Index;
import tuthub.logic.commands.exceptions.CommandException;
import tuthub.model.Model;
import tuthub.model.tutor.Comment;
import tuthub.model.tutor.Tutor;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Deletes a comment from a tutor identified using it's displayed index from tuthub.
 */
public class DeleteCommentCommand extends Command {

    public static final String COMMAND_WORD = "deletecomment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a comment identified by the index number used in the displayed tutor list.\n"
            + "Parameters: INDEX OF TUTOR, INDEX OF COMMENT (must be positive integers)\n"
            + "Example: " + COMMAND_WORD + " 1 1";

    public static final String MESSAGE_DELETE_COMMENT_SUCCESS = "Deleted Comment: %1$s";

    private final Index tutorIndex;

    private final Index commentIndex;

    public DeleteCommentCommand(Index tutorIndex, Index commentIndex) {
        this.tutorIndex = tutorIndex;
        this.commentIndex = commentIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tutor> lastShownList = model.getFilteredTutorList();

        if (tutorIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
        }

        Tutor tutor = lastShownList.get(tutorIndex.getZeroBased());
        Comment commentToDelete = tutor.getComments().deleteComment(commentIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_DELETE_COMMENT_SUCCESS, commentToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommentCommand // instanceof handles nulls
                && tutorIndex.equals(((DeleteCommentCommand) other).tutorIndex)
                && commentIndex.equals(((DeleteCommentCommand) other).commentIndex)); // state check
    }
}
