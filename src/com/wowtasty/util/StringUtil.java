package com.wowtasty.util;

import org.apache.log4j.Logger;

/**
 *
 * @author Seunghon Kim <sh.kim@live.com>
 */
public class StringUtil {

    /**
     * Logger
     */
    private static Logger logger = Logger.getLogger(StringUtil.class);

    /**
     * substring by limit
     *
     * @param src source string
     * @param limit limit of result string
     * @return
     */
    public static String substringLimit(String src, int limit) {
        return substringLimit(src, limit, "...");
    }

    /**
     * substring by limit and attach mark
     *
     * @param src source string
     * @param limit limit of result string
     * @param mark mark string to attach the end of result
     * @return
     */
    public static String substringLimit(String src, int limit, String mark) {
        String str = src;
        try {
            if (!str.isEmpty() && str.length() > limit) {
                str = str.substring(0, limit) + mark;
            }
        } catch (Exception e) {
            logger.error("!!!!!StringUtil substringLimit occurs error:" + e);
            throw new RuntimeException(e);
        }
        return str;
    }
}
