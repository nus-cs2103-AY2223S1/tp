package seedu.address.logic.parser.teams;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.ForEachCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.teams.AddTeamCommand;
import seedu.address.logic.commands.teams.RemoveUserFromTeamCommand;
import seedu.address.logic.commands.teams.TeamCommand;
import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;

/**
 * Parser for Team Command
 */
public class TeamCommandParser implements Parser<Command> {

    private static final String MESSAGE_USAGE = TeamCommand.COMMAND_WORD + " [new|delete|remove]";
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<subcommandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution. The input must be a valid subcommand for Task.
     * There should not be a TaskCommand prefix in the input.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("subcommandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case AddTeamCommand.SUBCOMMAND_WORD:
            return new AddTeamCommandParser().parse(arguments);

        case DeleteCommand.SUBCOMMAND_WORD:
            return DeleteCommand
                .<Group>parser((m, i) -> m.getFromFilteredTeams(i), (m, i) -> m.deleteTeam(i),
                    o -> o instanceof Group)
                .parse(arguments);

        case RemoveUserFromTeamCommand.SUBCOMMAND_WORD:
            return new RemoveUserFromTeamCommandParser().parse(arguments);

        case SelectCommand.SUBCOMMAND_WORD:
            return SelectCommand.parser((m, i) -> m.getFromFilteredTeams(i)).parse(arguments);

        case ForEachCommand.SUBCOMMAND_WORD:
            return ForEachCommand.parser(m -> m.getFilteredTeamList()).parse(arguments);

        case FindCommand.SUBCOMMAND_WORD:
            return new FindCommandParser<Group>((m, p) -> m.updateFilteredTeamList(p),
                m -> m.getFilteredTeamList().size()).parse(arguments);

        default:
            throw new ParseException(MESSAGE_USAGE);
        }
    }

}
