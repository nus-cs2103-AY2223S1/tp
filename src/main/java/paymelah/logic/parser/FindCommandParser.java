package paymelah.logic.parser;

import static paymelah.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static paymelah.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static paymelah.logic.parser.CliSyntax.PREFIX_EMAIL;
import static paymelah.logic.parser.CliSyntax.PREFIX_MONEY;
import static paymelah.logic.parser.CliSyntax.PREFIX_NAME;
import static paymelah.logic.parser.CliSyntax.PREFIX_PHONE;
import static paymelah.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import paymelah.logic.commands.FindCommand;
import paymelah.logic.parser.ParserUtil.PersonDescriptor;
import paymelah.logic.parser.exceptions.ParseException;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;
import paymelah.model.person.PersonMatchesDescriptorPredicate;
import paymelah.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * FindCommand and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_TAG, PREFIX_DESCRIPTION, PREFIX_MONEY);

        PersonDescriptor personDescriptor = new PersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            personDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            personDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            personDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            personDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTagsForFind(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(personDescriptor::setTags);
        parseDescriptionsForFind(argMultimap.getAllValues(PREFIX_DESCRIPTION))
                .ifPresent(personDescriptor::setDescriptions);
        parseMoniesForFind(argMultimap.getAllValues(PREFIX_MONEY)).ifPresent(personDescriptor::setMonies);

        if (!personDescriptor.isAnyFieldSet()) {
            throw new ParseException(FindCommand.MESSAGE_NO_KEYWORDS);
        }

        return new FindCommand(new PersonMatchesDescriptorPredicate(personDescriptor));
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     */
    private Optional<Set<Tag>> parseTagsForFind(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseTags(tags));
    }

    /**
     * Parses {@code Collection<String> descriptions} into a {@code Set<Description>}
     * if {@code descriptions} is non-empty.
     */
    private Optional<Set<Description>> parseDescriptionsForFind(Collection<String> descriptions) throws ParseException {
        assert descriptions != null;

        if (descriptions.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseDescriptions(descriptions));
    }

    /**
     * Parses {@code Collection<String> monies} into a {@code Set<Money>} if {@code monies} is non-empty.
     */
    private Optional<Set<Money>> parseMoniesForFind(Collection<String> monies) throws ParseException {
        assert monies != null;

        if (monies.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseMonies(monies));
    }

}
