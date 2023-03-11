package com.nooglers.utils;

import java.util.Objects;

public class Validators {
    public static boolean checkForNullOrBlank(String fid) {
        return Objects.nonNull(fid) && !fid.isBlank();
    }
}
