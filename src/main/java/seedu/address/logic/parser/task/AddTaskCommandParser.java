package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;

import java.util.Set;

import seedu.address.logic.commands.task.AddTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.TaskParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Project;
import seedu.address.model.task.Contact;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DEADLINE, PREFIX_CONTACT, PREFIX_PROJECT);

        if (argMultimap.getPreamble().isEmpty() || argMultimap.getValue(PREFIX_DEADLINE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        Title title = TaskParserUtil.parseTitle(argMultimap.getPreamble());
        Deadline deadline = TaskParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());

        Project project;
        if (argMultimap.getValue(PREFIX_PROJECT).isPresent()) {
            project = TaskParserUtil.parseProject(argMultimap.getValue(PREFIX_PROJECT).get());
        } else {
            project = Project.UNSPECIFIED;
        }

        Set<Contact> contactList = TaskParserUtil.parseContacts(argMultimap.getAllValues(PREFIX_CONTACT));

        Task task = new Task(title, false, deadline, project, contactList);

        return new AddTaskCommand(task);
    }
}
