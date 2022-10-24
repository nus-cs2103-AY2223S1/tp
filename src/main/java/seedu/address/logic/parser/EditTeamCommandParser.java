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

import seedu.address.logic.commands.EditTeamCommand;
import seedu.address.logic.commands.EditTeamCommand.EditTeamDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new EditTeamCommand object.
 */
public class EditTeamCommandParser implements Parser<EditTeamCommand> {
    private final Options options;

    public EditTeamCommandParser() {
        Options options = new Options();
        options.addOption(FLAG_NAME_STR, FLAG_NAME_STR_LONG, true, "Name of team");
        options.addOption(FLAG_DESCRIPTION_STR, FLAG_DESCRIPTION_LONG, true, "Description of team");
        this.options = options;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditTeamCommand
     * and returns an EditTeamCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public EditTeamCommand parse(String args) throws ParseException {
        try {
            CommandLineParser parser = new DefaultParser();
            String[] argsArray = ArgumentTokenizer.tokenize(args);
            CommandLine cmd = parser.parse(options, argsArray);
            EditTeamDescriptor editTeamDescriptor = new EditTeamDescriptor();

            if (cmd.getArgs().length > 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTeamCommand.MESSAGE_USAGE));
            }

            if (cmd.hasOption(FLAG_NAME_STR)) {
                Name name = ParserUtil.parseName(cmd.getOptionValue(FLAG_NAME_STR));
                editTeamDescriptor.setName(name.toString());
            }

            if (cmd.hasOption(FLAG_DESCRIPTION_STR)) {
                Name description = ParserUtil.parseName(cmd.getOptionValue(FLAG_DESCRIPTION_STR));
                editTeamDescriptor.setDescription(description.toString());
            }

            if (!editTeamDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditTeamCommand.MESSAGE_NOT_EDITED);
            }


            return new EditTeamCommand(editTeamDescriptor);
        } catch (MissingArgumentException e) {
            Option opt = e.getOption();
            switch (opt.getOpt()) {
            case FLAG_NAME_STR:
            case FLAG_DESCRIPTION_STR:
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTeamCommand.MESSAGE_USAGE));
            }
        } catch (org.apache.commons.cli.ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTeamCommand.MESSAGE_USAGE));
        }
    }
}
