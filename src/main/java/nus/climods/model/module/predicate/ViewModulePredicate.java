package nus.climods.model.module.predicate;

import java.util.function.Predicate;

import nus.climods.model.module.Module;

/**
 * Predicate to update module list to just one module
 */
public class ViewModulePredicate implements Predicate<Module> {
    private final String selectedModuleCode;

    public ViewModulePredicate(String selectedModuleCode) {
        this.selectedModuleCode = selectedModuleCode;
    }

    @Override
    public boolean test(Module module) {
        return module.getCode().equalsIgnoreCase(selectedModuleCode);
    }
}
