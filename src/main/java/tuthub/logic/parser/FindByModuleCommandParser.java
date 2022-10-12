package tuthub.logic.parser;

import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import tuthub.logic.commands.FindByModuleCommand;
import tuthub.logic.parser.exceptions.ParseException;
import tuthub.model.tutor.ModuleContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindByModuleCommand object.
 */
public class FindByModuleCommandParser implements Parser<FindByModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindByModuleCommand
     * and returns a FindByModuleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindByModuleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByModuleCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindByModuleCommand(new ModuleContainsKeywordPredicate(Arrays.asList(nameKeywords)));
    }
}
