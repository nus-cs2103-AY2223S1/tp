package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.pet.Pet;

import java.util.function.Predicate;

public class FilterPetCommand extends Command {
    public static final String COMMAND_WORD = "filter-p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all pets who are tagged: "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " s/Cat";

    private final Predicate<Pet> cPredicate;
    private final Predicate<Pet> nPredicate;
    private final Predicate<Pet> pPredicate;
    private final Predicate<Pet> sPredicate;
    private final Predicate<Pet> vsPredicate;

    /**
     * Creates a FilterLocCommand to filter the specified {@code Location}.
     */
    public FilterPetCommand(Predicate<Pet> cPredicate, Predicate<Pet> nPredicate, Predicate<Pet> pPredicate,
                            Predicate<Pet> sPredicate, Predicate<Pet> vsPredicate) {
        this.cPredicate = cPredicate;
        this.nPredicate = nPredicate;
        this.pPredicate = pPredicate;
        this.sPredicate = sPredicate;
        this.vsPredicate = vsPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPetList(cPredicate);
        model.updateFilteredPetList(nPredicate);
        model.updateFilteredPetList(pPredicate);
        model.updateFilteredPetList(sPredicate);
        model.updateFilteredPetList(vsPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPetList().size()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterPetCommand // instanceof handles nulls
                && cPredicate.equals(((FilterPetCommand) other).cPredicate)
                && nPredicate.equals(((FilterPetCommand) other).nPredicate)
                && pPredicate.equals(((FilterPetCommand) other).pPredicate)
                && sPredicate.equals(((FilterPetCommand) other).sPredicate)
                && vsPredicate.equals(((FilterPetCommand) other).vsPredicate)); // state check
    }
}
