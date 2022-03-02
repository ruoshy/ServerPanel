package cn.ousky.small.util;

import cn.ousky.small.model.Message;
import org.springframework.stereotype.Component;

@Component
public class ResultUtil {
    public Message getStateMessage(String msg, int code) {
        Message message = new Message();
        message.setCode(code);
        message.setMsg(msg);
        return message;
    }
}
