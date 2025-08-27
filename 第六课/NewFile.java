import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class NewFile {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("1000句常用生活用语.txt", false))) {
            for (int i = 1; i <= 1000; i++) {
                String fullWidthNumber = toFullWidthNumber(i);
                writer.write(fullWidthNumber + "．");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 将阿拉伯数字转换为全角数字
    private static String toFullWidthNumber(int number) {
        String numStr = String.valueOf(number);
        StringBuilder fullWidth = new StringBuilder();
        for (char c : numStr.toCharArray()) {
            if (Character.isDigit(c)) {
                fullWidth.append((char) ('０' + (c - '0')));
            } else {
                fullWidth.append(c);
            }
        }
        return fullWidth.toString();
    }
}
