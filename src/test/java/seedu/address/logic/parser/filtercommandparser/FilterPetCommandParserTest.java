package seedu.address.logic.parser.filtercommandparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.filtercommands.FilterPetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.predicates.PetNameContainsKeywordsPredicate;

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

    @Test
    public void parse_name_success() {
        Predicate<Pet> defaultPredicate = new Predicate<Pet>() {
            @Override
            public boolean test(Pet pet) {
                return true;
            }
            @Override
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };
        String input = " " + PREFIX_PET_NAME + "ashy";
        FilterPetCommand expectedCommand = new FilterPetCommand(new PetNameContainsKeywordsPredicate<>(
                Arrays.asList("ashy")),
                defaultPredicate, defaultPredicate, defaultPredicate, defaultPredicate);
        try {
            assertEquals(parser.parse(input), expectedCommand);
        } catch (ParseException e) {
            assert false;
        }
    }

}
