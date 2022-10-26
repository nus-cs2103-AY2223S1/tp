package nus.climods.ui.module.components;

import nus.climods.ui.common.Pill;

/**
 * A pill component for displaying a {@link nus.climods.model.module.Module} modular credits
 */
public class LessonPill extends Pill {
    // TODO: Explore giving different BG_COLOR for different lesson enums
    private static final String DEFAULT_BG_COLOR = "#61AFEF";
    private static final String DEFAULT_TEXT_COLOR = "#FFFFFF";
    private static final int DEFAULT_FONT_SIZE = 13;

    /**
     * Creates an ModuleCreditsPill.
     *
     * @param moduleCreditsStr module credits
     */
    public LessonPill(String moduleCreditsStr) {
        super(moduleCreditsStr, DEFAULT_BG_COLOR,
                DEFAULT_TEXT_COLOR,
                DEFAULT_FONT_SIZE);
    }

    /**
     * Creates an ModuleCreditsPill.
     *
     * @param moduleCreditsStr module credits
     * @param bgColor background color
     * @param textColor text color
     * @param fontSize font size
     */
    LessonPill(String moduleCreditsStr, String bgColor, String textColor, int fontSize) {
        super(moduleCreditsStr, bgColor, textColor, fontSize);
    }
}
