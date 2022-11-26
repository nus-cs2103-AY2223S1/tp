package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY_STATUS;

import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
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
    public static final String INVALID_INDEX_EDIT_TAG = "The index for tagedit should be an unsigned "
            + "positive integer greater than 0 "
            + "and lesser than 2147483648.";
    private final Logger logger = LogsCenter.getLogger(EditTagCommand.class);

    @Override
    public EditTagCommand parse(String args) throws ParseException {
        logger.info("Parsing arguments for EditTagCommand.");
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer
                .tokenize(args, PREFIX_DEADLINE, PREFIX_PRIORITY_STATUS);
        Index index;

        if (!argumentMultimap.getPreamble().matches("(-|\\+)?\\d+(\\.\\d+)?")) {
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

    //@@author dlimyy-reused
    //Reused from existing AB3 (https://github.com/nus-cs2103-AY2223S1/tp)
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
    //@@author
}
