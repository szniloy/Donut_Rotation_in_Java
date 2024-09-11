import java.util.Arrays;

public class Main {

    private static float A = 0, B = 0;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 22;
    private static final char[] CHARS = ".,-~:;=!*#$@".toCharArray();

    public static void main(String[] args) {


        float[] z = new float[WIDTH * HEIGHT];
        char[] b = new char[WIDTH * HEIGHT];

        String[] colors = {
                "\u001b[31m", // Red
                "\u001b[32m", // Green
                "\u001b[33m", // Yellow
                "\u001b[34m", // Blue
                "\u001b[35m", // Magenta
                "\u001b[36m", // Cyan
                "\u001b[37m", // White
        };

        while (true) {
            Arrays.fill(b, ' ');
            Arrays.fill(z, 0);

            for (float j = 0; j < 2 * Math.PI; j += 0.07F) {
                for (float i = 0; i < 2 * Math.PI; i += 0.02F) {
                    float c = (float) Math.sin(i);
                    float d = (float) Math.cos(j);
                    float e = (float) Math.sin(A);
                    float f = (float) Math.sin(j);
                    float g = (float) Math.cos(A);
                    float h = d + 2;
                    float D = 1 / (c * h * e + f * g + 5);
                    float l = (float) Math.cos(i);
                    float m = (float) Math.cos(B);
                    float n = (float) Math.sin(B);
                    float t = c * h * g - f * e;

                    int x = (int) (40 + 30 * D * (l * h * m - t * n));
                    int y = (int) (12 + 15 * D * (l * h * n + t * m));
                    int o = x + WIDTH * y;
                    int N = (int) (8 * ((f * e - c * d * g) * m - c * d * e - f * g - l * d * n));

                    if (0 <= y && y < HEIGHT && 0 <= x && x < WIDTH && D > z[o]) {
                        z[o] = D;
                        b[o] = CHARS[Math.max(N, 0)];
                    }
                }
            }

            printScreen(b, z, colors);
            A += 0.04F;
            B += 0.02F;

            try {
                Thread.sleep(30); // Delay between frames
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printScreen(char[] b, float[] z, String[] colors) {
        System.out.print("\033[H");
        for (int k = 0; k < b.length; k++) {
            if (k % WIDTH == 0 && k != 0) {
                System.out.println();
            }
            int colorIndex = (int) (z[k] * 7);  // Mapping depth to a color index
            colorIndex = Math.max(0, Math.min(colorIndex, colors.length - 1));
            System.out.print(colors[colorIndex] + b[k]);
        }
        System.out.print("\u001b[0m"); // Reset color at the end
        System.out.flush();
    }
}