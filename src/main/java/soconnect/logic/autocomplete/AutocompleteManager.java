package soconnect.logic.autocomplete;

import static soconnect.commons.util.StringUtil.startWithIgnoreCase;
import static soconnect.logic.parser.ArgumentTokenizer.PrefixArgument;
import static soconnect.logic.parser.ArgumentTokenizer.tokenizeToList;
import static soconnect.logic.parser.CliSyntax.*;
import static soconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static soconnect.model.Model.PREDICATE_SHOW_NO_PERSON;
import static soconnect.model.person.search.SearchPrefix.SearchPrefixCommand;
import static soconnect.model.person.search.SearchPrefix.convertPrefixToEnumType;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javafx.collections.transformation.FilteredList;
import soconnect.commons.core.LogsCenter;
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
    private static final Logger logger = LogsCenter.getLogger(AutocompleteManager.class);

    private static final Pattern SEARCH_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private static final String INVALID_SEARCH_COMMAND_ARGUMENT = "";

    private static final String INVALID_PREFIX_ARGUMENT = "";

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
        userInput = userInput.trim();
        String searchCommandArguments = this.getSearchCommandArguments(userInput);
        if (searchCommandArguments.equals(INVALID_SEARCH_COMMAND_ARGUMENT)) {
            return new ArrayList<>();
        }

        String lastPrefixArgumentInput = getLastPrefixArgumentInput(searchCommandArguments, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        if (lastPrefixArgumentInput.equals(INVALID_PREFIX_ARGUMENT)) {
            return new ArrayList<>();
        }

        String argsWithoutLastPrefixArgument = searchCommandArguments.substring(0, searchCommandArguments.length() -
                lastPrefixArgumentInput.trim().length());

        updateFilteredPersonList(argsWithoutLastPrefixArgument);

        return generateAutocompleteEntries(argsWithoutLastPrefixArgument, lastPrefixArgumentInput);
    }

    @Override
    public String getSearchCommandArguments(String userInput) {
        Matcher matcher = SEARCH_COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            return INVALID_SEARCH_COMMAND_ARGUMENT;
        }

        String commandWord = matcher.group("commandWord");
        String SearchCommandArguments = matcher.group("arguments");
        return commandWord.equals(SearchCommand.COMMAND_WORD)
                ? SearchCommandArguments : INVALID_SEARCH_COMMAND_ARGUMENT;
    }

    public List<String> generateAutocompleteEntries(String argsWithoutLastPrefixArgument,
                                                    String lastPrefixArgumentInput) {
        String autocompleteString = SearchCommand.COMMAND_WORD + " " + argsWithoutLastPrefixArgument;

        String[] prefixArgument = lastPrefixArgumentInput.split("/", 2);
        String prefix = prefixArgument[0] + "/";
        String argument = prefixArgument[1];

        switch (prefix) {
        case INDICATOR_NAME:
            return filteredPersons.stream()
                    .filter(person -> startWithIgnoreCase(person.getName().fullName, argument))
                    .map(person -> autocompleteString + prefix + person.getName().fullName)
                    .collect(Collectors.toList());
        case INDICATOR_ADDRESS:
            return filteredPersons.stream()
                    .filter(person -> startWithIgnoreCase(person.getAddress().value, argument))
                    .map(person -> autocompleteString + prefix + person.getAddress().value)
                    .collect(Collectors.toList());
        case INDICATOR_EMAIL:
            return filteredPersons.stream()
                    .filter(person -> startWithIgnoreCase(person.getEmail().value, argument))
                    .map(person -> autocompleteString + prefix + person.getEmail().value)
                    .collect(Collectors.toList());
        case INDICATOR_PHONE:
            return filteredPersons.stream()
                    .filter(person -> startWithIgnoreCase(person.getPhone().value, argument))
                    .map(person -> autocompleteString + prefix + person.getPhone().value)
                    .collect(Collectors.toList());
        case INDICATOR_TAG:
            return filteredPersons.stream()
                    .filter(person -> person.getTags().stream()
                            .anyMatch(tag -> startWithIgnoreCase(tag.tagName, argument)))
                    .map(person -> autocompleteString + prefix + person.getTags().stream()
                            .filter(tag -> startWithIgnoreCase(tag.tagName, argument))
                                    .findFirst())
                    .collect(Collectors.toList());
        default:
            return new ArrayList<>();
        }
    }

    public String getLastPrefixArgumentInput(String argsString, Prefix... prefixes) {
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

    public void updateFilteredPersonList(String argsWithoutLastPrefixArgument) {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(argsWithoutLastPrefixArgument, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_TAG);
        String condition = argMultimap.getPreamble().toLowerCase();

        logger.info("condition: " + condition );
        logger.info("first few arg: "+ argsWithoutLastPrefixArgument);
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
