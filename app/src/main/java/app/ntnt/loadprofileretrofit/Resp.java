package app.ntnt.loadprofileretrofit;

public class Resp {
    private String error;
    private String  message;
    private User user;

    public Resp(String error, String message, User user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
