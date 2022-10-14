package soconnect.logic.parser;

import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.parser.ArgumentTokenizer.PrefixArgument;
import static soconnect.logic.parser.ArgumentTokenizer.tokenizeToList;
import static soconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static soconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static soconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static soconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static soconnect.logic.parser.ParserUtil.parseTag;

import java.util.List;
import java.util.stream.Collectors;

import soconnect.logic.commands.SortCommand;
import soconnect.logic.commands.SortCommand.SortArgument;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.tag.Tag;

/**
 * Parses input arguments and creates a new SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public SortCommand parse(String args) throws ParseException {
        List<PrefixArgument> argList =
            tokenizeToList(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        boolean hasArguments = argList.size() != 1;

        if (!hasArguments) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        List<SortArgument> sortArgs = convertPrefixArgToSortArg(argList);

        for (SortArgument arg : sortArgs) {
            if (arg == null) {
                throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
            }
        }

        return new SortCommand(sortArgs);
    }

    /**
     * Converts a list of PrefixArgument to a list of SortArgument.
     * First dummy PrefixArgument in the list, which represents the preamble, is excluded.
     *
     * @param argList List of PrefixArgument.
     * @return        List of SortArgument.
     */
    private List<SortArgument> convertPrefixArgToSortArg(List<PrefixArgument> argList) {
        return argList.subList(1, argList.size()).stream().map(this::convertArguments).collect(Collectors.toList());
    }

    /**
     * Converts a PrefixArgument to a SortArgument.
     *
     * @param prefixArg PrefixArgument to convert.
     * @return          SortArgument.
     */
    private SortArgument convertArguments(PrefixArgument prefixArg) {
        Prefix prefix = prefixArg.getPrefix();
        String arg = prefixArg.getArgument();

        // Sorting is reversed if the prefix is followed by a '!'
        boolean isReverse = !arg.isEmpty() && arg.charAt(0) == '!';

        try {
            if (prefix.equals(PREFIX_TAG)) {
                Tag tag = isReverse ? parseTag(arg.substring(1)) : parseTag(arg);
                return new SortArgument(prefix, isReverse, tag);
            } else {
                return new SortArgument(prefix, isReverse, null);
            }
        } catch (ParseException e) {
            return null;
        }
    }
}
