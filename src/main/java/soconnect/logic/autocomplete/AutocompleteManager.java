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
    public static final String INVALID_SEARCH_COMMAND_ARGUMENT = "";

    public static final String INVALID_PREFIX_ARGUMENT = "";

    private static final Pattern SEARCH_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    // In the autocomplete display box, it will include the original search hence it
    // will show a maximum of AUTOCOMPLETE_ENTRIES_LIMIT + 1 autocomplete entries
    private static final int AUTOCOMPLETE_ENTRIES_LIMIT = 10;

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

        String argsWithoutLastPrefixArgument = searchCommandArguments.substring(0, searchCommandArguments.length()
                - lastPrefixArgument.length());

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
        String searchCommandArguments = matcher.group("arguments");
        return commandWord.equals(SearchCommand.COMMAND_WORD)
                ? searchCommandArguments : INVALID_SEARCH_COMMAND_ARGUMENT;
    }

    @Override
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

    @Override
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

    @Override
    public List<String> generateAutocompleteEntries(String argsWithoutLastPrefixArgument,
                                                    String lastPrefixArgument) {
        String autocompleteString = SearchCommand.COMMAND_WORD + argsWithoutLastPrefixArgument;

        String[] lastPrefixArgumentList = lastPrefixArgument.split("/", 2);
        String lastPrefix = lastPrefixArgumentList[0] + "/";
        String lastArgument = lastPrefixArgumentList[1];

        switch (lastPrefix) {
        case INDICATOR_NAME:
            return autocompleteForName(autocompleteString, lastArgument);
        case INDICATOR_ADDRESS:
            return autocompleteForAddress(autocompleteString, lastArgument);
        case INDICATOR_EMAIL:
            return autocompleteForEmail(autocompleteString, lastArgument);
        case INDICATOR_PHONE:
            return autocompleteForPhone(autocompleteString, lastArgument);
        case INDICATOR_TAG:
            return autocompleteForTag(autocompleteString, lastArgument);
        default:
            return new ArrayList<>();
        }
    }


    private List<String> autocompleteForName(String autocompleteString, String lastArgument) {
        return filteredPersons.stream()
                .filter(person -> startWithIgnoreCase(person.getName().fullName, lastArgument))
                .limit(AUTOCOMPLETE_ENTRIES_LIMIT)
                .map(person -> autocompleteString + PREFIX_NAME + person.getName().fullName)
                .collect(Collectors.toList());
    }

    private List<String> autocompleteForAddress(String autocompleteString, String lastArgument) {
        return filteredPersons.stream()
                .filter(person -> startWithIgnoreCase(person.getAddress().value, lastArgument))
                .limit(AUTOCOMPLETE_ENTRIES_LIMIT)
                .map(person -> autocompleteString + PREFIX_ADDRESS + person.getAddress().value)
                .collect(Collectors.toList());
    }

    private List<String> autocompleteForEmail(String autocompleteString, String lastArgument) {
        return filteredPersons.stream()
                .filter(person -> startWithIgnoreCase(person.getEmail().value, lastArgument))
                .limit(AUTOCOMPLETE_ENTRIES_LIMIT)
                .map(person -> autocompleteString + PREFIX_EMAIL + person.getEmail().value)
                .collect(Collectors.toList());
    }

    private List<String> autocompleteForPhone(String autocompleteString, String lastArgument) {
        return filteredPersons.stream()
                .filter(person -> startWithIgnoreCase(person.getPhone().value, lastArgument))
                .limit(AUTOCOMPLETE_ENTRIES_LIMIT)
                .map(person -> autocompleteString + PREFIX_PHONE + person.getPhone().value)
                .collect(Collectors.toList());
    }

    private List<String> autocompleteForTag(String autocompleteString, String lastArgument) {
        return filteredPersons.stream()
                .map(person -> person.getTags().stream()
                        .filter(tag -> startWithIgnoreCase(tag.tagName, lastArgument))
                        .map(tag -> autocompleteString + PREFIX_TAG + tag.tagName)
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .distinct()
                .limit(AUTOCOMPLETE_ENTRIES_LIMIT)
                .collect(Collectors.toList());
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
