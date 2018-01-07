package com.bisheng.core.common.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class BigDecimalUtil {

    public static String format(BigDecimal amount) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
        return currencyFormat.format(amount);
    }
    
    public static String formatMyCHINA(BigDecimal amount){
    	return "¥".concat(NumberFormat.getInstance().format(amount));
    }
    public static BigDecimal formatBigDecimal(BigDecimal amount, int size) {
        return amount.setScale(size, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * BigDecimal null 转换  0
     *
     * @param in
     * @return
     */
    public static BigDecimal convertNull(BigDecimal in) {
        if (null == in) {
            return new BigDecimal(0);
        }
        return in;
    }

	public static void main(String[] args) {
    	System.out.println(BigDecimalUtil.formatMyCHINA(new BigDecimal("1056.00")));
    }
    
    
}
