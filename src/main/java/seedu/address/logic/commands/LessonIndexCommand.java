package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Lesson;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class LessonIndexCommand extends LessonCommand {
    private Lesson lesson;
    private Index index;

    public static final String MESSAGE_ADD_LESSON_SUCCESS = "Added Lesson for Person: %1$s";


    public LessonIndexCommand(Lesson lesson, Index index) {
        super();
        requireNonNull(lesson);
        requireNonNull(index);
        this.lesson = lesson;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        personToEdit.addLesson(lesson);
        return new CommandResult(String.format(MESSAGE_ADD_LESSON_SUCCESS, lesson));
    }
}
