package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private String nameKeywords;
    private String addressKeywords;
    private String emailKeywords;
    private String phoneKeywords;

    private String schoolKeywords;
    private String levelKeywords;

    private String qualificationsKeywords;
    private String institutionKeywords;

    private String dayKeywords;
    private String subjectKeywords;
    private String timeKeywords;

    private HashMap<Prefix, String> keywords = new HashMap<>();

    Model.ListType listType;


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
                        PREFIX_TAG, PREFIX_SCHOOL, PREFIX_LEVEL);

        if (areAnyPrefixesEmpty(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE,
                PREFIX_TAG, PREFIX_SCHOOL, PREFIX_LEVEL, PREFIX_QUALIFICATION, PREFIX_INSTITUTION, PREFIX_SUBJECT,
                PREFIX_DAY, PREFIX_TIME)) {
            throw new ParseException(String.format("Please do not leave prefixes empty!", FindCommand.MESSAGE_USAGE));
        }

//        if (!existsExactlyOnePrefix(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE,
//                PREFIX_TAG, PREFIX_SCHOOL, PREFIX_LEVEL, PREFIX_QUALIFICATION, PREFIX_INSTITUTION, PREFIX_SUBJECT,
//                PREFIX_DAY, PREFIX_TIME)) {
//            throw new ParseException(String.format("PLEASE GIVE AT LEAST AND ONLY ONE CONDITION", FindCommand.MESSAGE_USAGE));
//        }

        switch (listType) {

        case STUDENT_LIST:
            if (areAnyPrefixesPresent(argMultimap, PREFIX_QUALIFICATION, PREFIX_INSTITUTION, PREFIX_SUBJECT,
                    PREFIX_DAY, PREFIX_TIME)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            break;


//            nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
//            addressKeywords = argMultimap.getAllValues(PREFIX_NAME);
//            emailKeywords = argMultimap.getAllValues(PREFIX_NAME);
//            phoneKeywords = argMultimap.getAllValues(PREFIX_NAME);
//            schoolKeywords = argMultimap.getAllValues(PREFIX_NAME);
//            levelKeywords = argMultimap.getAllValues(PREFIX_NAME);
//
//            return new FindCommand(nameKeywords, addressKeywords, emailKeywords,
//                    phoneKeywords, schoolKeywords, levelKeywords);

        case TUTOR_LIST:
            if (areAnyPrefixesPresent(argMultimap, PREFIX_SCHOOL, PREFIX_LEVEL, PREFIX_SUBJECT,
                    PREFIX_DAY, PREFIX_TIME)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            break;

        default:
            if (areAnyPrefixesPresent(argMultimap, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_SCHOOL,
                    PREFIX_QUALIFICATION, PREFIX_INSTITUTION)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
        }

//        Prefix inputPrefix = getPrefix(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE,
//                PREFIX_TAG, PREFIX_SCHOOL, PREFIX_LEVEL, PREFIX_QUALIFICATION, PREFIX_INSTITUTION, PREFIX_SUBJECT,
//                PREFIX_DAY, PREFIX_TIME);

//        String trimmedArgs = argMultimap.getValue(inputPrefix).get().trim();
//        if (trimmedArgs.isEmpty()) {
//            throw new ParseException(
//                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
//        }

//        String[] nameKeywords = trimmedArgs.split("\\s+");
//        String[] nameKeywords = new String[1];
//        nameKeywords[0] = trimmedArgs;

        nameKeywords = argMultimap.getValue(PREFIX_NAME).orElse("");
        addressKeywords = argMultimap.getValue(PREFIX_ADDRESS).orElse("");
        emailKeywords = argMultimap.getValue(PREFIX_EMAIL).orElse("");
        phoneKeywords = argMultimap.getValue(PREFIX_PHONE).orElse("");
        schoolKeywords = argMultimap.getValue(PREFIX_SCHOOL).orElse("");
        levelKeywords = argMultimap.getValue(PREFIX_LEVEL).orElse("");

        keywords.put(PREFIX_NAME, nameKeywords);
        keywords.put(PREFIX_ADDRESS, addressKeywords);
        keywords.put(PREFIX_EMAIL, emailKeywords);
        keywords.put(PREFIX_PHONE, phoneKeywords);
        keywords.put(PREFIX_SCHOOL, schoolKeywords);
        keywords.put(PREFIX_LEVEL, levelKeywords);


        return new FindCommand(keywords);


//        String trimmedArgs = args.trim();
//        if (trimmedArgs.isEmpty()) {
//            throw new ParseException(
//                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
//        }
//
//        String[] nameKeywords = trimmedArgs.split("\\s+");
//
//        return new FindCommand(Arrays.asList(nameKeywords));
    }


    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean areAnyPrefixesEmpty(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).orElse(" ").equals(""));
    }

//    private static boolean existsExactlyOnePrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
//        Boolean res = false;
//        for (Prefix prefix : prefixes) {
//            res = res ^ argumentMultimap.getValue(prefix).isPresent();
//        }
//        return res;
//    }
//
//    private static Prefix getPrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
//        for (Prefix prefix : prefixes) {
//            if (argumentMultimap.getValue(prefix).isPresent()) {
//                return prefix;
//            };
//        }
//        return null;
//    }
}
