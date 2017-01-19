package com.uhp.util;


import com.uhp.tinytypes.Password;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class HashingUtil {
    private static final int SALT_BYTE_SIZE = 24;
    private static final int PBKDF2_ITERATIONS = 64000;
    private static final int HASH_BYTE_SIZE = 18;
    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    public static String createHash(Password password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password.getValue().toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        byte[] hash = skf.generateSecret(spec).getEncoded();

        return DatatypeConverter.printBase64Binary(hash);
    }

    public static byte[] randomSalt() {
        final SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        return salt;
    }
}
