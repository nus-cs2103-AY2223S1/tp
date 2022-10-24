package seedu.phu.model.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.phu.model.ReadOnlyInternshipBook;
import seedu.phu.model.internship.Internship;

public class SampleDataUtilTest {
    private final SampleDataUtil sampleData = new SampleDataUtil();

    @Test
    public void sameInternships() {
        Internship[] internships = sampleData.getSampleInternships();
        ReadOnlyInternshipBook book = sampleData.getSampleInternshipBook();
        ObservableList<Internship> internshipList = book.getInternshipList();
        for (int i = 0; i < internships.length; i++) {
            assertTrue(internships[i].isSameInternship(internshipList.get(i)));
        }
    }
}
