package seedu.hrpro.testutil;

import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_BUDGET_AMY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_BUDGET_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_DEADLINE_AMY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.hrpro.model.project.Project;

/**
 * A utility class containing a list of {@code Project} objects to be used in tests.
 */
public class TypicalProjects {

    public static final Project APPLE = new ProjectBuilder().withName("Alice Pauline")
            .withBudget("94351253").withDeadline("2022-01-01")
            .withTags("friends").build();
    public static final Project BANANA = new ProjectBuilder().withName("Benson Meier")
            .withBudget("98765432").withDeadline("2022-12-28")
            .withTags("owesMoney", "friends").build();
    public static final Project COCONUT = new ProjectBuilder().withName("Coconut")
            .withBudget("95352563").withDeadline("2022-01-10").build();
    public static final Project DANIEL = new ProjectBuilder().withName("Daniel Meier")
            .withBudget("87652533").withDeadline("2022-08-02")
            .withTags("friends").build();
    public static final Project ELLE = new ProjectBuilder().withName("Elle Meyer")
            .withBudget("9482224").withDeadline("2022-10-05").build();
    public static final Project FIONA = new ProjectBuilder().withName("Fiona Kunz")
            .withBudget("9482427").withDeadline("2023-09-09").build();
    public static final Project GEORGE = new ProjectBuilder().withName("George Best")
            .withBudget("9482442").withDeadline("2021-10-05").build();

    // Manually added
    public static final Project HOON = new ProjectBuilder().withName("Hungry")
            .withBudget("8482424").withDeadline("2021-11-24").build();
    public static final Project IDA = new ProjectBuilder().withName("Ida Mueller")
            .withBudget("8482131").withDeadline("2016-05-21").build();

    // Manually added - Project's details found in {@code CommandTestUtil}
    public static final Project AMY = new ProjectBuilder().withName(VALID_NAME_AMY).withBudget(VALID_BUDGET_AMY)
            .withDeadline(VALID_DEADLINE_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Project BOB = new ProjectBuilder().withName(VALID_NAME_BOB).withBudget(VALID_BUDGET_BOB)
            .withDeadline(VALID_DEADLINE_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_HUNGRY = "Hungry"; // A keyword that matches HUNGRY

    private TypicalProjects() {} // prevents instantiation


    /**
     * Returns an {@code HrPro} with all the typical projects.
     */
    /*
    public static HrPro getTypicalHrProWithProjectOnly() {
        HrPro ab = new HrPro();
        for (Project project : getTypicalProjects()) {
            ab.addProject(project);
        }
        return ab;
    }
    */

    public static List<Project> getTypicalProjects() {
        return new ArrayList<>(Arrays.asList(APPLE, BANANA, COCONUT, DANIEL, ELLE, FIONA, GEORGE));
    }
}
