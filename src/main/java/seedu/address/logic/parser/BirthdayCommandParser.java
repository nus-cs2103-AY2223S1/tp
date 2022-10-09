//package seedu.address.logic.parser;
//
//import seedu.address.commons.core.index.Index;
//import seedu.address.commons.exceptions.IllegalValueException;
//import seedu.address.logic.commands.BirthdayCommand;
//import seedu.address.logic.parser.exceptions.ParseException;
//import seedu.address.model.person.Birthday;
//
//import static java.util.Objects.requireNonNull;
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
//
//public class BirthdayCommandParser {
//    public BirthdayCommand parse(String args) throws ParseException {
//        requireNonNull(args);
//        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
//                PREFIX_BIRTHDAY);
//
//        Index index;
//        try {
//            index = ParserUtil.parseIndex(argMultimap.getPreamble());
//        } catch (IllegalValueException ive) {
//            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
//                    BirthdayCommand.MESSAGE_USAGE), ive);
//        }
//
//        String birthday = argMultimap.getValue(PREFIX_BIRTHDAY).orElse("");
//
//        return new BirthdayCommand(index, new Birthday(birthday));
//    }
//
//}
