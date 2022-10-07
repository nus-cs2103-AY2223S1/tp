package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.tag.Tag;

public class ListCommandParser implements Parser {
    public ListCommand parse(String args) throws ParseException {
        if (args.length() == 0) {
            return new ListCommand(null, null, null, null);
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_ADDRESS,
                PREFIX_CATEGORY,
                PREFIX_GENDER,
                PREFIX_TAG);

        Optional<Address> address = argMultimap.getValue(PREFIX_ADDRESS).map(Address::new);
        Optional<String> category = argMultimap.getValue(PREFIX_CATEGORY).orElse("");
        Optional<Gender> gender = argMultimap.getValue(PREFIX_GENDER).orElse("");
        Optional<Tag> tag = argMultimap.getValue(PREFIX_TAG).map(Tag::new);

        return new ListCommand(address, category, gender, tag);
    }

}
