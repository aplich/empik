package me.plich.empik.exception;

public class ZeroFollowersNumberException extends RuntimeException {
    public ZeroFollowersNumberException() {
        super("followersNumber cannot be zero");
    }
}
