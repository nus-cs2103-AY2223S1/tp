package eatwhere.foodguide.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.logic.commands.exceptions.CommandException;
import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.eatery.Eatery;

/**
 * Finds and lists all eateries in food guide whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all eateries whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Optionally, you can randomly choose a given number of the found eateries.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]... [-r NUMTOSHOW]\n"
            + "Example: " + COMMAND_WORD + " Chicken Rice -r 1";

    private final Predicate<Eatery> predicate;

    private final int numRandPicks;

    public FindCommand(Predicate<Eatery> predicate) {
        this(predicate, -1);
    }

    /**
     * @param predicate What all found eateries must satisfy
     * @param numRandPicks The number of found eateries to randomly select
     */
    public FindCommand(Predicate<Eatery> predicate, int numRandPicks) {
        this.predicate = predicate;
        this.numRandPicks = numRandPicks;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredEateryList(predicate);
        if (numRandPicks > 0) {
            int numToShow = Math.min(numRandPicks, model.getFilteredEateryList().size());
            List<Integer> randomIndexes = new ArrayList<>(model.getFilteredEateryList().size());
            for (int i = 0; i < model.getFilteredEateryList().size(); ++i) {
                randomIndexes.add(i);
            }
            Collections.shuffle(randomIndexes);
            randomIndexes = randomIndexes.subList(0, numToShow);
            randomIndexes.sort(null);

            ArrayList<Eatery> eateriesChosen = new ArrayList<>(numToShow);
            for (Integer i : randomIndexes) {
                eateriesChosen.add(model.getFilteredEateryList().get(i));
            }
            model.updateFilteredEateryList(eateriesChosen::contains);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_EATERIES_LISTED_OVERVIEW, model.getFilteredEateryList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
