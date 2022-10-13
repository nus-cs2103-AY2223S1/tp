package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditTuitionClassDescriptor;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.level.Level;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.tuitionclass.Day;
import seedu.address.model.tuitionclass.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.student.NextOfKin;
import seedu.address.model.person.student.School;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Institution;
import seedu.address.model.person.tutor.Qualification;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuitionclass.Subject;
import seedu.address.model.tuitionclass.Time;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * A utility class to help with building EditTuitionClassDescriptor objects.
 */
public class EditTuitionClassDescriptorBuilder {

    private EditTuitionClassDescriptor descriptor;

    public EditTuitionClassDescriptorBuilder() {
        descriptor = new EditTuitionClassDescriptor();
    }

    public EditTuitionClassDescriptorBuilder(EditTuitionClassDescriptor descriptor) {
        this.descriptor = new EditTuitionClassDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTuitionClassDescriptor} with fields containing {@code tuitionClass}'s details
     */
    public EditTuitionClassDescriptorBuilder(TuitionClass tuitionClass) {
        descriptor = new EditTuitionClassDescriptor();
        descriptor.setName(tuitionClass.getName());
        descriptor.setSubject(tuitionClass.getSubject());
        descriptor.setLevel(tuitionClass.getLevel());
        descriptor.setDay(tuitionClass.getDay());
        descriptor.setTime(tuitionClass.getTime());
        descriptor.setTags(tuitionClass.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditTuitionClassDescriptor} that we are building.
     */
    public EditTuitionClassDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    public EditTuitionClassDescriptorBuilder withSubject(String subject) {
        descriptor.setSubject(Subject.createSubject(subject));
        return this;
    }

    public EditTuitionClassDescriptorBuilder withLevel(String level) {
        descriptor.setLevel(Level.createLevel(level));
        return this;
    }

    public EditTuitionClassDescriptorBuilder withDay(String day) {
        descriptor.setDay(Day.createDay(day));
        return this;
    }

    public EditTuitionClassDescriptorBuilder withTime(String startTime, String endTime) {
        try {
            descriptor.setTime(new Time(startTime, endTime));
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        return this;
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTuitionClassDescriptor}
     * that we are building.
     */
    public EditTuitionClassDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditTuitionClassDescriptor build() {
        return descriptor;
    }
}