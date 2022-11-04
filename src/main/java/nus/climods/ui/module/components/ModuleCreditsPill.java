package nus.climods.ui.module.components;

import nus.climods.ui.common.Pill;

/**
 * A pill component for displaying a {@link nus.climods.model.module.Module} modular credits
 */
public class ModuleCreditsPill extends Pill {

    private static final String DEFAULT_MODULE_CREDITS_BG_COLOR = "#61AFEF";
    private static final String DEFAULT_MODULE_CREDITS_TEXT_COLOR = "#FFFFFF";
    private static final int DEFAULT_MODULE_CREDITS_FONT_SIZE = 13;

    /**
     * Creates an ModuleCreditsPill.
     *
     * @param moduleCreditsStr module credits
     */
    public ModuleCreditsPill(String moduleCreditsStr) {
        super(getModuleCreditPillText(moduleCreditsStr), DEFAULT_MODULE_CREDITS_BG_COLOR,
            DEFAULT_MODULE_CREDITS_TEXT_COLOR,
            DEFAULT_MODULE_CREDITS_FONT_SIZE);
        super.setDisable(true);
    }

    /**
     * Creates an ModuleCreditsPill.
     *
     * @param moduleCreditsStr module credits
     * @param bgColor background color
     * @param textColor text color
     * @param fontSize font size
     */
    ModuleCreditsPill(String moduleCreditsStr, String bgColor, String textColor, int fontSize) {
        super(getModuleCreditPillText(moduleCreditsStr), bgColor, textColor, fontSize);
    }

    private static String getModuleCreditPillText(String moduleCreditsStr) {
        return String.format("%s MCs", moduleCreditsStr);
    }
}
