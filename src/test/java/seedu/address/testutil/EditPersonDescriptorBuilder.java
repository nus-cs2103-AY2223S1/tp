package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Duration;
import seedu.address.model.person.GradeProgress;
import seedu.address.model.person.Homework;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setLessonPlan(person.getLessonPlan());
        descriptor.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code LessonPlan} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withLessonPlan(String lessonPlan) {
        descriptor.setLessonPlan(new LessonPlan(lessonPlan));
        return this;
    }

    /**
     * Sets the {@code Homework} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withHomework(String homework) {
        descriptor.setHomework(new Homework(homework));
        return this;
    }

    /**
     * Sets the {@code Index} of the homework of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withHomeworkIndex(Index homeworkIndex) {
        descriptor.setHomeworkIndex(homeworkIndex);
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAttendance(String attendance) {
        descriptor.setAttendance(new Attendance(attendance));
        return this;
    }

    /**
     * Sets the {@code Index} of the attendance of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAttendanceIndex(Index attendanceIndex) {
        descriptor.setAttendanceIndex(attendanceIndex);
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDuration(String duration) {
        descriptor.setDuration(new Duration(duration));
        return this;
    }

    /**
     * Sets the {@code Index} of the duration of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDurationIndex(Index durationIndex) {
        descriptor.setDurationIndex(durationIndex);
        return this;
    }

    /**
     * Sets the {@code GradeProgress} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGradeProgress(String gradeProgress) {
        descriptor.setGradeProgress(new GradeProgress(gradeProgress));
        return this;
    }

    /**
     * Sets the {@code Index} of the grade of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGradeProgressIndex(Index gradeProgressIndex) {
        descriptor.setGradeProgressIndex(gradeProgressIndex);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
