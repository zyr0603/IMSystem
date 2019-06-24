package version2_345.serialize;


import version2_345.serialize.impl.JSONSerializer;

public interface Serializer {
    /**
     * 定义一下序列化算法的类型以及默认序列化算法
     */
    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}

