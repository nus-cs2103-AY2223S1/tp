package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_GET_PREFIX;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.GetCommand;
import seedu.address.logic.commands.getcommands.GetAppointmentByDateCommand;
import seedu.address.logic.commands.getcommands.GetFloorNumberCommand;
import seedu.address.logic.commands.getcommands.GetHospitalWingCommand;
import seedu.address.logic.commands.getcommands.GetInpatientCommand;
import seedu.address.logic.commands.getcommands.GetMedicationCommand;
import seedu.address.logic.commands.getcommands.GetNameCommand;
import seedu.address.logic.commands.getcommands.GetNextOfKinCommand;
import seedu.address.logic.commands.getcommands.GetOutpatientCommand;
import seedu.address.logic.commands.getcommands.GetPastAppointmentCommand;
import seedu.address.logic.commands.getcommands.GetWardNumberCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.getparsers.GetAppointmentByDateCommandParser;
import seedu.address.logic.parser.getparsers.GetFloorNumberCommandParser;
import seedu.address.logic.parser.getparsers.GetHospitalWingCommandParser;
import seedu.address.logic.parser.getparsers.GetMedicationCommandParser;
import seedu.address.logic.parser.getparsers.GetNameCommandParser;
import seedu.address.logic.parser.getparsers.GetNextOfKinCommandParser;
import seedu.address.logic.parser.getparsers.GetPastAppointmentCommandParser;
import seedu.address.logic.parser.getparsers.GetWardNumberCommandParser;

/**
 * Parses get command input
 */
public class GetCommandParser implements Parser<GetCommand> {

    private static final Pattern GET_COMMAND_FORMAT = Pattern.compile("(?<prefix>\\S+)(?<arguments>.*)");

    @Override
    public GetCommand parse(String userInput) throws ParseException {

        final Matcher getCommandMatcher = GET_COMMAND_FORMAT.matcher(userInput.trim());

        if (getCommandMatcher.matches()) {
            final String prefix = getCommandMatcher.group("prefix");
            final String arguments = getCommandMatcher.group("arguments");
            switch (prefix) {

            case GetOutpatientCommand.OUTPATIENT_PREFIX:
                return new GetOutpatientCommand();

            case GetInpatientCommand.INPATIENT_PREFIX:
                return new GetInpatientCommand();

            case GetFloorNumberCommand.FLOOR_NUMBER_PREFIX:
                return new GetFloorNumberCommandParser().parse(arguments);

            case GetHospitalWingCommand.HOSPITAL_WING_PREFIX:
                return new GetHospitalWingCommandParser().parse(arguments);

            case GetWardNumberCommand.WARD_NUMBER_PREFIX:
                return new GetWardNumberCommandParser().parse(arguments);

            case GetNameCommand.NAME_PREFIX:
                return new GetNameCommandParser().parse(arguments);

            case GetNextOfKinCommand.NEXT_OF_KIN_PREFIX:
                return new GetNextOfKinCommandParser().parse(arguments);

            case GetPastAppointmentCommand.PAST_APPOINTMENT_PREFIX:
                return new GetPastAppointmentCommandParser().parse(arguments);

            case GetMedicationCommand.MEDICATION_PREFIX:
                return new GetMedicationCommandParser().parse(arguments);

            case GetAppointmentByDateCommand.APPOINTMENT_BY_DATE_PREFIX:
                return new GetAppointmentByDateCommandParser().parse(arguments);

            default:
                throw new ParseException(String.format(MESSAGE_INVALID_GET_PREFIX,
                        GetCommand.MESSAGE_USAGE));
            }
        }

        if (!getCommandMatcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetCommand.MESSAGE_USAGE));
        }
        return null;
    }
}
