package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADUATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIVERSITY;

import java.util.Arrays;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.CapContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.GenderContainsKeywordsPredicate;
import seedu.address.model.person.predicates.GraduationDateContainsKeywordsPredicate;
import seedu.address.model.person.predicates.JobIdContainsKeywordsPredicate;
import seedu.address.model.person.predicates.JobTitleContainsKeywordsPredicate;
import seedu.address.model.person.predicates.ListOfContainsKeywordsPredicates;
import seedu.address.model.person.predicates.MajorContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;
import seedu.address.model.person.predicates.UniversityContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        String argToTokenize = "0 " + trimmedArgs; // dummy preamble for tokenizer
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(argToTokenize, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS,
                        PREFIX_GENDER,
                        PREFIX_GRADUATION_DATE,
                        PREFIX_CAP,
                        PREFIX_UNIVERSITY,
                        PREFIX_MAJOR,
                        PREFIX_JOB_ID,
                        PREFIX_JOB_TITLE,
                        PREFIX_TAG);
        ListOfContainsKeywordsPredicates predicateList = ListOfContainsKeywordsPredicates
                .newListOfContainsKeywordsPredicates();

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            String[] addressKeywords = StringUtil.splitByWhitespace(argMultimap.getValue(PREFIX_ADDRESS).get());
            predicateList.addPredicate(new AddressContainsKeywordsPredicate(Arrays.asList(addressKeywords)));
        }
        if (argMultimap.getValue(PREFIX_CAP).isPresent()) {
            String[] capKeywords = StringUtil.splitByWhitespace(argMultimap.getValue(PREFIX_CAP).get());
            CollectionUtil.checkCapKeywords(capKeywords);
            predicateList.addPredicate(new CapContainsKeywordsPredicate(Arrays.asList(capKeywords)));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String[] emailKeywords = StringUtil.splitByWhitespace(argMultimap.getValue(PREFIX_EMAIL).get());
            predicateList.addPredicate(new EmailContainsKeywordsPredicate(Arrays.asList(emailKeywords)));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            String[] genderKeywords = StringUtil.splitByWhitespace(argMultimap.getValue(PREFIX_GENDER).get());
            predicateList.addPredicate(new GenderContainsKeywordsPredicate(Arrays.asList(genderKeywords)));
        }
        if (argMultimap.getValue(PREFIX_GRADUATION_DATE).isPresent()) {
            String[] graduationDateKeywords = StringUtil.splitByWhitespace(
                    argMultimap.getValue(PREFIX_GRADUATION_DATE).get());
            predicateList.addPredicate(new GraduationDateContainsKeywordsPredicate(
                    Arrays.asList(graduationDateKeywords)));
        }
        if (argMultimap.getValue(PREFIX_JOB_ID).isPresent()) {
            String[] jobIdKeywords = StringUtil.splitByWhitespace(argMultimap.getValue(PREFIX_JOB_ID).get());
            predicateList.addPredicate(new JobIdContainsKeywordsPredicate(Arrays.asList(jobIdKeywords)));
        }
        if (argMultimap.getValue(PREFIX_JOB_TITLE).isPresent()) {
            String[] jobTitleKeywords = StringUtil.splitByWhitespace(argMultimap.getValue(PREFIX_JOB_TITLE).get());
            predicateList.addPredicate(new JobTitleContainsKeywordsPredicate(Arrays.asList(jobTitleKeywords)));
        }
        if (argMultimap.getValue(PREFIX_MAJOR).isPresent()) {
            String[] majorKeywords = StringUtil.splitByWhitespace(argMultimap.getValue(PREFIX_MAJOR).get());
            predicateList.addPredicate(new MajorContainsKeywordsPredicate(Arrays.asList(majorKeywords)));
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] nameKeywords = StringUtil.splitByWhitespace(argMultimap.getValue(PREFIX_NAME).get());
            predicateList.addPredicate(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String[] phoneKeywords = StringUtil.splitByWhitespace(argMultimap.getValue(PREFIX_PHONE).get());
            predicateList.addPredicate(new PhoneContainsKeywordsPredicate(Arrays.asList(phoneKeywords)));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            String[] tagKeywords = StringUtil.splitByWhitespace(argMultimap.getValue(PREFIX_TAG).get());
            predicateList.addPredicate(new TagContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
        }
        if (argMultimap.getValue(PREFIX_UNIVERSITY).isPresent()) {
            String[] universityKeywords = StringUtil.splitByWhitespace(argMultimap.getValue(PREFIX_UNIVERSITY).get());
            predicateList.addPredicate(new UniversityContainsKeywordsPredicate(Arrays.asList(universityKeywords)));
        }
        if (!predicateList.hasPredicate()) {
            throw new ParseException(FindCommand.MESSAGE_NO_FIELD_GIVEN);
        }

        return new FindCommand(predicateList);
    }

}
