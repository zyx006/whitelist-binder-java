package cn.czyx007.whitelist.service;

import cn.czyx007.whitelist.bean.User;
import cn.czyx007.whitelist.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    @Autowired
    private RconService rconService;

    public boolean isUsernameExists(String username) {
        boolean exists = lambdaQuery().eq(User::getUsername, username).exists();
        if (exists) {
            log.info("用户名已存在: {}", username);
        }
        return exists;
    }

    @Transactional(rollbackFor = Exception.class)
    public void bindUser(User user) throws Exception {
        log.info("正在绑定用户: {}", user);

        // 检查学号是否已绑定
        boolean studentIdExists = lambdaQuery()
                .eq(User::getStudentId, user.getStudentId())
                .exists();

        if (studentIdExists) {
            log.warn("学号已被绑定: {}", user.getStudentId());
            throw new IllegalStateException("该学号已经绑定过用户名");
        }

        try {
            rconService.addWhitelist(user.getUsername());
            save(user);
            log.info("用户保存成功: {}", user);
        } catch (Exception e) {
            log.error("绑定用户{}时出错: {}", user, e.getMessage());
            throw e;
        }
    }
}
