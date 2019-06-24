package version3_11.protocol.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import version3_11.protocol.Packet;

import static version2_345.protocol.command.Command.MESSAGE_REQUEST;

/**
 * 客户端发送至服务端的消息对象
 */

@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    private String message;

    public MessageRequestPacket(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}