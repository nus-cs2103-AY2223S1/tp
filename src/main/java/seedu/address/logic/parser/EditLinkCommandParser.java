package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_URL_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_URL_STR_LONG;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditLinkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.team.Link;
import seedu.address.model.team.Url;

/**
 * Parse input arguments and creates a new EditLinkCommand object.
 */
public class EditLinkCommandParser implements Parser<EditLinkCommand> {

    private final Options options;

    /**
     * Creates an EditLinkCommandParser with default options.
     */
    public EditLinkCommandParser() {
        Options options = new Options();
        options.addOption(FLAG_NAME_STR, FLAG_NAME_STR_LONG, true, "Name of link");
        options.addOption(FLAG_URL_STR, FLAG_URL_STR_LONG, true, "URL of link");
        this.options = options;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditLinkCommand
     * and returns an EditLinkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public EditLinkCommand parse(String args) throws ParseException {
        try {
            CommandLineParser parser = new DefaultParser();
            String[] argsArray = ArgumentTokenizer.tokenize(args);
            CommandLine cmd = parser.parse(options, argsArray);
            EditLinkCommand.EditLinkDescriptor editLinkDescriptor = new EditLinkCommand.EditLinkDescriptor();

            if (cmd.getArgs().length > 1) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLinkCommand.MESSAGE_USAGE));
            }

            Index index;
            try {
                index = ParserUtil.parseIndex(cmd.getArgs()[0]);
            } catch (ParseException | IndexOutOfBoundsException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        EditLinkCommand.MESSAGE_USAGE), e);
            }

            if (cmd.hasOption(FLAG_NAME_STR)) {
                Name name = ParserUtil.parseName(cmd.getOptionValue(FLAG_NAME_STR));
                editLinkDescriptor.setName(name);
            }

            if (cmd.hasOption(FLAG_URL_STR)) {
                Url url = ParserUtil.parseUrl(cmd.getOptionValue(FLAG_URL_STR));
                editLinkDescriptor.setUrl(url);
            }

            if (!editLinkDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditLinkCommand.MESSAGE_NOT_EDITED);
            }


            return new EditLinkCommand(index, editLinkDescriptor);
        } catch (MissingArgumentException e) {
            Option opt = e.getOption();
            switch (opt.getOpt()) {
            case FLAG_NAME_STR:
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            case FLAG_URL_STR:
                throw new ParseException(Link.URL_CONSTRAINTS);
            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLinkCommand.MESSAGE_USAGE));
            }
        } catch (org.apache.commons.cli.ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLinkCommand.MESSAGE_USAGE));
        }
    }
}
