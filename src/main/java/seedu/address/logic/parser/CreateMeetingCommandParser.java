package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;
import java.util.Objects;

import seedu.address.logic.commands.CreateMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.util.DateTimeProcessor;

/**
 * Parses input arguments and creates a new CreateMeetingCommand object
 */
public class CreateMeetingCommandParser implements Parser<CreateMeetingCommand> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.UK)
        .withResolverStyle(ResolverStyle.SMART);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm", Locale.UK)
        .withResolverStyle(ResolverStyle.SMART);
    public static final DateTimeProcessor DATE_TIME_VALIDATOR = new DateTimeProcessor(DATE_FORMATTER, TIME_FORMATTER);

    /**
     * Parses the given {@code String} of arguments in the context of the CreateMeetingCommand
     * and returns a CreateMeetingCommand object for execution.
     * @return a command to create a new meeting
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateMeetingCommand parse(String args) throws ParseException {
        String meetingInfo = args.trim();
        if (meetingInfo.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateMeetingCommand.MESSAGE_USAGE));
        }

        try {
            String[] newMeetingInformation = meetingInfo.split(";;;");

            String[] peopleToMeet = newMeetingInformation[0].strip().split("}}");
            String meetingTitle = newMeetingInformation[1].strip();
            String meetingDateAndTime = newMeetingInformation[2].strip();
            String meetingLocation = newMeetingInformation[3].strip();

            if ((Objects.equals(meetingTitle, "")) || (Objects.equals(meetingLocation, ""))) {
                throw new ParseException(CreateMeetingCommand.INCORRECT_NUMBER_OF_ARGUMENTS);
            }

            String processedMeetingDateAndTime = DATE_TIME_VALIDATOR.processDateTime(meetingDateAndTime);

            return new CreateMeetingCommand(peopleToMeet, meetingTitle, processedMeetingDateAndTime, meetingLocation);

        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(CreateMeetingCommand.INCORRECT_NUMBER_OF_ARGUMENTS);

        } catch (java.text.ParseException e) {
            throw new ParseException(String.format(CreateMeetingCommand.INVALID_DATE_AND_TIME_FORMAT, e.getMessage()));
        }

    }

}
