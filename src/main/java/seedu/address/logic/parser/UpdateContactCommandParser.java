package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UID;

import java.util.function.Supplier;

import seedu.address.logic.commands.UpdateContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.category.Category;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Uid;

/**
 * Parses user input for the set physician command.
 */
public class UpdateContactCommandParser implements Parser<UpdateContactCommand> {

    @Override
    public UpdateContactCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_UID, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_CATEGORY);

        Supplier<ParseException> exceptionSupplier = () -> new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateContactCommand.MESSAGE_USAGE));

        Uid uid;
        Name name;
        Phone phone;
        Email email;
        Category category;

        uid = ParserUtil.parseUid(argMultimap.getValue(PREFIX_UID).orElseThrow(exceptionSupplier));
        name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).orElseThrow(exceptionSupplier));
        phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).orElseThrow(exceptionSupplier));
        email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).orElseThrow(exceptionSupplier));
        category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).orElseThrow(exceptionSupplier));

        return new UpdateContactCommand(uid, name, phone, email, category);
    }
}
