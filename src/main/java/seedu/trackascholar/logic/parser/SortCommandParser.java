package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import seedu.trackascholar.logic.commands.SortCommand;
import seedu.trackascholar.logic.parser.exceptions.ParseException;
import seedu.trackascholar.model.applicant.Applicant;

/**
 * Parses input arguments and creates a new SortCommand object
 */

public class SortCommandParser implements Parser<SortCommand> {

    public static final String NAME = "name";
    public static final String SCHOLARSHIP = "scholarship";
    public static final String STATUS = "status";
    public static final String REVERSE_FLAG = "-r";

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {

        String trimmedArgs = args.toLowerCase().trim();
        String[] keywords = trimmedArgs.split("\\s+");
        List<String> keywordsList = Arrays.asList(keywords);

        if (keywordsList.contains(NAME)) {
            return new SortCommand(checkInputSizeAndReverseFlag(Applicant.sortByName(), keywordsList));
        } else if (keywordsList.contains(SCHOLARSHIP)) {
            return new SortCommand(checkInputSizeAndReverseFlag(Applicant.sortByScholarship(), keywordsList));
        } else if (keywordsList.contains(STATUS)) {
            return new SortCommand(checkInputSizeAndReverseFlag(Applicant.sortByStatus(), keywordsList));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }

    private static Comparator<Applicant> checkInputSizeAndReverseFlag(
            Comparator<Applicant> comparator, List<String> list) throws ParseException {
        if (list.size() == 2 && list.contains(REVERSE_FLAG)) {
            return comparator.reversed();
        } else if (list.size() == 1) {
            return comparator;
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }
}
