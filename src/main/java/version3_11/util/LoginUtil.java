package version3_11.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import version3_11.attributes.Attributes;

/**
 * LoginUtil用于设置登录标志位以及判断是否有标志位
 */
public class LoginUtil {
    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }
    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }

}
