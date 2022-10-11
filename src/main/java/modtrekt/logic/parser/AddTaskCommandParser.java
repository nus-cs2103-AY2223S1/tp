package modtrekt.logic.parser;

import static modtrekt.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static modtrekt.logic.parser.ParserUtil.arePrefixesPresent;

import java.time.LocalDate;

import modtrekt.logic.commands.AddDeadlineCommand;
import modtrekt.logic.commands.AddTaskCommand;
import modtrekt.logic.commands.Command;
import modtrekt.logic.commands.utils.AddCommandMessages;
import modtrekt.logic.parser.exceptions.ParseException;
import modtrekt.model.module.ModCode;
import modtrekt.model.task.Deadline;
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
    public Command parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_TASK, CliSyntax.PREFIX_MOD_CODE,
                        CliSyntax.PREFIX_DEADLINE);

        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_TASK, CliSyntax.PREFIX_MOD_CODE,
                CliSyntax.PREFIX_DEADLINE)) {
            Description description = ParserUtil.parseDescription(argMultimap.getValue(CliSyntax.PREFIX_TASK).get());
            LocalDate dueDate = ParserUtil.parseDueDate(argMultimap.getValue(CliSyntax.PREFIX_DEADLINE).get());
            ModCode code = ParserUtil.parseCode(argMultimap.getValue(CliSyntax.PREFIX_MOD_CODE).get());
            Task t = new Deadline(description, code, dueDate);
            return new AddDeadlineCommand(t);
        } else if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_TASK, CliSyntax.PREFIX_MOD_CODE)) {
            // Add task
            Description description = ParserUtil.parseDescription(argMultimap.getValue(CliSyntax.PREFIX_TASK).get());
            ModCode code = ParserUtil.parseCode(argMultimap.getValue(CliSyntax.PREFIX_MOD_CODE).get());

            Task t = new Task(description, code);
            return new AddTaskCommand(t);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommandMessages.COMBINED_TASK_DEADLINE_USAGE));

    }

}
