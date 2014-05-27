package com.github.pisatoshi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogEntry {
    private static Pattern pattern;

    private String source;
    private double timeStamp;
    private String type;
    private long usedHeapSizeBefore;
    private long usedHeapSizeAfter;
    private long totalHeapSize;
    private double gcTime;

    private LogEntry() {
        pattern = Pattern.compile("^([.0-9]+): \\[(.*) \\(.+\\)[ ]+([0-9]+)K->([0-9]+)K\\(([0-9]+)K\\), (.*) secs\\]$");
        source = null;
        timeStamp = 0;
        type = null;
        usedHeapSizeBefore = 0;
        usedHeapSizeAfter = 0;
        totalHeapSize = 0;
        gcTime = 0;
    }

    public static LogEntry getInstance(String line) {
        LogEntry entry = new LogEntry();
        Matcher m = pattern.matcher(line);
        if (!m.find()) {
            return null;
        }

        entry.source = new String(line);
        try {
            int index = 1;
            entry.timeStamp = Double.parseDouble(m.group(index++));
            entry.type = m.group(index++);
            entry.usedHeapSizeBefore = Long.parseLong(m.group(index++));
            entry.usedHeapSizeAfter = Long.parseLong(m.group(index++));
            entry.totalHeapSize = Long.parseLong(m.group(index++));
            entry.gcTime = Double.parseDouble(m.group(index++));
        } catch (NumberFormatException ex) {
            return null;
        }

        return entry;
    }

    public String getSource() {
        return source;
    }

    public Double getTimeStamp() {
        return timeStamp;
    }

    public String getType() {
        return type;
    }

    public Long getUsedHeapSizeBefore() {
        return usedHeapSizeBefore;
    }

    public Long getUsedHeapSizeAfter() {
        return usedHeapSizeAfter;
    }

    public Long getTotalHeapSize() {
        return totalHeapSize;
    }

    public Double getGcTime() {
        return gcTime;
    }
}
