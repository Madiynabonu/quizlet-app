package com.nooglers.utils;

import lombok.NonNull;
import org.mindrot.jbcrypt.BCrypt;

public class Encrypt {
    public static String decodePassword(String password) {
        return BCrypt.hashpw(password , BCrypt.gensalt());
    }

    public static boolean checkPassword(@NonNull String password , @NonNull String codePassword) {
        return BCrypt.checkpw(password , codePassword);
    }
}
