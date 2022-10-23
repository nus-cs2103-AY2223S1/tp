package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FindContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.person.ModuleTakenPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindContactCommand object
 */
public class FindContactCommandParser implements Parser<FindContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindContactCommand
     * and returns a FindContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindContactCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MODULE);

        Prefix searchPrefix = getSearchPrefix(argMultimap);

        if (searchPrefix.equals(PREFIX_NAME)) {

            List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
            return new FindContactCommand(new NameContainsKeywordsPredicate(nameKeywords));

        } else if (searchPrefix.equals(PREFIX_MODULE)) {

            List<Module> moduleNames = ParserUtil.parseModules(argMultimap.getAllValues(PREFIX_MODULE)).stream().collect(Collectors.toList());
            return new FindContactCommand(new ModuleTakenPredicate((moduleNames)));

        } else if (searchPrefix.equals(PREFIX_TASK)) {

            int taskIndex = Integer.parseInt(argMultimap.getValue(PREFIX_TASK).get());
            return new FindContactCommand(new ModuleTakenPredicate(taskIndex));

        } else {

            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));

        }
    }

    private static Prefix getSearchPrefix(ArgumentMultimap argumentMultimap) throws ParseException {
        List<Prefix> searchablePrefixes = new ArrayList<>();
        searchablePrefixes.add(PREFIX_NAME);
        searchablePrefixes.add(PREFIX_MODULE);
        List<Prefix> prefixesInArgs = new ArrayList<>();

        for (Prefix prefix : searchablePrefixes) {
            if (!argumentMultimap.getAllValues(prefix).isEmpty()) {
                prefixesInArgs.add(prefix);
            }
        }

        // if number of prefixes in arguments is not 1, the arguments are invalid
        if (prefixesInArgs.size() != 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
        }

        return prefixesInArgs.get(0);
    }

}
