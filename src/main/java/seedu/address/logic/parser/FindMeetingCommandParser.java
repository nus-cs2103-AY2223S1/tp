package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.FindMeetingCommand.GET_DESCRIPTION;
import static seedu.address.logic.commands.FindMeetingCommand.GET_LOCATION;
import static seedu.address.logic.commands.FindMeetingCommand.GET_PEOPLE;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.MeetingContainsKeywordsPredicate;
import seedu.address.model.util.FindMeetingFunctionalInterface;

/**
 * Parses input arguments and creates a new FindMeetingCommand object
 */
public class FindMeetingCommandParser implements Parser<FindMeetingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindMeetingCommand
     * and returns a FindMeetingCommand object for execution.
     * @param args arguments of the command, IE the user input
     * @return A new command to find a contact
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public Command parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMeetingCommand.MESSAGE_USAGE));
        }
        String[] nameKeywords = trimmedArgs.split("\\s+");
        String parameter = nameKeywords[0];
        ArrayList<String> stringList = new ArrayList<>(Arrays.asList(nameKeywords));
        stringList.remove(0);
        return new FindMeetingCommand(new MeetingContainsKeywordsPredicate(stringList, verifyParameters(parameter)));
    }

    /**
     * verifies the parameters and returns the appropriate meeting field
     * @param parameter user input to be parsed
     * @return returns a FindMeetingFunctionalInterface which encapsulates a method
     * @throws ParseException when parameter is wrong
     */
    private FindMeetingFunctionalInterface verifyParameters(String parameter) throws ParseException {
        switch (parameter) {
        case FindMeetingCommand.FIND_AT:
            return GET_LOCATION;

        case FindMeetingCommand.FIND_DESCRIPTION:
            return GET_DESCRIPTION;

        case FindMeetingCommand.FIND_WITH:
            return GET_PEOPLE;

        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMeetingCommand.MESSAGE_USAGE));
        }
    }
}
