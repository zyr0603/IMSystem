package version2_345.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
// 通信过程中的java对象
@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;


    @JSONField(serialize = false)
    public abstract Byte getCommand();

    public byte getVersion() {
        return version;
    }


}