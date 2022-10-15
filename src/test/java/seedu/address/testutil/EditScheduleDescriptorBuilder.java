package seedu.address.testutil;

import seedu.address.logic.commands.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.model.module.schedule.ClassType;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.module.schedule.Venue;
import seedu.address.model.module.schedule.Weekdays;

/**
 * A utility class to help with building EditScheduleDescriptor objects.
 */
public class EditScheduleDescriptorBuilder {
    private EditScheduleDescriptor editPersonDescriptor;

    public EditScheduleDescriptorBuilder() {
        this.editPersonDescriptor = new EditScheduleDescriptor();
    }

    public EditScheduleDescriptorBuilder(EditScheduleDescriptor editPersonDescriptor) {
        this.editPersonDescriptor = new EditScheduleDescriptor(editPersonDescriptor);
    }
    /**
     * Returns an {@code EditScheduleDescriptor} with fields containing {@code schedule}'s details
     */
    public EditScheduleDescriptorBuilder(Schedule schedule) {
        EditScheduleDescriptor editScheduleDescriptor = new EditScheduleDescriptor();
        editScheduleDescriptor.setModule(schedule.getModule());
        editScheduleDescriptor.setWeekday(schedule.getWeekday());
        editScheduleDescriptor.setStartTime(schedule.getStartTime());
        editScheduleDescriptor.setEndTime(schedule.getEndTime());
        editScheduleDescriptor.setVenue(schedule.getVenue());
        editScheduleDescriptor.setClassType(schedule.getClassType());
    }
    /**
     * Sets the module of the {@code EditScheduleDescriptor} that we are building.
     */
    public EditScheduleDescriptorBuilder withModule(String module) {
        editPersonDescriptor.setModule(module);
        return this;
    }
    /**
     * Sets the {@code Venue} of the {@code EditScheduleDescriptor} that we are building.
     */
    public EditScheduleDescriptorBuilder withVenue(Venue venue) {
        editPersonDescriptor.setVenue(venue);
        return this;
    }
    /**
     * Sets the {@code Weekdays} of the {@code EditScheduleDescriptor} that we are building.
     */
    public EditScheduleDescriptorBuilder withWeekday(Weekdays weekday) {
        editPersonDescriptor.setWeekday(weekday);
        return this;
    }
    /**
     * Sets the start time of the {@code EditScheduleDescriptor} that we are building.
     */
    public EditScheduleDescriptorBuilder withStartTime(String startTime) {
        editPersonDescriptor.setStartTime(startTime);
        return this;
    }
    /**
     * Sets the end time of the {@code EditScheduleDescriptor} that we are building.
     */
    public EditScheduleDescriptorBuilder withEndTime(String endTime) {
        editPersonDescriptor.setEndTime(endTime);
        return this;
    }
    /**
     * Sets the {@code ClassType} of the {@code EditScheduleDescriptor} that we are building.
     */
    public EditScheduleDescriptorBuilder withClassType(ClassType classType) {
        editPersonDescriptor.setClassType(classType);
        return this;
    }

    public EditScheduleDescriptor build() {
        return editPersonDescriptor;
    }

}
