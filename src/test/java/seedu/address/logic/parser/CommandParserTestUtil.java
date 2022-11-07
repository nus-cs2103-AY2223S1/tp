package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.event.AddEventCommand;
import seedu.address.logic.commands.event.AddProfilesToEventCommand;
import seedu.address.logic.commands.event.DeleteEventCommand;
import seedu.address.logic.commands.event.DeleteProfilesFromEventCommand;
import seedu.address.logic.commands.event.EditEventCommand;
import seedu.address.logic.commands.event.ViewEventsCommand;
import seedu.address.logic.commands.event.ViewUpcomingEventsCommand;
import seedu.address.logic.commands.profile.AddProfileCommand;
import seedu.address.logic.commands.profile.DeleteProfileCommand;
import seedu.address.logic.commands.profile.EditProfileCommand;
import seedu.address.logic.commands.profile.FindProfileCommand;
import seedu.address.logic.commands.profile.ViewProfilesCommand;
import seedu.address.logic.parser.event.AddEventCommandParser;
import seedu.address.logic.parser.event.AddProfilesToEventCommandParser;
import seedu.address.logic.parser.event.DeleteEventCommandParser;
import seedu.address.logic.parser.event.DeleteProfilesFromEventCommandParser;
import seedu.address.logic.parser.event.EditEventCommandParser;
import seedu.address.logic.parser.event.FindEventCommandParser;
import seedu.address.logic.parser.event.ViewEventsCommandParser;
import seedu.address.logic.parser.event.ViewUpcomingEventsCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.profile.AddProfileCommandParser;
import seedu.address.logic.parser.profile.DeleteProfileCommandParser;
import seedu.address.logic.parser.profile.EditProfileCommandParser;
import seedu.address.logic.parser.profile.FindProfileCommandParser;
import seedu.address.logic.parser.profile.ViewProfilesCommandParser;

/**
 * Contains helper methods for testing command parsers.
 */
public class CommandParserTestUtil {

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(Parser<? extends Command> parser, String userInput, Command expectedCommand) {
        if (parser instanceof AddProfileCommandParser) {
            userInput = " -" + AddProfileCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof DeleteProfileCommandParser) {
            userInput = " -" + DeleteProfileCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof EditProfileCommandParser) {
            userInput = " -" + EditProfileCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof FindProfileCommandParser) {
            userInput = " -" + FindProfileCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof ViewProfilesCommandParser) {
            userInput = " -" + ViewProfilesCommand.COMMAND_OPTION + " " + userInput;
        }

        if (parser instanceof AddEventCommandParser) {
            userInput = " -" + AddEventCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof DeleteEventCommandParser) {
            userInput = " -" + DeleteEventCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof EditEventCommandParser) {
            userInput = " -" + EditEventCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof FindEventCommandParser) {
            userInput = " -" + EditEventCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof ViewUpcomingEventsCommandParser) {
            userInput = " -" + ViewUpcomingEventsCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof ViewEventsCommandParser) {
            userInput = " -" + ViewEventsCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof AddProfilesToEventCommandParser) {
            userInput = " -" + AddProfilesToEventCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof DeleteProfilesFromEventCommandParser) {
            userInput = " -" + DeleteProfilesFromEventCommand.COMMAND_OPTION + " " + userInput;
        }

        try {
            Command command = parser.parse(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseFailure(Parser<? extends Command> parser, String userInput, String expectedMessage) {
        if (parser instanceof AddProfileCommandParser) {
            userInput = " -" + AddProfileCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof DeleteProfileCommandParser) {
            userInput = " -" + DeleteProfileCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof EditProfileCommandParser) {
            userInput = " -" + EditProfileCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof FindProfileCommandParser) {
            userInput = " -" + FindProfileCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof ViewProfilesCommandParser) {
            userInput = " -" + ViewProfilesCommand.COMMAND_OPTION + " " + userInput;
        }

        if (parser instanceof AddEventCommandParser) {
            userInput = " -" + AddEventCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof DeleteEventCommandParser) {
            userInput = " -" + DeleteEventCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof EditEventCommandParser) {
            userInput = " -" + EditEventCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof FindEventCommandParser) {
            userInput = " -" + EditEventCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof ViewUpcomingEventsCommandParser) {
            userInput = " -" + ViewUpcomingEventsCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof ViewEventsCommandParser) {
            userInput = " -" + ViewEventsCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof AddProfilesToEventCommandParser) {
            userInput = " -" + AddProfilesToEventCommand.COMMAND_OPTION + " " + userInput;
        } else if (parser instanceof DeleteProfilesFromEventCommandParser) {
            userInput = " -" + DeleteProfilesFromEventCommand.COMMAND_OPTION + " " + userInput;
        }

        try {
            parser.parse(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }
}
