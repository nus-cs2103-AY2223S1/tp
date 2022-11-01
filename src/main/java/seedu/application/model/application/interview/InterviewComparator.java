package seedu.application.model.application.interview;

import java.util.Comparator;

import seedu.application.model.application.Application;

/**
 * Creates a comparator for application with overridden compare method to compare two applications with their interview
 * fields. Applications are sorted by interview date first then interview time if date is the same.
 */
public class InterviewComparator implements Comparator<Application> {
    @Override
    public int compare(Application i1, Application i2) {
        if (!i1.hasInterview()) {
            return i2.hasInterview() ? 1 : 0;
        }
        if (!i2.hasInterview()) {
            return -1;
        }
        int compareDate = i1.getInterview().get().getInterviewDate().value
                .compareTo(i2.getInterview().get().getInterviewDate().value);
        int compareTime = i1.getInterview().get().getInterviewTime().value
                .compareTo(i2.getInterview().get().getInterviewTime().value);
        if (compareDate == 0) {
            return compareTime;
        } else {
            return compareDate;
        }
    }
}
