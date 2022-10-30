package hobbylist.testutil;

import java.util.Set;

import hobbylist.logic.commands.AddCommand;
import hobbylist.logic.commands.EditCommand;
import hobbylist.logic.parser.CliSyntax;
import hobbylist.model.activity.Activity;
import hobbylist.model.activity.Status;
import hobbylist.model.tag.Tag;

/**
 * A utility class for Activity.
 */
public class ActivityUtil {

    /**
     * Returns an add command string for adding the {@code activity}.
     */
    public static String getAddCommand(Activity activity) {
        return AddCommand.getCommandWord() + " " + getActivityDetails(activity);
    }

    /**
     * Returns the part of command string for the given {@code activity}'s details.
     */
    public static String getActivityDetails(Activity activity) {
        StringBuilder sb = new StringBuilder();
        sb.append(CliSyntax.PREFIX_NAME + activity.getName().fullName + " ");
        sb.append(CliSyntax.PREFIX_DESCRIPTION + activity.getDescription().value + " ");
        activity.getTags().stream().forEach(
            s -> sb.append(CliSyntax.PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditActivityDescriptor}'s details.
     */
    public static String getEditActivityDescriptorDetails(EditCommand.EditActivityDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(CliSyntax.PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getDescription().ifPresent(description -> sb.append(CliSyntax.PREFIX_DESCRIPTION)
                .append(description.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(CliSyntax.PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(CliSyntax.PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getDate().isPresent()) {
            sb.append(CliSyntax.PREFIX_DATE).append(descriptor.getDate().get()).append(" ");
        }
        descriptor.getStatus().ifPresent(status -> {
            if (status.status != Status.State.NONE) {
                sb.append(CliSyntax.PREFIX_STATUS).append(status).append(" ");
            }
        });
        return sb.toString();
    }
}
