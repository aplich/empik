package me.plich.empik.calculation;

import me.plich.empik.exception.ZeroFollowersNumberException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public final class CalculationService implements Calculation {

    private final BigDecimal SIX = BigDecimal.valueOf(6);
    private final BigDecimal TWO = BigDecimal.valueOf(2);

    @Override
    public BigDecimal someImportantCalculations(BigDecimal followersNumber, BigDecimal numberOfPublicRepos) throws IllegalArgumentException {
        if(followersNumber.signum() == 0) {
            throw new ZeroFollowersNumberException();
        }
        return (SIX.divide(followersNumber, 5, RoundingMode.HALF_UP))
                .multiply(TWO.add(numberOfPublicRepos));
    }
}
