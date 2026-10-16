package com.arithmatrix.organizer;

import java.nio.file.Files;
import java.nio.file.Path;

public class ConflictResolve {
//This class is responsible for resolving conflicts when moving files to the target directory.
//For example, if a file with the same name already exists in the target directory, this class will handle renaming or overwriting based on the chosen strategy.

 public static Path resolve(Path targetDir, String originalFileName) {

    //Directly check the path
    Path targetPath = targetDir.resolve(originalFileName);
    
    if (!Files.exists(targetPath)) {
        return targetPath; // No conflict, return the original path
    }

    //seperate the file name and extension and check base name and extension
    int dotIndex = originalFileName.lastIndexOf('.');
    String baseName = dotIndex > 0 ? originalFileName.substring(0, dotIndex) : originalFileName;
    String extension = dotIndex > 0 ? originalFileName.substring(dotIndex) : "";

    //Increment counter to create a new file name until we find one that doesn't exist
    int counter = 1;
    while (true) {
        String newFileName = baseName + "_" + counter + extension;
        Path newTargetPath = targetDir.resolve(newFileName);

        // If this candidate name does not exist on disk, we found our safe target!
        if (!Files.exists(newTargetPath)) {
            return newTargetPath; 
        }

        
        counter++;
    }

 }

    
}
