package com.fish.tools;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/8/5
 */
public class GetWindows10Pic {
    public static final String SRC_PICTURE_DIR = "C:/Users/jayzo/AppData/Local/Packages/Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy/LocalState/Assets";

    public static final String DEST_PICTURE_DIR = "C:/Users/jayzo/Pictures/Favorite";

    public static void main(String[] args) {
        try {

            // Clear previous files.
            Stream<Path> destPaths = Files.walk(Paths.get(DEST_PICTURE_DIR), 1, FileVisitOption.FOLLOW_LINKS);
            destPaths.forEach(e -> {
                try {
                    Files.deleteIfExists(e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });

            // Copy picture file to destination path
            Stream<Path> paths = Files.walk(Paths.get(SRC_PICTURE_DIR), 1, FileVisitOption.FOLLOW_LINKS);
            paths.forEach(e -> {
                try {
                    Files.copy(Files.newInputStream(e), Paths.get(DEST_PICTURE_DIR, UUID.randomUUID() + ".jpg"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
