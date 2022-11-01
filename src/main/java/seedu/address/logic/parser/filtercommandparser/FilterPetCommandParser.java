package seedu.address.logic.parser.filtercommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.function.Predicate;

import seedu.address.logic.commands.filtercommands.FilterPetCommand;
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
            String[] query = arg.split("/");
            switch (query[0]) {
            case COLOR_PREFIX:
                System.out.println(query[0]);
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
