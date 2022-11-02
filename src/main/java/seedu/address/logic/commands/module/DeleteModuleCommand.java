package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditStuCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;

/**
 * Deletes a person identified using it's displayed index from profNus.
 */
public class DeleteModuleCommand extends Command {

    public static final String COMMAND_WORD = "mdel";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the module identified by the module name used in the list of modules.\n"
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE CODE "
            + "Example: " + COMMAND_WORD + " " + PREFIX_MODULE_CODE + "CS2103T ";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted module: %1$s";

    private final ModuleCode targetModuleCode;

    public DeleteModuleCommand(ModuleCode targetModuleCode) {
        this.targetModuleCode = targetModuleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> moduleList = model.getAllModuleList();
        Module moduleToDelete;
        int positionInList = 0;
        boolean isInModuleList = false;

        for (Module m : moduleList) {
            String targetCodeInUpperCase = targetModuleCode.toString().toUpperCase();
            if (m.getCode().toString().equals(targetCodeInUpperCase)) {
                isInModuleList = true;
                positionInList = moduleList.indexOf(m);
                break;
            }
        }

        if (!isInModuleList) {
            throw new CommandException(Messages.MESSAGE_MODULE_DOES_NOT_EXIST);
        }

        Index targetIndex = Index.fromZeroBased(positionInList);
        moduleToDelete = moduleList.get(targetIndex.getZeroBased());
        model.deleteModule(moduleToDelete);

        //update students
        updateStudent(moduleToDelete, model);
        updateSchedule(model);

        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete),
                false, false, true,
                false, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModuleCommand // instanceof handles nulls
                && targetModuleCode.equals(((DeleteModuleCommand) other).targetModuleCode)); // state check
    }

    /**
     * Updates the student and tutor list after a module is deleted.
     *
     * @param moduleToDelete The module to delete.
     * @param model The model.
     */
    private void updateStudent(Module moduleToDelete, Model model) {
        List<Person> personList = model.getAllPersonList();
        for (Person person : personList) {
            if (person instanceof Student) {
                Student temp = (Student) person;
                Set<ModuleCode> studentSet = temp.getStudentModuleInfo();
                Set<ModuleCode> teachingSet = temp.getTeachingAssistantInfo();
                EditStuCommand.EditStudentDescriptor editStudentDescriptor =
                        new EditStuCommand.EditStudentDescriptor();
                if (studentSet.contains(moduleToDelete.getCode())) {
                    Set<ModuleCode> editedSet = new HashSet<>();
                    editedSet.addAll(studentSet);
                    editedSet.remove(moduleToDelete.getCode());
                    editStudentDescriptor.setStudentModuleInfo(editedSet);
                }
                if (teachingSet.contains(moduleToDelete.getCode())) {
                    Set<ModuleCode> editedSet = new HashSet<>();
                    editedSet.addAll(studentSet);
                    editedSet.remove(moduleToDelete.getCode());
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

    private void updateSchedule(Model model) {
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
    }
}
