package app.ntnt.loadprofileretrofit;

import java.util.List;

import app.ntnt.loadprofileretrofit.model.Product;

public class NewResp {

    private boolean success;
    private String message;
    private List<NewProduct> result;

    public NewResp(boolean success, String message, List<NewProduct> result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<NewProduct> getResult() {
        return result;
    }

    public void setResult(List<NewProduct> result) {
        this.result = result;
    }
}
