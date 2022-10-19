package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Lesson;
import seedu.address.model.person.user.EmptyUser;

import static java.util.Objects.requireNonNull;

public class LessonUserCommand extends LessonCommand {
    private Lesson lesson;
    private static final EmptyUser EMPTY_USER = new EmptyUser();
    public static final String MESSAGE_NO_USER = "No user to add lessons to!";
    public static final String MESSAGE_ADD_LESSON_SUCCESS = "Added Lesson for User: %1$s";


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
        return new CommandResult(String.format(MESSAGE_ADD_LESSON_SUCCESS, lesson));
    }
}
