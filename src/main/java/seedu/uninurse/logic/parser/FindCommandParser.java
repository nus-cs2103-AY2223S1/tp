package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
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
     * Parses the given arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @param args The given string of arguments.
     * @return FindCommand
     * @throws ParseException if the user input does not conform to the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireAllNonNull(args);

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
