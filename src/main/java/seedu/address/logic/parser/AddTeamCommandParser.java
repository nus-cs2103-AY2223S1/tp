package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_DESCRIPTION_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_DESCRIPTION_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import seedu.address.logic.commands.AddTeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.team.Team;

/**
 * Parses input arguments and creates a new AddTeamCommand object.
 */
public class AddTeamCommandParser implements Parser<AddTeamCommand> {
    private final Options options;

    /**
     * Creates an AddTeamCommandParser object.
     */
    public AddTeamCommandParser() {
        Options options = new Options();
        options.addRequiredOption(FLAG_NAME_STR, FLAG_NAME_STR_LONG, true, "Name of team");
        options.addOption(FLAG_DESCRIPTION_STR, FLAG_DESCRIPTION_LONG, true, "Description of team");
        this.options = options;
    }
    /**
     * Parses the given {@code String} of arguments in the context of the AddTeamCommand
     * and returns a AddTeamCommand object for execution.
     *
     * @throws ParseException if user input does not conform to the expected format
     */
    @Override
    public AddTeamCommand parse(String args) throws ParseException {
        try {
            CommandLineParser parser = new DefaultParser();
            String[] argsArray = ArgumentTokenizer.tokenize(args);
            CommandLine cmd = parser.parse(options, argsArray);

            if (cmd.getArgs().length > 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTeamCommand.MESSAGE_USAGE));
            }

            Name name = ParserUtil.parseName(cmd.getOptionValue(FLAG_NAME_STR));
            Name description = cmd.hasOption(FLAG_DESCRIPTION_STR)
                    ? ParserUtil.parseName(cmd.getOptionValue(FLAG_DESCRIPTION_STR))
                    : new Name("No description added");

            String nameString = name.toString();
            String descriptionString = description.toString();


            Team team = new Team(nameString, descriptionString);

            return new AddTeamCommand(team);
        } catch (MissingArgumentException e) {
            Option opt = e.getOption();
            switch (opt.getOpt()) {
            case FLAG_NAME_STR:
            case FLAG_DESCRIPTION_STR:
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTeamCommand.MESSAGE_USAGE));
            }
        } catch (org.apache.commons.cli.ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTeamCommand.MESSAGE_USAGE));
        }
    }
}
