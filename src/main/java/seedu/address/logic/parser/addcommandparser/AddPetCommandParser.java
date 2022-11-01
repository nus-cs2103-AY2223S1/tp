package seedu.address.logic.parser.addcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

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

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_INDEX) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPetCommand.MESSAGE_USAGE_EXISTING_SUPPLIER));
        }

        String indexStr = argMultimap.getValue(PREFIX_INDEX).orElse("").split(" ")[0];
        Index index = ParserUtil.parseIndex(indexStr);
        Pet pet = ParserUtil.parsePet(args, true);

        return new AddPetCommand(pet, index);
    }
}
