package me.plich.empik.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
public final class UserDto {

        private Integer id;
        private String login;
        private String name;
        private String type;
        private String avatarUrl;
        private String createdAt;
        @Setter
        private Float calculations;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Setter
        private List<ErrorDto> errors;

        private UserDto(Integer id, String login, String name, String type, String avatarUrl, String createdAt, Float calculations) {
                this.id = id;
                this.login = login;
                this.name = name;
                this.type = type;
                this.avatarUrl = avatarUrl;
                this.createdAt = createdAt;
                this.calculations = calculations;
        }

        private UserDto(Integer id, String login, String name, String type, String avatarUrl, String createdAt, List<ErrorDto> errors) {
                this.id = id;
                this.login = login;
                this.name = name;
                this.type = type;
                this.avatarUrl = avatarUrl;
                this.createdAt = createdAt;
                this.errors = errors;
        }

        public static UserDto createUserDtoWithCalculations(GithubUserDto githubUserDto, Float calculations) {
                return new UserDto(githubUserDto.getId(), githubUserDto.getLogin(), githubUserDto.getName(), githubUserDto.getType(), githubUserDto.getAvatarUrl(), githubUserDto.getCreatedAt(), calculations);
        }

        public static UserDto createUserDtoWithoutCalculations(GithubUserDto githubUserDto) {
                return new UserDto(githubUserDto.getId(), githubUserDto.getLogin(), githubUserDto.getName(), githubUserDto.getType(), githubUserDto.getAvatarUrl(), githubUserDto.getCreatedAt(), Arrays.asList(new ErrorDto("calculations", null, "Field is null because user followers number is zero")));

        }
}
