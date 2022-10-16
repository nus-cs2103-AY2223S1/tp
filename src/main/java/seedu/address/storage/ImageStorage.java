package seedu.address.storage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.student.Student;

/**
 * Encapsulate the Image Storage of application.
 */
public class ImageStorage {
    private static byte[] imageBytes = Base64.getDecoder().decode(
            "/9j/4AAQSkZJRgABAQAAAQABAAD//gAtQ29udmVydGVkIGZyb20"
            + "gIFdlYlAgdG8gSlBHIHVzaW5nIGV6Z2lmLmNvbf/bAEMABQMEBAQDBQQEBAUFBQYHDAgHBwcHDwsLCQwRDxISEQ8RERMWHBcTFBoVER"
            + "EYIRgaHR0fHx8TFyIkIh4kHB4fHv/bAEMBBQUFBwYHDggIDh4UERQeHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4eHh4e"
            + "Hh4eHh4eHh4eHh4eHv/AABEIAVQBVAMBIgACEQEDEQH/xAAcAAEBAAIDAQEAAAAAAAAAAAAABgUHAQIEAwj/xABDEAEAAgECAQcJBw"
            + "IDBgcAAAAAAQIDBBEFBhYhMUFV0RIiUWFxgZGToRMjMkJSscEzYhRTchUkorLh8DQ1Y3OCg5L/xAAWAQEBAQAAAAAAAAAAAAAAAAAA"
            + "AQL/xAAYEQEBAQEBAAAAAAAAAAAAAAAAAREhMf/aAAwDAQACEQMRAD8A/UYDTIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADA8W5TaXTTbFo6xqssdE232x1n29vu+IM9EejseLVcX4ZpZmubW4YtH5a"
            + "z5c/CN0PxDimv18z/idTeaf5dfNpHuj+XiiIiNojaFxNW2TlTwus+ZXVZPZjiP3l845WaDfp02rj3V8UcLiauMPKbhN587Jnxf68U7f"
            + "GN2S0mt0erj/AHXVYc0+it+n4dbWp2xPbHVPbCYa2iIPh3KDiOj2rbJ/icUfkyzvMey3XH1VfCOM6LiUeTitOPNt04b/AIvd6fcljWs"
            + "iAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA6Z82LBhvmz5K48dI3ta3VDta1a1m17RWtYmZmZ6Ij0oTlFxe/E9R5GOZrpMc/d1/VP6p/j0"
            + "Ehbjvx7jufiE2wYPKw6T9PVbJ67er1MODbAAAAAAARMxaLVmYmJ3iYnaYkAVfJ7lF9rauk4leIvPRTPPRFvVb1+tTTG0tW9fRPUrOSXG"
            + "bZPJ4dq773iNsF5npt/bPr9HwZsalUwCKAAAAAAAAAAAAAAAAAAAAAAAAAAA+Ou1NNHo82qydNcVZtt6Z7I987QCd5a8TmNuF4bdcRbP"
            + "MfGK/zPuSztmy5M+a+bNbysmS02tPpmXVqM0AVAAAAAAAAArMxMWrM1mJ3iYnpifSANgcnuJRxLh8ZLzH2+OfIzRHp7J9/X8WRQPJjXf"
            + "4Hi2Ob22w5vu8no6eqfdP8r7qnaWLG5QAAAAAAAAAAAAAAAAAAAAAAAAABN8u9V5On0+irPTktOS/sr0R9Z+ikQnK7N9tx7NXfeMNa"
            + "4490bz9ZlYlYkBpkAAAAAAAAAAABxMbxtLYvAtVOt4RptRad7zTyb/6q9E/tv72u1byDzzbSarTTP8ATyRePZaNp+sJVikAZaAAAAA"
            + "AAAAAAAAAAAAAAAAAAAcx0zEemdmteKZPteJ6vLP5s95/4pbKr+OvthrDP/Xy7/5lv+aViV0AaZAAAAAAAAAAAAFByEvtxTPj7L4N/h"
            + "aPFPs3yJ/88n/2L/wlWLYBloAAAAAAAAAAAAAAAAAAAAAAABzXotE+iWtOIU+z4hqsf6c14/4pbKQXKvD9jx/U9HRkmMsf/KPGJWJWL"
            + "AaZAAAAAAAAAAAAGf5C034tmv2U08/W0QwCr5BYdsOs1Ex+K1cce6Jmf3hKs9UwDLQAAAAAAAAAAAAAAAAAAAAAAAAl+Xml6NNrax"
            + "1b4b/vX+VQ8vFtJXX8OzaSdom9fMmey0dMT8SFa4C0Wraa3rNbVmYtE9kx1wNsAAAAAAAAAAAAOJ6I3nqhsHk3pp0nBdPjtG17x9pf"
            + "226f22RvAdD/ALQ4ph08xvjifLy/6I6/j0R72w5ZrUAEUAAAAAAAAAAAAAAAAAAAAAAAAABIctOGzh1McRxV+7yztl2/Lf0+/wDf2p"
            + "1s7UYcWowXwZqRfHkrNbVnthr/AI1w3LwzWThvvbHbpxZP1R4x2tSs2PCAqAAAAAAAADiZ2jeXKh5JcHnUZK8R1NPuKTvirMf1LR2"
            + "+yPrKDMcleGzoNB9pmrtqM+1rxPXWvZX+Z9rMEjLYAAAAAAAAAAAAAAAAAAAAAAAAAAAA+HENHp9dpbabU08qlumJjrrPpieyX3Aa9"
            + "4zwrU8LzeTljy8Np2x5Yjot6p9E+p4Gz8uPHlxWxZcdb47Rtato3iYS/FuS07zl4ZeJjr+xyW/5bfxPxWVmxMDvqcObTZZxajDfDk"
            + "j8t67T/wBXRpAAAHAOXHrl6+HcO1vELbaXBa9e3JPRSPerOD8nNLo5rm1Mxqc8dMbx5lZ9Uds+uUtWRiOTvJ++qmuq19LU0/XXHPRbJ7"
            + "fRX91lERWsVrERERtERG0RBPTIza1IAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABMxFZtMxFY65noiPexur47wrTTMW1dclo/Liibz9Oj6"
            + "gyQmdRyuxRvGm0OS/onLeK/SN3gzcqeKX/p102GP7cflT8ZkxNWWfFhz45xZ8ePLSfy3rEx9WJ1PJjhmad8ePNp5/8ASvvHwndL5eN8X"
            + "yfi4hmj1U2r+0PNk1utyf1Nbqbe3NbxXDVHk5Hzv91r7xH9+Dwl0jkdn36ddTb1YLeKanJknry5J9t58XHl3/Xf/wDUr1OKzDyPxRP3"
            + "2tz39VMUV/fdkdJwDhWlmLRpYy2jqtmmb/Sej6ISufPT8OfNX2ZLR/L74+J8Sx/g4hqq/wD2zP7plNjY8bbREbREdUR1QILFyh4xjnp"
            + "1n2kejJjrb+HuwcrdZXoz6TT5Y9NZmk/zBi6rxgdNyq4fk2jPi1Gnn07RePjHT9GW0ev0Ws/8Lq8OWf01t53wnpTF16An1gAAAAAAA"
            + "AAAAAAAAAAAAAAAAAA8nEuI6Th2Ly9Vl8mZjzaR03t7I/nqSfFOUmt1e+PTb6TDPR5s+fPtt2e4kNVXEeK6Dh8banURGT/Lp51/h2"
            + "e/ZO6/lXqb710Wnpgr+vJ59vh1R9U72zPbPX6xrGdfbV6vVay3larUZc0/323iPd1PiCoAAAAAAAAAAONunftjqlyAyOh43xPR7Rj1V"
            + "slI/Jl8+Pr0x7pZ7h/KrS5Nqa3DbT2/XTz6eMfVICYutnYM2LPijLgy0y456rUtvDu1no9VqNHm+10ua+G/bNZ6/bHVPvU/CeVOO+2L"
            + "iVIxW6vtqR5s+2Oz3Ji6pRxS1clK3pat6WjetqzvEx6pcooAAAAAAAAAAAAAAAADi9q0pa97VrSsb2tadoiPTIOU5xzlLTDNtPw2a5M"
            + "kdFs09Na/6fTPr6vaxvKLj19d5Wl0c2x6Xqtbqtl8K+rt7WDWRLXbLkyZstsubJbJktO9rWneZdQaZAAAAAAAAAAAAAAAAAAAAe7hPF"
            + "dXwzJ9xfysUzvbFb8NvCfXC14RxTS8TxTbBaa5Kx5+K34q+Metrx3wZcuDNXNgyWx5KTvW1Z6YSxZWzhh+T3G8fEqxgzRXHq6xvNY6s"
            + "kemv8wzDLQAAAAAAAAAAAAABMxEbzMREdcz2IjlNxqeIZJ02mtMaSk9f+bPpn1eiPe9/LLisxvwvT26431Fo+lP5n4JZZEtAGmQAAA"
            + "AAAAAAAAAAAAAAAAAAAAHNLWpet6WtS9Z3ras7TE+mFzyb4xXiWCcWbya6vHHnRHRF4/VH8whX002fLptRj1GC/kZcc71n/vsSxZWzR"
            + "5OE67FxHQ01OOPJmei9N/wW7Y/77HrZaAAAAAAAAAAHj41r68O4dk1M7TePNx1ntvPV4+57EXy11k5+J10lZ+700bT67z0z8I2j4kKw"
            + "d7Wve172m17TM2tPXMz1y4BtgAAAAAAAAAAAAAAAAAAAAAAAAAAABleS/Ef8BxKK5LbafPtTJv1RPZb3ftK9nff0NWTG8bT2r/kzrZ1"
            + "3CMV7zvlxfdZJ9Mx1T742ZrUZMBFAAAAAAAAdc2WuHDfNf8ABjrN7eyI3ayy5L5st82Sd75LTe0+uZ3XfKzLOLgGp2nacnk4/jMb/T"
            + "dBLEoA0yAAAAAAAAAAAAAAAAAAAAAAAAAAAAKDkNqZx8RzaWZ83Nj8qI/ur/0mfgn3s4Fm+w41o8u+0RmrWfZPRP7pVjYobbdAy0AAA"
            + "AAAAAx/H+H34noa6amauHbJF5tas232iejo9rB80c3eGH5NvFWBpiT5o5u8MPybeJzRzd4Yfk28VYLqYk+aObvDD8m3ic0c3eGH5NvF"
            + "WBpiT5o5u8MPybeJzRzd4Yfk28VYGmJPmjm7ww/Jt4nNHN3hh+TbxVgaYk+aObvDD8m3ic0c3eGH5NvFWBpiT5o5u8MPybeJzRzd4Yf"
            + "k28VYGmJPmjm7ww/Jt4nNHN3hh+TbxVgaYk+aObvDD8m3ic0c3eGH5NvFWBpiT5o5u8MPybeJzRzd4Yfk28VYGmJPmjm7ww/Jt4nNHN"
            + "3hh+TbxVgaYk+aObvDD8m3ic0c3eGH5NvFWBpiT5o5u8MPybeJzRzd4Yfk28VYGmJPmjm7ww/Jt4nNHN3hh+TbxVgaYk+aObvDD8m3i"
            + "c0c3eGH5NvFWBpiT5o5u8MPybeJzRzd4Yfk28VYGmJPmjm7ww/Jt4uack89b1vHEMW9bRP9K3ZO/pVYm1cczO9pn0y4AAAAAAAAAAAA"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAH//2Q==");

    private static final Image defaultImage = new Image(new ByteArrayInputStream(imageBytes));
    private static Stage primaryStage;

    private static String getImagePath(Student student) {
        return Paths.get(
                System.getProperty("user.dir"),
                "images", student.getStudentId().toString() + ".jpg"
        ).toString();
    }

    private static File getImageFile(Student student) {
        return new File(getImagePath(student));
    }

    /**
     * Returns the Image of a Student if the image file exists, otherwise return default image.
     * @param student
     * @return The Image of the student.
     */
    public static Image getStudentProfileImage(Student student) {
        File imageFile = getImageFile(student);
        return imageFile.exists() ? new Image(imageFile.toURI().toASCIIString()) : defaultImage;
    }

    /**
     * Open the file chooser to select file to upload.
     * @return The {@code File} selected by user.
     */

    public static File chooseImage() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select your image file");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("JPEG Image",
                    "*.jpg", "*.jpeg");
        chooser.getExtensionFilters().addAll(filter);
        chooser.setSelectedExtensionFilter(filter);
        return chooser.showOpenDialog(primaryStage);
    }

    /**
     * Upload the Image given in {@code File} and associate it with the {@code Student} it belongs to.
     * @param student
     * @param file
     * @throws CommandException If the file cannot be read.
     */

    public static void uploadImage(Student student, File file) throws CommandException {
        FileInputStream inputFile = null;
        FileOutputStream outputFile = null;
        try {
            inputFile = new FileInputStream(file);
            outputFile = new FileOutputStream(getImageFile(student));
            int dataByte;
            while ((dataByte = inputFile.read()) != -1) {
                outputFile.write(dataByte);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            throw new CommandException("File read has an error please use another file.");
        } finally {
            try {
                if (inputFile != null) {
                    inputFile.close();
                }
                if (outputFile != null) {
                    outputFile.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Delete the Image file for given {@code Student}.
     * @param student
     */

    public static void remove(Student student) {
        File imageFile = getImageFile(student);
        if (imageFile.exists()) {
            imageFile.delete();
        }
    }

    /**
     * Rename the Image file for given {@code Student}.
     * @param target
     * @param editedStudent
     */
    public static void renamePictureFile(Student target, Student editedStudent) {
        File imageFile = getImageFile(target);
        if (imageFile.exists()) {
            imageFile.renameTo(getImageFile(editedStudent));
        }
    }

    /**
     * Sets reference to primary stage
     * @param stage
     */
    public static void setStage(Stage stage) {
        primaryStage = stage;
    }
}
