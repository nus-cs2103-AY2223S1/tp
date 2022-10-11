package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Set;

import seedu.address.logic.commands.profile.AddProfileCommand;
import seedu.address.logic.commands.profile.EditProfileCommand.EditProfileDescriptor;
import seedu.address.model.profile.Profile;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Profile.
 */
public class ProfileUtil {

    /**
     * Returns an add command string for adding the {@code profile}.
     */
    public static String getAddProfileCommand(Profile profile) {
        return AddProfileCommand.COMMAND_WORD + " " + PREFIX_OPTION + AddProfileCommand.COMMAND_OPTION
                + " " + getProfileDetails(profile);
    }

    /**
     * Returns the part of command string for the given {@code profile}'s details.
     */
    public static String getProfileDetails(Profile profile) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + profile.getName().fullName + " ");
        sb.append(PREFIX_PHONE + profile.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + profile.getEmail().value + " ");
        sb.append(PREFIX_TELEGRAM + profile.getTelegram().value + " ");
        profile.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditProfileDescriptor}'s details.
     */
    public static String getEditProfileDescriptorDetails(EditProfileDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
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
