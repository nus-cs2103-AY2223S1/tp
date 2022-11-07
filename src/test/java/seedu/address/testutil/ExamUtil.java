package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.AddExamCommand;
import seedu.address.logic.commands.EditExamCommand;
import seedu.address.model.exam.Exam;




/**
 * A utility class for Exam.
 */
public class ExamUtil {

    /**
     * Returns an add exam command string for adding the {@code exam}.
     */
    public static String getAddExamCommand(Exam exam) {
        return "e " + AddExamCommand.COMMAND_WORD + " " + getExamDetails(exam);
    }

    /**
     * Returns the part of command string for the given {@code exam}'s details.
     */
    public static String getExamDetails(Exam exam) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_MODULE + exam.getModule().getModuleCode().moduleCode + " ");
        sb.append(PREFIX_EXAM_DESCRIPTION + exam.getDescription().description + " ");
        sb.append(PREFIX_EXAM_DATE + exam.getExamDate().examDate + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditExamDescriptor}'s details.
     */
    public static String getEditExamDescriptorDetails(EditExamCommand.EditExamDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getDescription().ifPresent(description
                -> sb.append(PREFIX_EXAM_DESCRIPTION).append(description).append(" "));
        descriptor.getModule().ifPresent(module
                -> sb.append(PREFIX_MODULE).append(module).append(" "));
        descriptor.getExamDate().ifPresent(examDate
                -> sb.append(PREFIX_EXAM_DATE).append(examDate).append(" "));
        return sb.toString();
    }
}
