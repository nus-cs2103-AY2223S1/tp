package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.user.EmptyUser;
import seedu.address.model.person.user.User;

/**
 * Command to show timetable of user.
 */
public class TimetableUserCommand extends TimetableCommand {
    public static final String MESSAGE_TIMETABLE_ACKNOWLEDGEMENT = "Showing user timetable as requested ...";
    public static final String MESSAGE_NO_USER = "No user to show timetable from!";
    public static final String MESSAGE_NO_LESSONS = "No lessons added to user!";
    private static final EmptyUser EMPTY_USER = new EmptyUser();

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasUser()) {
            throw new CommandException(MESSAGE_NO_USER);
        }

        User user = model.getUser();

        assert !user.equals(EMPTY_USER) : "user to show timetable should not be empty";

        if (!model.setTimetable(user.getLessons())) {
            throw new CommandException(MESSAGE_NO_LESSONS);
        }
        model.commitAddressBook();
        return new CommandResult(MESSAGE_TIMETABLE_ACKNOWLEDGEMENT, false, false, true);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (o instanceof TimetableUserCommand) {
            return true;
        }

        return false;
    }
}
