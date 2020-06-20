package com.ginko.learning.easyexcel.data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public enum TableHeader {

    THS_SIMULATION_CHANNEL("同花顺模拟通道", true),

    CANCEL_ORDER_EXCEPTION("撤单", false),
    ;

    public static final int SHOW_EXCEPTION_AMOUNT = 6;

    private static final int CHANNEL_REQUEST_STATISTICS_COLUMN_AMOUNT = 15;
    private static final int EXCEPTION_STATISTICS_COLUMN_AMOUNT = 7;
    private static final String AMOUNT = "数量";
    private static final String PERCENTAGE = "比例";
    private static final String DATE = "日期";
    private static final String TOTAL_AMOUNT = "总数量";
    private static final String EXCEPTION_INFO = "当日比例最高的" + SHOW_EXCEPTION_AMOUNT + "种异常";
    private static final String FAILED_AMOUNT_FORMAT = "%s失败数量";
    private static final String TOTAL_REQUEST_AMOUNT_FORMAT = "%s请求总数量";
    private static final String TOTAL_FAILED_PERCENTAGE_FORMAT = "总的%s失败比例";

    private static final String[] CHANNEL_REQUEST_TITLES = {"0~1s", "1~2s", "2~3s", "3~5s", "5+s",
            "失败不需要处理", "失败需要处理"};
    private static final String[] EXCEPTION_STATISTICS_TITLES = {DATE, FAILED_AMOUNT_FORMAT, TOTAL_REQUEST_AMOUNT_FORMAT,
            TOTAL_FAILED_PERCENTAGE_FORMAT, EXCEPTION_INFO, AMOUNT, PERCENTAGE};

    private final String topHeader;
    private final boolean multiHeader;
    private List<List<String>> header;

    TableHeader(String topHeader, boolean multiHeader) {
        this.topHeader = topHeader;
        this.multiHeader = multiHeader;
    }

    public List<List<String>> getHeader() {
        if (header == null) {
            header = buildTableHeader(topHeader);
        }

        return header;
    }

    private List<List<String>> buildTableHeader(String topHeader) {
        List<List<String>> res;
        if (multiHeader) {
            res = getChannelRequestStatisticsSubHeader();
            for (List<String> subHeader : res) {
                subHeader.add(0, topHeader);
            }
        } else {
            res = getExceptionHeader(topHeader);
        }

        return res;
    }

    private static List<List<String>> getChannelRequestStatisticsSubHeader() {
        List<List<String>> res = new ArrayList<>(CHANNEL_REQUEST_STATISTICS_COLUMN_AMOUNT);

        // 日期列的1,2,3行
        List<String> dateColumn = new LinkedList<>();
        dateColumn.add(DATE);
        dateColumn.add(DATE);
        res.add(dateColumn);

        // 子标题中每一列的1,2,3行
        List<String> amountTitleColumn;
        List<String> percentageTitleColumn;
        for (String title : CHANNEL_REQUEST_TITLES) {
            amountTitleColumn = new LinkedList<>();
            amountTitleColumn.add(title);
            amountTitleColumn.add(AMOUNT);
            res.add(amountTitleColumn);

            percentageTitleColumn = new LinkedList<>();
            percentageTitleColumn.add(title);
            percentageTitleColumn.add(PERCENTAGE);
            res.add(percentageTitleColumn);
        }

        // 总数量列的1,2,3行
        List<String> totalAmountColumn = new LinkedList<>();
        totalAmountColumn.add(TOTAL_AMOUNT);
        totalAmountColumn.add(TOTAL_AMOUNT);
        res.add(totalAmountColumn);

        return res;
    }

    private static List<List<String>> getExceptionHeader(String type) {
        List<List<String>> res = new ArrayList<>(EXCEPTION_STATISTICS_COLUMN_AMOUNT);

        List<String> columnList;
        for (String title : EXCEPTION_STATISTICS_TITLES) {
            columnList = new ArrayList<>(1);
            columnList.add(String.format(title, type));
            res.add(columnList);
        }

        return res;
    }
}
