package version2_345.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import version2_345.protocol.Packet;
import version2_345.protocol.PacketCodeC;
import version2_345.protocol.request.LoginRequestPacket;
import version2_345.protocol.request.MessageRequestPacket;
import version2_345.protocol.response.LoginResponsePacket;
import version2_345.protocol.response.MessageResponsePacket;

import java.util.Date;

/**
 * 服务端处理登录请求
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        System.out.println(new Date() + ": 客户端开始登录……");

        ByteBuf requestByteBuf = (ByteBuf) msg;

        // 解码
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        // 判断是否是登录请求数据包
        if (packet instanceof LoginRequestPacket) {
            // 登录流程
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            if (valid(loginRequestPacket)) {
                // 校验成功
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": 登录成功!");
            } else {
                // 校验失败
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(new Date() + ": 登录失败!");
            }
            // 登录响应
            // 编码
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(),
                    loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);

        //
        }else if (packet instanceof MessageRequestPacket) {
            // 客户端发来消息
            MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            System.out.println(new Date() + ": 收到客户端消息: "
                    + messageRequestPacket.getMessage());
            messageResponsePacket.setMessage("服务端回复【"
                    + messageRequestPacket.getMessage() + "】");
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(),
                    messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }


    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
