package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import java.util.stream.Stream;

import seedu.uninurse.logic.commands.AddPatientCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.person.Address;
import seedu.uninurse.model.person.Email;
import seedu.uninurse.model.person.Name;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Phone;
import seedu.uninurse.model.remark.RemarkList;
import seedu.uninurse.model.tag.TagList;
import seedu.uninurse.model.task.TaskList;

/**
 * Parses input arguments and creates a new AddPatientCommand object
 */
public class AddPatientCommandParser implements Parser<AddPatientCommand> {
    /**
     * Parses the given arguments in the context of the AddPatientCommand
     * and returns an AddPatientCommand object for execution.
     *
     * @param args The string of arguments given.
     * @return AddPatientCommand
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddPatientCommand parse(String args) throws ParseException {
        requireAllNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_CONDITION, PREFIX_MEDICATION, PREFIX_TASK_DESCRIPTION, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPatientCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        TagList tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        ConditionList conditionList = ParserUtil.parseConditions(argMultimap.getAllValues(PREFIX_CONDITION));
        MedicationList medicationList = ParserUtil.parseMedications(argMultimap.getAllValues(PREFIX_MEDICATION));
        TaskList taskList = ParserUtil.parseTasks(argMultimap.getAllValues(PREFIX_TASK_DESCRIPTION));
        RemarkList remarkList = ParserUtil.parseRemarks(argMultimap.getAllValues(PREFIX_REMARK));

        Patient person =
                new Patient(name, phone, email, address, tagList, conditionList, medicationList, taskList, remarkList);

        return new AddPatientCommand(person);
    }

    /**
     * Checks if all the prefixes are present in the given ArgumentMultimap.
     *
     * @param argumentMultimap The given ArgumentMultiMap.
     * @param prefixes The given prefixes.
     * @return True if none of the prefixes contains empty Optional values in the given ArgumentMultiMap.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
