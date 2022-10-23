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
import java.time.format.DateTimeParseException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Please input date in this format: "
            + "YYYY-MM-DD HH:mm \n"
            + "Example: 2023-12-24 23:59";
    private final Options options;

    /**
     * Constructor for AddTaskCommandParser.
     */
    public AddTaskCommandParser() {
        Options options = new Options();
        options.addRequiredOption(FLAG_NAME_STR, FLAG_NAME_STR_LONG, true, "Name of task");
        options.addOption(FLAG_DEADLINE_STR, FLAG_DEADLINE_STR_LONG, true, "Deadline of task");
        options.addOption(FLAG_ASSIGNEE_STR, FLAG_ASSIGNEE_STR_LONG, true, "Assignees of task");
        this.options = options;
    }
    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns a AddTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        try {
            CommandLineParser parser = new DefaultParser();
            String[] argsArray = ArgumentTokenizer.tokenize(args);
            CommandLine cmd = parser.parse(options, argsArray);

            if (cmd.getArgs().length > 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
            }

            String name = cmd.getOptionValue(FLAG_NAME_STR);
            String [] emptyAssigneeArray = {};
            String[] assignees = cmd.hasOption(FLAG_ASSIGNEE_STR)
                    ? cmd.getOptionValues(FLAG_ASSIGNEE_STR)
                    : emptyAssigneeArray;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime deadline = cmd.hasOption(FLAG_DEADLINE_STR)
                    ? LocalDateTime.parse(cmd.getOptionValue(FLAG_DEADLINE_STR), formatter)
                    : null;
            return new AddTaskCommand(name, assignees, deadline);
        } catch (MissingArgumentException e) {
            Option opt = e.getOption();
            switch (opt.getOpt()) {
            case FLAG_NAME_STR:
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddTaskCommand.MESSAGE_USAGE));
            }
        } catch (org.apache.commons.cli.ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_DATE_FORMAT);
        }
    }
}
