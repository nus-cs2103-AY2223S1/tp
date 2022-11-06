package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_PREFIX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PREFIX;
import static seedu.address.commons.core.Messages.MESSAGE_KEYWORDS_WITHOUT_PREFIX;
import static seedu.address.commons.core.Messages.MESSAGE_NO_PREFIX_SPECIFIED;
import static seedu.address.commons.core.Messages.MESSAGE_PREFIX_NOT_FOR_STUDENT;
import static seedu.address.commons.core.Messages.MESSAGE_PREFIX_NOT_FOR_TUITIONCLASS;
import static seedu.address.commons.core.Messages.MESSAGE_PREFIX_NOT_FOR_TUTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_OR_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.HashMap;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private Model.ListType listType;

    public FindCommandParser(Model.ListType listType) {
        this.listType = listType;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE,
                        PREFIX_TAG, PREFIX_SUBJECT_OR_SCHOOL, PREFIX_LEVEL, PREFIX_QUALIFICATION, PREFIX_INSTITUTION,
                        PREFIX_DAY, PREFIX_TIME);

        validateArguments(argMultimap);

        String nameKeywords;
        String addressKeywords;
        String emailKeywords;
        String phoneKeywords;

        String schoolKeywords;
        String levelKeywords;

        String qualificationKeywords;
        String institutionKeywords;

        String dayKeywords;
        String subjectKeywords;
        String timeKeywords;

        String tagKeywords;

        HashMap<Prefix, String> keywords = new HashMap<>();

        switch (listType) {

        case STUDENT_LIST:

            nameKeywords = argMultimap.getValue(PREFIX_NAME).orElse("");
            addressKeywords = argMultimap.getValue(PREFIX_ADDRESS).orElse("");
            emailKeywords = argMultimap.getValue(PREFIX_EMAIL).orElse("");
            phoneKeywords = argMultimap.getValue(PREFIX_PHONE).orElse("");
            schoolKeywords = argMultimap.getValue(PREFIX_SUBJECT_OR_SCHOOL).orElse("");
            levelKeywords = argMultimap.getValue(PREFIX_LEVEL).orElse("");

            keywords.put(PREFIX_NAME, nameKeywords);
            keywords.put(PREFIX_ADDRESS, addressKeywords);
            keywords.put(PREFIX_EMAIL, emailKeywords);
            keywords.put(PREFIX_PHONE, phoneKeywords);
            keywords.put(PREFIX_SUBJECT_OR_SCHOOL, schoolKeywords);
            keywords.put(PREFIX_LEVEL, levelKeywords);
            break;


        case TUTOR_LIST:

            nameKeywords = argMultimap.getValue(PREFIX_NAME).orElse("");
            addressKeywords = argMultimap.getValue(PREFIX_ADDRESS).orElse("");
            emailKeywords = argMultimap.getValue(PREFIX_EMAIL).orElse("");
            phoneKeywords = argMultimap.getValue(PREFIX_PHONE).orElse("");
            qualificationKeywords = argMultimap.getValue(PREFIX_QUALIFICATION).orElse("");
            institutionKeywords = argMultimap.getValue(PREFIX_INSTITUTION).orElse("");

            keywords.put(PREFIX_NAME, nameKeywords);
            keywords.put(PREFIX_ADDRESS, addressKeywords);
            keywords.put(PREFIX_EMAIL, emailKeywords);
            keywords.put(PREFIX_PHONE, phoneKeywords);
            keywords.put(PREFIX_QUALIFICATION, qualificationKeywords);
            keywords.put(PREFIX_INSTITUTION, institutionKeywords);
            break;


        default:

            nameKeywords = argMultimap.getValue(PREFIX_NAME).orElse("");
            dayKeywords = argMultimap.getValue(PREFIX_DAY).orElse("");
            subjectKeywords = argMultimap.getValue(PREFIX_SUBJECT_OR_SCHOOL).orElse("");
            levelKeywords = argMultimap.getValue(PREFIX_LEVEL).orElse("");
            timeKeywords = argMultimap.getValue(PREFIX_TIME).orElse("");

            keywords.put(PREFIX_NAME, nameKeywords);
            keywords.put(PREFIX_DAY, dayKeywords);
            keywords.put(PREFIX_SUBJECT_OR_SCHOOL, subjectKeywords);
            keywords.put(PREFIX_LEVEL, levelKeywords);
            keywords.put(PREFIX_TIME, timeKeywords);
            break;
        }

        tagKeywords = argMultimap.getValue(PREFIX_TAG).orElse("");
        keywords.put(PREFIX_TAG, tagKeywords);

        return new FindCommand(keywords);
    }

    /**
     * Validates the arguments in the input ArgumentMultiMap.
     *
     * @param argMultimap The input ArgumentMultiMap.
     * @throws ParseException If there are invalid arguments given.
     */
    public void validateArguments(ArgumentMultimap argMultimap) throws ParseException {

        // Check if prefixes specified is relevant to the current list
        switch (listType) {
        case STUDENT_LIST:
            if (areAnyPrefixesPresent(argMultimap, PREFIX_QUALIFICATION, PREFIX_INSTITUTION,
                    PREFIX_DAY, PREFIX_TIME)) {
                throw new ParseException(String.format(MESSAGE_PREFIX_NOT_FOR_STUDENT, FindCommand.FEEDBACK_MESSAGE));
            }
            break;

        case TUTOR_LIST:
            if (areAnyPrefixesPresent(argMultimap, PREFIX_SUBJECT_OR_SCHOOL, PREFIX_LEVEL,
                    PREFIX_DAY, PREFIX_TIME)) {
                throw new ParseException(String.format(MESSAGE_PREFIX_NOT_FOR_TUTOR, FindCommand.FEEDBACK_MESSAGE));
            }
            break;

        default:
            if (areAnyPrefixesPresent(argMultimap, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE,
                    PREFIX_QUALIFICATION, PREFIX_INSTITUTION)) {
                throw new ParseException(String.format(MESSAGE_PREFIX_NOT_FOR_TUITIONCLASS,
                        FindCommand.FEEDBACK_MESSAGE));
            }
            break;
        }

        // Check that there are no invalid prefixes
        if (!areValidPrefixValues(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE,
                PREFIX_TAG, PREFIX_SUBJECT_OR_SCHOOL, PREFIX_LEVEL, PREFIX_QUALIFICATION, PREFIX_INSTITUTION,
                PREFIX_DAY, PREFIX_TIME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_PREFIX, FindCommand.FEEDBACK_MESSAGE));
        }

        // Check that there are no keywords without prefixes
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_KEYWORDS_WITHOUT_PREFIX, FindCommand.FEEDBACK_MESSAGE));
        }

        // Check that there is at least one prefix is specified
        if (!areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE,
                PREFIX_TAG, PREFIX_SUBJECT_OR_SCHOOL, PREFIX_LEVEL, PREFIX_QUALIFICATION, PREFIX_INSTITUTION,
                PREFIX_DAY, PREFIX_TIME)) {
            throw new ParseException(String.format(MESSAGE_NO_PREFIX_SPECIFIED, FindCommand.FEEDBACK_MESSAGE));
        }

        // Check that there are no prefixes specified without any keywords
        if (areAnyPrefixesEmpty(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE,
                PREFIX_TAG, PREFIX_SUBJECT_OR_SCHOOL, PREFIX_LEVEL, PREFIX_QUALIFICATION, PREFIX_INSTITUTION,
                PREFIX_DAY, PREFIX_TIME)) {
            throw new ParseException(String.format(MESSAGE_EMPTY_PREFIX, FindCommand.FEEDBACK_MESSAGE));
        }
    }


    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean areAnyPrefixesEmpty(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).orElse(" ").equals(""));
    }

    private static boolean areValidPrefixValues(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).noneMatch(prefix -> argumentMultimap
                .getValue(prefix).orElse(" ").contains("/"));
    }
}
