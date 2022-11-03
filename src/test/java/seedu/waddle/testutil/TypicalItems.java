package seedu.waddle.testutil;

import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COST_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COST_BEACH;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COST_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COST_SKINNY;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_BEACH;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_SKINNY;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_BEACH;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_SKINNY;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PRIORITY_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PRIORITY_BEACH;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PRIORITY_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PRIORITY_SKINNY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.waddle.model.item.Item;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * A utility class containing a list of {@code Itinerary} objects to be used in tests.
 */
public class TypicalItems {
    public static final Item SHOPPING = new ItemBuilder().withDesc(VALID_ITEM_DESC_SHOPPING)
            .withDuration(VALID_DURATION_SHOPPING).withCost(VALID_COST_SHOPPING)
            .withPriority(VALID_PRIORITY_SHOPPING).build();
    public static final Item SKINNY = new ItemBuilder().withDesc(VALID_ITEM_DESC_SKINNY)
            .withDuration(VALID_DURATION_SKINNY).withCost(VALID_COST_SKINNY)
            .withPriority(VALID_PRIORITY_SKINNY).build();
    public static final Item BEACH = new ItemBuilder().withDesc(VALID_ITEM_DESC_BEACH)
            .withDuration(VALID_DURATION_BEACH).withCost(VALID_COST_BEACH)
            .withPriority(VALID_PRIORITY_BEACH).build();
    public static final Item ART = new ItemBuilder().withDesc(VALID_ITEM_DESC_ART)
            .withDuration(VALID_DURATION_ART).withCost(VALID_COST_ART)
            .withPriority(VALID_PRIORITY_ART).build();

    private TypicalItems() {
    } // prevents instantiation

    public static List<Item> getTypicalItems() {
        return new ArrayList<>(Arrays.asList(SHOPPING, SKINNY, BEACH, ART));
    }
}
