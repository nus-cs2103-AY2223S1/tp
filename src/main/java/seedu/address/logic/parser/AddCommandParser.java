package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CATEGORY;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNAVAILABLE_DATE;
import static seedu.address.model.category.Category.NURSE_SYMBOL;
import static seedu.address.model.category.Category.PATIENT_SYMBOL;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.category.Category;
import seedu.address.model.person.Address;
import seedu.address.model.person.Date;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.HomeVisit;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Uid;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_NAME,
                PREFIX_GENDER, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_DATE_AND_SLOT,
                PREFIX_UNAVAILABLE_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_CATEGORY, PREFIX_NAME, PREFIX_GENDER,
                PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        Boolean haveDateSlot = arePrefixesPresent(argMultimap, PREFIX_DATE_AND_SLOT);
        Boolean haveUnavailableDate = arePrefixesPresent(argMultimap, PREFIX_UNAVAILABLE_DATE);

        Category category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());

        Boolean isPatient = category.categoryName.equals(PATIENT_SYMBOL);
        Boolean isNurse = category.categoryName.equals(NURSE_SYMBOL);
        if (isPatient && haveUnavailableDate) {
            throw new ParseException(AddCommand.MESSAGE_INVALID_FIELD_PATIENT);
        }
        if (isNurse && haveDateSlot) {
            throw new ParseException(AddCommand.MESSAGE_INVALID_FIELD_NURSE);
        }

        Uid id = new Uid();
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Gender gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        List<DateSlot> dateTimeSlotList = ParserUtil.parseDatesSlots(argMultimap.getAllValues(PREFIX_DATE_AND_SLOT));
        List<Date> unavailableDateList = ParserUtil.parseDates(argMultimap.getAllValues(PREFIX_UNAVAILABLE_DATE));

        Person person;

        if (isNurse) {
            List<HomeVisit> nullHomeVisitList = new ArrayList<>();
            List<Date> nullFullyScheduledDateList = new ArrayList<>();
            person = new Nurse(id, name, gender, phone, email, address, tagList, unavailableDateList,
                    nullHomeVisitList, nullFullyScheduledDateList);

        } else if (isPatient) {
            person = new Patient(id, name, gender, phone, email, address, tagList, dateTimeSlotList);

        } else {
            throw new ParseException(MESSAGE_INVALID_CATEGORY);
        }

        return new AddCommand(person);
    }

}
