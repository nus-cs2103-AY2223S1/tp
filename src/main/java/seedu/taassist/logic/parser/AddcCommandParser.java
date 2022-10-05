package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

import seedu.taassist.logic.commands.AddcCommand;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Parses input arguments and creates a new AddcCommand object
 */
public class AddcCommandParser implements Parser<AddcCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddcCommand
     * and returns an AddcCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddcCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CLASS);

        if (!isPrefixPresent(argMultimap) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddcCommand.MESSAGE_USAGE));
        }

        ModuleClass moduleClass = ParserUtil.parseModuleClass(argMultimap.getValue(PREFIX_MODULE_CLASS).get());

        return new AddcCommand(moduleClass);
    }

    /**
     * Returns true if the {@code PREFIX_MODULE_CLASS} does not contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_MODULE_CLASS).isPresent();
    }
}
