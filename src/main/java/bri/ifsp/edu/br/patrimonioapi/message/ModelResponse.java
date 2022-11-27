package bri.ifsp.edu.br.patrimonioapi.message;

import java.util.ArrayList;
import java.util.List;

public class ModelResponse<T> extends Response {

    private boolean error;

    private String message;

    private T object = null;

    private List<T> listObject = new ArrayList<>();

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean isError() {
        return error;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public List<T> getListObject() {
        return listObject;
    }

    public void setListObject(List<T> listObject) {
        this.listObject = listObject;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
