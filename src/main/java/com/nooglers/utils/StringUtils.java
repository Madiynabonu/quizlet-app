package com.nooglers.utils;

import jakarta.servlet.http.Part;
import lombok.NonNull;

public class StringUtils {
    public static String getFileExtension(@NonNull String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static String getFileExtension(@NonNull Part part) {
        return getFileExtension(part.getSubmittedFileName());
    }

    public static String generateUniqueName(@NonNull String originalFileName) {
        return System.currentTimeMillis() + "." + getFileExtension(originalFileName);
    }

    public static String generateUniqueName(@NonNull Part part) {
        return generateUniqueName(part.getSubmittedFileName());
    }
}