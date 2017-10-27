package cn.lonecloud.student.cts;

/**
 * @author lonecloud
 * @version v1.0
 * @date 下午7:11 2017/10/27
 */
public enum Rcts {

    SUCCESS(200, "success"), ERROR(500, "error");


    private int value;

    private String msg;

    Rcts(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
