package tuthub.logic.commands;

import static tuthub.commons.util.CollectionUtil.requireAllNonNull;
import static tuthub.logic.parser.CliSyntax.PREFIX_COMMENT;
import static tuthub.model.Model.PREDICATE_SHOW_ALL_TUTORS;

import java.util.List;

import tuthub.commons.core.Messages;
import tuthub.commons.core.index.Index;
import tuthub.logic.commands.exceptions.CommandException;
import tuthub.model.Model;
import tuthub.model.tutor.Comment;
import tuthub.model.tutor.CommentList;
import tuthub.model.tutor.Tutor;

/**
 * Adds a comment to an existing tutor in tuthub.
 */
public class CommentCommand extends Command {

    public static final String COMMAND_WORD = "comment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a comment to the tutor identified "
            + "by the index number used in the last tutor listing. "
            + "Existing comment will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_COMMENT + "[COMMENT]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_COMMENT + "Good class management";

    public static final String MESSAGE_ADD_COMMENT_SUCCESS = "Added comment to Tutor: %1$s";
    public static final String MESSAGE_ADD_COMMENT_FAILURE = "There is no comment!";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Comment: %2$s";

    private final Index index;
    private final Comment comment;

    /**
     * @param index of the tutor in the filtered tutor list to edit the comment
     * @param comment of the tutor to be updated to
     */
    public CommentCommand(Index index, Comment comment) {
        requireAllNonNull(index, comment);

        this.index = index;
        this.comment = comment;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommentCommand)) {
            return false;
        }

        // state check
        CommentCommand e = (CommentCommand) other;
        return index.equals(e.index)
                && comment.equals(e.comment);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Tutor> lastShownList = model.getFilteredTutorList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
        }

        if (comment.value.isEmpty()) {
            throw new CommandException(MESSAGE_ADD_COMMENT_FAILURE);
        }

        Tutor tutorToEdit = lastShownList.get(index.getZeroBased());
        CommentList comments = tutorToEdit.getComments();
        comments = comments.addComment(comment);
        Tutor editedTutor = new Tutor(
                tutorToEdit.getName(), tutorToEdit.getPhone(), tutorToEdit.getEmail(),
                tutorToEdit.getModules(), tutorToEdit.getYear(), tutorToEdit.getStudentId(),
                comments, tutorToEdit.getTeachingNomination(), tutorToEdit.getRating(), tutorToEdit.getTags());

        model.setTutor(tutorToEdit, editedTutor);
        model.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);

        return new CommandResult(generateSuccessMessage(editedTutor));

    }

    /**
     * Generates a command execution success message based on whether
     * the comment is added to or removed from
     * {@code tutorToEdit}.
     */
    private String generateSuccessMessage(Tutor tutorToEdit) {
        String message = MESSAGE_ADD_COMMENT_SUCCESS;
        return String.format(message, tutorToEdit);
    }
}
