package seedu.address.logic.parser.addcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_CERTIFICATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_COLOR_PATTERN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_VACCINATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_WEIGHT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.addcommands.AddPetCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.pet.Pet;

/**
 * Parses input arguments and creates a new AddPetCommand object
 */
public class AddPetCommandParser implements Parser<AddPetCommand> {

    public AddPetCommandParser() {
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddPetCommand
     * and returns an AddPetCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPetCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_PET_NAME,
                PREFIX_PET_DATE_OF_BIRTH,
                PREFIX_PET_COLOR,
                PREFIX_PET_COLOR_PATTERN,
                PREFIX_PET_HEIGHT,
                PREFIX_PET_CERTIFICATE,
                PREFIX_PET_SPECIES,
                PREFIX_PET_VACCINATION_STATUS,
                PREFIX_PET_PRICE,
                PREFIX_PET_WEIGHT);

        String preamble = argMultimap.getPreamble();
        if (preamble.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_MISSING_INDEX
                    + AddPetCommand.MESSAGE_USAGE_EXISTING_SUPPLIER));
        }

        String indexStr = preamble.split(" ")[0];
        Index index = ParserUtil.parseIndex(indexStr);
        Pet pet = ParserUtil.parsePet(args, true);

        return new AddPetCommand(pet, index);
    }
}
