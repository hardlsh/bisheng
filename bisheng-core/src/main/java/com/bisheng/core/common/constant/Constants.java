package com.bisheng.core.common.constant;

public class Constants {

    //公司理财账户id
    //TODO 实际id，记得修改
    public static final long COMPANY_ACCOUNT_FINANCAIL_ID   = 12312;
    
    //美利理财账户id
    //TODO 实际id，记得修改
    public static final long MLJR_COMPANY_ACCOUNT_FINANCAIL_ID   = 1;

    //用户赎回或者理财到期时，平台接手生成的交易对应的理财id
    public static final long PLATFORM_FINANCIAL_INSTANCE_ID = 0L;
    //进件网关线程池线程数
    public static final int SEND_FUND_GW_POOLSIZE = 20;
    //进件网关单次数量
    public static final int SEND_FUND_GW_MAXBIZ = 1000;
    
    //分配资金方线程池线程数
    public static final int ALLO_FUND_POOLSIZE = 20;
    //分配资金方单次数量
    public static final int ALLO_FUND_MAXBIZ = 1000;
    //自动更新借款还款计划数量
    public static final long AUTO_BORROW_REPAYPLAN_SIZE = 1000;
    
    //字典-资金方
    public static final String FEE_RECEIPT_STATUS_DICT = "FEE_RECEIPT_STATUS_DICT";
    public static final String FEE_PAYMENT_STATUS_DICT = "FEE_PAYMENT_STATUS_DICT";
    
    //盐
    public static final String SALT = "HXWcjvQWVG0wI3FQBLZpQ3pWj48AV63d";
    //散列次数
    public static final int HASH_ITERATIONS = 2;
    //默认密码
    public static final String DEFAULT_PWD = "000000";
    
    public static final String CHANNEL_DICT = "CHANNEL_DICT";
    public static final String LOAN_PRODUCTS = "LOAN_PRODUCTS";
    public static final String REPAYMENT_PERIOD_UNIT = "REPAYMENT_PERIOD_UNIT";
    public static final String LOAN_EFFECT_UNIT = "LOAN_EFFECT_UNIT";
    
    public static final String LOAN_CANCEL_ERROR_RESPCODE = "9992";
    public static final String LOAN_CANCEL_ERROR_RESPINFO = "系统异常";

    public static final String Export_Excel = "Export_Excel";//导出Excel type
    public static final Double MAX_THREAD = Double.valueOf(3);//导出Exce最大执行数
}
