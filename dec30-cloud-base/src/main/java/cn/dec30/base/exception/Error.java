package cn.dec30.base.exception;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/25 22:09
 * @description todo
 */
public interface Error {

    int getCode();

    String getMsg();

    static Error build(int code, String msg) {
        return new Error() {
            @Override
            public int getCode() {
                return code;
            }

            @Override
            public String getMsg() {
                return msg;
            }
        };
    }
}
