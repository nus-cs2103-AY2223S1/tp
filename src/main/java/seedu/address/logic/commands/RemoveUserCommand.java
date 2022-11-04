package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Lesson;
import seedu.address.model.person.user.EmptyUser;

/**
 * Removes lesson from User
 */
public class RemoveUserCommand extends RemoveCommand {
    public static final String MESSAGE_REMOVE_LESSON_SUCCESS = "Removed Lesson for User: %1$s";
    public static final String MESSAGE_NO_USER = "No user to remove lessons from!";
    private static final EmptyUser EMPTY_USER = new EmptyUser();

    private Lesson lesson;

    /**
     * Constructor for new instance.
     *
     * @param lesson to remove from user
     */
    public RemoveUserCommand(Lesson lesson) {
        super();
        this.lesson = lesson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasUser()) {
            throw new CommandException(MESSAGE_NO_USER);
        }

        assert !model.getUser().equals(EMPTY_USER) : "user to edit should not be empty";

        model.removeLessonToUser(lesson);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_REMOVE_LESSON_SUCCESS, lesson.toFullString()));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (o instanceof RemoveUserCommand) {
            RemoveUserCommand command = (RemoveUserCommand) o;
            return command.lesson.equals(this.lesson);
        }

        return false;
    }
}
