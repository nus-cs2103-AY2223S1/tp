package seedu.address.logic.parser;

import seedu.address.logic.commands.ListTasksCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class ListTasksCommandParser implements Parser<ListTasksCommand> {
//    private final Options options;

    /**
     * Creates an AddCommandParser with default options
     */
    public ListTasksCommandParser() {
//        Options options = new Options();
//        options.addOption(FLAG_FILTER_STR, FLAG_FILTER_STR_LONG, true, "Filter task list by");
//        this.options = options;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ListTasksCommand
     * and returns a ListTasksCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ListTasksCommand parse(String args) throws ParseException {
//        try {
//            CommandLineParser parser = new DefaultParser();
//            String[] argsArray = ArgumentTokenizer.tokenize(args);
//            CommandLine cmd = parser.parse(options, argsArray);
//
//            if (cmd.getArgs().length > 0) {
//                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListTasksCommand.MESSAGE_USAGE));
//            }
//
//            String filter = cmd.hasOption(FLAG_FILTER_STR)
//                    ? cmd.getOptionValue(FLAG_FILTER_STR)
//                    : "";
//
        return new ListTasksCommand("");
//        } catch (org.apache.commons.cli.ParseException e) {
//            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListTasksCommand.MESSAGE_USAGE));
//        }
    }
}
