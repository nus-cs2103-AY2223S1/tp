package soconnect.logic.parser.todo;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.parser.ArgumentTokenizer.PrefixArgument;
import static soconnect.logic.parser.ArgumentTokenizer.tokenizeToList;
import static soconnect.logic.parser.CliSyntax.INDICATOR_DATE;
import static soconnect.logic.parser.CliSyntax.INDICATOR_PRIORITY;
import static soconnect.logic.parser.CliSyntax.INDICATOR_TAG;
import static soconnect.logic.parser.CliSyntax.PREFIX_DATE;
import static soconnect.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static soconnect.model.Model.PREDICATE_SHOW_ALL_TODOS;

import java.util.List;

import soconnect.logic.commands.todo.TodoShowCommand;
import soconnect.logic.parser.Parser;
import soconnect.logic.parser.ParserUtil;
import soconnect.logic.parser.Prefix;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.tag.Tag;
import soconnect.model.todo.Date;
import soconnect.model.todo.Priority;
import soconnect.model.todo.predicates.TodoContainsDatePredicate;
import soconnect.model.todo.predicates.TodoContainsDateRangePredicate;
import soconnect.model.todo.predicates.TodoContainsPriorityPredicate;
import soconnect.model.todo.predicates.TodoContainsTagPredicate;
import soconnect.ui.TodoListPanel;

/**
 * Parses input arguments and creates a new {@code TodoShowCommand} object.
 */
public class TodoShowCommandParser implements Parser<TodoShowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code TodoShowCommand}
     * and returns a {@code TodoShowCommand} object for execution.
     *
     * @throws ParseException If the user input does not conform with the expected format.
     */
    public TodoShowCommand parse(String args) throws ParseException {
        requireNonNull(args);

        List<PrefixArgument> argList = tokenizeToList(args, PREFIX_DATE, PREFIX_PRIORITY,
            PREFIX_TAG);

        PrefixArgument preambleCondition;

        switch (argList.size()) {
        case 1: // Only contains preamble and it's argument
            preambleCondition = argList.get(0);
            return parseShowPreambleCondition(preambleCondition);
        case 2: // First argument is the preamble. Second is the prefix argument
            preambleCondition = argList.get(0);
            String preambleArg = preambleCondition.getArgument();
            if (!preambleArg.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TodoShowCommand.MESSAGE_USAGE));
            }

            PrefixArgument prefixCondition = argList.get(1);
            return parseShowPrefixCondition(prefixCondition);
        default: // Only accept one condition, either preamble or prefix
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TodoShowCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the preamble filter condition in {@code prefixArg} into a {@code TodoShowCommand}.
     *
     * @param preambleCondition The preamble prefix argument.
     * @return The {@code TodoShowCommand} with the preamble filter condition.
     * @throws ParseException If the given {@code prefixArg} is invalid.
     */
    private TodoShowCommand parseShowPreambleCondition(PrefixArgument preambleCondition) throws ParseException {
        String arg = preambleCondition.getArgument();

        switch (arg) {
        case TodoShowCommand.EMPTY_CONDITION:
            return new TodoShowCommand(PREDICATE_SHOW_ALL_TODOS, TodoListPanel.ALL_HEADER);
        case TodoShowCommand.TODAY_CONDITION:
            return new TodoShowCommand(TodoContainsDatePredicate.currentDate(), TodoListPanel.TODAY_HEADER);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TodoShowCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the prefix filter condition in {@code prefixArg} into a {@code TodoShowCommand}.
     *
     * @param prefixCondition The prefix argument.
     * @return The {@code TodoShowCommand} with the prefix filter condition.
     * @throws ParseException If the given {@code prefixArg} is invalid.
     */
    private TodoShowCommand parseShowPrefixCondition(PrefixArgument prefixCondition) throws ParseException {
        Prefix prefix = prefixCondition.getPrefix();
        String arg = prefixCondition.getArgument();

        switch (prefix.getPrefix()) {
        case INDICATOR_DATE:
            return parseShowDateCondition(arg);
        case INDICATOR_PRIORITY:
            Priority priority = ParserUtil.parsePriority(arg);
            TodoContainsPriorityPredicate priorityPredicate = new TodoContainsPriorityPredicate(priority);
            return new TodoShowCommand(priorityPredicate, priority.toString());

        case INDICATOR_TAG:
            Tag tag = ParserUtil.parseTag(arg);
            TodoContainsTagPredicate tagPredicate = new TodoContainsTagPredicate(tag);
            return new TodoShowCommand(tagPredicate, tag.tagName);

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TodoShowCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the date filter condition in {@code dateArg} into a {@code TodoShowCommand}.
     *
     * @param dateArg The argument containing either a date or a date range.
     * @return The {@code TodoShowCommand} with the date filter condition.
     * @throws ParseException If the given {@code dateArg} is invalid.
     */
    private TodoShowCommand parseShowDateCondition(String dateArg) throws ParseException {
        String[] dateList = dateArg.split(" to ", 2);
        switch (dateList.length) {
        case 1:
            try {
                Date date = ParserUtil.parseDate(dateList[0]);
                TodoContainsDatePredicate datePredicate = new TodoContainsDatePredicate(date);
                return new TodoShowCommand(datePredicate, date.toString());
            } catch (ParseException e) {
                throw new ParseException(Date.MESSAGE_INVALID_DATE_RANGE);
            }
        case 2:
            List<Date> validDateRange = ParserUtil.parseDateRange(dateList[0], dateList[1]);
            TodoContainsDateRangePredicate dateRangePredicate = new TodoContainsDateRangePredicate(
                    validDateRange.get(0), validDateRange.get(1));
            String dateRangeHeader = validDateRange.get(0) + " to " + validDateRange.get(1);
            return new TodoShowCommand(dateRangePredicate, dateRangeHeader);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TodoShowCommand.MESSAGE_USAGE));
        }
    }
}
