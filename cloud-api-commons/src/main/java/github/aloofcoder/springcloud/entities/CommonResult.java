package github.aloofcoder.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @param <T>
 * @author hanle
 * @date 2020-03-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public CommonResult(Integer code, String msg) {
        this(code, msg, null);
    }
}
