package version2_345.serialize.impl;

import com.alibaba.fastjson.JSON;
import version2_345.serialize.Serializer;
import version2_345.serialize.SerializerAlogrithm;

public class JSONSerializer implements Serializer {
    // 选择序列化方法为json序列化
    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }
}