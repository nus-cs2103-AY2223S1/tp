package soconnect.logic.autocomplete;

import java.util.List;

import soconnect.logic.parser.Prefix;
import soconnect.model.ReadOnlySoConnect;

/**
 * API of Autocomplete component.
 */
public interface Autocomplete {
    /**
     * Updates the soConnect data whenever a command is executed.
     *
     * @param soConnect The updated soConnect data.
     */
    void updateSoConnect(ReadOnlySoConnect soConnect);

    /**
     * Generates a list of autocomplete entries that matches the given user input.
     *
     * @param userInput The input from the user.
     * @return A list of matching autocomplete entries.
     */
    List<String> getAutocompleteEntries(String userInput);

    /**
     * Gets a valid argument if the given {@code userInput} is a search command input,
     * otherwise returns an invalid argument. It determines whether the {@code userInput} is a
     * search command input by checking if the {@code userInput} has the search command format.
     *
     * @param userInput The input from the user.
     * @return A valid argument if the {@code userInput} is a search command input, otherwise an invalid argument.
     */
    String getSearchCommandArguments(String userInput);

    /**
     * Gets the last prefix argument string from the given {@code argsString} if the prefix and argument are present,
     * otherwise returns an invalid prefix argument string.
     *   <br>examples:<pre>
     *       If{@code argsString} = "and n/alex p/900", {@code prefix} = "n/" and "p/", this method returns
     *       "p/900" as it is the last prefix with an argument.
     *       If {@code argsString} = "and n/alex p/", {@code prefix} = "n/" and "p/", this method returns
     *       and invalid prefix argument as the last prefix "p/" does not have an argument.
     *       If {@code argsString} = "and n/alex p/", {@code prefix} = "e/" and "a/", this method returns
     *       and invalid prefix argument as there is no matching prefix in the {@code argsString}.
     *   </pre>
     *
     * @param argsString The arguments string of the form: {@code preamble <prefix>value <prefix>value ...}
     * @param prefixes Prefixes to tokenize the arguments string with.
     * @return The string of the last prefix with argument if they are present, otherwise an invalid prefix argument.
     */
    String getLastPrefixArgument(String argsString, Prefix... prefixes);

    /**
     * Updates the filter of the filtered person list based on the preamble in the given {@code argsString}.
     *
     * @param argsString Arguments string of the form: {@code preamble <prefix>value <prefix>value...}
     */
    void updateFilteredPersonList(String argsString);

    /**
     * Generates a list of autocomplete entries with given {@code argsWithoutLastPrefixArgument} and
     * {@code lastPrefixArgument}.
     *
     * @param argsWithoutLastPrefixArgument The arguments string of the
     *                                      form:{@code preamble <prefix>value <prefix>value ...}
     * @param lastPrefixArgument The prefix argument string which will be autocompleted.
     * @return A list of autocomplete entries.
     */
    List<String> generateAutocompleteEntries(String argsWithoutLastPrefixArgument, String lastPrefixArgument);
}
