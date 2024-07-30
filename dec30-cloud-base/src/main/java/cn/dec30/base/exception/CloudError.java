package cn.dec30.base.exception;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/25 22:10
 * @description 底层error
 */
public enum CloudError implements Error {

    OK(200, "处理成功！"),
    INVALID_PARAM(400, "参数错误！"),
    SYS_BUSY(800, "系统繁忙，请稍候重试！"),

    ;

    private final int code;
    private final String msg;

    CloudError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
