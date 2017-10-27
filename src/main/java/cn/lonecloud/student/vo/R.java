package cn.lonecloud.student.vo;

import cn.lonecloud.student.cts.Rcts;

/**
 * @author lonecloud
 * @version v1.0
 * @date 下午7:09 2017/10/27
 */
public class R<T>{

    private int status;

    private String msg;

    private T data;

    public R(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public R(Rcts cts,T data){
        this.status=cts.getValue();
        this.msg=cts.getMsg();
        this.data=data;
    }

    public R(Rcts cts) {
        this.status=cts.getValue();
        this.msg=cts.getMsg();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public static R success(String msg){
        return new R(Rcts.SUCCESS.getValue(),msg,null);
    }
    public static R success(){
        return new R(Rcts.SUCCESS);
    }
    public static R error(String msg){
        return new R(Rcts.ERROR.getValue(),msg,null);
    }
    public static <T> R<T> success(T data){
        return new R(Rcts.SUCCESS,data);
    }
}
