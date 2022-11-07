package modtrekt.testutil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import modtrekt.logic.commands.AddDeadlineCommand;
import modtrekt.model.module.ModCode;
import modtrekt.model.task.Description;

/**
 * Utility class that returns an instance of AddDeadlineCommand.
 */
public class AddDeadlineCommandBuilder {

    /**
     * Returns an instance of AddDeadlineCommand according to the specified attributes.
     */
    public static AddDeadlineCommand build(String description,
                                           String date, String code) {
        Description d = new Description(description);
        LocalDate ld;
        ModCode m;
        ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-M-d"));

        if (code != null) {
            m = new ModCode(code);
        } else {
            m = null;
        }

        return new AddDeadlineCommand(d, m, ld);
    }
}
