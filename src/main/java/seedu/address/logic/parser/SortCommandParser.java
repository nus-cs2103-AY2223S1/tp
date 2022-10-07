package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentTokenizer.PrefixArgument;
import static seedu.address.logic.parser.ArgumentTokenizer.tokenizeToList;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommand.SortArgument;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        List<PrefixArgument> argList =
            tokenizeToList(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        boolean hasArguments = argList.size() != 1;

        if (!hasArguments) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        List<SortArgument> sortArgs = convertPrefixArgToSortArg(argList);
        return new SortCommand(sortArgs);
    }

    /**
     * Convert a list of PrefixArgument to a list of SortArgument.
     * First dummy PrefixArgument in the list, which represents the preamble, is excluded.
     *
     * @param argList List of PrefixArgument
     * @return        List of SortArgument
     */
    private List<SortArgument> convertPrefixArgToSortArg(List<PrefixArgument> argList) {
        return argList.subList(1, argList.size()).stream().map(this::convertArguments).collect(Collectors.toList());
    }

    /**
     * Convert a PrefixArgument to a SortArgument.
     *
     * @param prefixArg PrefixArgument to convert
     * @return          SortArgument
     */
    private SortArgument convertArguments(PrefixArgument prefixArg) {
        Prefix prefix = prefixArg.getPrefix();
        String arg = prefixArg.getArgument();

        // Sorting is reversed if the prefix is followed by a '!'
        boolean isReverse = !arg.isEmpty() && arg.charAt(0) == '!';

        if (prefix.equals(PREFIX_TAG)) {
            Tag tag = isReverse ? new Tag(arg.substring(1)) : new Tag(arg);
            return new SortArgument(prefix, isReverse, tag);
        } else {
            return new SortArgument(prefix, isReverse, null);
        }
    }
}
