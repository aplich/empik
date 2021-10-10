package me.plich.empik.controller;

import lombok.extern.slf4j.Slf4j;
import me.plich.empik.github.GithubDataFetcher;
import me.plich.empik.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
class UserController {

    private final GithubDataFetcher dataFetcher;

    public UserController(GithubDataFetcher dataFetcher) {
        this.dataFetcher = dataFetcher;
    }

    @GetMapping(value = "/users/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> getUserInfo(@PathVariable String login) {
        log.info("Incoming request with login {}", login);
        UserDto userDto = dataFetcher.getUserInfo(login);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
