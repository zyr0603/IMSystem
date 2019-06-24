package version2_345.protocol.request;

import lombok.Data;
import version2_345.protocol.Packet;

import static version2_345.protocol.command.Command.MESSAGE_REQUEST;

/**
 * 客户端发送至服务端的消息对象
 */
@Data
public class MessageRequestPacket extends Packet {
    private String message;
    @Override
    public Byte getCommand(){
        return MESSAGE_REQUEST;
    }

}
