package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Lesson;
import seedu.address.model.person.Person;

/**
 * Commands that add lessons to a contact
 */
public class LessonIndexCommand extends LessonCommand {
    public static final String MESSAGE_ADD_LESSON_SUCCESS = "Added Lesson (%s) to contact at index %d, %s";

    private Lesson lesson;
    private Index index;

    /**
     * constructor for new instance.
     *
     * @param lesson to be added to the contact.
     * @param index of contact to add lesson to.
     */
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
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_ADD_LESSON_SUCCESS, lesson.toFullString(),
                index.getZeroBased(), personToEdit.getName()));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (o instanceof LessonIndexCommand) {
            LessonIndexCommand lesson = (LessonIndexCommand) o;
            return lesson.lesson.equals(this.lesson) && lesson.index.equals(this.index);
        }

        return false;
    }
}
