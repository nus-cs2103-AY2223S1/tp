package seedu.address.logic.commands;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Stores the details to mark/unmark the person with. Each non-empty field value will replace the
 * corresponding field value of the person.
 */
public class MarkPersonDescriptor {
    private Name name;
    private Phone phone;
    private LessonPlan lessonPlan;
    private Index homeworkIndex;
    private Index attendanceIndex;


    public MarkPersonDescriptor() {
    }

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public MarkPersonDescriptor(MarkPersonDescriptor toCopy) {
        setHomeworkIndex(toCopy.homeworkIndex);
        setAttendanceIndex(toCopy.attendanceIndex);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }

    public void setLessonPlan(LessonPlan lessonPlan) {
        this.lessonPlan = lessonPlan;
    }

    public Optional<LessonPlan> getLessonPlan() {
        return Optional.ofNullable(lessonPlan);
    }

    public void setHomeworkIndex(Index homeworkIndex) {
        this.homeworkIndex = homeworkIndex;
    }

    public Optional<Index> getHomeworkIndex() {
        return Optional.ofNullable(homeworkIndex);
    }

    public void setAttendanceIndex(Index attendanceIndex) {
        this.attendanceIndex = attendanceIndex;
    }

    public Optional<Index> getAttendanceIndex() {
        return Optional.ofNullable(attendanceIndex);
    }


    @Override
    public boolean equals(Object other) {
        // for same object
        if (other == this) {
            return true;
        }

        // checks for null object
        if (!(other instanceof MarkPersonDescriptor)) {
            return false;
        }

        // check for same values in fields
        MarkPersonDescriptor m = (MarkPersonDescriptor) other;
        return getHomeworkIndex().equals(m.getHomeworkIndex())
                && getAttendanceIndex().equals(m.getAttendanceIndex());
    }
}
