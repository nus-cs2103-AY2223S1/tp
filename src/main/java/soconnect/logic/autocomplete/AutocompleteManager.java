package soconnect.logic.autocomplete;

import static soconnect.commons.util.StringUtil.startWithIgnoreCase;
import static soconnect.logic.parser.ArgumentTokenizer.PrefixArgument;
import static soconnect.logic.parser.ArgumentTokenizer.tokenizeToList;
import static soconnect.logic.parser.CliSyntax.INDICATOR_ADDRESS;
import static soconnect.logic.parser.CliSyntax.INDICATOR_EMAIL;
import static soconnect.logic.parser.CliSyntax.INDICATOR_NAME;
import static soconnect.logic.parser.CliSyntax.INDICATOR_PHONE;
import static soconnect.logic.parser.CliSyntax.INDICATOR_TAG;
import static soconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static soconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static soconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static soconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static soconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static soconnect.model.Model.PREDICATE_SHOW_NO_PERSON;
import static soconnect.model.person.search.SearchPrefix.SearchPrefixCommand;
import static soconnect.model.person.search.SearchPrefix.convertPrefixToEnumType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javafx.collections.transformation.FilteredList;
import soconnect.logic.commands.SearchCommand;
import soconnect.logic.parser.ArgumentMultimap;
import soconnect.logic.parser.ArgumentTokenizer;
import soconnect.logic.parser.Prefix;
import soconnect.model.ReadOnlySoConnect;
import soconnect.model.person.Person;
import soconnect.model.person.search.ContactContainsAllKeywordsPredicate;

/**
 * Manager of the autocomplete component.
 */
public class AutocompleteManager implements Autocomplete {
    private static final Pattern SEARCH_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    // In the autocomplete display box, it will include the original search hence it
    // will show a maximum of AUTOCOMPLETE_ENTRIES_LIMIT + 1 autocomplete entries
    private static final int AUTOCOMPLETE_ENTRIES_LIMIT = 10;

    public static final String INVALID_SEARCH_COMMAND_ARGUMENT = "";

    public static final String INVALID_PREFIX_ARGUMENT = "";

    private ReadOnlySoConnect soConnect;

    private final FilteredList<Person> filteredPersons;

    /**
     * Constructs a {@code AutocompleteManager} with the given {@code soConnect}.
     *
     * @param soConnect The soConnect data.
     */
    public AutocompleteManager(ReadOnlySoConnect soConnect) {
        this.soConnect = soConnect;
        filteredPersons = new FilteredList<>(this.soConnect.getPersonList());
    }

    @Override
    public void updateSoConnect(ReadOnlySoConnect soConnect) {
        this.soConnect = soConnect;
    }

