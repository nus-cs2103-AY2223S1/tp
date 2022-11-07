package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELIGION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SURVEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsAttributePredicate;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    public static final String MESSAGE_EMPTY_STRING = "Please specify at least one word for each prefix entered!";

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_GENDER, PREFIX_BIRTHDATE, PREFIX_RACE, PREFIX_RELIGION, PREFIX_SURVEY,
                        PREFIX_TAG);

        if (!areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_GENDER, PREFIX_BIRTHDATE, PREFIX_RACE, PREFIX_RELIGION, PREFIX_SURVEY,
                PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()
                || args.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        if (!areAllPrefixValuesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_GENDER, PREFIX_BIRTHDATE, PREFIX_RACE, PREFIX_RELIGION, PREFIX_SURVEY,
                PREFIX_TAG)) {
            throw new ParseException(MESSAGE_EMPTY_STRING);
        }

        List<String> nameList = getKeywordsAsList(argMultimap.getValue(PREFIX_NAME));
        List<String> phoneList = getKeywordsAsList(argMultimap.getValue(PREFIX_PHONE));
        List<String> emailList = getKeywordsAsList(argMultimap.getValue(PREFIX_EMAIL));
        List<String> addressList = getKeywordsAsList(argMultimap.getValue(PREFIX_ADDRESS));
        List<String> genderList = getKeywordsAsList(argMultimap.getValue(PREFIX_GENDER));
        List<String> birthdateList = getKeywordsAsList(argMultimap.getValue(PREFIX_BIRTHDATE));
        List<String> raceList = getKeywordsAsList(argMultimap.getValue(PREFIX_RACE));
        List<String> religionList = getKeywordsAsList(argMultimap.getValue(PREFIX_RELIGION));
        List<String> surveyList = getKeywordsAsList(argMultimap.getValue(PREFIX_SURVEY));
        List<String> tagList = getKeywordsAsList(argMultimap.getValue(PREFIX_TAG));

        PersonContainsAttributePredicate predicate = new PersonContainsAttributePredicate(nameList, phoneList,
                emailList, addressList, genderList, birthdateList, raceList, religionList, surveyList,
                tagList);

        return new ViewCommand(predicate);
    }

    /**
     * Parses the given (possibly empty) {@code Optional<String>} of a given prefix.
     * @return A list of {@code String} of keywords associated to the given prefix.
     */
    private static List<String> getKeywordsAsList(Optional<String> attributeStringOptional) {
        return attributeStringOptional
                .map(ViewCommandParser::parseWithQuotations)
                .orElse(new ArrayList<>());
    }

    /**
     * Parses the given {@code String} of a given prefix with quotation marks.
     * @return A list of {@code String} of keywords associated to the given prefix, taking into account
     *     quotation marks to parse exact phrases.
     */
    //Solution below adapted from https://stackoverflow.com/a/7804472
    private static List<String> parseWithQuotations(String input) {
        if (input.trim().isEmpty()) {
            return new ArrayList<>();
        }
        input = input.replaceAll("\"", " \" ");
        ArrayList<String> parsedArray = new ArrayList<>();
        Matcher m = Pattern.compile("\\s*([^\"]\\S*|\".+?\")\\s*").matcher(input);
        while (m.find()) {
            String toAdd = m.group(1).replace("\"", "").trim();
            if (!toAdd.isEmpty()) {
                parsedArray.add(toAdd);
            }
        }
        return parsedArray;
    }

    /**
     * Returns true if some prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if some prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAllPrefixValuesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .noneMatch(prefix -> {
                    String value = argumentMultimap.getValue(prefix).get().trim();
                    return parseWithQuotations(value).isEmpty();
                });
    }

}
