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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsAttributePredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_GENDER, PREFIX_BIRTHDATE, PREFIX_RACE, PREFIX_RELIGION, PREFIX_SURVEY);

        if (!areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_GENDER, PREFIX_BIRTHDATE, PREFIX_RACE, PREFIX_RELIGION, PREFIX_SURVEY)
                || !argMultimap.getPreamble().isEmpty()
                || args.trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
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

        return new ViewCommand(new PersonContainsAttributePredicate(nameList, phoneList, emailList, addressList,
                genderList, birthdateList, raceList, religionList, surveyList));
    }

    /**
     * Parses the given (possibly empty) {@code attributeStringOptional} of a given prefix.
     * @return A list of {@code String} of keywords associated to the given prefix.
     * If no keywords were specified, returns an empty {@code ArrayList)
     */
    private static List<String> getKeywordsAsList(Optional<String> attributeStringOptional) {
        return attributeStringOptional
                .map(arg -> arg.trim().split("\\s+"))
                .map(Arrays::asList)
                .orElse(new ArrayList<>());
    }

    /**
     * Returns true if some prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
