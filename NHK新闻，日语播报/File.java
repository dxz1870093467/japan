import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class File {
    public static void main(String[] args) {
        // 指定目标目录
        String directoryPath = "D:\\project\\japan\\japan\\NHK新闻，日语播报\\"; // 注意路径中要使用双反斜杠

        // 循环创建文件，从 0 到 100
        for (int i = 1; i <= 400; i++) {
            // 文件名
            String fileName = directoryPath + i + ".txt"; // 拼接目录和文件名
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                // 将数字写入文件
                writer.write(String.valueOf(i));
                System.out.println("文件 " + fileName + " 已成功创建，内容为：" + i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
