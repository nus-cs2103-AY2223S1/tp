package nus.climods.model.module.comparator;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import nus.climods.model.module.Module;

/**
 * A comparator to sort modules based on keyword matches
 */
public class ModuleBestMatchKeywordComparator implements Comparator<Module> {

    private final List<Pattern> searchRegexes;

    public ModuleBestMatchKeywordComparator(List<Pattern> searchRegexes) {
        this.searchRegexes = searchRegexes;
    }

    private long getKeywordNumMatches(Module module) {
        return searchRegexes.stream().filter(module::containsKeyword).count();
    }

    /**
     * Compares its two modules for order. Returns a negative integer, zero, or a positive integer as the first module
     * has more matches than the second, equal matches, or the first module has fewer matches than the second.
     *
     * @param o1 first module
     * @param o2 second module
     * @return a negative integer, zero, or a positive integer as the first module has more matches than the second,
     *         equal matches, or the first module has fewer matches than the second.
     */
    @Override
    public int compare(Module o1, Module o2) {
        return Long.compare(getKeywordNumMatches(o2), getKeywordNumMatches(o1));
    }
}
