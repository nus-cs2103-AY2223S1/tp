package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

import java.util.List;
import java.util.Set;

import seedu.taassist.logic.commands.AddcCommand;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Parses input arguments and creates a new AddcCommand object.
 */
public class AddcCommandParser implements Parser<AddcCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddcCommand
     * and returns an AddcCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddcCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CLASS);
        if (!argMultimap.containsPrefixes(PREFIX_MODULE_CLASS) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddcCommand.COMMAND_WORD,
                    AddcCommand.MESSAGE_USAGE));
        }
        List<String> moduleClassNames = argMultimap.getAllValuesIgnoreCase(PREFIX_MODULE_CLASS);
        Set<ModuleClass> moduleClasses = ParserUtil.parseModuleClasses(moduleClassNames);
        return new AddcCommand(moduleClasses);
    }
}
