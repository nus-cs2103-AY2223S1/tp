package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditExamCommand;
import seedu.address.model.exam.Exam;
import seedu.address.model.exam.ExamDate;
import seedu.address.model.exam.ExamDescription;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditExamDescriptorBuilder {

    private EditExamCommand.EditExamDescriptor descriptor;

    public EditExamDescriptorBuilder() {
        descriptor = new EditExamCommand.EditExamDescriptor();
    }

    public EditExamDescriptorBuilder(EditExamCommand.EditExamDescriptor descriptor) {
        this.descriptor = new EditExamCommand.EditExamDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditExamDescriptorBuilder(Exam exam) {
        descriptor = new EditExamCommand.EditExamDescriptor();
        descriptor.setModule(exam.getModule());
        descriptor.setExamDate(exam.getExamDate());
        descriptor.setDescription(exam.getDescription());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditExamDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new ExamDescription(description));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditExamDescriptorBuilder withModule(String module) {
        descriptor.setModule(new Module(new ModuleCode(module)));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditExamDescriptorBuilder withDate(String examDate) {
        descriptor.setExamDate(new ExamDate(examDate));
        return this;
    }

    public EditExamCommand.EditExamDescriptor build() {
        return descriptor;
    }
}