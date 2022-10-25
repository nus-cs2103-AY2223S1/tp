package seedu.nutrigoals.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.FoodCalorieList;
import seedu.nutrigoals.model.meal.FoodList;
import seedu.nutrigoals.model.meal.Name;
import seedu.nutrigoals.model.user.User;

/**
 * Wraps all data at the Nutrigoals level
 */
public class NutriGoals implements ReadOnlyNutriGoals {

    private final FoodList foods;
    private final FoodCalorieList foodCalorieList;
    private final TipList tipList;
    private User user = new User();
    private Calorie calorieTarget = new Calorie(); // defaults calorie to 2000 on the first edit to the book

    private final List<Location> nusGymsLocations;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        foods = new FoodList();
        nusGymsLocations = new ArrayList<>(List.of(
            new Location("MPSH", "1.3007599674153045, 103.77578206094384"),
            new Location("STEPHEN RIADY CENTRE", "1.304511666901411, 103.77205745840185"),
            new Location("USC", "1.2998680145010344, 103.77528575803385"),
            // https://goo.gl/maps/yHdjhHzPWx6eoRAAA
            new Location("EUSOFF HALL", "1.2937696692719094, 103.77043696074193"),
            // https://goo.gl/maps/5xx4jyh87QDcd83M8
            new Location("TEMASEK HALL", "1.2930639982706684, 103.77084097639103"),
            // https://goo.gl/maps/h4oQXbtMLyQeuPwM7
            new Location("KENT RIDGE HALL", "1.2918512226940035, 103.77477995285786"))
        );
        foodCalorieList = new FoodCalorieList();
        tipList = new TipList();
    }

    public NutriGoals() {
    }

    /**
     * Creates NutriGoals using the Foods in the {@code toBeCopied}
     */
    public NutriGoals(ReadOnlyNutriGoals toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the food list with {@code foods}.
     */
    public void setFoods(List<Food> foods) {
        this.foods.setFood(foods);
    }

    /**
     * Resets the existing data of this {@code NutriGoals} with {@code newData}.
     */
    public void resetData(ReadOnlyNutriGoals newData) {
        requireNonNull(newData);
        setCalorieTarget(newData.getCalorieTarget());
        setFoods(newData.getFoodList());
        setUser(newData.getUser());
        setNusGymsLocations(newData.getGymLocations());
        setFoodCaloriesList(newData.getFoodCalorieList());
    }

    //// food-level operations

    /**
     * Returns true if a food with the same name as {@code food} exists in the food list.
     */
    public boolean hasFood(Food food) {
        requireNonNull(food);
        return foods.contains(food);
    }

    /**
     * Adds a food item to the nutrigoals.
     */
    public void addFood(Food p) {
        foods.add(p);
    }

    /**
     * Replaces the given food {@code target} in the list with {@code editedFood}.
     * {@code target} must exist in the food list.
     */
    public void setFood(Food target, Food editedFood) {
        requireNonNull(editedFood);
        foods.setFood(target, editedFood);
    }

    /**
     * Removes {@code key} from this {@code NutriGoal}.
     * {@code key} must exist in the food list.
     */
    public void removeFood(Food key) {
        foods.remove(key);
    }

    public void setCalorieTarget(Calorie calorie) {
        requireNonNull(calorie);
        this.calorieTarget = calorie;
    }

    public void setUser(User user) {
        requireNonNull(user);
        this.user = user;
    }

    public void setFoodCaloriesList(Map<Name, Calorie> foodCaloriesList) {
        requireNonNull(foodCaloriesList);
        this.foodCalorieList.setFoodCalorieMapping(foodCaloriesList);
    }

    @Override
    public ObservableList<Food> getFoodList() {
        return foods.asUnmodifiableObservableList();
    }

    public Calorie calculateSuggestedCalorie() {
        return user.calculateSuggestedCalorieIntake();
    }

    public void setNusGymsLocations(List<Location> nusGymsLocations) {
        requireNonNull(nusGymsLocations);
        this.nusGymsLocations.clear();
        this.nusGymsLocations.addAll(nusGymsLocations);
    }

    @Override
    public List<Location> getGymLocations() {
        return new ArrayList<>(this.nusGymsLocations); // #defensive-programming
    }

    /**
     * @return Calorie
     */
    @Override
    public Calorie getCalorieTarget() {
        return this.calorieTarget;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public Map<Name, Calorie> getFoodCalorieList() {
        return foodCalorieList.asUnmodifiableMap();
    }

    @Override
    public Tip getTip() {
        return tipList.generateTip();
    }

    //// util methods

    @Override
    public String toString() {
        return foods.asUnmodifiableObservableList().size() + " foods";
        // TODO: refine later
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof NutriGoals // instanceof handles nulls
            && foods.equals(((NutriGoals) other).foods)
            && this.calorieTarget.equals(((NutriGoals) other).calorieTarget)
            && this.user.equals(((NutriGoals) other).user)
            && this.foodCalorieList.equals(((NutriGoals) other).foodCalorieList));
    }

    @Override
    public int hashCode() {
        return foods.hashCode();
    }
}
