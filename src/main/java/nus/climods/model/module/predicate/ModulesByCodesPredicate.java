package nus.climods.model.module.predicate;

import java.util.List;
import java.util.function.Predicate;

import nus.climods.model.module.Module;

/**
 * Tests that a Module's code matches one of the supplied codes
 */
public class ModulesByCodesPredicate implements Predicate<Module> {
    private final List<String> moduleCodes;

    public ModulesByCodesPredicate(List<String> moduleCodes) {
        this.moduleCodes = moduleCodes;
    }

    @Override
    public boolean test(Module module) {
        return moduleCodes.contains(module.getCode());
    }
}
