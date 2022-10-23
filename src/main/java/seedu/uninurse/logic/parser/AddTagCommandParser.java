package seedu.uninurse.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.NoSuchElementException;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.AddTagCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.tag.Tag;

/**
 * Parses input arguments and creates a new {@code AddTagCommand}.
 */
public class AddTagCommandParser implements Parser<AddTagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddTagCommand
     * and returns an AddTagCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        try {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            Tag tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).orElseThrow());
            return new AddTagCommand(index, tag);
            // TODO: display specific error messages
        } catch (ParseException pe) { // handles invalid indices and tags
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTagCommand.MESSAGE_USAGE), pe);
        } catch (NoSuchElementException nse) { // Handles missing prefix
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        }

    }
}
