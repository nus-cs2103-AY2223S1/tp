package eatwhere.foodguide.model;

import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.model.eatery.exceptions.DuplicateEateryException;
import eatwhere.foodguide.testutil.Assert;
import eatwhere.foodguide.testutil.EateryBuilder;
import eatwhere.foodguide.testutil.TypicalEateries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FoodGuideTest {

    private final FoodGuide foodGuide = new FoodGuide();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), foodGuide.getEateryList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> foodGuide.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFoodGuide_replacesData() {
        FoodGuide newData = TypicalEateries.getTypicalFoodGuide();
        foodGuide.resetData(newData);
        assertEquals(newData, foodGuide);
    }

    @Test
    public void resetData_withDuplicateEateries_throwsDuplicateEateryException() {
        // Two eateries with the same identity fields
        Eatery editedAlice = new EateryBuilder(TypicalEateries.ALICE)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        List<Eatery> newEateries = Arrays.asList(TypicalEateries.ALICE, editedAlice);
        FoodGuideStub newData = new FoodGuideStub(newEateries);

        Assert.assertThrows(DuplicateEateryException.class, () -> foodGuide.resetData(newData));
    }

    @Test
    public void hasEatery_nullEatery_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> foodGuide.hasEatery(null));
    }

    @Test
    public void hasEatery_eateryNotInAddressBook_returnsFalse() {
        assertFalse(foodGuide.hasEatery(TypicalEateries.ALICE));
    }

    @Test
    public void hasEatery_eateryInFoodGuide_returnsTrue() {
        foodGuide.addEatery(TypicalEateries.ALICE);
        assertTrue(foodGuide.hasEatery(TypicalEateries.ALICE));
    }

    @Test
    public void hasEatery_eateryWithSameIdentityFieldsInFoodGuide_returnsTrue() {
        foodGuide.addEatery(TypicalEateries.ALICE);
        Eatery editedAlice = new EateryBuilder(TypicalEateries.ALICE)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(foodGuide.hasEatery(editedAlice));
    }

    @Test
    public void getEateryList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> foodGuide.getEateryList().remove(0));
    }

    /**
     * A stub ReadOnlyFoodGuide whose eateries list can violate interface constraints.
     */
    private static class FoodGuideStub implements ReadOnlyFoodGuide {
        private final ObservableList<Eatery> eateries = FXCollections.observableArrayList();

        FoodGuideStub(Collection<Eatery> eateries) {
            this.eateries.setAll(eateries);
        }

        @Override
        public ObservableList<Eatery> getEateryList() {
            return eateries;
        }
    }

}
