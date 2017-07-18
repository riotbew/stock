package com.tauren.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 * Created by Tauren on 17/7/18.
 */

public class MD5Coder {

    public static String md5(String input) {
        if (input == null)
            return null;

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes("UTF-8"));
            byte[] byteMD5 = digest.digest();
            StringBuilder builder = new StringBuilder();
            String tmp;
            for (byte aByteMD5 : byteMD5) {
                tmp = Integer.toHexString(aByteMD5 & 255);
                if (tmp.length() < 2)
                    builder.append(0);
                builder.append(tmp);
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
