package version2_345.protocol.response;

import version2_345.protocol.Packet;

import static version2_345.protocol.command.Command.LOGIN_RESPONSE;
import lombok.Data;

@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}