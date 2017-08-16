/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gisfiletranslator;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Jennifer.Broderick
 */
public final class FileIteratorUtility {

    public void changeAllFileExtension(String directoryName, String oldExtension, String newExtension) {
        Path directory = Paths.get(directoryName);
        if (Files.notExists(directory)) {  // check that the input directory exists
            System.out.println("ERROR***** NO SUCH DIRECTORY FOUND");
        } else {
            File[] inFiles = directory.toFile().listFiles();
            for (File inFile : inFiles) {
                System.out.println(inFile.toString());
                inFile.renameTo(new File(inFile.getName().substring(0, inFile.getName().indexOf(oldExtension)) + newExtension));
                System.out.println(inFile.toString());
            }

        }
    }
}
