package seedu.address.model.statistics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum ChartType {

    BAR_CHART("bar"),
    LINE_CHART("line"),
    PIE_CHART("pie");

    //private static final Set<String> MALE_GENDERS = new HashSet<>(Arrays.asList("m", "male", "M", "Male"));
    //private static final Set<String> FEMALE_GENDERS = new HashSet<>(Arrays.asList("F", "Female", "f", "female"));
    private String chartType;


    ChartType(String chartType) {
        this.chartType = chartType;
    }

    @Override
    public String toString() {
        return this.chartType;
    }
}
