package tuthub.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tuthub.logic.commands.EditCommand.EditTutorDescriptor;
import tuthub.model.tag.Tag;
import tuthub.model.tutor.CommentList;
import tuthub.model.tutor.Email;
import tuthub.model.tutor.Module;
import tuthub.model.tutor.Name;
import tuthub.model.tutor.Phone;
import tuthub.model.tutor.Rating;
import tuthub.model.tutor.StudentId;
import tuthub.model.tutor.TeachingNomination;
import tuthub.model.tutor.Tutor;
import tuthub.model.tutor.Year;

/**
 * A utility class to help with building EditTutorDescriptor objects.
 */
public class EditTutorDescriptorBuilder {

    private EditTutorDescriptor descriptor;

    public EditTutorDescriptorBuilder() {
        descriptor = new EditTutorDescriptor();
    }

    public EditTutorDescriptorBuilder(EditTutorDescriptor descriptor) {
        this.descriptor = new EditTutorDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTutorDescriptor} with fields containing {@code tutor}'s details
     */
    public EditTutorDescriptorBuilder(Tutor tutor) {
        descriptor = new EditTutorDescriptor();
        descriptor.setName(tutor.getName());
        descriptor.setPhone(tutor.getPhone());
        descriptor.setEmail(tutor.getEmail());
        descriptor.setModules(tutor.getModules());
        descriptor.setYear(tutor.getYear());
        descriptor.setStudentId(tutor.getStudentId());
        descriptor.setTeachingNomination(tutor.getTeachingNomination());
        descriptor.setComments(new CommentList(tutor.getComments().getList()));
        descriptor.setRating(tutor.getRating());
        descriptor.setTags(tutor.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withModule(String... modules) {
        Set<Module> moduleSet = Stream.of(modules).map(Module::new).collect(Collectors.toSet());
        descriptor.setModules(moduleSet);
        return this;
    }

    /**
     * Sets the {@code Year} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withYear(String year) {
        descriptor.setYear(new Year(year));
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withStudentId(String studentId) {
        descriptor.setStudentId(new StudentId(studentId));
        return this;
    }

    /**
     * Sets the {@code TeachingNomination} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withTeachingNomination(String teachingNomination) {
        descriptor.setTeachingNomination(new TeachingNomination(teachingNomination));
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code EditTutorDescriptor} that we are building.
     */
    public EditTutorDescriptorBuilder withRating(String rating) {
        descriptor.setRating(new Rating(rating));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTutorDescriptor}
     * that we are building.
     */
    public EditTutorDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditTutorDescriptor build() {
        return descriptor;
    }
}
