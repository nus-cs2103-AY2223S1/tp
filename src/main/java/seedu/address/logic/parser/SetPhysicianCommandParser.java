package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UID;

import java.util.function.Supplier;

import seedu.address.logic.commands.UpdatePhysicianCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Uid;

/**
 * Parses user input for the set physician command.
 */
public class SetPhysicianCommandParser implements Parser<UpdatePhysicianCommand> {

    @Override
    public UpdatePhysicianCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_UID, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL);

        Supplier<ParseException> exceptionSupplier = () ->
                new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdatePhysicianCommand.MESSAGE_USAGE));

        Uid uid;
        Name pName;
        Phone pPhone;
        Email pEmail;

        uid = ParserUtil.parseUid(argMultimap.getValue(PREFIX_UID).orElseThrow(exceptionSupplier));
        pName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).orElseThrow(exceptionSupplier));
        pPhone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).orElseThrow(exceptionSupplier));
        pEmail = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).orElseThrow(exceptionSupplier));

        return new UpdatePhysicianCommand(uid, pName, pPhone, pEmail);
    }
}
