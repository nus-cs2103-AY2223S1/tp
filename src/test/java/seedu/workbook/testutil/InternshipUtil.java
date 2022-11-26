package seedu.workbook.testutil;


import static seedu.workbook.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_LANGUAGE_TAG;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_STAGE;
import static seedu.workbook.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.workbook.logic.commands.AddCommand;
import seedu.workbook.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.model.tag.Tag;

/**
 * A utility class for Internship.
 */
public class InternshipUtil {

    /**
     * Returns an add command string for adding the {@code internship}.
     */
    public static String getAddCommand(Internship internship) {
        return AddCommand.COMMAND_WORD + " " + getInternshipDetails(internship);
    }

    /**
     * Returns the part of command string for the given {@code internship}'s details.
     */
    public static String getInternshipDetails(Internship internship) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_COMPANY + internship.getCompany().name + " ");
        sb.append(PREFIX_ROLE + internship.getRole().value + " ");
        sb.append(PREFIX_EMAIL + internship.getEmail().value + " ");
        sb.append(PREFIX_STAGE + internship.getStage().value + " ");
        sb.append(PREFIX_DATETIME + internship.getDateTime().value + " ");
        internship.getLanguageTags().stream().forEach(
            s -> sb.append(PREFIX_LANGUAGE_TAG + s.tagName + " ")
        );
        internship.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditInternshipDescriptor}'s details.
     */
    public static String getEditInternshipDescriptorDetails(EditInternshipDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getCompany().ifPresent(company -> sb.append(PREFIX_COMPANY).append(company.name).append(" "));
        descriptor.getRole().ifPresent(role -> sb.append(PREFIX_ROLE).append(role.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getStage().ifPresent(stage -> sb.append(PREFIX_STAGE).append(stage.value).append(" "));
        descriptor.getDate().ifPresent(dateTime -> sb.append(PREFIX_DATETIME).append(dateTime.value).append(" "));
        if (descriptor.getLanguageTags().isPresent()) {
            Set<Tag> languageTags = descriptor.getLanguageTags().get();
            if (languageTags.isEmpty()) {
                sb.append(PREFIX_LANGUAGE_TAG).append(" ");
            } else {
                languageTags.forEach(s -> sb.append(PREFIX_LANGUAGE_TAG).append(s.tagName).append(" "));
            }
        }
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
