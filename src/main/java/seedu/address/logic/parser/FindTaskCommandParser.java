package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR_LONG;

import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.team.TaskNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindTaskCommandParser object
 */
public class FindTaskCommandParser implements Parser<FindTaskCommand> {

    private final Options options;

    /**
     * Creates a FindTaskCommandParser object.
     */
    public FindTaskCommandParser() {
        Options options = new Options();
        options.addRequiredOption(FLAG_NAME_STR, FLAG_NAME_STR_LONG, true, "Name of task");
        this.options = options;
    }

    @Override
    public FindTaskCommand parse(String args) throws ParseException {
        try {
            CommandLineParser parser = new DefaultParser();
            String[] argsArray = ArgumentTokenizer.tokenize(args);
            CommandLine cmd = parser.parse(options, argsArray);

            if (cmd.getArgs().length > 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
            }

            String searchString = cmd.getOptionValue(FLAG_NAME_STR);
            String[] nameKeywords = searchString.split("\\s+");
            return new FindTaskCommand(new TaskNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));

        } catch (MissingArgumentException e) {
            Option opt = e.getOption();
            switch (opt.getOpt()) {
            case FLAG_NAME_STR:
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
            }
        } catch (org.apache.commons.cli.ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        }
    }
}
