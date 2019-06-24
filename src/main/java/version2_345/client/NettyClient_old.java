//package version3_11.client;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.util.concurrent.Future;
//import io.netty.util.concurrent.GenericFutureListener;
//
//public class NettyClient_old {
//    public static void main(String[] args) {
//        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
//
//        Bootstrap bootstrap = new Bootstrap();
//        bootstrap
//                // 1.指定线程模型
//                .group(workerGroup)
//                // 2.指定 IO 类型为 NIO
//                .channel(NioSocketChannel.class)
//                // 3.IO 处理逻辑
//                .handler(new ChannelInitializer<SocketChannel>() {
//                    @Override
//                    public void initChannel(SocketChannel ch) {
//                        // 指定连接数据读写逻辑
//                        ch.pipeline().addLast(new ClientHandler());
//                    }
//                });
//        // 4.建立连接
//        bootstrap.connect("127.0.0.1", 8000).addListener(new GenericFutureListener<Future<? super Void>>() {
//            public void operationComplete(Future<? super Void> future) throws Exception {
//                if (future.isSuccess()) {
//                    System.out.println("连接成功!");
//                } else {
//                    System.err.println("连接失败!");
//                }
//
//            }
//        });
//    }
//
//}
///*
//1 这个逻辑处理器继承自 ChannelInboundHandlerAdapter，然后覆盖了 channelActive()方法，这个方法会在客户端连接建立成功之后被调用
//2 客户端连接建立成功之后，调用到 channelActive() 方法，在这个方法里面，我们编写向服务端写数据的逻辑
//3 写数据的逻辑分为两步：首先我们需要获取一个 netty 对二进制数据的抽象 ByteBuf，上面代码中, ctx.alloc() 获取到一个 ByteBuf 的内存管理器，这个 内存管理器的作用就是分配一个 ByteBuf，然后我们把字符串的二进制数据填充到 ByteBuf，这样我们就获取到了 Netty 需要的一个数据格式，
//最后我们调用 ctx.channel().writeAndFlush() 把数据写到服务端
// */