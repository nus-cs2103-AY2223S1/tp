package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.commission.CommissionContainsAllTagPredicate;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

/**
 * Finds and lists all commissions under the selected customer in ArtBuddy that includes all the tags.
 * Tag matching is case-sensitive.
 */
public class AllTagCommissionCommand extends Command {
    public static final String COMMAND_WORD = "allcom";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all commissions under the selected customer that"
            + "includes all the tags.\n"
            + "Parameters: TAG [MORE_TAGS]...\n"
            + "Example: " + COMMAND_WORD + " t/tag1 t/tag2 t/tag3";

    private final CommissionContainsAllTagPredicate predicate;

    public AllTagCommissionCommand(CommissionContainsAllTagPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, Storage storage) {
        requireNonNull(model);
        model.updateFilteredCommissionList(predicate);
        model.selectTab(GuiTab.COMMISSION);
        return new CommandResult(String.format(Messages.MESSAGE_COMMISSIONS_LISTED_OVERVIEW,
                model.getFilteredCommissionList().size()));
    }
}
