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
 * Finds and lists all eateries in food guide whose tags contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTagCommand extends Command {

    public static final String COMMAND_WORD = "findTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all eateries whose list of tags matches any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]... [-r NUMBER]\n"
            + "Example: " + COMMAND_WORD + " vegetarian -r 1";

    private final Predicate<Eatery> predicate;
    private final int numRandPicks;

    public FindTagCommand(Predicate<Eatery> predicate) {
        this(predicate, -1);
    }

    /**
     * @param predicate What all found eateries must satisfy
     * @param numRandPicks The number of found eateries to randomly select
     */
    public FindTagCommand(Predicate<Eatery> predicate, int numRandPicks) {
        this.predicate = predicate;
        this.numRandPicks = numRandPicks;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        String warning = "";
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

            if (numToShow < numRandPicks) {
                warning = "\nWarning: there are fewer eateries matching the specified criteria than " +
                        "the requested number of eateries to randomly select.";
            }
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_EATERIES_LISTED_OVERVIEW, model.getFilteredEateryList().size()) + warning);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTagCommand // instanceof handles nulls
                && predicate.equals(((FindTagCommand) other).predicate)); // state check
    }
}
