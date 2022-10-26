package nus.climods.model.module.predicate;

import nus.climods.model.module.Module;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@Module's code} matches one of the supplied codes
 */
public class ModulesByCodesPredicate implements Predicate<Module> {
    private List<String> moduleCodes;

    public ModulesByCodesPredicate(List<String> moduleCodes) {
        this.moduleCodes = moduleCodes;
    }

    @Override
    public boolean test(Module module) {
        return moduleCodes.contains(module.getCode());
    }
}
