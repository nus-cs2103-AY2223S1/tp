package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddInternshipCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.InternshipId;
import seedu.address.model.internship.InternshipRole;
import seedu.address.model.internship.InternshipStatus;
import seedu.address.model.internship.InterviewDate;
import seedu.address.model.person.PersonId;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddInternshipCommandParser implements Parser<AddInternshipCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddInternshipCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args,
                        PREFIX_COMPANY_NAME,
                        PREFIX_INTERNSHIP_ROLE,
                        PREFIX_INTERNSHIP_STATUS,
                        PREFIX_PERSON_ID,
                        PREFIX_INTERVIEW_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_COMPANY_NAME, PREFIX_INTERNSHIP_ROLE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInternshipCommand.MESSAGE_USAGE));
        }

        CompanyName companyName =
                ParserUtil.parseCompanyName(argMultimap.getValue(PREFIX_COMPANY_NAME).get());
        InternshipRole internshipRole =
                ParserUtil.parseInternshipRole(argMultimap.getValue(PREFIX_INTERNSHIP_ROLE).get());
        InternshipStatus internshipStatus =
                ParserUtil.parseInternshipStatus(argMultimap.getValue(PREFIX_INTERNSHIP_STATUS).get());
        PersonId contactPersonId = ParserUtil.parsePersonId(argMultimap.getValue(PREFIX_PERSON_ID).get());
        InterviewDate interviewDate = ParserUtil.parseInterviewDate(argMultimap.getValue(PREFIX_INTERVIEW_DATE).get());

        return new AddInternshipCommand(companyName, internshipRole, internshipStatus, contactPersonId, interviewDate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
