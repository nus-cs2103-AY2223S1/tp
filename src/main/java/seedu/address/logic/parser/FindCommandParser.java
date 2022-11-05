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
import java.util.List;

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
    public static final String MESSAGE_EMPTY_FIELD = "Specifier keyword to search with must be provided.";

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
            List<String> addressKeywords = generateKeywords(argMultimap, PREFIX_ADDRESS);
            predicateList.addPredicate(new AddressContainsKeywordsPredicate(addressKeywords));
        }
        if (argMultimap.getValue(PREFIX_CAP).isPresent()) {
            List<String> capKeywords = generateKeywords(argMultimap, PREFIX_CAP);
            CollectionUtil.checkCapKeywords(capKeywords);
            predicateList.addPredicate(new CapContainsKeywordsPredicate(capKeywords));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            List<String> emailKeywords = generateKeywords(argMultimap, PREFIX_EMAIL);
            predicateList.addPredicate(new EmailContainsKeywordsPredicate(emailKeywords));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            List<String> genderKeywords = generateKeywords(argMultimap, PREFIX_GENDER);
            predicateList.addPredicate(new GenderContainsKeywordsPredicate(genderKeywords));
        }
        if (argMultimap.getValue(PREFIX_GRADUATION_DATE).isPresent()) {
            List<String> graduationDateKeywords = generateKeywords(argMultimap, PREFIX_GRADUATION_DATE);
            predicateList.addPredicate(new GraduationDateContainsKeywordsPredicate(graduationDateKeywords));
        }
        if (argMultimap.getValue(PREFIX_JOB_ID).isPresent()) {
            List<String> jobIdKeywords = generateKeywords(argMultimap, PREFIX_JOB_ID);
            predicateList.addPredicate(new JobIdContainsKeywordsPredicate(jobIdKeywords));
        }
        if (argMultimap.getValue(PREFIX_JOB_TITLE).isPresent()) {
            List<String> jobTitleKeywords = generateKeywords(argMultimap, PREFIX_JOB_TITLE);
            predicateList.addPredicate(new JobTitleContainsKeywordsPredicate(jobTitleKeywords));
        }
        if (argMultimap.getValue(PREFIX_MAJOR).isPresent()) {
            List<String> majorKeywords = generateKeywords(argMultimap, PREFIX_MAJOR);
            predicateList.addPredicate(new MajorContainsKeywordsPredicate(majorKeywords));
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            List<String> nameKeywords = generateKeywords(argMultimap, PREFIX_NAME);
            predicateList.addPredicate(new NameContainsKeywordsPredicate(nameKeywords));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            List<String> phoneKeywords = generateKeywords(argMultimap, PREFIX_PHONE);
            predicateList.addPredicate(new PhoneContainsKeywordsPredicate(phoneKeywords));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            List<String> tagKeywords = generateKeywords(argMultimap, PREFIX_TAG);
            predicateList.addPredicate(new TagContainsKeywordsPredicate(tagKeywords));
        }
        if (argMultimap.getValue(PREFIX_UNIVERSITY).isPresent()) {
            List<String> universityKeywords = generateKeywords(argMultimap, PREFIX_UNIVERSITY);
            predicateList.addPredicate(new UniversityContainsKeywordsPredicate(universityKeywords));
        }
        if (!predicateList.hasPredicate()) {
            throw new ParseException(FindCommand.MESSAGE_NO_FIELD_GIVEN);
        }

        return new FindCommand(predicateList);
    }
    private static List<String> generateKeywords(ArgumentMultimap argMultimap, Prefix prefix) throws ParseException {
        String keywordsString = argMultimap.getValue(prefix).get();
        if (keywordsString.isEmpty()) {
            throw new ParseException(MESSAGE_EMPTY_FIELD);
        }
        return Arrays.asList(StringUtil.splitByWhitespace(keywordsString));
    }

}
