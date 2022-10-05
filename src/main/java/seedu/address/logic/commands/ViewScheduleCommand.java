package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;

/**
 * Views all slots in the schedule which satisfies selection requirements
 */
public class ViewScheduleCommand extends Command{

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views all timeslots which satisfies all selection "
            + "requirements.\n"
            + "Format: view schedule /w WEEKDAY /d DATE /m MODULE\n"
            + "Example: " + COMMAND_WORD + "/m CS2103T";

    private final String keywords;

    public ViewScheduleCommand(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        //model.
        return new CommandResult(
                "Done ViewScheduleCommand"
                //String.format(Messages.MESS)
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ViewScheduleCommand
                && keywords.equals(((ViewScheduleCommand) other).keywords));
    }

}
