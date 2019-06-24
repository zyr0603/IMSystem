package version3_11.protocol.response;

import lombok.Data;
import version3_11.protocol.Packet;

import static version2_345.protocol.command.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}