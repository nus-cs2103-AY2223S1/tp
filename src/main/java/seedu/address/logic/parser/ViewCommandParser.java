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

        Optional<String> nameStringOptional = argMultimap.getValue(PREFIX_NAME);
        Optional<String> phoneStringOptional = argMultimap.getValue(PREFIX_PHONE);
        Optional<String> emailStringOptional = argMultimap.getValue(PREFIX_EMAIL);
        Optional<String> addressStringOptional = argMultimap.getValue(PREFIX_ADDRESS);
        Optional<String> genderStringOptional = argMultimap.getValue(PREFIX_GENDER);
        Optional<String> birthdateStringOptional = argMultimap.getValue(PREFIX_BIRTHDATE);
        Optional<String> raceStringOptional = argMultimap.getValue(PREFIX_RACE);
        Optional<String> religionStringOptional = argMultimap.getValue(PREFIX_RELIGION);
        Optional<String> surveyStringOptional = argMultimap.getValue(PREFIX_SURVEY);

        String[] nameKeywords = nameStringOptional.isEmpty() ? new String[] {}
                : nameStringOptional.get().trim().split("\\s+");
        String[] phoneKeywords = phoneStringOptional.isEmpty() ? new String[] {}
                : phoneStringOptional.get().trim().split("\\s+");
        String[] emailKeywords = emailStringOptional.isEmpty() ? new String[] {}
                : emailStringOptional.get().trim().split("\\s+");
        String[] addressKeywords = addressStringOptional.isEmpty() ? new String[] {}
                : addressStringOptional.get().trim().split("\\s+");
        String[] genderKeywords = genderStringOptional.isEmpty() ? new String[] {}
                : genderStringOptional.get().trim().split("\\s+");
        String[] birthdateKeywords = birthdateStringOptional.isEmpty() ? new String[] {}
                : birthdateStringOptional.get().trim().split("\\s+");
        String[] raceKeywords = raceStringOptional.isEmpty() ? new String[] {}
                : raceStringOptional.get().trim().split("\\s+");
        String[] religionKeywords = religionStringOptional.isEmpty() ? new String[] {}
                : religionStringOptional.get().trim().split("\\s+");
        String[] surveyKeywords = surveyStringOptional.isEmpty() ? new String[] {}
                : surveyStringOptional.get().trim().split("\\s+");

        List<String> nameList = Arrays.asList(nameKeywords);
        List<String> phoneList = Arrays.asList(phoneKeywords);
        List<String> emailList = Arrays.asList(emailKeywords);
        List<String> addressList = Arrays.asList(addressKeywords);
        List<String> genderList = Arrays.asList(genderKeywords);
        List<String> birthdateList = Arrays.asList(birthdateKeywords);
        List<String> raceList = Arrays.asList(raceKeywords);
        List<String> religionList = Arrays.asList(religionKeywords);
        List<String> surveyList = Arrays.asList(surveyKeywords);

        return new ViewCommand(new PersonContainsAttributePredicate(nameList, phoneList, emailList, addressList,
                genderList, birthdateList, raceList, religionList, surveyList));
    }

    /**
     * Returns true if some of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
