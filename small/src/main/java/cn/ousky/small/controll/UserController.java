package cn.ousky.small.controll;

import cn.ousky.small.server.UserServer;
import cn.ousky.small.model.Message;
import cn.ousky.small.model.RegInfo;
import cn.ousky.small.util.Md5;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private UserServer userSeriver; // 用户

    /**
     * 通过字段phone 查询用户信息
     *
     * @param phone 手机号
     * @return User
     */
    @RequestMapping("/getReceivingAddress")
    public String getAddressByPhone(@RequestParam("phone") String phone) {
        RegInfo info = new RegInfo();
        info.setPhone(phone);
        return userSeriver.getAddressByPhone(info).toString();
    }

    /**
     * 发送验证码
     *
     * @param phone 短信(邮箱)
     * @return Message
     */
    @RequestMapping("/sendEmail")
    public Message sendEmail(@RequestParam("phone") String phone) {

        return userSeriver.sendEmail(phone);
    }

    /**
     * 用户注册
     *
     * @param info 用户基本信息
     * @return Message
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Message register(@RequestBody RegInfo info) {
        return userSeriver.register(info);
    }

    /**
     * 更改用户密码
     *
     * @param info 用户基本信息
     * @return Message
     */
    @RequestMapping(value = "/updpwd", method = RequestMethod.POST)
    public Message updatePwd(@RequestBody RegInfo info) {
        return userSeriver.updatePwd(info);
    }


    /**
     * 登录
     *
     * @param info 用户基本信息
     * @return Message
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Message login(@RequestBody RegInfo info) throws Exception {
        Md5.getMd5(info.getPhone());
        return userSeriver.login(info);
    }
}
