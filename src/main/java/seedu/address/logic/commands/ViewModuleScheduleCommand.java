package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;

import seedu.address.model.Model;
import seedu.address.model.module.schedule.WeekdayContainsKeywordsPredicate;

/**
 * Views all slots in the schedule which satisfies selection requirements
 */
public class ViewModuleScheduleCommand extends Command {

    private final WeekdayContainsKeywordsPredicate predicate;

    public ViewModuleScheduleCommand(WeekdayContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredScheduleList(predicate);
        return new CommandResult(String.format("accc", model.getFilteredScheduleList().size()));
//        return new CommandResult(
//                String.format("And show number of schedules: ", model.getFilteredScheduleList().size(), model.getFilteredPersonList().size()),
//                false, false, false, false,
//                false, true);
    }

}
