package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import seedu.address.commons.FilterInfo;
import seedu.address.logic.commands.FilterTaskCommand;
import seedu.address.logic.commands.FilterTaskCommand.FilterTaskDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskCategory;
import seedu.address.model.task.TaskDate;

/**
 * Parses input arguments and creates a new FilterTaskCommand object
 */
public class FilterTaskParser implements Parser<FilterTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterTaskCommand
     * and returns a FilterTaskCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public FilterTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_DATE);

        FilterTaskDescriptor filterTaskDescriptor = new FilterTaskDescriptor();
        StringBuilder filterInfoCategory = new StringBuilder();
        StringBuilder filterInfoDate = new StringBuilder();

        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            TaskCategory category = ParserUtil.parseTaskCategory(argMultimap.getValue(PREFIX_CATEGORY).get());
            filterTaskDescriptor.setCategory(category);
            filterInfoCategory.append("category ").append(category.getCategoryName().toUpperCase());
        }

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            TaskDate date = ParserUtil.parseTaskDate(argMultimap.getValue(PREFIX_DATE).get());
            filterTaskDescriptor.setDate(date);
            filterInfoDate.append("deadline ").append(date).append(" or before");
        }

        if (!filterTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTaskCommand.MESSAGE_USAGE));
        }

        return new FilterTaskCommand(filterTaskDescriptor,
                new FilterInfo(filterInfoCategory.toString()),
                new FilterInfo(filterInfoDate.toString()));
    }
}
