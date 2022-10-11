package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Gender;
import seedu.address.model.tag.Tag;

public class ListCommandParser implements Parser {
    public ListCommand parse(String args) throws ParseException {
        if (args.length() == 0) {
            return new ListCommand(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_ADDRESS,
                PREFIX_CATEGORY,
                PREFIX_GENDER,
                PREFIX_TAG);

        Optional<Address> address = argMultimap.getValue(PREFIX_ADDRESS).map(Address::new);
        Optional<String> category = argMultimap.getValue(PREFIX_CATEGORY);
        Optional<Gender>[] gender = new Optional[1];
        argMultimap.getValue(PREFIX_GENDER).ifPresentOrElse(
                x -> {
                    if (Gender.isValidGender(x)) {
                        gender[0] = Optional.of(new Gender(x));
                    } else {
                        gender[0] = Optional.empty();
                    }
                },
                () -> gender[0] = Optional.empty());
        Optional<Tag> tag = argMultimap.getValue(PREFIX_TAG).map(Tag::new);

        return new ListCommand(address, category, gender[0], tag);
    }

}
