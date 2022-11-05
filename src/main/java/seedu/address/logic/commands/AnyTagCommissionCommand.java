package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.commission.CommissionContainsAnyTagPredicate;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

/**
 * Finds and lists all commissions under the selected customer in ArtBuddy that includes at least one of the tags.
 * Tag matching is case-sensitive.
 */
public class AnyTagCommissionCommand extends Command {
    public static final String COMMAND_WORD = "anycom";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all commissions by the selected customer that "
            + "includes at least one of the tags.\n"
            + "Parameters: TAG [MORE_TAGS]...\n"
            + "Example: " + COMMAND_WORD + " t/tag1 t/tag2 t/tag3";

    private final CommissionContainsAnyTagPredicate predicate;

    public AnyTagCommissionCommand(CommissionContainsAnyTagPredicate predicate) {
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
