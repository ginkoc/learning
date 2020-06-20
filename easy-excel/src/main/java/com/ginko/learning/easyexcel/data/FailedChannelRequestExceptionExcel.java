package com.ginko.learning.easyexcel.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.util.Date;

@HeadStyle(fillForegroundColor = 1)
@HeadFontStyle(fontHeightInPoints = 11)
@ContentStyle(borderLeft = BorderStyle.THIN, borderRight = BorderStyle.THIN,
        borderTop = BorderStyle.THIN, borderBottom = BorderStyle.THIN)
@ContentFontStyle(fontName = "宋体", fontHeightInPoints = 11)
public class FailedChannelRequestExceptionExcel {

    @DateTimeFormat("MM月dd日")
    @ContentStyle(horizontalAlignment = HorizontalAlignment.CENTER)
    @ContentFontStyle(fontName = "宋体", fontHeightInPoints = 11, bold = true)
    @ContentLoopMerge(eachRow = TableHeader.SHOW_EXCEPTION_AMOUNT)
    @ColumnWidth(12)
    @ExcelProperty(index = 0)
    private Date date;

    @ContentStyle(horizontalAlignment = HorizontalAlignment.CENTER)
    @ContentLoopMerge(eachRow = TableHeader.SHOW_EXCEPTION_AMOUNT)
    @ColumnWidth(14)
    @ExcelProperty(index = 1)
    private long totalFailedAmount;

    @ContentStyle(horizontalAlignment = HorizontalAlignment.CENTER)
    @ContentLoopMerge(eachRow = TableHeader.SHOW_EXCEPTION_AMOUNT)
    @ColumnWidth(16)
    @ExcelProperty(index = 2)
    private long totalRequestAmount;

    @ContentStyle(horizontalAlignment = HorizontalAlignment.CENTER)
    @ContentLoopMerge(eachRow = TableHeader.SHOW_EXCEPTION_AMOUNT)
    @NumberFormat("#.##%")
    @ColumnWidth(18)
    @ExcelProperty(index = 3)
    private double failedPercentage;

    @ContentStyle(horizontalAlignment = HorizontalAlignment.LEFT)
    @ColumnWidth(68)
    @ExcelProperty(index = 4)
    private String exceptionInfo;

    @ContentStyle(horizontalAlignment = HorizontalAlignment.RIGHT)
    @ColumnWidth(10)
    @ExcelProperty(index = 5)
    private long exceptionAmount;

    @ContentStyle(horizontalAlignment = HorizontalAlignment.RIGHT)
    @NumberFormat("#.##%")
    @ColumnWidth(10)
    @ExcelProperty(index = 6)
    private double exceptionPercentage;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTotalFailedAmount() {
        return totalFailedAmount;
    }

    public void setTotalFailedAmount(long totalFailedAmount) {
        this.totalFailedAmount = totalFailedAmount;
    }

    public long getTotalRequestAmount() {
        return totalRequestAmount;
    }

    public void setTotalRequestAmount(long totalRequestAmount) {
        this.totalRequestAmount = totalRequestAmount;
    }

    public double getFailedPercentage() {
        return failedPercentage;
    }

    public void setFailedPercentage(double failedPercentage) {
        this.failedPercentage = failedPercentage;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public long getExceptionAmount() {
        return exceptionAmount;
    }

    public void setExceptionAmount(long exceptionAmount) {
        this.exceptionAmount = exceptionAmount;
    }

    public double getExceptionPercentage() {
        return exceptionPercentage;
    }

    public void setExceptionPercentage(double exceptionPercentage) {
        this.exceptionPercentage = exceptionPercentage;
    }
}
