package seedu.foodrem.logic.commands.statscommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.STATS_COMMAND;

import java.util.List;
import java.util.stream.Collectors;

import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.itemcomparators.ItemCostComparator;
import seedu.foodrem.viewmodels.Stats;

/**
 * Displays FoodRem statistics to the user.
 */
public class StatsCommand extends Command {
    @Override
    public CommandResult<Stats> execute(Model model) {
        requireNonNull(model);
        List<Item> expensiveItems = getTopThreeExpensiveItems(model.getFoodRem().getItemList());
        double amountWasted = getAmountWastedFromWastage();
        return CommandResult.from(new Stats(amountWasted, expensiveItems));
    }

    public static String getUsage() {
        return STATS_COMMAND.getUsage();
    }

    private List<Item> getTopThreeExpensiveItems(List<Item> itemList) {
        return itemList.stream().sorted(new ItemCostComparator().reversed()).limit(3).collect(Collectors.toList());
    }

    private double getAmountWastedFromWastage() {
        return 120;
    }
}
