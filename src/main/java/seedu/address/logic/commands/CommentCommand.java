package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT_TITLE;

import seedu.address.model.Model;
import seedu.address.model.comment.Comment;

public class CommentCommand extends Command {
    public static final String COMMAND_WORD = "comment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a comment. "
            + "Parameters: "
            + PREFIX_COMMENT_TITLE + "TITLE "
            + PREFIX_COMMENT_DESCRIPTION + "DESCRIPTION... \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMMENT_TITLE + "Test Title "
            + PREFIX_COMMENT_DESCRIPTION + "Very descriptive description ";

    public static final String MESSAGE_SUCCESS = "New comment added: %1$s";
//    public static final String MESSAGE_DUPLICATE_STUDENT = "This comment already exists";

    private final Comment toAdd;

    public CommentCommand(Comment comment) {
        requireNonNull(comment);
        toAdd = comment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

//        if (model.hasComment(toAdd)) {
//            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
//        }

        model.addComment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommentCommand // instanceof handles nulls
                && toAdd.equals(((CommentCommand) other).toAdd));
    }
}
