package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIXES_PATIENT_ALL;

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
        if (args.trim().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIXES_PATIENT_ALL);

        String[] keywords = argMultimap.getPreamble().trim().split("\\s+");
        return new FindCommand(new PatientMatchPredicate(List.of(keywords), argMultimap));
    }
}
