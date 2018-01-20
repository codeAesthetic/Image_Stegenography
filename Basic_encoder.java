package Steg;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.util.Pair;

public class Basic_encoder implements Encoder {
    @Override
    public Image encode(Image image, String message) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        WritableImage copy = new WritableImage(image.getPixelReader(), width, height);
        PixelWriter writer = copy.getPixelWriter();
        PixelReader reader = image.getPixelReader();

        boolean[] bits = encode(message);

        int bound = bits.length;
        for (int i = 0; i < bound; i++) {
            Pair<Integer, Integer> pair = new Pair<>(i, reader.getArgb(i % width, i / width));
            Pair<Integer, Integer> encodeData = new Pair<>(pair.getKey(),
                    bits[pair.getKey()] ? pair.getValue() | 1 : pair.getValue() &~ 1);
            int x = encodeData.getKey() % width;
            int y = encodeData.getKey() / width;

            writer.setArgb(x, y, encodeData.getValue());
        }

        return copy;
    }

    private boolean[] encode(String message) {
        byte[] data = message.getBytes();

        boolean[] bits = new boolean[32 + data.length*8];

        // encode length
        String binary = Integer.toBinaryString(data.length);
        while (binary.length() < 32) {
            binary = "0" + binary;
        }

        for (int i = 0; i < 32; i++) {
            bits[i] = binary.charAt(i) == '1';
        }

        // encode message
        for (int i = 0; i < data.length; i++) {
            byte b = data[i];

            for (int j = 0; j < 8; j++) {
                bits[32 + i*8 + j] = ((b >> (7 - j)) & 1) == 1;
            }
        }

        return bits;
    }
}