package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_SLOT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UID;
import static seedu.address.logic.parser.ParserUtil.parseIndexes;
import static seedu.address.logic.parser.ParserUtil.parseUid;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Uid;

/**
 * Parses input arguments and creates a new Assign object
 */
public class AssignCommandParser implements Parser<AssignCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * AssignCommand
     * and returns an AssignCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_UID, PREFIX_DATE_AND_SLOT_INDEX);

        List<String> uidInput = argMultimap.getAllValues(PREFIX_UID);

        if (uidInput.size() != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE));
        }

        String uid1String = uidInput.get(0);
        String uid2String = uidInput.get(1);

        Uid uid1 = parseUid(uid1String);
        Uid uid2 = parseUid(uid2String);

        List<Index> indexList = parseIndexes(argMultimap.getAllValues(PREFIX_DATE_AND_SLOT_INDEX));

        return new AssignCommand(uid1, uid2, indexList);
    }

}
