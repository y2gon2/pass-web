package com.fastcampus.pass.service.statistics;

import com.fastcampus.pass.repository.statistics.StatisticsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@DisplayName("StatisticsService - Mockito Test")
@ExtendWith(MockitoExtension.class)
class StatisticsServiceTest {
    @Mock
    private StatisticsRepository statisticsRepository;
    @InjectMocks
    private StatisticsService statisticsService;

    @Nested
    @DisplayName("makcChartData method 를 사용하여 화면에 보여질 통계 data 인 chart data 를 생성한다.")
    class MakeChartData {
        final LocalDateTime to = LocalDateTime.of(2023, 9, 10, 0, 0);

        @DisplayName("통계 데이터가 있을 때")
        @Test
        void makeChartData_when_hasStatistics() {
            // Given
            List<AggregatedStatistics> statisticsList = List.of(
                    new AggregatedStatistics(to.minusDays(1), 15, 10, 5),
                    new AggregatedStatistics(to, 10, 8, 2)
            );

            // When
            when(statisticsRepository
                    .findByStatisticsAtBetweenandGroupBy(eq(to.minusDays(10)), eq(to)))
                    .thenReturn(statisticsList);
            final ChartData chartData = statisticsService.makeChartData(to);

            // Then
            verify(statisticsRepository, times(1))
                    .findByStatisticsAtBetweenandGroupBy(eq(to.minusDays(10)), eq(to));

            assertNotNull(chartData);
            assertEquals(new ArrayList<>(List.of("09-09", "09-10")), chartData.labels());
            assertEquals(new ArrayList<>(List.of(10L, 8L)), chartData.attendedCounts());
            assertEquals(new ArrayList<>(List.of(5L, 2L)), chartData.cancelledCounts());
        }

        @DisplayName("통계 데이터가 없을 때")
        @Test
        void makeChartData_when_notHasStatistics() {
            // When
            when(statisticsRepository
                    .findByStatisticsAtBetweenandGroupBy(eq(to.minusDays(10)), eq(to)))
                    .thenReturn(Collections.emptyList());
            final ChartData chartData = statisticsService.makeChartData(to);

            // Then
            verify(statisticsRepository, times(1))
                    .findByStatisticsAtBetweenandGroupBy(eq(to.minusDays(10)), eq(to));

            assertNotNull(chartData);
            assertTrue(chartData.labels().isEmpty());
            assertTrue(chartData.attendedCounts().isEmpty());
            assertTrue(chartData.cancelledCounts().isEmpty());
        }
    }


}