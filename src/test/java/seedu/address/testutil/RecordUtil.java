package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECORD;

import java.util.Set;

import seedu.address.logic.commands.AddRecordCommand;
import seedu.address.logic.commands.EditRecordCommand.EditRecordDescriptor;
import seedu.address.model.record.Medication;
import seedu.address.model.record.Record;

/**
 * A utility class for Person.
 */
public class RecordUtil {

    /**
     * Returns a record command string for adding the {@code record}.
     */
    public static String getAddRecordCommand(Record record) {
        return AddRecordCommand.COMMAND_WORD + " " + getRecordDetails(record);
    }

    /**
     * Returns the part of command string for the given {@code record}'s details.
     */
    public static String getRecordDetails(Record record) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_RECORD + record.getRecordData() + " ");
        sb.append(PREFIX_DATE + record.getRecordDate().format(Record.DATE_FORMAT) + " ");

        record.getMedications().stream().forEach(
                s -> sb.append(PREFIX_MEDICATION + s.medicationName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditRecordDescriptor}'s details.
     */
    public static String getEditRecordDescriptorDetails(EditRecordDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getRecord().ifPresent(record -> sb.append(PREFIX_RECORD).append(record).append(" "));
        descriptor.getRecordDate().ifPresent(
                recordDate -> sb.append(PREFIX_DATE).append(recordDate.format(Record.DATE_FORMAT)).append(" "));

        if (descriptor.getMedications().isPresent()) {
            Set<Medication> medications = descriptor.getMedications().get();
            if (medications.isEmpty()) {
                sb.append(PREFIX_MEDICATION);
            } else {
                medications.forEach(s -> sb.append(PREFIX_MEDICATION).append(s.medicationName).append(" "));
            }
        }
        return sb.toString();
    }
}
