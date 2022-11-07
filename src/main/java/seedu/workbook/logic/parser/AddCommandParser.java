package seedu.workbook.logic.parser;

import static seedu.workbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_LANGUAGE_TAG;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_STAGE;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.workbook.model.internship.DateTime.EMPTY_DATETIME;
import static seedu.workbook.model.internship.Email.EMPTY_EMAIL;

import java.util.Set;
import java.util.stream.Stream;

import seedu.workbook.logic.commands.AddCommand;
import seedu.workbook.logic.parser.exceptions.ParseException;
import seedu.workbook.logic.parser.util.CheckedFunction;
import seedu.workbook.model.internship.Company;
import seedu.workbook.model.internship.DateTime;
import seedu.workbook.model.internship.Email;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.model.internship.Role;
import seedu.workbook.model.internship.Stage;
import seedu.workbook.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_COMPANY, PREFIX_ROLE,
                PREFIX_EMAIL, PREFIX_STAGE, PREFIX_DATETIME, PREFIX_LANGUAGE_TAG, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_COMPANY, PREFIX_ROLE, PREFIX_STAGE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Company company = ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get());
        Role role = ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get());
        Email email = parseOptionalEmail(PREFIX_EMAIL, argMultimap, ParserUtil::parseEmail);
        Stage stage = ParserUtil.parseStage(argMultimap.getValue(PREFIX_STAGE).get());
        DateTime dateTime = parseOptionalDateTime(PREFIX_DATETIME, argMultimap, ParserUtil::parseDateTime);
        Set<Tag> languageTagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_LANGUAGE_TAG));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Internship internship = new Internship(company, role, email, stage, dateTime, languageTagList, tagList);

        return new AddCommand(internship);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    // Adapted and changed from CS2103T-T10-4
    // https://github.com/AY2223S1-CS2103T-T10-4/tp/commit/118c73f20a9eac789f37778a2f05e225f76a1110
    private DateTime parseOptionalDateTime(Prefix prefix, ArgumentMultimap argMultimap,
                                         CheckedFunction<String, DateTime> parserFn) throws ParseException {
        if (argMultimap.getValue(prefix).isPresent()) {
            return parserFn.apply(argMultimap.getValue(prefix).get());
        }
        return EMPTY_DATETIME;
    }

    // Adapted and changed from CS2103T-T10-4
    // https://github.com/AY2223S1-CS2103T-T10-4/tp/commit/118c73f20a9eac789f37778a2f05e225f76a1110
    private Email parseOptionalEmail(Prefix prefix, ArgumentMultimap argMultimap,
                                           CheckedFunction<String, Email> parserFn) throws ParseException {
        if (argMultimap.getValue(prefix).isPresent()) {
            return parserFn.apply(argMultimap.getValue(prefix).get());
        }
        return EMPTY_EMAIL;
    }

}
