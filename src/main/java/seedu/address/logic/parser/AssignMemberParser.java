package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
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

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEMBER_INDEX, PREFIX_TEAM_INDEX);
        Index memberIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MEMBER_INDEX).get());
        Index teamIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TEAM_INDEX).get());

        return new AssignMemberCommand(memberIndex, teamIndex);
    }
}
