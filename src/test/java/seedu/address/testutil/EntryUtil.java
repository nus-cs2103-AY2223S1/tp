package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_EXPENDITURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_INCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryType;

/**
 * A utility class for Entry.
 */
public class EntryUtil {

    /**
     * Returns an add command string for adding the {@code entry}.
     */
    public static String getAddCommand(EntryType type, Entry entry) {
        return AddCommand.COMMAND_WORD + " " + getEntryDetails(type, entry);
    }

    /**
     * Returns the part of command string for the given {@code entry}'s details.
     */
    public static String getEntryDetails(EntryType type, Entry entry) {
        StringBuilder sb = new StringBuilder();
        switch (type.getEntryType()) {
        case EXPENDITURE:
            sb.append(PREFIX_TYPE + VALID_TYPE_EXPENDITURE + " ");
            break;
        case INCOME:
            sb.append(PREFIX_TYPE + VALID_TYPE_INCOME + " ");
            break;
        default:
            break;
        }
        sb.append(PREFIX_AMOUNT + entry.getAmount().toString() + " ");
        sb.append(PREFIX_DATE + entry.getDate().toString() + " ");
        sb.append(PREFIX_DESCRIPTION + entry.getDescription().fullDescription + " ");
        sb.append(PREFIX_TAG + entry.getTag().getTagName() + " ");

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditEntryDescriptor}'s details.
     */
    public static String getEditEntryDescriptorDetails(EditCommand.EditEntryDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getType().ifPresent(type -> sb.append(PREFIX_TYPE).append(type.getEntryType().toString()).append(" "));
        descriptor.getAmount().ifPresent(amount -> sb.append(PREFIX_AMOUNT).append(amount.getValue()).append(" "));
        descriptor.getDate().ifPresent(date -> sb.append(PREFIX_DATE).append(date).append(" "));
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_DESCRIPTION)
                .append(description.fullDescription).append(" "));
        descriptor.getTag().ifPresent(tag -> sb.append(PREFIX_TAG).append(tag.tagName).append(" "));
        return sb.toString();
    }


}
