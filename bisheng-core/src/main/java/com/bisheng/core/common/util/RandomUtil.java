package com.bisheng.core.common.util;

import java.security.SecureRandom;

/**
 * 随机数实用类
 *
 * @author chao.liu
 */
public class RandomUtil {
    private static String tSpaceString = "00000000";

    /**
     * 生成随机数字
     *
     * @param aLength 随机数位数
     * @return 生成的随机数
     */
    public static String genRandNum(int aLength) {
        SecureRandom tRandom = new SecureRandom();
        long tLong;
        StringBuilder sb = new StringBuilder("");
        tRandom.nextLong();
        tLong = Math.abs(tRandom.nextLong());
        sb.append((String.valueOf(tLong)).trim());
        while (sb.length() < aLength) {
            tLong = Math.abs(tRandom.nextLong());
            sb.append((String.valueOf(tLong)).trim());
        }
        String aString = sb.substring(0, aLength);

        return aString;
    }

    /**
     * 生成22位随机数字
     *
     * @return YYMMDDhhmmSSRRRNNNN
     */
    public static String genDTRandNum() {
     /*   StringBuffer tSb = new StringBuffer();
        String tCurDT = "";
        tCurDT = DateUtil.getCurrentTime("DTM","AD2");
        tSb.append(tCurDT);
        tSb.append(genRandNum(3));
        int tHashCode = Thread.currentThread().hashCode();
        String tHCS = Integer.toString(tHashCode);
        tSb.append(tHCS.substring(tHCS.length() - 4));
        return tSb.toString();
        */
        StringBuffer tSb = new StringBuffer();
        String tCurDT = "";
        tCurDT = DateUtil.getCurrentTime();
        tSb.append(tCurDT);
        tSb.append(genRandNum(3));
        int tHashCode = tSb.hashCode();
        String tHCS = Integer.toString(tHashCode);
        if (tHCS.length() < 4) {
            tSb.append(tSpaceString.substring(0, 4 - tHCS.length()));
            tSb.append(tHCS);
        } else {
            tSb.append(tHCS.substring(tHCS.length() - 4));
        }
        return tSb.toString();
    }

    /**
     * 生成传入参数加14位的随机数
     *
     * @param aCustom 传入参数
     * @return 传入参数加14位的随机数
     */
    public static String genCustomRandNum(String aCustom) {
        StringBuffer tSb = new StringBuffer(aCustom);
        String tCurTime = "";
        tCurTime = DateUtil.getCurrentTime();
        tSb.append(tCurTime);
        int tHashCode = Thread.currentThread().hashCode();
        String tHCS = Integer.toString(tHashCode);
        tSb.append(tHCS.substring(tHCS.length() - 5));
        return tSb.toString();
    }
}
