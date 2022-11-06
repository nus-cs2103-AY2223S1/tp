package seedu.address.logic.parser.filtercommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_SPECIES;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
//import java.util.Arrays;
//import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.filtercommands.FilterPetCommand;
//import seedu.address.model.pet.Pet;
//import seedu.address.model.pet.predicates.SpeciesContainsKeywordsPredicate;

public class FilterPetCommandParserTest {
    private FilterPetCommandParser parser = new FilterPetCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterPetCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertParseFailure(parser, "ft/invalid", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterPetCommand.MESSAGE_USAGE));
    }

    /*
    @Test
    public void parse_validSpecies_returnsFilterPetCommand() {
        Predicate<Pet> defaultPredicate = new Predicate<Pet>() {
            @Override
            public boolean test(Pet pet) {
                return true;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };

        String input = " " + PREFIX_PET_SPECIES + "pokemon";
        String inputWithSpaces = " \n" + PREFIX_PET_SPECIES + "pokemon \t \n";
        FilterPetCommand expectedCommand = new FilterPetCommand(defaultPredicate, defaultPredicate,
                defaultPredicate, new SpeciesContainsKeywordsPredicate<>(Arrays.asList("pokemon")), defaultPredicate);
        assertParseSuccess(parser, input, expectedCommand);
        assertParseSuccess(parser, inputWithSpaces, expectedCommand);
    }

     */
}
