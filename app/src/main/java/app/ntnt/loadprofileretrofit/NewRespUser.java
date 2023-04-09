package app.ntnt.loadprofileretrofit;

import java.util.List;

public class NewRespUser {
    private  String success,message;
    private List<User> result;

    public NewRespUser(String success, String message, List<User> result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public NewRespUser() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }
}
