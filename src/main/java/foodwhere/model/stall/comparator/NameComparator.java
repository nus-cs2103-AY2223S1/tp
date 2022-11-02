package foodwhere.model.stall.comparator;

import static foodwhere.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import foodwhere.model.stall.Stall;

/**
 * Creates a comparator class {@code NameComparator} that compares the stall by name lexicographically.
 */
public class NameComparator implements Comparator<Stall> {
    @Override
    public int compare(Stall s1, Stall s2) {
        requireAllNonNull(s1, s2);
        return s1.getName().fullName.compareToIgnoreCase(s2.getName().fullName);
    }

    /**
     * Returns true if both Comparators are the same.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof NameComparator;
    }
}
