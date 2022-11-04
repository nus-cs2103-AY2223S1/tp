package seedu.address.logic.commands.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_OF_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEKDAY;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.schedule.ClassType;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.module.schedule.Venue;
import seedu.address.model.module.schedule.Weekdays;

/**
 * Edit an existing schedule
 */
public class EditScheduleCommand extends Command {

    public static final String COMMAND_WORD = "sedit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an existing schedule. "
            + "Parameters: INDEX (must be a positive integer)"
            + "[" + PREFIX_MODULE_OF_SCHEDULE + "MODULE CODE]"
            + "[" + PREFIX_WEEKDAY + "WEEKDAY]"
            + "[" + PREFIX_CLASS_TIME + "TIME PERIOD]"
            + "[" + PREFIX_CLASS_CATEGORY + "CLASS TYPE]"
            + "[" + PREFIX_CLASS_VENUE + "CLASS VENUE] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_WEEKDAY + " Wednesday "
            + PREFIX_CLASS_TIME + " 08:00-10:00 "
            + PREFIX_CLASS_VENUE + " LT31 \n"
            + "Reminder: the INDEX is the latest schedule list. \n"
            + "You can type in \"view\" command or click Schedules button to view schedule list.";

    public static final String MESSAGE_SUCCESS = "Schedule edited: %1$s";
    public static final String MESSAGE_SCHEDULE_NOT_EXIST = "The schedule doesn't exist";
    public static final String MESSAGE_MODULE_NOT_EXIST = "The module doesn't exist";
    public static final String MESSAGE_CONFLICT_SCHEDULE = "This schedule conflicts with other schedules";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private static final Logger logger = LogsCenter.getLogger(EditScheduleCommand.class);
    private final EditScheduleDescriptor editScheduleDescriptor;
    private final Index index;

    /**
     * Creates an EditScheduleCommand to edit the specified schedule.
     */
    public EditScheduleCommand(Index index, EditScheduleDescriptor editScheduleDescriptor) {
        requireNonNull(index);
        requireNonNull(editScheduleDescriptor);
        this.index = index;
        this.editScheduleDescriptor = editScheduleDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Schedule> lastShownList = model.getFilteredScheduleList();
        if (index.getZeroBased() >= lastShownList.size()) {
            logger.warning("Index is invalid.");
            throw new CommandException(MESSAGE_SCHEDULE_NOT_EXIST);
        }
        Schedule target = lastShownList.get(index.getZeroBased());
        logger.info("Obtained the target schedule: " + target.toString());
        Schedule editedSchedule = EditScheduleCommand.createEditedSchedule(target, editScheduleDescriptor);
        if (model.getModuleByModuleCode(editedSchedule.getModule()) == null) {
            logger.warning("Cannot find the target module.");
            throw new CommandException(MESSAGE_MODULE_NOT_EXIST);
        }
        if (model.conflictScheduleWithTarget(editedSchedule, target)) {
            logger.warning("Edited schedule conflicts with other schedules.");
            throw new CommandException(MESSAGE_CONFLICT_SCHEDULE);
        }
        model.setSchedule(target, editedSchedule);
        logger.fine("EditScheduleCommand executes successfully.");
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedSchedule), false, false, false,
                false, false, false, true, false, false);

    }
    /**
     * Creates and returns a {@code Schedule} with the details of {@code scheduleToEdit}
     * edited with {@code editScheduleDescriptor}.
     */
    public static Schedule createEditedSchedule(Schedule scheduleToEdit, EditScheduleDescriptor scheduleDescriptor) {
        assert scheduleToEdit != null;
        String module = scheduleDescriptor.getModule().orElse(scheduleToEdit.getModule());
        Venue venue = scheduleDescriptor.getVenue().orElse(scheduleToEdit.getVenue());
        Weekdays weekday = scheduleDescriptor.getWeekday().orElse(scheduleToEdit.getWeekday());
        String startTime = scheduleDescriptor.getStartTime().orElse(scheduleToEdit.getStartTime());
        String endTime = scheduleDescriptor.getEndTime().orElse(scheduleToEdit.getEndTime());
        ClassType classType = scheduleDescriptor.getClassType().orElse(scheduleToEdit.getClassType());
        String classGroup = scheduleDescriptor.getClassGroup().orElse(scheduleToEdit.getClassGroup());

        return new Schedule(module, venue, weekday, startTime, endTime, classType, classGroup);
    }

    /**
     * Stores the details to edit the schedule with. Each non-empty field value will replace the
     * corresponding field value of the schedule.
     */
    public static class EditScheduleDescriptor {
        private String module;
        private Venue venue;
        private Weekdays weekday;
        private String startTime;
        private String endTime;
        private ClassType classType;
        private String classGroup;

        public EditScheduleDescriptor() {}
        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditScheduleDescriptor(EditScheduleDescriptor toCopy) {
            setModule(toCopy.module);
            setClassType(toCopy.classType);
            setWeekday(toCopy.weekday);
            setStartTime(toCopy.startTime);
            setEndTime(toCopy.endTime);
            setVenue(toCopy.venue);
            setClassGroup(toCopy.classGroup);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(module, venue, classType, startTime, endTime, weekday);
        }
        public void setVenue(Venue venue) {
            this.venue = venue;
        }

        public void setWeekday(Weekdays weekday) {
            this.weekday = weekday;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public void setClassType(ClassType classType) {
            this.classType = classType;
        }

        public void setModule(String module) {
            this.module = module;
        }
        public void setClassGroup(String classGroup) {
            this.classGroup = classGroup;
        }

        public Optional<String> getModule() {
            return Optional.ofNullable(module);
        }

        public Optional<Venue> getVenue() {
            return Optional.ofNullable(venue);
        }

        public Optional<Weekdays> getWeekday() {
            return Optional.ofNullable(weekday);
        }

        public Optional<String> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public Optional<String> getEndTime() {
            return Optional.ofNullable(endTime);
        }

        public Optional<ClassType> getClassType() {
            return Optional.ofNullable(classType);
        }
        public Optional<String> getClassGroup() {
            return Optional.ofNullable(classGroup);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            EditScheduleDescriptor that = (EditScheduleDescriptor) o;
            return Objects.equals(module, that.module)
                    && Objects.equals(venue, that.venue)
                    && weekday == that.weekday
                    && Objects.equals(startTime, that.startTime)
                    && Objects.equals(endTime, that.endTime)
                    && classType == that.classType
                    && classGroup.equals(that.classGroup);
        }

        @Override
        public int hashCode() {
            return Objects.hash(module, venue, weekday, startTime, endTime, classType, classGroup);
        }
    }
}
