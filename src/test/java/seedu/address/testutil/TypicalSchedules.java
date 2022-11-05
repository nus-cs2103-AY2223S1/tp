package seedu.address.testutil;

import static seedu.address.testutil.TypicalModules.getTypicalModules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ProfNus;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.schedule.ClassType;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.module.schedule.Venue;
import seedu.address.model.module.schedule.Weekdays;



/**
 * A utility class containing a list of {@code Schedule} objects to be used in tests.
 */
public class TypicalSchedules {

    private static final Schedule schedule1 = new ScheduleBuilder().withModule("CS2107").withVenue(new Venue("I3-AUD"))
            .withClassType(ClassType.Lecture).withStartTime("16:00").withEndTime("18:00").withWeekday(Weekdays.Friday)
            .build();
    private static final Schedule schedule2 = new ScheduleBuilder().withModule("CS2106").withVenue(new Venue("I3-AUD"))
            .withClassType(ClassType.Lecture).withStartTime("10:00").withEndTime("12:00").withWeekday(Weekdays.Friday)
            .build();
    private static final Schedule schedule3 = new ScheduleBuilder().withModule("CS2103T").withVenue(new Venue("Zoom"))
            .withClassType(ClassType.Tutorial).withStartTime("11:00").withEndTime("12:00")
            .withWeekday(Weekdays.Wednesday).build();
    private static final Schedule schedule4 = new ScheduleBuilder().withModule("CS2102").withVenue(
            new Venue("SR_LT19")).withClassType(ClassType.Lecture).withStartTime("09:00")
            .withEndTime("12:00").withWeekday(Weekdays.Monday).build();
    private static final Schedule schedule5 = new ScheduleBuilder().withModule("CS2103T")
            .withVenue(new Venue("COM2-0218")).withClassType(ClassType.Tutorial).withStartTime("09:00")
            .withEndTime("10:00").withWeekday(Weekdays.Wednesday).build();

    public TypicalSchedules() {}
    /**
     * Returns an {@code ProfNus} with all the typical schedules.
     */
    public static ProfNus getTypicalProfNusWithSchedules() {
        ProfNus ab = new ProfNus();
        for (Module module: getTypicalModules()) {
            ab.addModule(module);
        }
        for (Schedule schedule: getTypicalSchedules()) {
            ab.addSchedule(schedule);
        }
        return ab;
    }

    public static List<Schedule> getTypicalSchedules() {
        return new ArrayList<>(Arrays.asList(schedule1, schedule2, schedule3, schedule4, schedule5));
    }

    public static ArrayList<ModuleCode> getTypicalModuleCodeFromTypicalSchedules() {
        ArrayList<ModuleCode> typicalModuleCodeList = new ArrayList<>();
        if (!getTypicalSchedules().isEmpty()) {
            for(Schedule schedule : getTypicalSchedules()) {
                ModuleCode curModuleCode = new ModuleCode(schedule.getModule());
                typicalModuleCodeList.add(curModuleCode);
            }
        }
        return typicalModuleCodeList;
    }

}
