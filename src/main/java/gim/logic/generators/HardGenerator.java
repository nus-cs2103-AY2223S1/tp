package gim.logic.generators;

import gim.model.exercise.Name;

/**
 * Generator for easy workout session.
 */
public class HardGenerator implements Generator {
    private Name name;

    public HardGenerator(Name name) {
        this.name = name;
    }

    @Override
    public String suggest() {
        return "hard workout for " + name;
    }
}
