package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Lesson;
import seedu.address.model.person.user.EmptyUser;

/**
 * Commands that add lessons to the User
 */
public class LessonUserCommand extends LessonCommand {
    public static final String MESSAGE_ADD_LESSON_SUCCESS = "Added Lesson for User: %1$s";
    public static final String MESSAGE_NO_USER = "No user to add lessons to!";
    private static final EmptyUser EMPTY_USER = new EmptyUser();

    private Lesson lesson;

    /**
     * Constructor for new instance.
     *
     * @param lesson to add to user
     */
    public LessonUserCommand(Lesson lesson) {
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

        model.addLessonToUser(lesson);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_ADD_LESSON_SUCCESS, lesson.toFullString()));
    }
}
