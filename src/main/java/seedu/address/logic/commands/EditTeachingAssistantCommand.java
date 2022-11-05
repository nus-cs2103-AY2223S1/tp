package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_TA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Student;

/**
 * Edits the details of an existing student that is a TA in profNus.
 */
public class EditTeachingAssistantCommand extends EditStuCommand {

    public static final String COMMAND_WORD = "editta";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the ta identified "
            + "by the index number used in the displayed list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]"
            + PREFIX_ID + "ID "
            + PREFIX_HANDLE + "HANDLE "
            + "[" + PREFIX_MODULE_CODE + "MODULE]..."
            + "[" + PREFIX_STUDENT_TA + "TA]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com"
            + PREFIX_CLASS_GROUP + "CS2030S:Tut07";

    private final Index teachingIndex;
    private final EditStudentDescriptor editTeachingDescriptor;

    /**
     * @param index                 of the student in the filtered list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditTeachingAssistantCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        super(index, editStudentDescriptor);
        this.teachingIndex = index;
        this.editTeachingDescriptor = editStudentDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredTutorList();

        if (teachingIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(teachingIndex.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editTeachingDescriptor);

        //checks for same module in student and ta
        Set<ModuleCode> student = editedStudent.getStudentModuleInfo();
        Set<ModuleCode> teacher = editedStudent.getTeachingAssistantInfo();
        for (ModuleCode moduleCode : student) {
            for (ModuleCode otherModuleCode: teacher) {
                if (moduleCode.equals(otherModuleCode)) {
                    throw new CommandException(Messages.MESSAGE_STUDENT_AND_TA);
                }
            }
        }

        if (!studentToEdit.isSamePerson(editedStudent) && model.hasPerson(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        //checks if module exists
        if (editedStudent.getStudentModuleInfo().size() > 0 || editedStudent.isTeachingAssistant()) {
            List<Module> moduleList = model.getFilteredModuleList();
            List<ModuleCode> studentModules = new ArrayList<>();
            studentModules.addAll(editedStudent.getStudentModuleInfo());
            studentModules.addAll(editedStudent.getTeachingAssistantInfo());
            ModuleCode lastViewedCode = null;
            for (ModuleCode moduleCode : studentModules) {
                boolean isValid = false;
                for (Module module : moduleList) {
                    if (module.getCode().equals(moduleCode)) {
                        isValid = true;
                    } else {
                        lastViewedCode = moduleCode;
                    }
                }
                if (!isValid) {
                    throw new CommandException(Messages.MESSAGE_MODULE_DOES_NOT_EXIST
                            + " Module not found: " + lastViewedCode + ".\nPlease create the module first using "
                            + "the madd command or specify an existing module!");
                }
            }
        }

        model.setPerson(studentToEdit, editedStudent);
        if (editedStudent.isTeachingAssistant()) {
            if (studentToEdit.isTeachingAssistant()) {
                model.setTutor(studentToEdit, editedStudent);
            } else {
                model.addTutor(editedStudent);
            }
        } else {
            model.deleteTutor(studentToEdit);
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent),
                false, false, false,
                true, false, false, false, false, false);
    }
}
