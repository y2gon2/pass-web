package com.fastcampus.pass.service.statistics;

import java.util.List;

public record ChartData(
        List<String> labels,
        List<Long> attendedCounts,
        List<Long> cancelledCounts
) {
    public static ChartData of(
            List<String> labels,
            List<Long> attendedCounts,
            List<Long> cancelledCounts
    ) {
        return new ChartData(labels, attendedCounts, cancelledCounts);
    }

    @Override
    public List<String> labels() {
        return labels;
    }

    @Override
    public List<Long> attendedCounts() {
        return attendedCounts;
    }

    @Override
    public List<Long> cancelledCounts() {
        return cancelledCounts;
    }
}
