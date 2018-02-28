package com.bisheng.util;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by miaoxing on 16/6/15.
 */
public class ExportUtil {

    private static Logger logger = LoggerFactory.getLogger(ExportUtil.class);

    private final static int FIRST_PAGE_NUM = 1;
    private final static int DEFAULT_PAGE_SIZE = 2000;

    public interface PageFunction<R, T> {
        T apply(R record, int pageNum, int pageSize);
    }

    /**
     * 分页导出
     *
     * @param title
     * @param excelModel
     * @param clazz
     * @param record
     * @param response
     * @param function
     * @param <R>
     * @param <T>
     * @throws Exception
     */
    public static <R, T> void exportWorkbook(String title, String excelModel, Class<T> clazz, R record, HttpServletResponse response, PageFunction<R, List<T>> function) throws Exception {
        response.reset();
        response.setHeader("Content-Disposition", "attachment;fileName=" + title);
        response.setContentType("application/x-download");
        ServletOutputStream outputStream = response.getOutputStream();
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(outputStream);
            XSSFParams xssfParams = new XSSFParams(excelModel + ".xlsx");
            int page = FIRST_PAGE_NUM;
            while (true) {
                List<T> list = function.apply(record, page, DEFAULT_PAGE_SIZE);
                if (CollectionUtils.isEmpty(list)) {
                    break;
                }
                ExcelManager.exportSXSSFExcel(list, excelModel, clazz, xssfParams);
                page++;
            }
            xssfParams.getWorkbook().write(os);
            os.close();
            xssfParams.getWorkbook().dispose();
        } catch (Exception e) {
            logger.error("导出文件异常:", e);
        } finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(outputStream);
        }
    }

}
