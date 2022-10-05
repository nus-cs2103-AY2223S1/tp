package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

import java.util.ArrayList;
import java.util.List;

import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.UnassignCommand;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * Parses input arguments and creates a new UnassignCommand object
 */
public class UnassignCommandParser implements Parser<UnassignCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnassignCommand
     * and returns an UnassignCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnassignCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CLASS);

        if (!isPrefixPresent(argMultimap) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignCommand.MESSAGE_USAGE));
        }
        ModuleClass moduleClass = ParserUtil.parseModuleClass(argMultimap.getValue(PREFIX_MODULE_CLASS).get());

        List<Index> indices = new ArrayList<>();
        String indexArguments = argMultimap.getPreamble();
        String[] indicesString = indexArguments.split(" ");
        for (String indexString : indicesString) {
            try {
                Index index = ParserUtil.parseIndex(indexString);
                indices.add(index);
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        UnassignCommand.MESSAGE_USAGE), pe);
            }
        }

        return new UnassignCommand(indices, moduleClass);
    }

    /**
     * Returns true if the {@code PREFIX_MODULE_CLASS} does not contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_MODULE_CLASS).isPresent();
    }
}
