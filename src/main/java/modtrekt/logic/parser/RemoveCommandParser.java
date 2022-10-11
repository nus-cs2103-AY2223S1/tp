package modtrekt.logic.parser;

import static modtrekt.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static modtrekt.logic.parser.ParserUtil.arePrefixesPresent;

import modtrekt.commons.core.index.Index;
import modtrekt.logic.commands.Command;
import modtrekt.logic.commands.RemoveModuleCommand;
import modtrekt.logic.commands.RemoveTaskCommand;
import modtrekt.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments for remove command and returns a Command object
 */
public class RemoveCommandParser implements Parser<RemoveTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of RemoveCommand.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
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
