package taskbook.logic.parser.categoryless;

import static taskbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static taskbook.logic.parser.CliSyntax.PREFIX_HELP_COMMAND;

import java.util.HashMap;
import java.util.Map;

import taskbook.logic.commands.categoryless.HelpCommand;
import taskbook.logic.parser.ArgumentMultimap;
import taskbook.logic.parser.ArgumentTokenizer;
import taskbook.logic.parser.Parser;
import taskbook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object.
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    private static final Map<String, HelpCommand.CommandWord> mapToEnum;
    static {
        mapToEnum = new HashMap<>();
        for (HelpCommand.CommandWord cw : HelpCommand.CommandWord.values()) {
            mapToEnum.put(cw.toString(), cw);
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_HELP_COMMAND);
        if (argumentMultimap.getValue(PREFIX_HELP_COMMAND).isEmpty()) {
            return new HelpCommand(null);
        }

        String commandArgument = argumentMultimap.getValue(PREFIX_HELP_COMMAND).get();
        HelpCommand.CommandWord commandWord = parseToEnum(commandArgument);

        return new HelpCommand(commandWord);
    }

    private HelpCommand.CommandWord parseToEnum(String commandArgument) throws ParseException {
        if (!mapToEnum.containsKey(commandArgument)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        return mapToEnum.get(commandArgument);
    }
}
