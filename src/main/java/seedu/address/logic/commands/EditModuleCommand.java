package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.COMMAND_IDENTIFIER_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE_ZOOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_ZOOM;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.nusmodules.NusModulesParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.assignmentdetails.AssignmentDetails;
import seedu.address.model.module.LectureDetails;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.TutorialDetails;
import seedu.address.model.module.ZoomLink;


/**
 * Edits the details of an existing module in the module list.
 */
public class EditModuleCommand extends Command {

    public static final String COMMAND_TYPE = "edit";
    public static final String COMMAND_WORD = COMMAND_TYPE + COMMAND_IDENTIFIER_MODULE;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the module identified "
        + "by the index number used in the displayed module list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_MODULE + "MODULE CODED] "
        + "[" + PREFIX_LECTURE + "LECTURE] "
        + "[" + PREFIX_TUTORIAL + "TUTORIAL] "
        + "[" + PREFIX_LECTURE_ZOOM + "LECTURE ZOOM] "
        + "[" + PREFIX_TUTORIAL_ZOOM + "TUTORIAL ZOOM] "
        + "[" + PREFIX_ASSIGNMENT + "ASSIGNMENT]...\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_LECTURE + "Monday 9am "
        + PREFIX_TUTORIAL + "Thursday 10am";

    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "Edited Module: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the module list.";

    private final Index index;
    private final EditModuleDescriptor editModuleDescriptor;

    /**
     * @param index of the module in the filtered module list to edit
     * @param editModuleDescriptor details to edit the module with
     */
    public EditModuleCommand(Index index, EditModuleDescriptor editModuleDescriptor) {
        requireNonNull(index);
        requireNonNull(editModuleDescriptor);

        this.index = index;
        this.editModuleDescriptor = new EditModuleDescriptor(editModuleDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToEdit = lastShownList.get(index.getZeroBased());
        Module editedModule = createEditedModule(moduleToEdit, editModuleDescriptor);

        if (!moduleToEdit.isSameModule(editedModule) && model.hasModule(editedModule)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.setModule(moduleToEdit, editedModule);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, editedModule));
    }

    /**
     * Creates and returns a {@code Module} with the details of {@code moduleToEdit}
     * edited with {@code editModuleDescriptor}.
     */
    private static Module createEditedModule(Module moduleToEdit, EditModuleDescriptor editModuleDescriptor) {
        assert moduleToEdit != null;

        ModuleCode updatedModuleCode = editModuleDescriptor.getModuleCode().orElse(moduleToEdit.getModuleCode());
        LectureDetails updatedLecture = editModuleDescriptor.getLecture().orElse(moduleToEdit.getLectureDetails());
        TutorialDetails updatedTutorial = editModuleDescriptor.getTutorial().orElse(moduleToEdit.getTutorialDetails());
        ZoomLink updatedLectureZoomLink = editModuleDescriptor
            .getLectureZoomLink().orElse(moduleToEdit.getLectureZoomLink());
        ZoomLink updatedTutorialZoomLink = editModuleDescriptor.getTutorialZoomLink()
            .orElse(moduleToEdit.getTutorialZoomLink());
        Set<AssignmentDetails> updatedAssignment =
            editModuleDescriptor.getAssignments().orElse(moduleToEdit.getAssignmentDetails());
        return new Module(updatedModuleCode, updatedLecture, updatedTutorial,
            updatedLectureZoomLink, updatedTutorialZoomLink, updatedAssignment);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditModuleCommand)) {
            return false;
        }

        // state check
        EditModuleCommand e = (EditModuleCommand) other;
        return index.equals(e.index)
            && editModuleDescriptor.equals(e.editModuleDescriptor);
    }

    /**
     * Stores the details to edit the module with. Each non-empty field value will replace the
     * corresponding field value of the module.
     */
    public static class EditModuleDescriptor {
        private ModuleCode moduleCode;
        private LectureDetails lecture;
        private TutorialDetails tutorial;
        private ZoomLink lectureZoomLink;
        private ZoomLink tutorialZoomLink;
        private Set<AssignmentDetails> assignments;

        public EditModuleDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditModuleDescriptor(EditModuleDescriptor toCopy) {
            setModuleCode(toCopy.moduleCode);
            setLecture(toCopy.lecture);
            setTutorial(toCopy.tutorial);
            setLectureZoomLink(toCopy.lectureZoomLink);
            setTutorialZoomLink(toCopy.tutorialZoomLink);
            setAssignments(toCopy.assignments);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(moduleCode, lecture,
                tutorial, lectureZoomLink, tutorialZoomLink, assignments);
        }

        public void setModuleCode(ModuleCode moduleCode) {
            this.moduleCode = moduleCode;
        }

        public void setModuleTitle(NusModulesParser nusModulesParser) throws ParseException {
            moduleCode.setModuleTitle(nusModulesParser.getModuleTitle(moduleCode.moduleCode));
        }

        public Optional<ModuleCode> getModuleCode() {
            return Optional.ofNullable(moduleCode);
        }

        public void setLecture(LectureDetails lecture) {
            this.lecture = lecture;
        }

        public Optional<LectureDetails> getLecture() {
            return Optional.ofNullable(lecture);
        }

        public void setTutorial(TutorialDetails tutorial) {
            this.tutorial = tutorial;
        }

        public Optional<TutorialDetails> getTutorial() {
            return Optional.ofNullable(tutorial);
        }

        public void setLectureZoomLink(ZoomLink lectureZoomLink) {
            this.lectureZoomLink = lectureZoomLink;
        }

        public Optional<ZoomLink> getLectureZoomLink() {
            return Optional.ofNullable(lectureZoomLink);
        }

        public void setTutorialZoomLink(ZoomLink tutorialZoomLink) {
            this.tutorialZoomLink = tutorialZoomLink;
        }

        public Optional<ZoomLink> getTutorialZoomLink() {
            return Optional.ofNullable(tutorialZoomLink);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setAssignments(Set<AssignmentDetails> assignments) {
            this.assignments = (assignments != null) ? new HashSet<>(assignments) : null;
        }

        /**
         * Returns an unmodifiable assignment set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code assignment} is null.
         */
        public Optional<Set<AssignmentDetails>> getAssignments() {
            return (assignments != null) ? Optional.of(Collections.unmodifiableSet(assignments)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditModuleDescriptor)) {
                return false;
            }

            // state check
            EditModuleDescriptor e = (EditModuleDescriptor) other;

            return getModuleCode().equals(e.getModuleCode())
                && getLecture().equals(e.getLecture())
                && getTutorial().equals(e.getTutorial())
                && getLectureZoomLink().equals(e.getLectureZoomLink())
                && getTutorialZoomLink().equals(e.getTutorialZoomLink())
                && getAssignments().equals(e.getAssignments());
        }
    }
}
