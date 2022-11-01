package seedu.application.model.application.interview;

import java.util.Comparator;
import java.util.Optional;

import seedu.application.model.application.Application;

/**
 * Creates a comparator for application with overriden compare method to compare two applications with their interview
 * fields. Applications are sorted by interview date first then interview time if date is the same.
 */
public class InterviewComparator implements Comparator<Application> {

    private static int DUMMY_INT = 0;

    @Override
    public int compare(Application a1, Application a2) {
        Optional<Interview> i1 = a1.getInterview();
        Optional<Interview> i2 = a2.getInterview();
        if (i1.isPresent() && i2.isPresent()) {
            int compareDate = i1.get().getInterviewDate().value
                    .compareTo(i2.get().getInterviewDate().value);
            int compareTime = i1.get().getInterviewTime().value
                    .compareTo(i2.get().getInterviewTime().value);
            if (compareDate == 0) {
                return compareTime;
            } else {
                return compareDate;
            }
        } else {
            assert false : "Interview should exist while using comparator to sort";
            return DUMMY_INT;
        }
    }
}
