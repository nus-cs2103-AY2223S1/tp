package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import java.util.Arrays;
import java.util.List;

import seedu.uninurse.logic.commands.FindCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.person.PatientMatchPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_CONDITION, PREFIX_TASK_DESCRIPTION, PREFIX_TAG);

        String[] keywords = argMultimap.getPreamble().trim().split("\\s+");

        return new FindCommand(new PatientMatchPredicate(
                sanitizeList(Arrays.asList(keywords)),
                sanitizeList(argMultimap.getAllValues(PREFIX_NAME)),
                sanitizeList(argMultimap.getAllValues(PREFIX_PHONE)),
                sanitizeList(argMultimap.getAllValues(PREFIX_EMAIL)),
                sanitizeList(argMultimap.getAllValues(PREFIX_ADDRESS)),
                sanitizeList(argMultimap.getAllValues(PREFIX_TAG)),
                sanitizeList(argMultimap.getAllValues(PREFIX_CONDITION)),
                sanitizeList(argMultimap.getAllValues(PREFIX_TASK_DESCRIPTION))));
    }

    /**
     * Adds an empty string if the list is empty.
     */
    private List<String> sanitizeList(List<String> list) {
        if (list.isEmpty()) {
            list.add("");
        }
        return list;
    }
}
