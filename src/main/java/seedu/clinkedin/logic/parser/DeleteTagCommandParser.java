package seedu.clinkedin.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.DeleteTagCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.person.UniqueTagTypeMap;



/**
 * Parses input arguments and creates a new DeleteTagCommand object
 */
public class DeleteTagCommandParser implements Parser<DeleteTagCommand> {
    @Override
    public DeleteTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.getPrefixes());

        boolean foundAny = CliSyntax.getPrefixTags().stream().anyMatch(pref -> argMultimap
                .getAllValues(pref).size() > 0);
        if (!foundAny) {
            StringBuilder message = new StringBuilder("No valid tag type prefix found! ");
            if (CliSyntax.getPrefixTags().size() != 0) {
                message.append("Prefixes must be amongst the following:\n\n");
                for (Prefix p : UniqueTagTypeMap.getPrefixMap().keySet()) {
                    message.append(String.format("\tTagType: %s\n\tPrefix: %s\n\n", UniqueTagTypeMap.getPrefixMap()
                            .get(p).toString(), p.toString()));
                }
            }
            throw new ParseException(message);
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE), pe);
        }

        Map<Prefix, List<String>> prefToStrings = new HashMap<>();
        CliSyntax.getPrefixTags().forEach(pref -> prefToStrings.put(pref, argMultimap.getAllValues(pref)));
        UniqueTagTypeMap tagMap = ParserUtil.parseTags(prefToStrings);
        return new DeleteTagCommand(index, tagMap);
    }
}
