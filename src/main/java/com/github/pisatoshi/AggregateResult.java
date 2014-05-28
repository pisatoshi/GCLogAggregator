package com.github.pisatoshi;

public class AggregateResult {
    private long recordCount;
    private int gcCount;
    private int fullGcCount;
    private long maxUsedHeapSize;
    private long totalUsedHeapSize;
    private double totalGcTime;

    public AggregateResult() {
        recordCount = 0;
        gcCount = 0;
        fullGcCount = 0;
        maxUsedHeapSize = 0;
        totalUsedHeapSize = 0;
        totalGcTime = 0;
    }

    public void add(LogEntry entry) {
        recordCount++;

        if (entry.getType().startsWith("Full GC")) {
            fullGcCount++;
        }
        if (entry.getType().startsWith("GC")) {
            gcCount++;
        }

        if (maxUsedHeapSize < entry.getUsedHeapSizeBefore()) {
            maxUsedHeapSize = entry.getUsedHeapSizeBefore();
        }

        totalUsedHeapSize += entry.getUsedHeapSizeBefore();

        totalGcTime += entry.getGcTime();
    }

    public String getCsvColumns() {
        return "GcCount,FullGcCount,MaxUsedHeap,AvgUsedHeap,TotalGcTime";
    }

    public String getCsvRecord() {
        StringBuilder builder = new StringBuilder();
        builder.append(gcCount);
        builder.append(",");
        builder.append(fullGcCount);
        builder.append(",");
        builder.append(maxUsedHeapSize);
        builder.append(",");
        builder.append(totalUsedHeapSize / recordCount);
        builder.append(",");
        builder.append(totalGcTime);
        return builder.toString();
    }
}
