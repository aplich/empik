package me.plich.empik.calculation;



import me.plich.empik.exception.ZeroFollowersNumberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class CalculationServiceTest {

    private Calculation calculationService = new CalculationService();


    private static Stream<Arguments> provideBigDecimalsForSomeImportantCalculations() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(1), BigDecimal.valueOf(1), new BigDecimal("18.00000")),
                Arguments.of(BigDecimal.valueOf(1), BigDecimal.valueOf(7), new BigDecimal("54.00000")),
                Arguments.of(BigDecimal.valueOf(4), BigDecimal.valueOf(5), new BigDecimal("10.50000")),
                Arguments.of(BigDecimal.valueOf(22), BigDecimal.valueOf(0), new BigDecimal("0.54546")),
                Arguments.of(BigDecimal.valueOf(23), BigDecimal.valueOf(8), new BigDecimal("2.60870")),
                Arguments.of(BigDecimal.valueOf(17), BigDecimal.valueOf(144), new BigDecimal("51.52924")),
                Arguments.of(BigDecimal.valueOf(15), BigDecimal.valueOf(500), new BigDecimal("200.80000")),
                Arguments.of(BigDecimal.valueOf(5), BigDecimal.valueOf(55), new BigDecimal("68.40000")),
                Arguments.of(BigDecimal.valueOf(49), BigDecimal.valueOf(23), new BigDecimal("3.06125")),
                Arguments.of(BigDecimal.valueOf(99), BigDecimal.valueOf(199), new BigDecimal("12.18261"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideBigDecimalsForSomeImportantCalculations")
    void shouldReturnCorrecrResultForGivenParamiters(BigDecimal followersNumber, BigDecimal numberOfPublicRepos, BigDecimal result) {
        assertThat(calculationService.someImportantCalculations(followersNumber, numberOfPublicRepos)).isEqualTo(result);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenFollowersNumberIsZero() {
        assertThatExceptionOfType(ZeroFollowersNumberException.class)
                .isThrownBy(() -> calculationService.someImportantCalculations(BigDecimal.ZERO, BigDecimal.ONE))
                .withMessage("followersNumber cannot be zero");
    }

    @Test
    void shouldNotThrowAnyExceptionWhenNumberOfPublicReposIsZero() {
        assertThatCode(() -> calculationService.someImportantCalculations(BigDecimal.ONE, BigDecimal.ZERO))
                .doesNotThrowAnyException();
    }
}