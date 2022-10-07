package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM_NAME;

import seedu.address.logic.commands.AssignMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TEAM_NAME);

        Name memberName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        seedu.address.model.team.Name teamName = ParserUtil.parseTeamName(argMultimap.getValue(PREFIX_TEAM_NAME).get());

        return new AssignMemberCommand(memberName, teamName);
    }
}
