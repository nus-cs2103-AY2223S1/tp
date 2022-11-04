package seedu.clinkedin.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.AddTagCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.person.UniqueTagTypeMap;

/**
 * Parses input arguments and creates a new AddTagCommand object
 */
public class AddTagCommandParser implements Parser<AddTagCommand> {
    @Override
    public AddTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.getPrefixes());
        if (CliSyntax.getPrefixTags().size() == 0) {
            throw new ParseException("No tag type found in the address book. "
                    + "Use 'createTagType' to create new tag types!");
        }

        boolean foundAny = CliSyntax.getPrefixTags().stream().anyMatch(pref -> argMultimap
                .getAllValues(pref).size() > 0);
        if (!foundAny) {
            StringBuilder message = new StringBuilder();
            message.append("No valid tag type prefix found! Prefixes must be amongst the following:\n\n");
            for (Prefix p : UniqueTagTypeMap.getPrefixMap().keySet()) {
                message.append(String.format("\tTagType: %s\n\tPrefix: %s\n\n", UniqueTagTypeMap.getPrefixMap().get(p)
                        .toString(), p.toString()));
            }
            throw new ParseException(message);
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE), pe);
        }
        Map<Prefix, List<String>> prefToStrings = new HashMap<>();
        CliSyntax.getPrefixTags().forEach(pref -> prefToStrings.put(pref, argMultimap.getAllValues(pref)));
        UniqueTagTypeMap tagMap = ParserUtil.parseTags(prefToStrings);
        return new AddTagCommand(index, tagMap);
    }
}
