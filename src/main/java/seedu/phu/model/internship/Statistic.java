package seedu.phu.model.internship;

import javafx.collections.ObservableList;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class Statistic {
    private ObservableList<Internship> internships;

    public Statistic(ObservableList<Internship> internships) {
        requireNonNull(internships);
        this.internships = internships;
    }

    public Map<ApplicationProcess.ApplicationProcessState, Double> getWidth() {
        Map<ApplicationProcess.ApplicationProcessState, Double> statesWidth = new HashMap<>();
        Map<ApplicationProcess.ApplicationProcessState, Integer> statesNum = getGroupedData();
        int totalNum = internships.size();

        assert totalNum > 0;

        ApplicationProcess.ApplicationProcessState[] states = ApplicationProcess.ApplicationProcessState.values();

        for(ApplicationProcess.ApplicationProcessState s : states) {
            double width = (double) statesNum.get(s) / totalNum * 100;
            System.out.println(width);
            statesWidth.put(s, width);
        }

        return statesWidth;
    }

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
