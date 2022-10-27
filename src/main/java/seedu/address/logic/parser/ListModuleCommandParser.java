package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.logic.commands.list.ListModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Module;
import seedu.address.model.task.ModuleContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ListModuleCommand object
 */
public class ListModuleCommandParser implements Parser<ListModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListModuleCommand
     * and returns a ListModuleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListModuleCommand parse(String args) throws ParseException {
        String[] listTypes = args.split(" ", 2);

        if (listTypes.length < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, Module.MESSAGE_CONSTRAINTS));
        }

        return new ListModuleCommand(getPredicate(listTypes[1]));
    }

    public ModuleContainsKeywordsPredicate getPredicate(String keywords) throws ParseException {
        ParserUtil.parseModule(keywords);

        return new ModuleContainsKeywordsPredicate(List.of(keywords));
    }
}
