package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_ASSIGNEE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_ASSIGNEE_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_DEADLINE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_DEADLINE_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parse input arguments and creates a new EditTaskCommand object.
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {

    private final Options options;

    /**
     * Creates an EditTaskCommandParser with default options.
     */
    public EditTaskCommandParser() {
        Options options = new Options();
        options.addOption(FLAG_NAME_STR, FLAG_NAME_STR_LONG, true, "Name of task");
        options.addOption(FLAG_DEADLINE_STR, FLAG_DEADLINE_STR_LONG, true, "Deadline of task");
        options.addOption(FLAG_ASSIGNEE_STR, FLAG_ASSIGNEE_STR_LONG, true, "Assignees of task");
        this.options = options;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditTaskCommand
     * and returns an EditTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public EditTaskCommand parse(String args) throws ParseException {
        try {
            CommandLineParser parser = new DefaultParser();
            String[] argsArray = ArgumentTokenizer.tokenize(args);
            CommandLine cmd = parser.parse(options, argsArray);
            EditTaskCommand.EditTaskDescriptor editTaskDescriptor = new EditTaskCommand.EditTaskDescriptor();

            if (cmd.getArgs().length > 1) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE));
            }

            Index index;
            try {
                index = ParserUtil.parseIndex(cmd.getArgs()[0]);
            } catch (ParseException | IndexOutOfBoundsException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        EditTaskCommand.MESSAGE_USAGE), e);
            }

            if (cmd.hasOption(FLAG_NAME_STR)) {
                String name = cmd.getOptionValue(FLAG_NAME_STR);
                editTaskDescriptor.setName(name);
            }

            if (cmd.hasOption(FLAG_ASSIGNEE_STR)) {
                String[] names = cmd.getOptionValues(FLAG_ASSIGNEE_STR);
                editTaskDescriptor.setAssignees(names);
            }

            if (cmd.hasOption(FLAG_DEADLINE_STR)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime deadline = LocalDateTime.parse(cmd.getOptionValue(FLAG_DEADLINE_STR), formatter);
                editTaskDescriptor.setDeadline(deadline);
            }

            if (!editTaskDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditTaskCommand.MESSAGE_NOT_EDITED);
            }
            return new EditTaskCommand(index, editTaskDescriptor);
        } catch (MissingArgumentException e) {
            Option opt = e.getOption();
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE));
        } catch (org.apache.commons.cli.ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE));
        }
    }
}
