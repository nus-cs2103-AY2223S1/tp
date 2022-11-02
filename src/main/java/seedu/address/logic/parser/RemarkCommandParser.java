package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.remark.Remark;
import seedu.address.model.remark.Text;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class RemarkCommandParser implements Parser<RemarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Index index;
        String[] preambles = argMultimap.getPreamble().split(" ", 2);

        if (!isValidInput(preambles)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(preambles[0]);
        } catch (ParseException ive) {
            throw new ParseException(MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX, ive);
        }

        if (preambles.length < 2 || preambles[1].isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemarkCommand.MESSAGE_USAGE));
        }

        Text text = ParserUtil.parseText(preambles[1]);

        Remark remark = new Remark(text);

        return new RemarkCommand(index, remark);
    }

    private boolean isValidInput(String[] inputs) {
        if (inputs.length != 2) {
            return false;
        }

        try {
            Integer.parseInt(inputs[0]);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
