package seedu.address.logic.parser;

import seedu.address.logic.commands.CommentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.comment.Comment;
import seedu.address.model.comment.CommentDescription;
import seedu.address.model.comment.CommentTitle;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT_DESCRIPTION;

public class CommentCommandParser implements Parser<CommentCommand> {

    public CommentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMMENT_TITLE, PREFIX_COMMENT_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_COMMENT_TITLE, PREFIX_COMMENT_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CommentCommand.MESSAGE_USAGE));
        }

        CommentTitle title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_COMMENT_TITLE).get());
        CommentDescription description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_COMMENT_DESCRIPTION).get());

        Comment comment = new Comment(title, description);

        return new CommentCommand(comment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
