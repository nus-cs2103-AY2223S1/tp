package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditStuCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleDescription;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing module in profNus.
 */
public class EditModuleCommand extends Command {

    public static final String COMMAND_WORD = "medit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the module identified "
            + "by the module code. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "MODULE NAME] "
            + "[" + PREFIX_MODULE_CODE + "MODULE CODE] "
            + "[" + PREFIX_MODULE_DESCRIPTION + "MODULE DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + "cs2103t "
            + PREFIX_NAME + "SWE "
            + PREFIX_MODULE_DESCRIPTION + "Practical exam for this is the following week";

    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "Edited module: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the ProfNUS.";

    private final ModuleCode moduleCode;
    private final seedu.address.logic.commands.module.EditModuleCommand.EditModuleDescriptor editModuleDescriptor;

    /**
     * @param moduleCode           of the module in the filtered module list to edit
     * @param editModuleDescriptor details to edit the module with
     */
    public EditModuleCommand(ModuleCode moduleCode,
                             seedu.address.logic.commands.module.EditModuleCommand
                                     .EditModuleDescriptor editModuleDescriptor) {
        requireNonNull(moduleCode);
        requireNonNull(editModuleDescriptor);

        this.moduleCode = moduleCode;
        this.editModuleDescriptor =
                new seedu.address.logic.commands.module.EditModuleCommand.EditModuleDescriptor(editModuleDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getAllModuleList();
        boolean isInModuleList = false;
        int positionInList = 0;
        String targetCodeToUpperCase = moduleCode.toString().toUpperCase();
        for (Module m : lastShownList) {
            String moduleCodeToUpperCase = m.getCode().toString().toUpperCase();
            if (moduleCodeToUpperCase.equals(targetCodeToUpperCase)) {
                isInModuleList = true;
                positionInList = lastShownList.indexOf(m);
                break;
            }
        }

        if (!isInModuleList) {
            throw new CommandException(Messages.MESSAGE_MODULE_DOES_NOT_EXIST);
        }

        Index targetIndex = Index.fromZeroBased(positionInList);
        Module moduleToEdit = lastShownList.get(targetIndex.getZeroBased());
        Module editedModule = createEditedModule(moduleToEdit, editModuleDescriptor);

        if (!moduleToEdit.isSameModule(editedModule) && model.hasModule(editedModule)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.setModule(moduleToEdit, editedModule);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);

        //update students and teaching assistants
        if (!editedModule.getCode().equals(this.moduleCode)) {
            changeStudentDetails(this.moduleCode, editedModule.getCode(), model);
        }

        return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, editedModule));
    }

    /**
     * Creates and returns a {@code Module} with the details of {@code moduleToEdit}
     * edited with {@code editModuleDescriptor}.
     */
    private static Module createEditedModule(Module moduleToEdit,
                                             EditModuleCommand
                                                     .EditModuleDescriptor editModuleDescriptor) {
        assert moduleToEdit != null;

        ModuleName updatedName = editModuleDescriptor.getName().orElse(moduleToEdit.getName());
        ModuleCode updatedCode = editModuleDescriptor.getCode().orElse(moduleToEdit.getCode());
        ModuleDescription updatedDescription = editModuleDescriptor
                .getDescription().orElse(moduleToEdit.getDescription());
        Set<Tag> updatedTags = editModuleDescriptor.getTags().orElse(moduleToEdit.getTags());

        return new Module(updatedName, updatedCode, updatedDescription, updatedTags);
    }

    /**
     * Updates the student details to reflect the new Module Code.
     *
     * @param oldModuleCode The old module code.
     * @param updatedModuleCode The new module code.
     * @param model The model.
     */
    private void changeStudentDetails(ModuleCode oldModuleCode, ModuleCode updatedModuleCode, Model model) {
        List<Person> personList = model.getAllPersonList();
        for (Person person : personList) {
            if (person instanceof Student) {
                Student temp = (Student) person;
                Set<ModuleCode> studentSet = temp.getStudentModuleInfo();
                Set<ModuleCode> teachingSet = temp.getTeachingAssistantInfo();
                EditStuCommand.EditStudentDescriptor editStudentDescriptor =
                        new EditStuCommand.EditStudentDescriptor();
                if (studentSet.contains(oldModuleCode)) {
                    Set<ModuleCode> editedSet = new HashSet<>();
                    editedSet.addAll(studentSet);
                    editedSet.remove(oldModuleCode);
                    editedSet.add(updatedModuleCode);
                    editStudentDescriptor.setStudentModuleInfo(editedSet);
                }
                if (teachingSet.contains(oldModuleCode)) {
                    Set<ModuleCode> editedSet = new HashSet<>();
                    editedSet.addAll(studentSet);
                    editedSet.remove(oldModuleCode);
                    editedSet.add(updatedModuleCode);
                    editStudentDescriptor.setTeachingAssistantInfo(editedSet);
                }

                Student editedStudent = EditStuCommand.createEditedStudent(temp, editStudentDescriptor);
                model.setPerson(person, editedStudent);
                if (editedStudent.isTeachingAssistant()) {
                    if (temp.isTeachingAssistant()) {
                        model.setTutor(temp, editedStudent);
                    } else {
                        model.addTutor(editedStudent);
                    }
                } else {
                    if (temp.isTeachingAssistant()) {
                        model.deleteTutor(temp);
                    }
                }
            }
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.logic.commands.module.EditModuleCommand)) {
            return false;
        }

        // state check
        seedu.address.logic.commands.module.EditModuleCommand e =
                (seedu.address.logic.commands.module.EditModuleCommand) other;
        return moduleCode.equals(e.moduleCode)
                && editModuleDescriptor.equals(e.editModuleDescriptor);
    }

    /**
     * Stores the details to edit the module with. Each non-empty field value will replace the
     * corresponding field value of the module.
     */
    public static class EditModuleDescriptor {
        private ModuleName moduleName;
        private ModuleCode moduleCode;
        private ModuleDescription moduleDescription;
        private Set<Tag> tags;
        private ArrayList<Student> students;

        public EditModuleDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditModuleDescriptor(seedu.address.logic.commands.module
                                            .EditModuleCommand.EditModuleDescriptor toCopy) {
            setName(toCopy.moduleName);
            setModuleCode(toCopy.moduleCode);
            setModuleDescription(toCopy.moduleDescription);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(moduleName, moduleCode, moduleDescription, tags, students);
        }

        public void setName(ModuleName name) {
            this.moduleName = name;
        }

        public Optional<ModuleName> getName() {
            return Optional.ofNullable(moduleName);
        }

        public void setModuleCode(ModuleCode moduleCode) {
            this.moduleCode = moduleCode;
        }

        public Optional<ModuleCode> getCode() {
            return Optional.ofNullable(moduleCode);
        }

        public void setModuleDescription(ModuleDescription moduleDescription) {
            this.moduleDescription = moduleDescription;
        }

        public Optional<ModuleDescription> getDescription() {
            return Optional.ofNullable(moduleDescription);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof seedu.address.logic.commands
                    .module.EditModuleCommand.EditModuleDescriptor)) {
                return false;
            }

            // state check
            seedu.address.logic.commands.module.EditModuleCommand.EditModuleDescriptor e =
                    (seedu.address.logic.commands.module.EditModuleCommand.EditModuleDescriptor) other;

            return getName().equals(e.getName())
                    && getCode().equals(e.getCode())
                    && getDescription().equals(e.getDescription())
                    && getTags().equals(e.getTags());
        }
    }
}
