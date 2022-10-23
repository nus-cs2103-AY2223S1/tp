package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.SetPhysicianCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class SetPhysicianCommandParser implements Parser<SetPhysicianCommand> {

    @Override
    public SetPhysicianCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_UID, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL);

        Supplier<ParseException> exceptionSupplier = () ->
                new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetPhysicianCommand.MESSAGE_USAGE));

        Index index;
        Name pName;
        Phone pPhone;
        Email pEmail;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException e) {
            throw exceptionSupplier.get();
        }

        pName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).orElseThrow(exceptionSupplier));
        pPhone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).orElseThrow(exceptionSupplier));
        pEmail = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).orElseThrow(exceptionSupplier));

        return new SetPhysicianCommand(index, pName, pPhone, pEmail);
    }
}
