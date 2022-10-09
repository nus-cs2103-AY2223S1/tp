package modtrekt.logic.parser;

import static modtrekt.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import modtrekt.commons.core.index.Index;
import modtrekt.logic.commands.Command;
import modtrekt.logic.commands.RemoveModuleCommand;
import modtrekt.logic.commands.RemoveTaskCommand;
import modtrekt.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

/**
 * Parses input arguments for remove command and returns a Command object
 */
public class RemoveCommandParser implements Parser<RemoveTaskCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    public Command parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                CliSyntax.PREFIX_MODULE, CliSyntax.PREFIX_TASK);
        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_TASK)) {
            // Remove task
            try {
                Index index = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_TASK).get());
                return new RemoveTaskCommand(index);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveTaskCommand.MESSAGE_USAGE), pe);
            }
        } else if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE)) {
            // Remove module
            try {
                Index index = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_MODULE).get());
                return new RemoveModuleCommand(index);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveModuleCommand.MESSAGE_USAGE), pe);
            }
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveTaskCommand.MESSAGE_USAGE));
    }

}
