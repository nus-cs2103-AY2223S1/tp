package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY_STATUS;

import java.util.stream.Stream;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.PriorityTag;

/**
 * AddTagCommandParser parses the arguments and supplies these arguments
 * to the AddTagCommand.
 */
public class AddTagCommandParser implements Parser<Command> {
    @Override
    public Command parse(String args) throws ParseException {
        String[] indexWithTags = args.strip().split(" ", 2);
        if (indexWithTags.length != 2) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTagCommand.MESSAGE_USAGE));
        }
        try {
            Index index = ParserUtil.parseIndex(indexWithTags[0]);
            String tags = " " + indexWithTags[1];
            ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(tags, PREFIX_PRIORITY_STATUS);
            if (!arePrefixesPresent(argMultiMap, PREFIX_PRIORITY_STATUS)
                    || !argMultiMap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        AddTagCommand.MESSAGE_USAGE));
            }
            PriorityTag priorityTag = null;
            String priorityStatus = argMultiMap.getValue(PREFIX_PRIORITY_STATUS).orElse(null);
            if (priorityStatus != null) {
                priorityTag = ParserUtil.parsePriorityTag(priorityStatus);
            }
            return new AddTagCommand(priorityTag, index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTagCommand.MESSAGE_USAGE), pe);
        }
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
