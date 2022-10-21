package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindStaffCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.ProjectName;

/**
 * Parses input arguments and creates a new FindStaffCommand object.
 */
public class FindStaffCommandParser implements Parser<FindStaffCommand> {

    /**
     * Parses input arguments and creates a FindStaffCommand.
     */
    public FindStaffCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PROJECT_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_PROJECT_NAME)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStaffCommand.MESSAGE_USAGE));
        }

        ProjectName projectName = ParserUtil.parseProjectName(argMultimap.getValue(PREFIX_PROJECT_NAME).get());

        String[] nameKeywords = argMultimap.getPreamble().trim().split("\\s+");

        return new FindStaffCommand(projectName, Arrays.asList(nameKeywords));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
