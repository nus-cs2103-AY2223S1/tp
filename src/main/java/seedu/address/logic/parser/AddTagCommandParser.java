package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY_STATUS;

import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.DeadlineTag;
import seedu.address.model.tag.PriorityTag;

/**
 * AddTagCommandParser parses the arguments and supplies these arguments
 * to the AddTagCommand.
 */
public class AddTagCommandParser implements Parser<AddTagCommand> {
    private final Logger logger = LogsCenter.getLogger(AddTagCommand.class);
    @Override
    public AddTagCommand parse(String args) throws ParseException {
        logger.info("Parsing arguments for AddTagCommand");
        requireNonNull(args);
        String[] indexWithTags = args.strip().split("\\s", 2);
        if (indexWithTags.length != 2 || !indexWithTags[0].matches("(-|\\+)?\\d+(\\.\\d+)?")) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTagCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseIndex(indexWithTags[0]);
        String tags = " " + indexWithTags[1];
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(tags, PREFIX_PRIORITY_STATUS, PREFIX_DEADLINE);
        if (!areAnyPrefixesPresent(argMultiMap, PREFIX_PRIORITY_STATUS, PREFIX_DEADLINE)
                || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTagCommand.MESSAGE_USAGE));
        }
        String priorityStatus = argMultiMap.getValue(PREFIX_PRIORITY_STATUS).orElse(null);
        String deadline = argMultiMap.getValue(PREFIX_DEADLINE).orElse(null);
        PriorityTag priorityTag = priorityStatus != null ? ParserUtil
                .parsePriorityTag(priorityStatus) : null;
        DeadlineTag deadlineTag = deadline != null ? ParserUtil
                .parseDeadlineTag(deadline) : null;
        return new AddTagCommand(priorityTag, deadlineTag, index);
    }

    //@@author dlimyy-reused
    //Reused from existing AB3 (https://github.com/nus-cs2103-AY2223S1/tp)
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
    //@@author
}
