package nus.climods.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import nus.climods.commons.core.Messages;
import nus.climods.model.Model;
import nus.climods.model.module.predicate.ModuleContainsKeywordsPredicate;

/**
 * Find a module using a search phrase
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Find a module using a search phrase. "
        + "Parameters: "
        + "KEYWORD [KEYWORD...]"
        + "Example: " + COMMAND_WORD + " " + "Software Engineering";

    private final List<String> searchTokens;
    private final List<Pattern> searchRegexes;

    public FindCommand(List<String> searchTokens) {
        this.searchTokens = searchTokens;
        this.searchRegexes = generateSearchRegexes(searchTokens);
    }

    private static List<Pattern> generateSearchRegexes(List<String> searchTokens) {
        return searchTokens.stream().map(token -> Pattern.compile(token, Pattern.CASE_INSENSITIVE))
            .collect(Collectors.toList());
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFilteredModuleList(new ModuleContainsKeywordsPredicate(searchRegexes));

        return new CommandResult(
            String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, model.getFilteredModuleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof FindCommand
            && searchTokens.equals(((FindCommand) other).searchTokens));
    }
}
