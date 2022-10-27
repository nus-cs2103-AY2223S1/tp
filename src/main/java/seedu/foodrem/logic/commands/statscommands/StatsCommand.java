package seedu.foodrem.logic.commands.statscommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.enums.CommandType.STATS_COMMAND;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.item.itemcomparators.ItemCostComparator;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.viewmodels.Stats;

/**
 * Displays FoodRem statistics to the user.
 */
public class StatsCommand extends Command {
    @Override
    public CommandResult<Stats> execute(Model model) {
        requireNonNull(model);
        ObservableList<Item> itemList = model.getFoodRem().getItemList();
        ObservableList<Tag> tagList = model.getFoodRem().getTagList();
        List<Item> expensiveItems = getTopThreeExpensiveItems(itemList);
        List<Tag> commonTags = getTopThreeCommonTags(itemList, tagList);
        double amountWasted = getAmountWasted(itemList);
        return CommandResult.from(new Stats(amountWasted, expensiveItems, commonTags));
    }

    public static String getUsage() {
        return STATS_COMMAND.getUsage();
    }

    private List<Item> getTopThreeExpensiveItems(List<Item> itemList) {
        return itemList.stream()
                .sorted(new ItemCostComparator()
                        .reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    private double getAmountWasted(List<Item> itemList) {
        return itemList.stream()
                .filter(i -> !i.getQuantity().isZero() && i.getExpiryDate().isAfterExpiryDate(LocalDate.now()))
                .map(i -> i.getPrice().getItemPrice()).reduce(0.0, Double::sum);
    }

    private List<Tag> getTopThreeCommonTags(List<Item> itemList, List<Tag> tagList) {
        HashMap<Tag, Integer> tagToFrequencyMap = new HashMap<>();

        // TODO: Replace with streams, this is janky and ugly
        for (int i = 0; i < tagList.size(); i++) {
            Tag tag = tagList.get(i);
            for (int j = 0; j < itemList.size(); j++) {
                if (itemList.get(j).getTagSet().contains(tag)) {
                    if (tagToFrequencyMap.containsKey(tag)) {
                        tagToFrequencyMap.put(tag, tagToFrequencyMap.get(tag) + 1);
                    } else {
                        tagToFrequencyMap.put(tag, 1);
                    }
                }
            }
        }

        return tagToFrequencyMap.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
