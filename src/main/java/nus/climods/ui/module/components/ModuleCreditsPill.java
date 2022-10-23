package nus.climods.ui.module.components;

import nus.climods.ui.common.Pill;

public class ModuleCreditsPill extends Pill {

    private static final String DEFAULT_MODULE_CREDITS_BG_COLOR = "#61AFEF";
    private static final String DEFAULT_MODULE_CREDITS_TEXT_COLOR = "#FFFFFF";
    private static final int DEFAULT_MODULE_CREDITS_FONT_SIZE = 13;

    public ModuleCreditsPill(String moduleCreditsStr) {
        super(getModuleCreditPillText(moduleCreditsStr), DEFAULT_MODULE_CREDITS_BG_COLOR,
            DEFAULT_MODULE_CREDITS_TEXT_COLOR,
            DEFAULT_MODULE_CREDITS_FONT_SIZE);
    }

    ModuleCreditsPill(String moduleCreditsStr, String bgColor, String textColor, int fontSize) {
        super(getModuleCreditPillText(moduleCreditsStr), bgColor, textColor, fontSize);
    }

    private static String getModuleCreditPillText(String moduleCreditsStr) {
        return String.format("%s MCs", moduleCreditsStr);
    }
}
