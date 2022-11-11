package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;

import java.util.Arrays;

import seedu.address.logic.commands.FindInternshipCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.InternshipContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindInternshipCommand object
 */
public class FindInternshipCommandParser implements Parser<FindInternshipCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindInternshipCommand
     * and returns a FindInternshipCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindInternshipCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args,
                        PREFIX_COMPANY_NAME,
                        PREFIX_INTERNSHIP_ROLE,
                        PREFIX_INTERNSHIP_STATUS,
                        PREFIX_INTERVIEW_DATE);

        if (!ParserUtil.isAnyPrefixPresent(
                argMultimap,
                PREFIX_COMPANY_NAME,
                PREFIX_INTERNSHIP_ROLE,
                PREFIX_INTERNSHIP_STATUS,
                PREFIX_INTERVIEW_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindInternshipCommand.MESSAGE_USAGE));
        }

        String companyNameArgs = argMultimap.getValue(PREFIX_COMPANY_NAME).orElse("");
        String trimmedCompanyNameArgs = companyNameArgs.trim();
        String[] companyNameKeywords;
        if (trimmedCompanyNameArgs.isEmpty()) {
            companyNameKeywords = new String[]{};
        } else {
            companyNameKeywords = trimmedCompanyNameArgs.split("\\s+");
        }

        String internshipRoleArgs = argMultimap.getValue(PREFIX_INTERNSHIP_ROLE).orElse("");
        String trimmedInternshipRoleArgs = internshipRoleArgs.trim();
        String[] internshipRoleKeywords;
        if (trimmedInternshipRoleArgs.isEmpty()) {
            internshipRoleKeywords = new String[]{};
        } else {
            internshipRoleKeywords = trimmedInternshipRoleArgs.split("\\s+");
        }

        String internshipStatusArgs = argMultimap.getValue(PREFIX_INTERNSHIP_STATUS).orElse("");
        String trimmedInternshipStatusArgs = internshipStatusArgs.trim();
        String[] internshipStatusKeywords;
        if (trimmedInternshipStatusArgs.isEmpty()) {
            internshipStatusKeywords = new String[]{};
        } else {
            internshipStatusKeywords = trimmedInternshipStatusArgs.split("\\s+");
        }

        String interviewDateArgs = argMultimap.getValue(PREFIX_INTERVIEW_DATE).orElse("");
        String trimmedInterviewDateArgs = interviewDateArgs.trim();
        String[] interviewDateKeywords;
        if (trimmedInterviewDateArgs.isEmpty()) {
            interviewDateKeywords = new String[]{};
        } else {
            interviewDateKeywords = trimmedInterviewDateArgs.split("\\s+");
        }

        return new FindInternshipCommand(
                new InternshipContainsKeywordsPredicate(
                        Arrays.asList(companyNameKeywords),
                        Arrays.asList(internshipRoleKeywords),
                        Arrays.asList(internshipStatusKeywords),
                        Arrays.asList(interviewDateKeywords)));
    }
}
