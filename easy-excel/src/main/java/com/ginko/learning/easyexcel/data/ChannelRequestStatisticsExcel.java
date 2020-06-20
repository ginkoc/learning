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
@ContentStyle(horizontalAlignment = HorizontalAlignment.RIGHT, borderLeft = BorderStyle.THIN,
        borderRight = BorderStyle.THIN, borderTop = BorderStyle.THIN, borderBottom = BorderStyle.THIN)
@ContentFontStyle(fontName = "宋体", fontHeightInPoints = 11)
public class ChannelRequestStatisticsExcel {

    @DateTimeFormat("MM月dd日")
    @ContentFontStyle(fontName = "宋体", fontHeightInPoints = 11, bold = true)
    @ExcelProperty(index = 0)
    private Date date;

    @ColumnWidth(14)
    @ExcelProperty(index = 1)
    private long zero2OneSecondAmount;

    @NumberFormat("#.##%")
    @ExcelProperty(index = 2)
    private double zero2OneSecondPercentage;

    @ExcelProperty(index = 3)
    private long one2TwoSecondAmount;

    @NumberFormat("#.##%")
    @ExcelProperty(index = 4)
    private double one2TwoSecondPercentage;

    @ExcelProperty(index = 5)
    private long two2threeSecondAmount;

    @NumberFormat("#.##%")
    @ExcelProperty(index = 6)
    private double two2threeSecondPercentage;

    @ExcelProperty(index = 7)
    private long three2FiveSecondAmount;

    @NumberFormat("#.##%")
    @ExcelProperty(index = 8)
    private double three2FiveSecondPercentage;

    @ExcelProperty(index = 9)
    private long moreThanFiveSecondAmount;

    @NumberFormat("#.##%")
    @ExcelProperty(index = 10)
    private double  moreThanFiveSecondPercentage;

    @ExcelProperty(index = 11)
    private long failedAmount;

    @NumberFormat("#.##%")
    @ExcelProperty(index = 12)
    private double  failedPercentage;

    @ExcelProperty(index = 13)
    private long failedAncExecuteAmount;

    @NumberFormat("#.##%")
    @ExcelProperty(index = 14)
    private double  failedAncExecutePercentage;

    @ExcelProperty(index = 15)
    private long totalAmount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getZero2OneSecondAmount() {
        return zero2OneSecondAmount;
    }

    public void setZero2OneSecondAmount(long zero2OneSecondAmount) {
        this.zero2OneSecondAmount = zero2OneSecondAmount;
    }

    public double getZero2OneSecondPercentage() {
        return zero2OneSecondPercentage;
    }

    public void setZero2OneSecondPercentage(double zero2OneSecondPercentage) {
        this.zero2OneSecondPercentage = zero2OneSecondPercentage;
    }

    public long getOne2TwoSecondAmount() {
        return one2TwoSecondAmount;
    }

    public void setOne2TwoSecondAmount(long one2TwoSecondAmount) {
        this.one2TwoSecondAmount = one2TwoSecondAmount;
    }

    public double getOne2TwoSecondPercentage() {
        return one2TwoSecondPercentage;
    }

    public void setOne2TwoSecondPercentage(double one2TwoSecondPercentage) {
        this.one2TwoSecondPercentage = one2TwoSecondPercentage;
    }

    public long getTwo2threeSecondAmount() {
        return two2threeSecondAmount;
    }

    public void setTwo2threeSecondAmount(long two2threeSecondAmount) {
        this.two2threeSecondAmount = two2threeSecondAmount;
    }

    public double getTwo2threeSecondPercentage() {
        return two2threeSecondPercentage;
    }

    public void setTwo2threeSecondPercentage(double two2threeSecondPercentage) {
        this.two2threeSecondPercentage = two2threeSecondPercentage;
    }

    public long getThree2FiveSecondAmount() {
        return three2FiveSecondAmount;
    }

    public void setThree2FiveSecondAmount(long three2FiveSecondAmount) {
        this.three2FiveSecondAmount = three2FiveSecondAmount;
    }

    public double getThree2FiveSecondPercentage() {
        return three2FiveSecondPercentage;
    }

    public void setThree2FiveSecondPercentage(double three2FiveSecondPercentage) {
        this.three2FiveSecondPercentage = three2FiveSecondPercentage;
    }

    public long getMoreThanFiveSecondAmount() {
        return moreThanFiveSecondAmount;
    }

    public void setMoreThanFiveSecondAmount(long moreThanFiveSecondAmount) {
        this.moreThanFiveSecondAmount = moreThanFiveSecondAmount;
    }

    public double getMoreThanFiveSecondPercentage() {
        return moreThanFiveSecondPercentage;
    }

    public void setMoreThanFiveSecondPercentage(double moreThanFiveSecondPercentage) {
        this.moreThanFiveSecondPercentage = moreThanFiveSecondPercentage;
    }

    public long getFailedAmount() {
        return failedAmount;
    }

    public void setFailedAmount(long failedAmount) {
        this.failedAmount = failedAmount;
    }

    public double getFailedPercentage() {
        return failedPercentage;
    }

    public void setFailedPercentage(double failedPercentage) {
        this.failedPercentage = failedPercentage;
    }

    public long getFailedAncExecuteAmount() {
        return failedAncExecuteAmount;
    }

    public void setFailedAncExecuteAmount(long failedAncExecuteAmount) {
        this.failedAncExecuteAmount = failedAncExecuteAmount;
    }

    public double getFailedAncExecutePercentage() {
        return failedAncExecutePercentage;
    }

    public void setFailedAncExecutePercentage(double failedAncExecutePercentage) {
        this.failedAncExecutePercentage = failedAncExecutePercentage;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }
}
