package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class NioMain {

    public static void main(String[] args) {
        var main = new NioMain();
        var buffer = main.read();
        main.write(buffer);
    }

    private ByteBuffer read() {
        var buffer = ByteBuffer.allocate(1024);
        try (var inputStream = new FileInputStream("src/java-prom-path/io-and-nio/src/nio/info.txt")) {
            try (var inputChannel = inputStream.getChannel()) {
                buffer.clear();
                inputChannel.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    private void write(ByteBuffer buffer) {
        try (var outputStream = new FileOutputStream("src/java-prom-path/io-and-nio/src/nio/new.txt")) {
            try (var outputChannel = outputStream.getChannel()) {
                buffer.flip();
                outputChannel.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}