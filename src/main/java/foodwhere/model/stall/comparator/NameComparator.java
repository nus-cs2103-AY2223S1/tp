package foodwhere.model.stall.comparator;

import java.util.Comparator;

import foodwhere.model.stall.Stall;

/**
 * Creates a comparator class {@code NameComparator} that compares the stall by name lexicographically.
 */
public class NameComparator implements Comparator<Stall> {
    @Override
    public int compare(Stall s1, Stall s2) {
        return s1.getName().fullName.compareTo(s2.getName().fullName);
    }
}
