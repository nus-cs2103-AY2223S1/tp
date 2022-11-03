package seedu.watson.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.watson.commons.core.index.Index;
import seedu.watson.logic.commands.RemarkCommand;
import seedu.watson.logic.parser.exceptions.ParseException;
import seedu.watson.model.student.Remark;

/**
 * Parses input arguments and creates a new RemarkCommand object
 */
public class RemarkCommandParser implements Parser<RemarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RemarkCommand
     * and returns a RemarkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty() || trimmedArgs.split("\\s+").length < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE));
        }

        String[] remarkKeywords = trimmedArgs.split("\\s+", 2);
        Index index = ParserUtil.parseIndex(remarkKeywords[0]);
        Remark remark = ParserUtil.parseRemark(remarkKeywords[1]);
        return new RemarkCommand(index, remark);
    }
}
