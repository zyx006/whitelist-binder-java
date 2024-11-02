package cn.czyx007.whitelist.controller;

import cn.czyx007.whitelist.bean.User;
import cn.czyx007.whitelist.dto.UserBindRequest;
import cn.czyx007.whitelist.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WhitelistController {
    @Autowired
    private UserService userService;

    @PostMapping("/bind")
    public ResponseEntity<?> bindUser(@Valid @RequestBody UserBindRequest request) {
        Map<String, String> response = new HashMap<>();

        if (userService.isUsernameExists(request.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "用户名已存在，请选择其他用户名。"));
        }

        try {
            User user = new User();
            user.setStudentId(request.getStudentId());
            user.setName(request.getName());
            user.setUsername(request.getUsername());

            userService.bindUser(user);

            return ResponseEntity.ok()
                    .body(Map.of("message", "成功绑定用户名并添加白名单！"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("message", "添加白名单时出错，请稍后再试。"));
        }
    }
}