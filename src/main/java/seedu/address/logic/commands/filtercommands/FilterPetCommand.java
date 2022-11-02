package seedu.address.logic.commands.filtercommands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.parser.filtercommandparser.FilterPetCommandParser;
import seedu.address.model.Model;
import seedu.address.model.pet.Pet;

/**
 * Filters and lists all pets in address book whose attributes match the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FilterPetCommand extends FilterCommand {
    public static final String COMMAND_WORD = "filter-p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all pets with attributes: "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: PREFIX/[KEYWORDS] PREFIX/[KEYWORDS] ...\n"
            + "There are five possible attributes to filter: Color, Name, Price, Species, Vaccination status \n"
            + "For Color, use the prefix " + FilterPetCommandParser.COLOR_PREFIX + "\n"
            + "For Name, use the prefix " + FilterPetCommandParser.PET_NAME_PREFIX + "\n"
            + "For Price, use the prefix " + FilterPetCommandParser.PRICE_PREFIX + "\n"
            + "For Species, use the prefix " + FilterPetCommandParser.SPECIES_PREFIX + "\n"
            + "For Vaccination Status, use the prefix " + FilterPetCommandParser.VACCINATION_PREFIX + "\n"
            + "Example: " + COMMAND_WORD + " p_n/Ashy p_s/Cat";

    private final Predicate<Pet> colorPredicate;
    private final Predicate<Pet> namePredicate;
    private final Predicate<Pet> pricePredicate;
    private final Predicate<Pet> speciesPredicate;
    private final Predicate<Pet> vaccinationPredicate;

    /**
     * Creates a FilterLocCommand to filter the specified {@code Location}.
     */
    public FilterPetCommand(Predicate<Pet> cPredicate, Predicate<Pet> nPredicate, Predicate<Pet> pPredicate,
                            Predicate<Pet> sPredicate, Predicate<Pet> vaccinationPredicate) {
        this.colorPredicate = cPredicate;
        this.namePredicate = nPredicate;
        this.pricePredicate = pPredicate;
        this.speciesPredicate = sPredicate;
        this.vaccinationPredicate = vaccinationPredicate;
    }

    /**
     * Creates a Predicate to filter the specified {@code Pet}.
     */
    public Predicate<Pet> generatePredicate() {
        return new Predicate<Pet>() {
            @Override
            public boolean test(Pet pet) {
                return colorPredicate.test(pet) && namePredicate.test(pet) && pricePredicate.test(pet)
                        && speciesPredicate.test(pet) && vaccinationPredicate.test(pet);
            }
        };
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Pet> finalPredicate = generatePredicate();
        model.updateFilteredPetList(finalPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PETS_LISTED_OVERVIEW, model.getFilteredPetList().size()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterPetCommand // instanceof handles nulls
                && colorPredicate.equals(((FilterPetCommand) other).colorPredicate)
                && namePredicate.equals(((FilterPetCommand) other).namePredicate)
                && pricePredicate.equals(((FilterPetCommand) other).pricePredicate)
                && speciesPredicate.equals(((FilterPetCommand) other).speciesPredicate)
                && vaccinationPredicate.equals(((FilterPetCommand) other).vaccinationPredicate)); // state check
    }
}
