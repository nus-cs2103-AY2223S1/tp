package seedu.phu.model.internship;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;

/**
 * A class representing the logic for the statistic bar.
 */
public class Statistic {
    private ObservableList<Internship> internships;

    /**
     * Constructs a Statistinc instance representing the
     * list of internships.
     *
     * @param internships an ObservableList.
     */
    public Statistic(ObservableList<Internship> internships) {
        requireNonNull(internships);
        this.internships = internships;
    }

    /**
     * Returns the width percentage of each region in the bar chart
     *
     * @return a Map instance
     */
    public Map<ApplicationProcess.ApplicationProcessState, Double> getWidth() {
        Map<ApplicationProcess.ApplicationProcessState, Double> statesWidth = new HashMap<>();
        Map<ApplicationProcess.ApplicationProcessState, Integer> statesNum = getGroupedData();
        int totalNum = internships.size();

        assert totalNum > 0;

        ApplicationProcess.ApplicationProcessState[] states = ApplicationProcess.ApplicationProcessState.values();

        for (ApplicationProcess.ApplicationProcessState s : states) {
            double width = (double) statesNum.get(s) / totalNum * 100;
            statesWidth.put(s, width);
        }

        return statesWidth;
    }

    /**
     * Returns the number of data entries for each group
     *
     * @return a Map instance
     */
    public Map<ApplicationProcess.ApplicationProcessState, Integer> getGroupedData() {
        Map<ApplicationProcess.ApplicationProcessState, Integer> map = new HashMap<>();
        ApplicationProcess.ApplicationProcessState[] states = ApplicationProcess.ApplicationProcessState.values();

        for (ApplicationProcess.ApplicationProcessState process : states) {
            int num = 0;

            for (Internship i : internships) {
                if (i.getApplicationProcess().equals(new ApplicationProcess(process.name()))) {
                    ++num;
                }
            }

            map.put(process, num);
        }

        return map;
    }
}
