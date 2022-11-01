package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskContainsKeywordsPredicate;
import seedu.address.model.task.TaskContainsModulesPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindTaskCommandParser implements Parser<FindTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MODULE);
        Prefix searchPrefix = getSearchPrefix(argMultimap);

        if (searchPrefix.equals(PREFIX_NAME)) {

            List<String> strings = argMultimap.getAllValues(PREFIX_NAME);
            List<String> keywordsSpaceSeparated = new ArrayList<>();
            for (String string : strings) {
                for (String keyword : string.split("\\s+")) {
                    keywordsSpaceSeparated.add(keyword);
                }
            }
            // ["name", "name name"] -> ["name", "name", "name"]
            return new FindTaskCommand(new TaskContainsKeywordsPredicate(keywordsSpaceSeparated));

        } else if (searchPrefix.equals(PREFIX_MODULE)) {

            List<String> strings = argMultimap.getAllValues(PREFIX_MODULE);
            List<String> keywordsSpaceSeparated = new ArrayList<>();
            for (String string : strings) {
                for (String keyword : string.split("\\s+")) {
                    keywordsSpaceSeparated.add(keyword);
                }
            }
            // ["name", "name name"] -> ["name", "name", "name"]
            return new FindTaskCommand(new TaskContainsModulesPredicate(keywordsSpaceSeparated));

        } else {

            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));

        }
    }

    private static Prefix getSearchPrefix(ArgumentMultimap argumentMultimap) throws ParseException {
        List<Prefix> searchablePrefixes = new ArrayList<>();
        searchablePrefixes.add(PREFIX_NAME);
        searchablePrefixes.add(PREFIX_MODULE);
        searchablePrefixes.add(PREFIX_TASK);

        List<Prefix> prefixesInArgs = new ArrayList<>();

        for (Prefix prefix : searchablePrefixes) {
            if (!argumentMultimap.getAllValues(prefix).isEmpty()) {
                prefixesInArgs.add(prefix);
            }
        }

        // if number of prefixes in arguments is not 1, the arguments are invalid
        if (prefixesInArgs.size() != 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        }

        return prefixesInArgs.get(0);
    }

}
