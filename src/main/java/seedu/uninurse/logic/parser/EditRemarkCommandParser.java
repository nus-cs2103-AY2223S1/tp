package seedu.uninurse.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.List;
import java.util.NoSuchElementException;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.EditRemarkCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.remark.Remark;

/**
 * Parses input arguments and creates a new EditRemarkCommand object
 */
public class EditRemarkCommandParser implements Parser<EditRemarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditRemarkCommand
     * and returns an EditRemarkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditRemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);

        try {
            List<Index> indices = ParserUtil.parseTwoIndex(argMultimap.getPreamble());
            Remark updatedRemark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).orElseThrow());

            return new EditRemarkCommand(indices.get(0), indices.get(1), updatedRemark);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditRemarkCommand.MESSAGE_USAGE), pe);
        } catch (NoSuchElementException nse) {
            throw new ParseException(EditRemarkCommand.MESSAGE_NOT_EDITED);
        }
    }
}
