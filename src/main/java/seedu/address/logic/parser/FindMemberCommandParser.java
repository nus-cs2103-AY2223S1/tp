package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR_LONG;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;

import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import seedu.address.logic.commands.FindMemberCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindMemberCommandParser object
 */
public class FindMemberCommandParser implements Parser<FindMemberCommand> {
    private final Options options;

    /**
     * Creates a FindMemberCommandParser object.
     */
    public FindMemberCommandParser() {
        Options options = new Options();
        options.addOption(FLAG_NAME_STR, FLAG_NAME_STR_LONG, true, "Name of member");
        options.addOption(FLAG_EMAIL_STR, FLAG_EMAIL_STR_LONG, true, "Email of member");
        this.options = options;
    }
    @Override
    public FindMemberCommand parse(String args) throws ParseException {
        try {
            CommandLineParser parser = new DefaultParser();
            String[] argsArray = ArgumentTokenizer.tokenize(args);
            CommandLine cmd = parser.parse(options, argsArray);

            if (cmd.getArgs().length > 0) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMemberCommand.MESSAGE_USAGE));
            }

            if (cmd.getOptions().length > 1) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMemberCommand.MESSAGE_ONE_FLAG));
            }

            if (cmd.hasOption(FLAG_NAME_STR)) {
                String searchString = cmd.getOptionValue(FLAG_NAME_STR);
                String[] nameKeywords = searchString.split("\\s+");
                return new FindMemberCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
            } else if (cmd.hasOption(FLAG_EMAIL_STR)) {
                String searchString = cmd.getOptionValue(FLAG_EMAIL_STR);
                String[] nameKeywords = searchString.split("\\s+");
                return new FindMemberCommand(new EmailContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMemberCommand.MESSAGE_USAGE));
            }
        } catch (MissingArgumentException e) {
            Option opt = e.getOption();
            switch (opt.getOpt()) {
            case FLAG_NAME_STR:
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            case FLAG_EMAIL_STR:
                throw new ParseException(Email.MESSAGE_CONSTRAINTS);
            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMemberCommand.MESSAGE_USAGE));
            }
        } catch (org.apache.commons.cli.ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMemberCommand.MESSAGE_USAGE));
        }
    }
}
