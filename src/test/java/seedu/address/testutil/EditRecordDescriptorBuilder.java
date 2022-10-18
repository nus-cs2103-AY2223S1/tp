package seedu.address.testutil;

import static seedu.address.model.record.Record.DATE_FORMAT;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditRecordCommand.EditRecordDescriptor;
import seedu.address.model.record.Medication;
import seedu.address.model.record.Record;

/**
 * A utility class to help with building EditRecordDescriptor objects.
 */
public class EditRecordDescriptorBuilder {

    private EditRecordDescriptor descriptor;

    public EditRecordDescriptorBuilder() {
        descriptor = new EditRecordDescriptor();
    }

    public EditRecordDescriptorBuilder(EditRecordDescriptor descriptor) {
        this.descriptor = new EditRecordDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditRecordDescriptor} with fields containing {@code record}'s details
     */
    public EditRecordDescriptorBuilder(Record record) {
        descriptor = new EditRecordDescriptor();
        descriptor.setRecordDate(record.getRecordDate());
        descriptor.setRecord(record.getRecordData());
        descriptor.setMedications(record.getMedications());
    }

    /**
     * Sets the {@code Record} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withData(String data) {
        descriptor.setRecord(data);
        return this;
    }

    /**
     * Sets the {@code Record Date} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withDate(String recordDate) {
        descriptor.setRecordDate(LocalDateTime.parse(recordDate, DATE_FORMAT));
        return this;
    }

    /**
     * Parses the {@code medications} into a {@code Set<Medication>} and set it to the {@code EditRecordDescriptor}
     * that we are building.
     */
    public EditRecordDescriptorBuilder withMedications(String... medications) {
        Set<Medication> medicationSet = Stream.of(medications).map(Medication::of).collect(Collectors.toSet());
        descriptor.setMedications(medicationSet);
        return this;
    }

    public EditRecordDescriptor build() {
        return descriptor;
    }
}
