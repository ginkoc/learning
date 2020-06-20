package com.ginko.learning.easyexcel.writer;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.ginko.learning.easyexcel.data.ChannelRequestStatisticsExcel;
import com.ginko.learning.easyexcel.data.FailedChannelRequestExceptionExcel;
import com.ginko.learning.easyexcel.data.TableHeader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyExcelWriter {


    public static void main(String[] args) {
        String path = "D:\\dev\\test\\";
        String fileName = path + "dynamicHeadWrite" + System.currentTimeMillis() + ".xlsx";

        ExcelWriter excelWriter = null;
        try {
            // 这里 指定文件
            excelWriter = EasyExcel.write(fileName).build();

            // 创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class 实际上可以一直变
            WriteSheet thsSheet =
                    EasyExcel.writerSheet(0, "ths")
                            .head(TableHeader.THS_SIMULATION_CHANNEL.getHeader())
                            .head(ChannelRequestStatisticsExcel.class)
                            .build();
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            excelWriter.write(dataChannelRequestStatisticsExcel(), thsSheet);

            WriteSheet failSheet =
                    EasyExcel.writerSheet(1, "fail")
                            .head(TableHeader.CANCEL_ORDER_EXCEPTION.getHeader())
                            .head(FailedChannelRequestExceptionExcel.class)
                            .build();
            excelWriter.write(dataFailedChannelRequestExceptionExcel(), failSheet);
        } finally {
            // 千万别忘记finish
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    private static List<ChannelRequestStatisticsExcel> dataChannelRequestStatisticsExcel() {
        List<ChannelRequestStatisticsExcel> list = new ArrayList<>();
        ChannelRequestStatisticsExcel requestStatisticsExcel = new ChannelRequestStatisticsExcel();
        requestStatisticsExcel.setDate(new Date());
        requestStatisticsExcel.setZero2OneSecondAmount(1000L);
        requestStatisticsExcel.setZero2OneSecondPercentage(1000.0/1111);
        requestStatisticsExcel.setOne2TwoSecondAmount(100L);
        requestStatisticsExcel.setOne2TwoSecondPercentage(100.0/1111);
        requestStatisticsExcel.setTwo2threeSecondAmount(10L);
        requestStatisticsExcel.setTwo2threeSecondPercentage(10.0/1111);
        requestStatisticsExcel.setThree2FiveSecondAmount(1L);
        requestStatisticsExcel.setThree2FiveSecondPercentage(1.0/1111);
        requestStatisticsExcel.setFailedAmount(0L);
        requestStatisticsExcel.setFailedPercentage(0);
        requestStatisticsExcel.setFailedAncExecuteAmount(0L);
        requestStatisticsExcel.setFailedAncExecutePercentage(0);
        requestStatisticsExcel.setTotalAmount(1111L);
        list.add(requestStatisticsExcel);
        return list;
    }

    private static List<FailedChannelRequestExceptionExcel> dataFailedChannelRequestExceptionExcel() {
        List<FailedChannelRequestExceptionExcel> list = new ArrayList<>();

        FailedChannelRequestExceptionExcel tmpObj;
        for (int i = 1; i <= TableHeader.SHOW_EXCEPTION_AMOUNT; i++) {
            tmpObj = new FailedChannelRequestExceptionExcel();
            tmpObj.setDate(new Date());
            tmpObj.setExceptionAmount(i * 2);
            tmpObj.setExceptionInfo("hello world");
            tmpObj.setTotalFailedAmount(42);
            tmpObj.setTotalRequestAmount(1234);
            tmpObj.setFailedPercentage(42 * 1.0 / 1234);
            tmpObj.setExceptionPercentage(i * 2.0 / 1234);
            list.add(tmpObj);
        }

        return list;
    }
}
