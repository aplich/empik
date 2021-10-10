package me.plich.empik.github;

import me.plich.empik.calculation.Calculation;
import me.plich.empik.database.DatabaseAccessor;
import me.plich.empik.dto.GithubUserDto;
import me.plich.empik.dto.UserDto;
import me.plich.empik.exception.SomeServerException;
import me.plich.empik.exception.UserNotFoundException;
import me.plich.empik.exception.ZeroFollowersNumberException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

@Service
public final class GithubDataFetcher {

    @Value("${url.github.users}")
    private String GITHUB_USERS_URL;
    private final Calculation calculationService;
    private final HttpDataFetcher restTemplateDataFetcher;
    private final DatabaseAccessor databaseAccessor;

    public GithubDataFetcher(Calculation calculationService, HttpDataFetcher restTemplateDataFetcher, DatabaseAccessor databaseAccessor) {
        this.calculationService = calculationService;
        this.restTemplateDataFetcher = restTemplateDataFetcher;
        this.databaseAccessor = databaseAccessor;
    }

    public UserDto getUserInfo(String login) {
        databaseAccessor.logQueryForUser(login);
        GithubUserDto githubUserDto = null;

        try {
            githubUserDto = restTemplateDataFetcher.makeGetRequest(GITHUB_USERS_URL + login, GithubUserDto.class);
            float calculations = calculationService.someImportantCalculations(BigDecimal.valueOf(githubUserDto.getFollowers()), BigDecimal.valueOf(githubUserDto.getPublicRepos())).floatValue();
            return UserDto.createUserDtoWithCalculations(githubUserDto, calculations);
        } catch (ZeroFollowersNumberException ex) {
            return UserDto.createUserDtoWithoutCalculations(githubUserDto);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new UserNotFoundException();
        } catch (Exception ex) {
            throw new SomeServerException();
        }
    }
}