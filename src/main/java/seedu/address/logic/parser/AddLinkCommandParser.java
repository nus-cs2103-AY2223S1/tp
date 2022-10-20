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

import seedu.address.logic.commands.AddLinkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.team.Link;
import seedu.address.model.team.Url;

/**
 * Parses input arguments and creates a new AddLinkCommandParser object.
 */
public class AddLinkCommandParser implements Parser<AddLinkCommand> {
    private final Options options;

    /**
     * Creates an AddLinkCommandParser object.
     */
    public AddLinkCommandParser() {
        Options options = new Options();
        options.addRequiredOption(FLAG_NAME_STR, FLAG_NAME_STR_LONG, true, "Name of link");
        options.addRequiredOption(FLAG_URL_STR, FLAG_URL_STR_LONG, true, "URL of link");
        this.options = options;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddLinkCommand
     * and returns an AddLinkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public AddLinkCommand parse(String args) throws ParseException {
        try {
            CommandLineParser parser = new DefaultParser();
            String[] argsArray = ArgumentTokenizer.tokenize(args);
            CommandLine cmd = parser.parse(options, argsArray);

            if (cmd.getArgs().length > 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLinkCommand.MESSAGE_USAGE));
            }

            Name name = ParserUtil.parseName(cmd.getOptionValue(FLAG_NAME_STR));
            Url url = ParserUtil.parseUrl(cmd.getOptionValue(FLAG_URL_STR));

            Link link = new Link(name, url);

            return new AddLinkCommand(link);
        } catch (MissingArgumentException e) {
            Option opt = e.getOption();
            switch (opt.getOpt()) {
            case FLAG_NAME_STR:
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            case FLAG_URL_STR:
                throw new ParseException(Link.URL_CONSTRAINTS);
            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLinkCommand.MESSAGE_USAGE));
            }
        } catch (org.apache.commons.cli.ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLinkCommand.MESSAGE_USAGE));
        }
    }
}