    @Override
    public List<String> getAutocompleteEntries(String userInput) {
        String searchCommandArguments = this.getSearchCommandArguments(userInput);
        if (searchCommandArguments.equals(INVALID_SEARCH_COMMAND_ARGUMENT)) {
            return new ArrayList<>();
        }

        String lastPrefixArgument = getLastPrefixArgument(searchCommandArguments, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        if (lastPrefixArgument.equals(INVALID_PREFIX_ARGUMENT)) {
            return new ArrayList<>();
        }

        String argsWithoutLastPrefixArgument = searchCommandArguments.substring(0, searchCommandArguments.length() -
                lastPrefixArgument.length());

        updateFilteredPersonList(argsWithoutLastPrefixArgument);

        return generateAutocompleteEntries(argsWithoutLastPrefixArgument, lastPrefixArgument);
    }

    @Override
    public String getSearchCommandArguments(String userInput) {
        String trimmedUserInput = userInput.trim();
        Matcher matcher = SEARCH_COMMAND_FORMAT.matcher(trimmedUserInput);
        if (!matcher.matches()) {
            return INVALID_SEARCH_COMMAND_ARGUMENT;
        }

        String commandWord = matcher.group("commandWord");
        String SearchCommandArguments = matcher.group("arguments");
        return commandWord.equals(SearchCommand.COMMAND_WORD)
                ? SearchCommandArguments : INVALID_SEARCH_COMMAND_ARGUMENT;
    }

    public String getLastPrefixArgument(String argsString, Prefix... prefixes) {
        List<PrefixArgument> argsList = tokenizeToList(argsString, prefixes);
        assert argsList.size() >= 1;
        PrefixArgument lastPrefixArgument = argsList.get(argsList.size() - 1);

        String lastArgument = lastPrefixArgument.getArgument();
        if (lastArgument.isEmpty()) {
            return INVALID_PREFIX_ARGUMENT;
        }

        Prefix lastPrefix = lastPrefixArgument.getPrefix();
        SearchPrefixCommand lastPrefixCommand = convertPrefixToEnumType(lastPrefix);

        switch (lastPrefixCommand) {
        case NAME:
        case ADDRESS:
        case EMAIL:
        case PHONE:
        case TAG:
            return lastPrefix.getPrefix() + lastArgument;
        case NOTPREFIX:
        default:
            return INVALID_PREFIX_ARGUMENT;
        }
    }

    public void updateFilteredPersonList(String argsString) {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(argsString, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_TAG);
        String condition = argMultimap.getPreamble().toLowerCase();

        switch (condition) {
        case SearchCommand.AND_CONDITION:
        case SearchCommand.EMPTY_CONDITION:
            filteredPersons.setPredicate(new ContactContainsAllKeywordsPredicate(argMultimap));
            break;
        case SearchCommand.OR_CONDITION:
            filteredPersons.setPredicate(PREDICATE_SHOW_ALL_PERSONS);
            break;
        default:
            filteredPersons.setPredicate(PREDICATE_SHOW_NO_PERSON);
        }

    }

    public List<String> generateAutocompleteEntries(String argsWithoutLastPrefixArgument,
                                                    String lastPrefixArgument) {
        String autocompleteString = SearchCommand.COMMAND_WORD + argsWithoutLastPrefixArgument;

        String[] prefixArgument = lastPrefixArgument.split("/", 2);
        String prefix = prefixArgument[0] + "/";
        String argument = prefixArgument[1];

        switch (prefix) {
        case INDICATOR_NAME:
            return filteredPersons.stream()
                    .filter(person -> startWithIgnoreCase(person.getName().fullName, argument))
                    .limit(AUTOCOMPLETE_ENTRIES_LIMIT)
                    .map(person -> autocompleteString + prefix + person.getName().fullName)
                    .collect(Collectors.toList());
        case INDICATOR_ADDRESS:
            return filteredPersons.stream()
                    .filter(person -> startWithIgnoreCase(person.getAddress().value, argument))
                    .limit(AUTOCOMPLETE_ENTRIES_LIMIT)
                    .map(person -> autocompleteString + prefix + person.getAddress().value)
                    .collect(Collectors.toList());
        case INDICATOR_EMAIL:
            return filteredPersons.stream()
                    .filter(person -> startWithIgnoreCase(person.getEmail().value, argument))
                    .limit(AUTOCOMPLETE_ENTRIES_LIMIT)
                    .map(person -> autocompleteString + prefix + person.getEmail().value)
                    .collect(Collectors.toList());
        case INDICATOR_PHONE:
            return filteredPersons.stream()
                    .filter(person -> startWithIgnoreCase(person.getPhone().value, argument))
                    .limit(AUTOCOMPLETE_ENTRIES_LIMIT)
                    .map(person -> autocompleteString + prefix + person.getPhone().value)
                    .collect(Collectors.toList());
        case INDICATOR_TAG:
            return filteredPersons.stream()
                    .map(person -> person.getTags().stream()
                            .filter(tag -> startWithIgnoreCase(tag.tagName, argument))
                            .map(tag -> autocompleteString + prefix + tag.tagName)
                            .collect(Collectors.toList()))
                    .flatMap(Collection::stream)
                    .distinct()
                    .limit(AUTOCOMPLETE_ENTRIES_LIMIT)
                    .collect(Collectors.toList());
        default:
            return new ArrayList<>();
        }
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof AutocompleteManager)) {
            return false;
        }

        // state check
        AutocompleteManager other = (AutocompleteManager) obj;
        return soConnect.equals(other.soConnect);
    }
}
