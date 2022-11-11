package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_OR_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditTuitionClassDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * A utility class for Tuition Class.
 */
public class ClassUtil {

    /**
     * Returns an add command string for adding the {@code tuitionClass}.
     */
    public static String getAddCommand(TuitionClass tuitionClass) {
        return AddCommand.COMMAND_WORD + " " + getClassDetails(tuitionClass);
    }

    /**
     * Returns the part of command string for the given {@code tuitionClass}'s details.
     */
    public static String getClassDetails(TuitionClass tuitionClass) {
        StringBuilder sb = new StringBuilder();
        sb.append("class ");
        sb.append(PREFIX_NAME + tuitionClass.getName().name + " ");
        sb.append(PREFIX_SUBJECT_OR_SCHOOL + tuitionClass.getSubject().subject + " ");
        sb.append(PREFIX_LEVEL + tuitionClass.getLevel().level + " ");
        sb.append(PREFIX_DAY + tuitionClass.getDay().day + " ");
        sb.append(PREFIX_TIME + tuitionClass.getTime().timeFrame + " ");
        tuitionClass.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditTuitionClassDescriptorDetails(EditTuitionClassDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.name).append(" "));
        descriptor.getSubject().ifPresent(subject -> sb.append(PREFIX_SUBJECT_OR_SCHOOL).append(subject.subject)
                .append(" "));
        descriptor.getLevel().ifPresent(level -> sb.append(PREFIX_LEVEL).append(level.level).append(" "));
        descriptor.getDay().ifPresent(day -> sb.append(PREFIX_DAY).append(day.day).append(" "));
        descriptor.getTime().ifPresent(time -> sb.append(PREFIX_TIME).append(time.timeFrame).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }

        return sb.toString();
    }

}
