package seedu.address.logic.commands.iteration;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ITERATION_IMAGEPATH_COLOR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITERATION_IMAGEPATH_FINALISE;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import seedu.address.model.StorageStub;

class StorageWithImageStub extends StorageStub {
    public static final String BASE_DIRECTORY = System.getProperty("user.dir") + "/src/test/data/images/";
    private final Map<Path, BufferedImage> images = new HashMap<>();

    public StorageWithImageStub() {
        try {
            images.put(Path.of(VALID_ITERATION_IMAGEPATH_FINALISE),
                    ImageIO.read(new File(VALID_ITERATION_IMAGEPATH_FINALISE)));
            images.put(Path.of(VALID_ITERATION_IMAGEPATH_COLOR),
                    ImageIO.read(new File(VALID_ITERATION_IMAGEPATH_COLOR)));
        } catch (IOException e) {
            System.out.println("Failed to initialise image stubs:" + e.getMessage());
        }
    }

    @Override
    public Path getRandomImagePath() {
        return Paths.get(BASE_DIRECTORY, images.size() + 1 + ".png");
    }

    public Path getCurrentPath() {
        return Paths.get(BASE_DIRECTORY, images.size() + ".png");
    }
    @Override
    public void saveImage(BufferedImage image, Path path) {
        images.put(path, image);
    }

    @Override
    public BufferedImage getImage(Path path) throws IOException {
        if (!images.containsKey(path)) {
            throw new IOException("Image not found");
        }
        return images.get(path);
    }
}
