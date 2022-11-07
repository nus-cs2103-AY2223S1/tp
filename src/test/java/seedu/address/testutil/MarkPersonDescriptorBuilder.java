package seedu.address.testutil;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkPersonDescriptor;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

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
     * Sets the {@code Index} of the homework of the {@code MarkPersonDescriptor} that we are building.
     */
    public MarkPersonDescriptorBuilder withHomeworkIndex(Index homeworkIndex) {
        descriptor.setHomeworkIndex(homeworkIndex);
        return this;
    }


    /**
     * Sets the {@code Index} of the attendance of the {@code MarkPersonDescriptor} that we are building.
     */
    public MarkPersonDescriptorBuilder withAttendanceIndex(Index attendanceIndex) {
        descriptor.setAttendanceIndex(attendanceIndex);
        return this;
    }



    public MarkPersonDescriptor build() {
        return descriptor;
    }
}
