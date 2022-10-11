package modtrekt.logic.parser;

import static modtrekt.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static modtrekt.logic.parser.ParserUtil.arePrefixesPresent;

import modtrekt.logic.commands.AddTaskCommand;
import modtrekt.logic.parser.exceptions.ParseException;
import modtrekt.model.module.ModCode;
import modtrekt.model.task.Description;
import modtrekt.model.task.Task;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_TASK, CliSyntax.PREFIX_MOD_CODE);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_TASK, CliSyntax.PREFIX_MOD_CODE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        Description description = ParserUtil.parseDescription(argMultimap.getValue(CliSyntax.PREFIX_TASK).get());
        ModCode module = ParserUtil.parseCode(argMultimap.getValue(CliSyntax.PREFIX_MOD_CODE).get());

        Task t = new Task(description, module);

        return new AddTaskCommand(t);
    }

}
