package cn.sher6j.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回前端的统一json数据
 * @author sher6j
 * @create 2020-05-19-13:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    //如：404(code) NOT_FOUND(message)
    private Integer code;
    private String  message;
    private T       data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
