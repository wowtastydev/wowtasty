package com.wowtasty.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * @author Hak C.
 *
 */
public class EncryptUtil {
	/** Logger */	
	private static Logger logger = Logger.getLogger(EncryptUtil.class);
	
	/** User's password */
	private String userPassword;

	/**
	 * @param userPassword
	 * @return encrypted string :SHA-512
	 */
	public void encrypt(String userPassword) {

		MessageDigest md;
        String tempPassword = "";

        try {
            md = MessageDigest.getInstance("SHA-512");

            md.update(userPassword.getBytes());
            byte[] mb = md.digest();
            for (int i = 0; i < mb.length; i++) {
                byte temp = mb[i];
                String s = Integer.toHexString(new Byte(temp));
                while (s.length() < 2) {
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);
                tempPassword += s;
            }
            setPassword(tempPassword);
        } catch (NoSuchAlgorithmException e) {
        	logger.error("!!!!!EncryptUtil encrypt occurs error:" + e);
        	throw new RuntimeException(e);
        }
    }
	
    private void setPassword(String temppassword) {
        this.userPassword = temppassword;
    }

    public String getPassword() {
        return userPassword;
    }
}
