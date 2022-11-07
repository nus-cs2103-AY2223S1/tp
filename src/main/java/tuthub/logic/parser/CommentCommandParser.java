package tuthub.logic.parser;

import static java.util.Objects.requireNonNull;
import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tuthub.logic.parser.CliSyntax.PREFIX_COMMENT;

import tuthub.commons.core.index.Index;
import tuthub.commons.exceptions.IllegalValueException;
import tuthub.logic.commands.CommentCommand;
import tuthub.logic.parser.exceptions.ParseException;
import tuthub.model.tutor.Comment;

/**
 * Parses input arguments and creates a new {@code CommentCommand} object
 */
public class CommentCommandParser implements Parser<CommentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code CommentCommand}
     * and returns a {@code CommentCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CommentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_COMMENT);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CommentCommand.MESSAGE_USAGE), ive);
        }

        String comment = argMultimap.getValue(PREFIX_COMMENT).orElse("");

        return new CommentCommand(index, new Comment(comment));
    }
}
