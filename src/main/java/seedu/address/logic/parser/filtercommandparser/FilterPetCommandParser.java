package seedu.address.logic.parser.filtercommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_VACCINATION_STATUS;

import java.util.function.Predicate;

import seedu.address.logic.commands.filtercommands.FilterPetCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.PredicateParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.pet.Pet;

/**
 * Parses input arguments and creates a new FilterPetCommand object.
 */
public class FilterPetCommandParser implements Parser<FilterPetCommand> {
    public static final String COLOR_PREFIX = "p_c";
    public static final String PET_NAME_PREFIX = "p_n";
    public static final String PRICE_PREFIX = "p_p";
    public static final String SPECIES_PREFIX = "p_s";
    public static final String VACCINATION_PREFIX = "p_v";

    private static Predicate<Pet> defaultPredicate = new Predicate<Pet>() {
        @Override
        public boolean test(Pet pet) {
            return true;
        }
        public boolean equals(Object object) {
            return object instanceof Predicate;
        }
    };

    /**
     * Parses the given {@code String} of arguments in the context of the FilterPetCommand
     * and returns a FilterPetCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FilterPetCommand parse(String trimmedArgs) throws ParseException {

        boolean isTokenized = false;

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPetCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(trimmedArgs, PREFIX_PET_COLOR, PREFIX_PET_NAME,
                        PREFIX_PET_PRICE, PREFIX_PET_SPECIES, PREFIX_PET_VACCINATION_STATUS);

        Predicate<Pet> colorPredicate = defaultPredicate;
        Predicate<Pet> namePredicate = defaultPredicate;
        Predicate<Pet> pricePredicate = defaultPredicate;
        Predicate<Pet> speciesPredicate = defaultPredicate;
        Predicate<Pet> vaccinationPredicate = defaultPredicate;

        if (argMultimap.getValue(PREFIX_PET_COLOR).isPresent()) {
            colorPredicate = PredicateParser.parsePet(argMultimap.getValue(PREFIX_PET_COLOR).get(),
                    PREFIX_PET_COLOR.getPrefix());
            isTokenized = true;
        }

        if (argMultimap.getValue(PREFIX_PET_NAME).isPresent()) {
            colorPredicate = PredicateParser.parsePet(argMultimap.getValue(PREFIX_PET_NAME).get(),
                    PREFIX_PET_NAME.getPrefix());
            isTokenized = true;
        }

        if (argMultimap.getValue(PREFIX_PET_PRICE).isPresent()) {
            colorPredicate = PredicateParser.parsePet(argMultimap.getValue(PREFIX_PET_PRICE).get(),
                    PREFIX_PET_PRICE.getPrefix());
            isTokenized = true;
        }

        if (argMultimap.getValue(PREFIX_PET_SPECIES).isPresent()) {
            colorPredicate = PredicateParser.parsePet(argMultimap.getValue(PREFIX_PET_SPECIES).get(),
                    PREFIX_PET_SPECIES.getPrefix());
            isTokenized = true;
        }

        if (argMultimap.getValue(PREFIX_PET_VACCINATION_STATUS).isPresent()) {
            colorPredicate = PredicateParser.parsePet(argMultimap.getValue(PREFIX_PET_VACCINATION_STATUS).get(),
                    PREFIX_PET_VACCINATION_STATUS.getPrefix());
            isTokenized = true;
        }

        if (!isTokenized) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPetCommand.MESSAGE_USAGE));
        }

        return new FilterPetCommand(colorPredicate, namePredicate, pricePredicate, speciesPredicate,
                vaccinationPredicate);
    }
}
