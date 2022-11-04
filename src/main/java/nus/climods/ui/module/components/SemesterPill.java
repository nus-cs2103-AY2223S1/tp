package nus.climods.ui.module.components;

import org.openapitools.client.model.SemestersEnum;

import nus.climods.ui.common.Pill;

/**
 * A pill component for displaying a {@link nus.climods.model.module.Module} semesters
 */
public class SemesterPill extends Pill {

    private static final String DEFAULT_SEMESTER_BG_COLOR = "#C678DD";
    private static final String DEFAULT_SEMESTER_TEXT_COLOR = "#000000";
    private static final int DEFAULT_SEMESTER_FONT_SIZE = 13;

    /**
     * Creates a SemesterPill.
     *
     * @param semester semester enum
     */
    public SemesterPill(SemestersEnum semester) {
        super(semester.toString(), DEFAULT_SEMESTER_BG_COLOR, DEFAULT_SEMESTER_TEXT_COLOR, DEFAULT_SEMESTER_FONT_SIZE);
        disableFocus();
    }

    /**
     * Creates a SemesterPill.
     *
     * @param semester semester enum
     * @param bgColor background color
     * @param textColor text color
     */
    public SemesterPill(SemestersEnum semester, String bgColor, String textColor) {
        super(semester.toString(), bgColor, textColor, DEFAULT_SEMESTER_FONT_SIZE);
        disableFocus();
    }

    /**
     * Creates a SemesterPill.
     *
     * @param semester semester enum
     * @param bgColor background color
     * @param textColor text color
     * @param fontSize font size
     */
    public SemesterPill(SemestersEnum semester, String bgColor, String textColor, int fontSize) {
        super(semester.toString(), bgColor, textColor, fontSize);
        disableFocus();
    }

    private void disableFocus() {
        super.setDisable(true);
    }
}
