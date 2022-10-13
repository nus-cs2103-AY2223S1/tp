package seedu.phu.commons.core.index;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.phu.commons.exceptions.IllegalIndexException;
import seedu.phu.logic.parser.ParserUtil;
import seedu.phu.logic.parser.exceptions.ParseException;
import seedu.phu.model.internship.Internship;
import seedu.phu.model.internship.UniqueInternshipList;

/**
 * Represents a unique collection of Index objects.
 */
public class Indexes {
    private Set<Index> indexes;

    /**
     * Constructs a new Indexes object given an array of strings.
     *
     * @param unParsedIndexes The array of strings that is going to be parsed.
     * @throws ParseException If there is a string from unParsedIndexes that can't be parsed.
     */
    public Indexes(String[] unParsedIndexes) throws ParseException {
        indexes = new HashSet<>();

        for (String arg : unParsedIndexes) {
            indexes.add(ParserUtil.parseIndex(arg));
        }
    }

    public Indexes(Set<Index> indexes) {
        this.indexes = indexes;
    }

    public UniqueInternshipList getAllInternshipsFromIndexes(List<Internship> internshipList)
            throws IllegalIndexException {
        UniqueInternshipList selectedInternships = new UniqueInternshipList();

        for (Index selectedIndex : indexes) {
            if (selectedIndex.getZeroBased() >= internshipList.size()) {
                throw new IllegalIndexException();
            }

            Internship currentInternship = internshipList.get(selectedIndex.getZeroBased());
            selectedInternships.add(currentInternship);
        }

        return selectedInternships;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Indexes // instanceof handles nulls
                && indexes.equals(((Indexes) other).indexes)); // state check
    }
}
