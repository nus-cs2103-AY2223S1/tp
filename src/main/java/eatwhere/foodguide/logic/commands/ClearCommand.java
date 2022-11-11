package eatwhere.foodguide.logic.commands;

import static java.util.Objects.requireNonNull;

import eatwhere.foodguide.model.FoodGuide;
import eatwhere.foodguide.model.Model;

/**
 * Clears the food guide.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears the food guide. ";

    public static final String MESSAGE_SUCCESS = "food guide has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFoodGuide(new FoodGuide());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
