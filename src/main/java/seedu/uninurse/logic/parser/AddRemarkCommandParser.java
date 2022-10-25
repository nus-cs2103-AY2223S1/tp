package seedu.uninurse.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.NoSuchElementException;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.AddRemarkCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.remark.Remark;

/**
 * Parses input arguments and creates a new {@code AddRemarkCommand}.
 */
public class AddRemarkCommandParser implements Parser<AddRemarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddRemarkCommand
     * and returns an AddRemarkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddRemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);

        try {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            Remark remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).orElseThrow());
            return new AddRemarkCommand(index, remark);
            // TODO: display specific error messages
        } catch (ParseException pe) {
            // handles invalid indices and remarks
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddRemarkCommand.MESSAGE_USAGE), pe);
        } catch (NoSuchElementException nse) {
            // Handles missing prefix
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRemarkCommand.MESSAGE_USAGE));
        }
    }
}
