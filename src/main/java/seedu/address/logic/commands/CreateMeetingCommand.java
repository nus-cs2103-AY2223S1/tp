package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.text.ParseException;
import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Creates a meeting with a person in the address book
 */
public class CreateMeetingCommand extends Command {

    public static final String COMMAND_WORD = "meet";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Schedules a new meeting between you and another user.\n"
        + "Parameters: NAMES OF PEOPLE (from address book) YOU ARE MEETING, (split names by }} ) ;;;\n"
        + "Title of meeting;;;\n"
        + "Date and time of meeting (in dd-MM-yyyy HHmm) format;;;\n"
        + "location of meeting\n"
        + "Example: " + COMMAND_WORD + " Alex Yeoh }} Bernice Yu ;;; Study Session ;;; 06-10-2022 2015 ;;; UTown";

    public static final String MESSAGE_CREATE_MEETING_SUCCESS = "Created meeting with: \n%1$s";

    private final String meetingInfo;

    public CreateMeetingCommand(String meetingInfo) {
        this.meetingInfo = meetingInfo;
    }



    private String peopleToList(ArrayList<Person> arrayOfPeopleToMeet) {
        String output = "";
        for (Person personToMeet : arrayOfPeopleToMeet) {
            String toAppend = personToMeet.getName() + "\n";
            output += toAppend;
        }
        return output;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        try {
            requireNonNull(model);
            String[] newMeetingInformation = this.meetingInfo.split(";;;");
            String[] peopleToMeet = newMeetingInformation[0].strip().split("}}");
            String meetingTitle = newMeetingInformation[1].strip();
            String meetingDateAndTime = newMeetingInformation[2].strip();
            String meetingLocation = newMeetingInformation[3].strip();

            ArrayList<Person> arrayOfPeopleToMeet = Meeting.convertNameToPerson(model, peopleToMeet);

            Meeting newMeeting = model.createNewMeeting(arrayOfPeopleToMeet, meetingTitle,
                meetingDateAndTime, meetingLocation);
            model.addMeeting(newMeeting);

            return new CommandResult(
                String.format(MESSAGE_CREATE_MEETING_SUCCESS, peopleToList(arrayOfPeopleToMeet))
                    + String.format("For: %1$s \n", meetingTitle)
                    + String.format("On: %1$s \n", newMeeting.getDateAndTime())
                    + String.format("At: %1$s \n", meetingLocation)
            );

        } catch (ParseException e) {
            return new CommandResult(e.toString());

        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("Make sure you have entered "
                + "the correct amount of information correctly separated!");

        } catch (PersonNotFoundException e) {
            return new CommandResult("Oops! The person you are meeting with doesn't exist"
                + "in the address book. Do check if you have entered their name correctly.");
        } catch (DuplicatePersonException e) {
            return new CommandResult("It looks like you are adding the same "
                + "person to a meeting twice!");
        }
    }

}
