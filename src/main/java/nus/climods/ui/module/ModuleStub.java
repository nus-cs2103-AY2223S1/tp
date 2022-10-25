package nus.climods.ui.module;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stub of Module for making ViewLesson's UI
 */
public class ModuleStub {

    /* Should be <Enum, T> Also i am assuming that the lessons come sorted according to their code E.g. t01, t02, t03*/
    private final HashMap<String, List<String []>> lessonMap = new HashMap<>() {{
            put("Lecture", Arrays.asList(new String[]{"L01", "Monday 1300-1500"},
                    new String[]{"L02", "Friday 1400 - 1600"}));
            put("Tutorial", Arrays.asList(new String[]{"T01", "Monday 1300-1500"},
                    new String[]{"T02", "Friday 1400 - 1600"},
                new String[]{"T03", "Monday 1300-1500"}, new String[]{"T04", "Friday 1400 - 1600"},
                new String[]{"T05", "Monday 1300-1500"}, new String[]{"T06", "Friday 1400 - 1600"}));
            put("Lab", Arrays.asList(new String[]{"LAB01", "Monday 1300-1500"},
                    new String[]{"LAB02", "Friday 1400 - 1600"}));
            put("Seminar", Arrays.asList(new String[]{"S01", "Monday 1300-1500"},
                    new String[]{"S02", "Friday 1400 - 1600"}));
        }};

    /**
     * Returns the module title.
     *
     * @return module title
     */
    public String getTitle() {
        return "Discrete Structures";
    }

    /**
     * Returns the module code.
     *
     * @return module code
     */
    public String getCode() {
        return "CS1231S";
    }

    public Map<String, List<String []>> getLessons() { return lessonMap; }

}
