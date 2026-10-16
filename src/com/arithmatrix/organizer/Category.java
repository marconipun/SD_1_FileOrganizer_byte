package com.arithmatrix.organizer;
import java.util.Set;
import java.util.Collections;

public enum Category {
    Images("Images", Set.of("jpg", "jpeg", "png", "gif", "bmp", "tiff", "svg")),
    Documents("Documents", Set.of("pdf", "doc", "docx", "txt", "xls", "xlsx", "ppt", "pptx")),
    Archives("Archives", Set.of("zip", "rar", "7z", "tar", "gz")),
    Audio("Audio", Set.of("mp3", "wav", "flac", "aac", "ogg")),
    Videos("Videos", Set.of("mp4", "avi", "mkv", "mov", "wmv", "flv")),
    Code("Code", Set.of("java", "py", "js", "html", "css", "cpp", "c", "cs", "rb", "php")),
    Others("Others", Collections.emptySet());

    //hold data for each category
    private final String folderName;
    private final Set<String> extensions;

    //constructor for each enum 
    Category(String folderName, Set<String> extensions) {
        this.folderName = folderName;
        this.extensions = extensions;
    }

    //getter for read the folder name
    public String getFolderName() {
        return folderName;
    }

    //we are using the classifier algorithm
    public static Category fromFileName(String fileName){

        //if empty or null file name is passed, we will return Others category
        if(fileName == null){
            return Others;
        }

        //get the index of last dot in the file name
        int indexDot = fileName.lastIndexOf('.');

        //if no dot is found, we will return Others category
        if(indexDot == -1 || indexDot == fileName.length() - 1){
            return Others;
        }

        // Extract the extension AFTER the dot and make it lowercase
        String ext = fileName.substring(indexDot + 1).toLowerCase();

        //iterate through all the categories and check if the extension is present in any of them
        for (Category category : Category.values()) {
            if (category.extensions.contains(ext)) {
                return category;
            }
        }

        return Others; // If no matching category is found, return Others



    }

}

