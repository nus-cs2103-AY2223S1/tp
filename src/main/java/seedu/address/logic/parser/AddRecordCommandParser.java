package seedu.address.logic.parser;

import seedu.address.logic.commands.AddRecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Record;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class AddRecordCommandParser implements Parser<AddRecordCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddRecordCommand
     * and returns a AddRecordCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddRecordCommand parse(String args) throws ParseException {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_RECORD);

            if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_RECORD)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecordCommand.MESSAGE_USAGE));
            }

            String recordDate = argMultimap.getValue(PREFIX_DATE).get();
            String recordData = argMultimap.getValue(PREFIX_RECORD).get();

            Record record = ParserUtil.parseRecord(recordDate, recordData);

            return new AddRecordCommand(record);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
