package nus.climods.ui.module.components;

import org.openapitools.client.model.SemestersEnum;

import nus.climods.ui.common.Pill;

public class SemesterPill extends Pill {

    private static final String DEFAULT_SEMESTER_BG_COLOR = "#C678DD";
    private static final String DEFAULT_SEMESTER_TEXT_COLOR = "#000000";
    private static final int DEFAULT_SEMESTER_FONT_SIZE = 13;

    public SemesterPill(SemestersEnum semester) {
        super(semester.toString(), DEFAULT_SEMESTER_BG_COLOR, DEFAULT_SEMESTER_TEXT_COLOR, DEFAULT_SEMESTER_FONT_SIZE);
    }

    public SemesterPill(SemestersEnum semester, String bgColor, String textColor, int fontSize) {
        super(semester.toString(), bgColor, textColor, fontSize);
    }
}
