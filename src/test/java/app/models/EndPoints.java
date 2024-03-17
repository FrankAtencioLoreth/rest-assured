package app.models;

public enum EndPoints {

    REGISTER("register"),
    USERS("users/{userId}"),
    USER("users"),
    AUTH("login");

    private String path;

    EndPoints(String path) {
        this.path = path;
    }

    public String path() {
        return this.path;
    }


}
