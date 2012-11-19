package com.wowtasty.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import com.wowtasty.action.CodeAction;

/**
 * @author Hak C.
 *
 */
public class EncryptUtil {
	/** Logger */	
	private static Logger logger = Logger.getLogger(CodeAction.class);
	
	/** User's password */
	private String userPassword;

	/**
	 * @param userPassword
	 * @return encrypted string :SHA-512
	 */
	public boolean encrypt(String userPassword) {

		MessageDigest md;
        boolean isSuccess;
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
            isSuccess = true;
        } catch (NoSuchAlgorithmException e) {
        	logger.error(":::::EncryptUtil encrypt occurs error:" + e);
            isSuccess = false;
            return isSuccess;
        }
        return isSuccess;
    }
	
    private void setPassword(String temppassword) {
        this.userPassword = temppassword;
    }

    public String getPassword() {
        return userPassword;
    }
}
