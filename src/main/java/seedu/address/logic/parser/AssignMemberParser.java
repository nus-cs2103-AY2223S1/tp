package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AssignMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AssignMemberCommand object
 */
public class AssignMemberParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AssignMemberCommand
     * and returns a AssignMemberCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignMemberCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index memberIndex;
        Index teamIndex;

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEMBER_INDEX, PREFIX_TEAM_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_MEMBER_INDEX, PREFIX_TEAM_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignMemberCommand.MESSAGE_USAGE));
        }

        try {
            memberIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MEMBER_INDEX).get());
            teamIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TEAM_INDEX).get());
        } catch (ParseException p) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AssignMemberCommand.MESSAGE_USAGE), p);
        }

        return new AssignMemberCommand(memberIndex, teamIndex);
    }
}
