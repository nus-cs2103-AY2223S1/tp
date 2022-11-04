package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddInternshipCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.InternshipRole;
import seedu.address.model.internship.InternshipStatus;
import seedu.address.model.internship.InterviewDate;

/**
 * Parses input arguments and creates a new AddInternshipCommand object
 */
public class AddInternshipCommandParser implements Parser<AddInternshipCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddInternshipCommand
     * and returns an AddInternshipCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddInternshipCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args,
                        PREFIX_COMPANY_NAME,
                        PREFIX_INTERNSHIP_ROLE,
                        PREFIX_INTERNSHIP_STATUS,
                        PREFIX_INTERVIEW_DATE,
                        PREFIX_LINK_INDEX);

        if (!ParserUtil.arePrefixesPresent(
                argMultimap,
                PREFIX_COMPANY_NAME,
                PREFIX_INTERNSHIP_ROLE,
                PREFIX_INTERNSHIP_STATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInternshipCommand.MESSAGE_USAGE));
        }

        CompanyName companyName =
                ParserUtil.parseCompanyName(argMultimap.getValue(PREFIX_COMPANY_NAME).get());
        InternshipRole internshipRole =
                ParserUtil.parseInternshipRole(argMultimap.getValue(PREFIX_INTERNSHIP_ROLE).get());
        InternshipStatus internshipStatus =
                ParserUtil.parseInternshipStatus(argMultimap.getValue(PREFIX_INTERNSHIP_STATUS).get());

        String interviewDateStr = argMultimap.getValue(PREFIX_INTERVIEW_DATE).orElse(null);
        InterviewDate interviewDate = ParserUtil.parseInterviewDate(interviewDateStr);
        String linkIndexStr = argMultimap.getValue(PREFIX_LINK_INDEX).orElse(null);
        Index linkIndex = null;
        if (linkIndexStr != null) {
            linkIndex = ParserUtil.parseIndex(linkIndexStr);
        }

        return new AddInternshipCommand(companyName, internshipRole, internshipStatus, interviewDate, linkIndex);
    }
}
