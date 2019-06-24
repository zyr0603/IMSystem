package version3_11.attributes;

import io.netty.util.AttributeKey;

/**
 * 定义是否登录成功的标志位
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

}
