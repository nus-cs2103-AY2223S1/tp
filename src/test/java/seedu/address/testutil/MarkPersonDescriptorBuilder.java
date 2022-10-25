package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkPersonDescriptor;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.GradeProgress;
import seedu.address.model.person.Homework;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Session;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building MarkPersonDescriptor objects.
 */
public class MarkPersonDescriptorBuilder {

    private MarkPersonDescriptor descriptor;

    public MarkPersonDescriptorBuilder() {
        descriptor = new MarkPersonDescriptor();
    }

    public MarkPersonDescriptorBuilder(MarkPersonDescriptor descriptor) {
        this.descriptor = new MarkPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code MarkPersonDescriptor} with fields containing {@code person}'s details
     */
    public MarkPersonDescriptorBuilder(Person person) {
        descriptor = new MarkPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setLessonPlan(person.getLessonPlan());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code MarkPersonDescriptor} that we are building.
     */
    public MarkPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code MarkPersonDescriptor} that we are building.
     */
    public MarkPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code LessonPlan} of the {@code MarkPersonDescriptor} that we are building.
     */
    public MarkPersonDescriptorBuilder withLessonPlan(String lessonPlan) {
        descriptor.setLessonPlan(new LessonPlan(lessonPlan));
        return this;
    }

    /**
     * Sets the {@code Homework} of the {@code MarkPersonDescriptor} that we are building.
     */
    public MarkPersonDescriptorBuilder withHomework(String homework) {
        descriptor.setHomework(new Homework(homework));
        return this;
    }

    /**
     * Sets the {@code Index} of the homework of the {@code MarkPersonDescriptor} that we are building.
     */
    public MarkPersonDescriptorBuilder withHomeworkIndex(Index homeworkIndex) {
        descriptor.setHomeworkIndex(homeworkIndex);
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code MarkPersonDescriptor} that we are building.
     */
    public MarkPersonDescriptorBuilder withAttendance(String attendance) {
        descriptor.setAttendance(new Attendance(attendance));
        return this;
    }

    /**
     * Sets the {@code Index} of the attendance of the {@code MarkPersonDescriptor} that we are building.
     */
    public MarkPersonDescriptorBuilder withAttendanceIndex(Index attendanceIndex) {
        descriptor.setAttendanceIndex(attendanceIndex);
        return this;
    }

    /**
     * Sets the {@code Session} of the {@code MarkPersonDescriptor} that we are building.
     */
    public MarkPersonDescriptorBuilder withSession(String session) {
        descriptor.setSession(new Session(session));
        return this;
    }

    /**
     * Sets the {@code Index} of the session of the {@code MarkPersonDescriptor} that we are building.
     */
    public MarkPersonDescriptorBuilder withSessionIndex(Index sessionIndex) {
        descriptor.setSessionIndex(sessionIndex);
        return this;
    }

    /**
     * Sets the {@code GradeProgress} of the {@code MarkPersonDescriptor} that we are building.
     */
    public MarkPersonDescriptorBuilder withGradeProgress(String gradeProgress) {
        descriptor.setGradeProgress(new GradeProgress(gradeProgress));
        return this;
    }

    /**
     * Sets the {@code Index} of the grade of the {@code MarkPersonDescriptor} that we are building.
     */
    public MarkPersonDescriptorBuilder withGradeProgressIndex(Index gradeProgressIndex) {
        descriptor.setGradeProgressIndex(gradeProgressIndex);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code MarkPersonDescriptor}
     * that we are building.
     */
    public MarkPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public MarkPersonDescriptor build() {
        return descriptor;
    }
}
