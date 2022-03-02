package cn.ousky.small.server;

import cn.ousky.small.entity.User;
import cn.ousky.small.mapper.IUserMapper;
import cn.ousky.small.model.Message;
import cn.ousky.small.model.RegInfo;
import cn.ousky.small.util.ResultUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class UserServer {

    @Resource
    private IUserMapper userMapper;

    @Resource
    private ResultUtil resultUtil;

    @Resource
    private JavaMailSender mailSender; //邮件发送器

    @Resource
    private RedisTemplate<String, Object> redisTemplate; //redis模板

    public User getAddressByPhone(RegInfo info) {
        return userMapper.findByPhone(info);
    }

    /**
     * 发送邮件
     *
     * @param phone 手机号(邮箱)
     * @return
     */
    public Message sendEmail(String phone) {
        // 生成验证码
        int code = (int) (Math.random() * 8999 + 1000);
        // 写入验证码存在3分钟
        redisTemplate.opsForValue().set(phone, code, 3, TimeUnit.MINUTES);
        SimpleMailMessage smessage = new SimpleMailMessage();
        smessage.setFrom("2018115248@qq.com");
        smessage.setTo(phone);
        smessage.setSubject("验证码");
        smessage.setText("验证码：" + code + "，有效期三分钟。");
        int mcode = 1;
        try {
            mailSender.send(smessage); //发送短信
        } catch (Exception e) {
            mcode = 0;
        }
        return resultUtil.getStateMessage(null, mcode);
    }

    public Message register(RegInfo info) {
        int code = 0;
        // 效验验证码
        if (info.getCode().equals(redisTemplate.opsForValue().get(info.getPhone()))) {
            // 注册
            code = userMapper.insertReg(info);
        }
        return resultUtil.getStateMessage(null, code);
    }

    /**
     * 更新密码
     * @param info
     * @return
     */
    public Message updatePwd(RegInfo info) {
        int code = 0;
        if (info.getCode().equals(redisTemplate.opsForValue().get(info.getPhone()))) {
            code = userMapper.updatePwd(info);
        }
        return resultUtil.getStateMessage(null, code);
    }

    /**
     * 登录
     * @param info
     * @return
     */
    public Message login(RegInfo info) {
        // 判断密码是否合法
        if (info.getPwd() == null) {
            return resultUtil.getStateMessage("请输入密码", 0);
        }
        // 判断是否有记录
        User user = userMapper.findByPhone(info);
        if (user == null) {
            return resultUtil.getStateMessage("无该用户", 0);
        }
        return resultUtil.getStateMessage("登录成功", 1);
    }
}
