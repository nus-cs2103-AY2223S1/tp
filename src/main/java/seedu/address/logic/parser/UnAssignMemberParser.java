package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnAssignMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnAssignMemberCommand object
 */
public class UnAssignMemberParser {

    /**
     * Parses the given {@code String} of arguments in the context of the UnAssignMemberCommand
     * and returns a UnAssignMemberCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnAssignMemberCommand parse(String args) throws ParseException {

        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEMBER_INDEX, PREFIX_TEAM_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_MEMBER_INDEX, PREFIX_TEAM_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnAssignMemberCommand.MESSAGE_USAGE));
        }

        Index memberIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MEMBER_INDEX).get());
        Index teamIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TEAM_INDEX).get());

        return new UnAssignMemberCommand(memberIndex, teamIndex);
    }
}
