package taskbook.logic.parser.tasks;

import java.time.LocalDate;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.tasks.TaskDeadlineCommand;
import taskbook.logic.parser.ArgumentMultimap;
import taskbook.logic.parser.ArgumentTokenizer;
import taskbook.logic.parser.CliSyntax;
import taskbook.logic.parser.Parser;
import taskbook.logic.parser.ParserUtil;
import taskbook.logic.parser.Prefix;
import taskbook.logic.parser.exceptions.ParseException;
import taskbook.model.person.Name;
import taskbook.model.tag.Tag;
import taskbook.model.task.Description;
import taskbook.model.task.enums.Assignment;

/**
 * Parses input arguments and creates a new TaskDeadlineCommand object.
 */
public class TaskDeadlineCommandParser implements Parser<TaskDeadlineCommand> {

    // Note: the space at the start of the arguments is necessary due to ArgumentTokenizer behavior.
    private static final Pattern ASSIGN_TO_COMMAND_FORMAT =
            Pattern.compile(String.format("\\s+%s.*", CliSyntax.PREFIX_ASSIGN_TO.getPrefix()));

    // Note: the space at the start of the arguments is necessary due to ArgumentTokenizer behavior.
    private static final Pattern ASSIGN_FROM_COMMAND_FORMAT =
            Pattern.compile(String.format("\\s+%s.*", CliSyntax.PREFIX_ASSIGN_FROM.getPrefix()));

    /**
     * Parses the given {@code String} of arguments in the context of the TaskDeadlineCommand
     * and returns an TaskDeadlineCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public TaskDeadlineCommand parse(String args) throws ParseException {
        if (args.contains(CliSyntax.PREFIX_ASSIGN_FROM.getPrefix())
                && args.contains(CliSyntax.PREFIX_ASSIGN_TO.getPrefix())) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskDeadlineCommand.MESSAGE_USAGE));
        }

        if (ASSIGN_TO_COMMAND_FORMAT.matcher(args).matches()) {
            return parseWithPrefix(args, CliSyntax.PREFIX_ASSIGN_TO);
        }

        if (ASSIGN_FROM_COMMAND_FORMAT.matcher(args).matches()) {
            return parseWithPrefix(args, CliSyntax.PREFIX_ASSIGN_FROM);
        }

        return parseWithoutPrefix(args);
    }

    private TaskDeadlineCommand parseWithPrefix(String args, Prefix firstPrefix) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, firstPrefix, CliSyntax.PREFIX_DESCRIPTION,
                        CliSyntax.PREFIX_DATE, CliSyntax.PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, firstPrefix, CliSyntax.PREFIX_DESCRIPTION, CliSyntax.PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskDeadlineCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(firstPrefix).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION).get());
        Assignment assignment =
                firstPrefix.equals(CliSyntax.PREFIX_ASSIGN_FROM)
                        ? Assignment.FROM
                        : Assignment.TO;
        LocalDate date = ParserUtil.parseDate(argMultimap.getValue(CliSyntax.PREFIX_DATE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(CliSyntax.PREFIX_TAG));

        return new TaskDeadlineCommand(name, description, assignment, date, tagList);
    }

    private TaskDeadlineCommand parseWithoutPrefix(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_DESCRIPTION, CliSyntax.PREFIX_DATE,
                        CliSyntax.PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_DESCRIPTION, CliSyntax.PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskDeadlineCommand.MESSAGE_USAGE));
        }

        Description description = ParserUtil.parseDescription(argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION).get());
        LocalDate date = ParserUtil.parseDate(argMultimap.getValue(CliSyntax.PREFIX_DATE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(CliSyntax.PREFIX_TAG));

        return new TaskDeadlineCommand(Name.SELF, description, Assignment.TO, date, tagList);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
