package version2_345.protocol.request;

import lombok.Data;
import version2_345.protocol.Packet;

import static version2_345.protocol.command.Command.LOGIN_REQUEST;

/**
 * 定义登陆请求数据包
 */
@Data
public class LoginRequestPacket extends Packet {
    private String UserId;

    private String Username;

    private String Password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }


}