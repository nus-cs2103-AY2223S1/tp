package nus.climods.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import nus.climods.commons.core.Messages;
import nus.climods.model.Model;
import nus.climods.model.module.comparator.ModuleBestMatchKeywordComparator;
import nus.climods.model.module.predicate.ModuleContainsKeywordsPredicate;

/**
 * Find a module using a search phrase
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Find a module using a search phrase.\n"
        + "Parameters: KEYWORD [KEYWORD...]\n"
        + "KEYWORD can be a String or a valid RegEx\n"
        + "Example: " + COMMAND_WORD + " " + "Software Engineering";

    private final List<Pattern> searchRegexes;

    /**
     * Constructor for FindCommand class
     */
    public FindCommand(List<Pattern> searchRegexes) {
        this.searchRegexes = searchRegexes;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFilteredModuleList(new ModuleContainsKeywordsPredicate(searchRegexes),
            new ModuleBestMatchKeywordComparator(searchRegexes));

        return new CommandResult(
            String.format(Messages.MESSAGE_MODULES_LISTED_OVERVIEW, model.getFilteredModuleList().size()),
            COMMAND_WORD);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof FindCommand
            && searchRegexes.stream().map(Pattern::toString).collect(Collectors.toList())
            .equals(((FindCommand) other).searchRegexes.stream().map(Pattern::toString).collect(Collectors.toList())));
    }
}
