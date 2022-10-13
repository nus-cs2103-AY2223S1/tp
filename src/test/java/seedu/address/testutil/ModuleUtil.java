package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM;

import java.util.Set;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;



/**
 * A utility class for Module.
 */
public class ModuleUtil {

    /**
     * Returns an add command string for adding the {@code module}.
     */
    public static String getAddModuleCommand(Module module) {
        return AddModuleCommand.COMMAND_WORD + " " + getModuleDetails(module);
    }

    /**
     * Returns the part of command string for the given {@code module}'s details.
     */
    public static String getModuleDetails(Module module) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_MODULE + module.getModuleCode().moduleCode + " ");
        sb.append(PREFIX_LECTURE + module.getLectureDetails().value + " ");
        sb.append(PREFIX_TUTORIAL + module.getTutorialDetails().value + " ");
        sb.append(PREFIX_ZOOM + module.getZoomLink().zoomLink + " ");
        module.getAssignmentDetails().stream().forEach(
            s -> sb.append(PREFIX_ADDRESS + s.assignmentDetails + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditModuleDescriptor}'s details.
     */
    //TODO update once updated edit to support module
    public static String getEditModuleDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
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
