package entity;

/**
 * @author Luomo
 * Date : 2020/5/2
 * Time : 9:12
 * LastEditor Luomo
 */
public class Result<T> {

    private Boolean flag;
    private Integer code;
    private String message;
    private Integer status;
    private T data;

    public Result() {
    }

    public Result(Boolean flag, Integer status,Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message  = message;
        this.status  = status;

    }

    public Result(Boolean flag,Integer status, Integer code, String message, T data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
        this.status  = status;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
