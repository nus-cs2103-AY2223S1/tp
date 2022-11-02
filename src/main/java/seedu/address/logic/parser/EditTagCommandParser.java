package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY_STATUS;

import java.util.stream.Stream;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.DeadlineTag;
import seedu.address.model.tag.PriorityTag;

/**
 * EditTagCommandParser parses the prefix arguments given by the user to
 * create a EditTagCommand object.
 */
public class EditTagCommandParser implements Parser<EditTagCommand> {
    public static final String INVALID_INDEX_EDIT_TAG = "The index for tagedit should be a positive integer.";

    @Override
    public EditTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer
                .tokenize(args, PREFIX_DEADLINE, PREFIX_PRIORITY_STATUS);
        Index index;
        try {
            Integer.parseInt(argumentMultimap.getPreamble());
        } catch (NumberFormatException npe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTagCommand.MESSAGE_USAGE));
        }
        if (!areAnyPrefixesPresent(argumentMultimap, PREFIX_PRIORITY_STATUS, PREFIX_DEADLINE)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTagCommand.MESSAGE_USAGE));
        }
        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(INVALID_INDEX_EDIT_TAG);
        }
        String priorityStatus = argumentMultimap.getValue(PREFIX_PRIORITY_STATUS).orElse(null);
        String deadline = argumentMultimap.getValue(PREFIX_DEADLINE).orElse(null);
        PriorityTag priorityTag = priorityStatus != null ? ParserUtil
                .parsePriorityTag(priorityStatus) : null;
        DeadlineTag deadlineTag = deadline != null ? ParserUtil
                .parseDeadlineTag(deadline) : null;
        return new EditTagCommand(index, priorityTag, deadlineTag);
    }

    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
