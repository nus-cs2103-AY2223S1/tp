// TODO Redo this class
//package seedu.address.logic.parser;
//
//import seedu.address.logic.commands.AddBuyerCommand;
//import seedu.address.logic.commands.AddPersonCommand;
//import seedu.address.logic.commands.AddSupplierCommand;
//import seedu.address.logic.parser.exceptions.ParseException;
//import seedu.address.model.person.Address;
//import seedu.address.model.person.Email;
//import seedu.address.model.person.Name;
//import seedu.address.model.person.Person;
//import seedu.address.model.person.PersonCategory;
//import seedu.address.model.person.Phone;
//import seedu.address.model.tag.Tag;
//
//import java.util.Set;
//import java.util.stream.Stream;
//
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_CATEGORY;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
//
///**
// * Parses input arguments and creates a new AddPersonCommand object
// */
//public class AddSupplierCommandParser extends AddPersonCommandParser implements Parser<AddSupplierCommand> {
//
//    public AddSupplierCommandParser() {
//
//    }
//
//    /**
//     * Parses the given {@code String} of arguments in the context of the AddPersonCommand
//     * and returns an AddPersonCommand object for execution.
//     * @throws ParseException if the user input does not conform the expected format
//     */
//    public AddPersonCommand parse(String args) throws ParseException {
//
//        ArgumentMultimap argMultimap =
//                ArgumentTokenizer.tokenize(args, PREFIX_PERSON_CATEGORY);
//
//        if (!arePrefixesPresent(argMultimap, PREFIX_PERSON_CATEGORY)
//                || !argMultimap.getPreamble().isEmpty()) {
//            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE)); //TODO Replace the MESSAGE_USAGE
//        }
//
//        PersonCategory personCategory =
//                ParserUtil.parsePersonCategory(argMultimap.getValue(PREFIX_PERSON_CATEGORY).orElse(""));
//
//        switch (personCategory) {
//        case BUYER:
//            return
//        }
//
//        PersonCategory personCategory =
//                ParserUtil.parsePersonCategory(argMultimap.getValue(PREFIX_PERSON_CATEGORY).get());
//        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
//        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
//        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
//        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
//        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
//
//        Person person = new Person(personCategory, name, phone, email, address, tagList);
//
//        return new AddPersonCommand(person);
//    }
//
//    /**
//     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
//     * {@code ArgumentMultimap}.
//     */
//    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
//        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
//    }
//
//}
