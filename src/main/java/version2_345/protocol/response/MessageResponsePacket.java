package version2_345.protocol.response;

import lombok.Data;
import version2_345.protocol.Packet;

import static version2_345.protocol.command.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {
    private String message;

    @Override
    public Byte getCommand(){
        return MESSAGE_RESPONSE;
    }
}
