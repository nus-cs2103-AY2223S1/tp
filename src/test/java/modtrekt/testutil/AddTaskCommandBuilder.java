package modtrekt.testutil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import modtrekt.logic.commands.AddTaskCommand;
import modtrekt.model.module.ModCode;
import modtrekt.model.task.Description;

/**
 * Utility class that returns an instance of AddTaskCommand.
 */
public class AddTaskCommandBuilder {

    /**
     * Returns an instance of AddTaskCommand according to the specified attributes.
     */
    public static AddTaskCommand build(String description,
                                       String date, String code) {
        Description d = new Description(description);
        LocalDate ld;
        ModCode m;
        if (date != null) {
            ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-M-d"));
        } else {
            ld = null;
        }
        if (code != null) {
            m = new ModCode(code);
        } else {
            m = null;
        }

        return new AddTaskCommand(d, m, ld);
    }
}
