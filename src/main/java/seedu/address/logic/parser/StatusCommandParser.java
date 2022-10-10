package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.StatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.Status;


/**
 * Parses input arguments and creates a new {@code StatusCommand} object
 */
public class StatusCommandParser implements Parser<StatusCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code StatusCommand}
     * and returns a {@code StatusCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STATUS);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatusCommand.MESSAGE_USAGE), ive);
        }

        String status = argMultimap.getValue(PREFIX_STATUS).orElse("");
        String value;

        if (status.equals("r") || status.equals("R")) {
            value = "Rejected";
        } else if (status.equals("o") || status.equals("O")) {
            value = "Offered";
        } else {
            value = "Progress";
        }

        //checks if the status command word is within the constraints set
        if (!status.equals("p") && !status.equals("P") && !status.equals("o")
                && !status.equals("O") && !status.equals("r") && !status.equals("R")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    StatusCommand.STATUS_COMMAND_CONSTRAINTS));
        }
        return new StatusCommand(index, new Status(value));
    }
}
