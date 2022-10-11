package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
        }

        // John Lim Jacky
        // n/ Johnathan Sally
        // m/ Jacob

        Scanner argScanner = new Scanner(trimmedArgs);
        Prefix prefix = new Prefix(argScanner.next());

        if (prefix.equals(PREFIX_NAME)) {

            String trimmedKeywords = argScanner.nextLine().trim();
            String[] nameKeywords = trimmedKeywords.split("\\s+");
            return new FindContactCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));

        } else if (prefix.equals(PREFIX_MODULE)) {

            String trimmedModules = argScanner.nextLine().trim();
            String[] modKeywords = trimmedModules.split("\\s+");
            ArrayList<Module> mods = new ArrayList<>();
            for (String modName : modKeywords) {
                mods.add(new Module(modName));
            }
            return new FindContactCommand(new ModuleTakenPredicate(mods));

        } else {

            String[] nameKeywords = trimmedArgs.split("\\s+");
            return new FindContactCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));

        }

    }

}
