package seedu.waddle.testutil;

import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COST_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COST_BEACH;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COST_LUNCH;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COST_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COST_SKINNY;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COST_TOUR;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_BEACH;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_BREAKFAST;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_LUNCH;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_SKINNY;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_TOUR;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_BEACH;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_BREAKFAST;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_LUNCH;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_SKINNY;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_TOUR;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PRIORITY_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PRIORITY_BEACH;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PRIORITY_BREAKFAST;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PRIORITY_LUNCH;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PRIORITY_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PRIORITY_SKINNY;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PRIORITY_TOUR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.waddle.model.item.Item;

/**
 * A utility class containing a list of {@code Itinerary} objects to be used in tests.
 */
public class TypicalItems {
    private TypicalItems() {
    } // prevents instantiation

    public static Item getShopping() {
        return new ItemBuilder().withDesc(VALID_ITEM_DESC_SHOPPING)
                .withDuration(VALID_DURATION_SHOPPING).withCost(VALID_COST_SHOPPING)
                .withPriority(VALID_PRIORITY_SHOPPING).build();
    }

    public static Item getSkinny() {
        return new ItemBuilder().withDesc(VALID_ITEM_DESC_SKINNY)
                .withDuration(VALID_DURATION_SKINNY).withCost(VALID_COST_SKINNY)
                .withPriority(VALID_PRIORITY_SKINNY).build();
    }

    public static Item getBeach() {
        return new ItemBuilder().withDesc(VALID_ITEM_DESC_BEACH)
                .withDuration(VALID_DURATION_BEACH).withCost(VALID_COST_BEACH)
                .withPriority(VALID_PRIORITY_BEACH).build();
    }

    public static Item getArt() {
        return new ItemBuilder().withDesc(VALID_ITEM_DESC_ART)
                .withDuration(VALID_DURATION_ART).withCost(VALID_COST_ART)
                .withPriority(VALID_PRIORITY_ART).build();
    }

    public static Item getTour() {
        return new ItemBuilder().withDesc(VALID_ITEM_DESC_TOUR)
                .withDuration(VALID_DURATION_TOUR).withCost(VALID_COST_TOUR)
                .withPriority(VALID_PRIORITY_TOUR).build();
    }

    public static Item getBreakfast() {
        return new ItemBuilder().withDesc(VALID_ITEM_DESC_BREAKFAST)
                .withDuration(VALID_DURATION_BREAKFAST).withCost(VALID_COST_ART)
                .withPriority(VALID_PRIORITY_BREAKFAST).build();
    }

    public static Item getLunch() {
        return new ItemBuilder().withDesc(VALID_ITEM_DESC_LUNCH)
                .withDuration(VALID_DURATION_LUNCH).withCost(VALID_COST_LUNCH)
                .withPriority(VALID_PRIORITY_LUNCH).build();
    }

    public static List<Item> getTypicalItems() {
        return new ArrayList<>(Arrays.asList(getShopping(), getSkinny(),
                getBeach(), getArt(), getTour(), getBreakfast(), getLunch()));
    }
}
