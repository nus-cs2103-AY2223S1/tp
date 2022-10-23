package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
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
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        }

        Scanner argScanner = new Scanner(trimmedArgs);
        Prefix prefix = new Prefix(argScanner.next());

        if (prefix.equals(PREFIX_NAME) && argScanner.hasNextLine()) {

            String trimmedKeywords = argScanner.nextLine().trim();
            String[] nameKeywords = trimmedKeywords.split("\\s+");

            return new FindTaskCommand(new TaskContainsKeywordsPredicate((Arrays.asList(nameKeywords))));

        } else if (prefix.equals(PREFIX_MODULE) && argScanner.hasNextLine()) {

            String trimmedModules = argScanner.nextLine().trim();
            String[] modKeywords = trimmedModules.split("\\s+");
            ArrayList<Module> mods = new ArrayList<>();
            for (String modName : modKeywords) {
                mods.add(new Module(modName));
            }

            return new FindTaskCommand(new TaskContainsModulesPredicate(mods));

        } else {

            String[] nameKeywords = trimmedArgs.split("\\s+");

            return new FindTaskCommand(new TaskContainsKeywordsPredicate(Arrays.asList(nameKeywords)));

        }
    }

}
