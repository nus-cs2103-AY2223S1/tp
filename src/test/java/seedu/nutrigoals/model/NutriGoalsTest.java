package seedu.nutrigoals.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_TAG_BREAKFAST;
import static seedu.nutrigoals.testutil.Assert.assertThrows;
import static seedu.nutrigoals.testutil.TypicalFoods.APPLE;
import static seedu.nutrigoals.testutil.TypicalFoods.getTypicalNutriGoals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.Name;
import seedu.nutrigoals.model.user.User;
import seedu.nutrigoals.testutil.FoodBuilder;

public class NutriGoalsTest {

    private final NutriGoals nutriGoals = new NutriGoals();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), nutriGoals.getFoodList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nutriGoals.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyNutriGoals_replacesData() {
        NutriGoals newData = getTypicalNutriGoals();
        nutriGoals.resetData(newData);
        assertEquals(newData, nutriGoals);
    }

    @Test
    public void hasFood_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nutriGoals.hasFood(null));
    }

    @Test
    public void hasFood_foodNotInNutriGoals_returnsFalse() {
        assertFalse(nutriGoals.hasFood(APPLE));
    }

    @Test
    public void hasFood_foodInNutriGoals_returnsTrue() {
        nutriGoals.addFood(APPLE);
        assertTrue(nutriGoals.hasFood(APPLE));
    }

    @Test
    public void hasFood_foodWithSameIdentityFieldsInNutriGoals_returnsTrue() {
        nutriGoals.addFood(APPLE);
        Food editedAlice = new FoodBuilder(APPLE).withTag(VALID_TAG_BREAKFAST).build();
        assertTrue(nutriGoals.hasFood(editedAlice));
    }

    @Test
    public void getFoodList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> nutriGoals.getFoodList().remove(0));
    }

    @Test
    public void getFoodCaloriesList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> nutriGoals.getFoodCalorieList().clear());
    }

    /**
     * A stub ReadOnlyNutriGoals whose foods list can violate interface constraints.
     */
    private static class NutriGoalsStub implements ReadOnlyNutriGoals {
        private final ObservableList<Food> foods = FXCollections.observableArrayList();
        private final Calorie calorieGoal = new Calorie();
        private final User user = new User();
        NutriGoalsStub(Collection<Food> foods) {
            this.foods.setAll(foods);
        }

        @Override
        public ObservableList<Food> getFoodList() {
            return foods;
        }

        @Override
        public Calorie getCalorieTarget() {
            return calorieGoal;
        }

        @Override
        public User getUser() {
            return user;
        }

        @Override
        public List<Location> getGymLocations() {
            return new ArrayList<>();
        }

        @Override
        public Map<Name, Calorie> getFoodCalorieList() {
            return new HashMap<>();
        }

        @Override
        public Tip getTip() {
            return new TipList().generateTip();
        }
    }
}
