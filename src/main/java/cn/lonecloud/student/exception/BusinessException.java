package cn.lonecloud.student.exception;

/**
 * @author lonecloud
 * @version v1.0
 * @date 下午11:00 2017/10/26
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {

    }

    public BusinessException(String message) {
        super(message);
    }
}
