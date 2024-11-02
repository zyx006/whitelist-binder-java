package cn.czyx007.whitelist.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserBindRequest {
    @Pattern(regexp = "^[0-9]{10}$", message = "学号必须为10位数字")
    private String studentId;

    @Size(min = 1, message = "姓名为必填项")
    private String name;

    @Pattern(regexp = "^(?!\\d+$)[a-zA-Z0-9_]{3,16}$",
            message = "用户名长度需在3到16个字符之间，只能包含字母、数字和下划线，且不能为纯数字")
    private String username;
}