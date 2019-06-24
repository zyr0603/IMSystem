package version2_345.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import version2_345.protocol.Packet;
import version2_345.protocol.PacketCodeC;
import version2_345.protocol.request.LoginRequestPacket;
import version2_345.protocol.response.LoginResponsePacket;
import version2_345.protocol.response.MessageResponsePacket;
import version2_345.util.LoginUtil;

import java.util.Date;
import java.util.UUID;
/**
 * 客户端发送登录请求
 */
// 我们实现在客户端连接上服务端之后，立即登录
// 在连接上服务端之后，Netty 会回调到 ClientHandler 的 channelActive() 方法
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        System.out.println(new Date() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("zyr");
        loginRequestPacket.setPassword("pwd");
        // 编码
        //实参 ctx.alloc() 获取的就是与当前连接相关的 ByteBuf 分配器
        ByteBuf buf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);
        // 写数据
        // 写数据的时候，我们通过 ctx.channel() 获取到当前连接（Netty 对连接的抽象为 Channel)
        ctx.channel().writeAndFlush(buf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if (loginResponsePacket.isSuccess()) {
                LoginUtil.maskAsLogin(ctx.channel());
                System.out.println(new Date() + ": 客户端登录成功");
            } else {
                System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
        }else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
        }
    }
}
