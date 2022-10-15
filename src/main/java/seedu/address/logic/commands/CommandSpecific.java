package seedu.address.logic.commands;

/**
 * Represents what the command's execution is specific to, i.e., an AddMeetingCommand should be specific to MEETING.
 */
public enum CommandSpecific {
    CLIENT, MEETING, DETAILED_CLIENT, DETAILED_MEETING, NONSPECIFIC
}
