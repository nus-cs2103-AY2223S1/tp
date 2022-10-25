package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.function.Predicate;

import seedu.address.logic.commands.FilterPetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.pet.Pet;

/**
 * Parses input arguments and creates a new FilterPetCommand object.
 */
public class FilterPetCommandParser implements Parser<FilterPetCommand> {
    public static final char COLOR_PREFIX = 'c';
    public static final char PET_NAME_PREFIX = 'n';
    public static final char PRICE_PREFIX = 'p';
    public static final char SPECIES_PREFIX = 's';
    public static final char VACCINATION_PREFIX = 'v';

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
    public FilterPetCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPetCommand.MESSAGE_USAGE));
        }
        String[] nameKeywords = trimmedArgs.split("\\s+");

        Predicate<Pet> colorPredicate = defaultPredicate;
        Predicate<Pet> namePredicate = defaultPredicate;
        Predicate<Pet> pricePredicate = defaultPredicate;
        Predicate<Pet> speciesPredicate = defaultPredicate;
        Predicate<Pet> vaxPredicate = defaultPredicate;

        for (String arg: nameKeywords) {
            arg = arg.trim();
            switch (arg.charAt(0)) {
            case COLOR_PREFIX:
                colorPredicate = PredicateParser.parsePet(arg);
                break;
            case PET_NAME_PREFIX:
                namePredicate = PredicateParser.parsePet(arg);
                break;
            case PRICE_PREFIX:
                pricePredicate = PredicateParser.parsePet(arg);
                break;
            case SPECIES_PREFIX:
                speciesPredicate = PredicateParser.parsePet(arg);
                break;
            case VACCINATION_PREFIX:
                vaxPredicate = PredicateParser.parsePet(arg);
                break;
            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterPetCommand.MESSAGE_USAGE));
            }
        }
        return new FilterPetCommand(colorPredicate, namePredicate, pricePredicate, speciesPredicate, vaxPredicate);
    }
}
