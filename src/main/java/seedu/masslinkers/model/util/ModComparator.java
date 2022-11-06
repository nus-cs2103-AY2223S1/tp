package seedu.masslinkers.model.util;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.masslinkers.model.student.Mod;

//@@author jonasgwt
/**
 * Comparator to compare order for mods.
 */
public class ModComparator implements Comparator<Mod> {
    /**
     * Compares a mod with another for order. Returns a
     * negative integer, zero, or a positive integer if a mod is less
     * than, equal to, or greater than the other.
     *
     * @param mod1 the object to be compared.
     * @param mod2 the other object to be compared.
     * @return a negative integer, zero, or a positive integer if a mod
     *         is less than, equal to, or greater than the other.
     */
    public int compare(Mod mod1, Mod mod2) {
        requireNonNull(mod1);
        requireNonNull(mod2);

        if (mod1.getModStatus()) {
            if (mod2.getModStatus()) {
                return mod1.getModName().compareTo(mod2.getModName());
            } else {
                return 1;
            }
        } else {
            if (mod2.getModStatus()) {
                return -1;
            } else {
                return mod1.getModName().compareTo(mod2.getModName());
            }
        }
    }
}
