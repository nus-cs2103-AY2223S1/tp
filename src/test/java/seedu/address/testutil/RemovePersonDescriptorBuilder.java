package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveCommand.RemovePersonDescriptor;
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
 * A utility class to help with building RemovePersonDescriptor objects.
 */
public class RemovePersonDescriptorBuilder {

    private RemovePersonDescriptor descriptor;

    public RemovePersonDescriptorBuilder() {
        descriptor = new RemovePersonDescriptor();
    }

    /**
     * Returns a {@code RemovePersonDescriptor}
     */
    public RemovePersonDescriptorBuilder(RemovePersonDescriptor descriptor) {
        this.descriptor = new RemovePersonDescriptor(descriptor);
    }

    /**
     * Returns a {@code RemovePersonDescriptor} with fields containing {@code person}'s details
     */
    public RemovePersonDescriptorBuilder(Person person) {
        descriptor = new RemovePersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setLessonPlan(person.getLessonPlan());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code RemovePersonDescriptor} that we are building.
     */
    public RemovePersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code RemovePersonDescriptor} that we are building.
     */
    public RemovePersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code LessonPlan} of the {@code RemovePersonDescriptor} that we are building.
     */
    public RemovePersonDescriptorBuilder withLessonPlan(String lessonPlan) {
        descriptor.setLessonPlan(new LessonPlan(lessonPlan));
        return this;
    }

    /**
     * Sets the {@code Homework} of the {@code RemovePersonDescriptor} that we are building.
     */
    public RemovePersonDescriptorBuilder withHomework(String homework) {
        descriptor.setHomework(new Homework(homework));
        return this;
    }

    /**
     * Sets the {@code Index} of the homework of the {@code RemovePersonDescriptor} that we are building.
     */
    public RemovePersonDescriptorBuilder withHomeworkIndex(Index homeworkIndex) {
        descriptor.setHomeworkIndex(homeworkIndex);
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code RemovePersonDescriptor} that we are building.
     */
    public RemovePersonDescriptorBuilder withAttendance(String attendance) {
        descriptor.setAttendance(new Attendance(attendance));
        return this;
    }

    /**
     * Sets the {@code Index} of the attendance of the {@code RemovePersonDescriptor} that we are building.
     */
    public RemovePersonDescriptorBuilder withAttendanceIndex(Index attendanceIndex) {
        descriptor.setAttendanceIndex(attendanceIndex);
        return this;
    }

    /**
     * Sets the {@code Session} of the {@code RemovePersonDescriptor} that we are building.
     */
    public RemovePersonDescriptorBuilder withSession(String session) {
        descriptor.setSession(new Session(session));
        return this;
    }

    /**
     * Sets the {@code Index} of the session of the {@code RemovePersonDescriptor} that we are building.
     */
    public RemovePersonDescriptorBuilder withSessionIndex(Index sessionIndex) {
        descriptor.setSessionIndex(sessionIndex);
        return this;
    }

    /**
     * Sets the {@code GradeProgress} of the {@code RemovePersonDescriptor} that we are building.
     */
    public RemovePersonDescriptorBuilder withGradeProgress(String gradeProgress) {
        descriptor.setGradeProgress(new GradeProgress(gradeProgress));
        return this;
    }

    /**
     * Sets the {@code Index} of the grade of the {@code RemovePersonDescriptor} that we are building.
     */
    public RemovePersonDescriptorBuilder withGradeProgressIndex(Index gradeProgressIndex) {
        descriptor.setGradeProgressIndex(gradeProgressIndex);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code RemovePersonDescriptor}
     * that we are building.
     */
    public RemovePersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public RemovePersonDescriptor build() {
        return descriptor;
    }
}
