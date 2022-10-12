package seedu.intrack.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.intrack.commons.core.index.Index;
import seedu.intrack.commons.exceptions.IllegalValueException;
import seedu.intrack.logic.commands.StatusCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;
import seedu.intrack.model.internship.Status;


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
        System.out.println("COMMAND IS:" + args);
        //ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STATUS);
        String[] splitedCommand = args.split("\\s+");

        Index index;
        try {
            //commented out parts are for if we wish to use prefix s/ in the future
            //index = ParserUtil.parseIndex(argMultimap.getPreamble());
            index = ParserUtil.parseIndex(splitedCommand[1]);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatusCommand.MESSAGE_USAGE), ive);
        }

        //this is the user input
        //String status = argMultimap.getValue(PREFIX_STATUS).orElse("");
        String status = splitedCommand[2];
        String value;

        System.out.println("INDEX IS:" + index);
        System.out.println("STATUS IS:" + status);

        if (status.equals("r") || status.equals("R")) {
            value = "Rejected";
        } else if (status.equals("o") || status.equals("O")) {
            value = "Offered";
        } else {
            value = "Progressing";
        }
        System.out.println("VALUE IS:" + value);

        //checks if the status command word is within the constraints set
        if (!status.equals("p") && !status.equals("P") && !status.equals("o")
                && !status.equals("O") && !status.equals("r") && !status.equals("R")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    StatusCommand.STATUS_COMMAND_CONSTRAINTS));
        }
        return new StatusCommand(index, new Status(value));
    }
}
