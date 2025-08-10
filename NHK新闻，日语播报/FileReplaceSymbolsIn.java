import java.io.*;

public class FileReplaceSymbolsIn {
    public static void main(String[] args) {
        // 指定文件夹路径
        String folderPath = "D:\\project\\japan\\japan\\NHK新闻，日语播报";
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("文件夹不存在或不是文件夹：" + folderPath);
            return;
        }

        // 要删除的符号列表
        String symbols = "[*\\-#]";

        // 遍历文件夹中的所有文件
        File[] files = folder.listFiles();
        if (files == null) {
            System.out.println("读取文件夹内容失败");
            return;
        }

        for (File file : files) {
            // 只处理普通文件且后缀为 .txt 的文件
            if (file.isFile() && file.getName().endsWith(".txt")) {
                System.out.println("处理文件: " + file.getName());

                StringBuilder content = new StringBuilder();

                // 读取文件内容并替换符号
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String replaced = line.replaceAll(symbols, "");
                        content.append(replaced).append(System.lineSeparator());
                    }
                } catch (IOException e) {
                    System.err.println("读取文件 " + file.getName() + " 失败：" + e.getMessage());
                    continue; // 出错后继续处理下一个文件
                }

                // 写回文件（覆盖原文件）
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    bw.write(content.toString());
                } catch (IOException e) {
                    System.err.println("写入文件 " + file.getName() + " 失败：" + e.getMessage());
                }
            }
        }

        System.out.println("所有文件符号替换完成！");
    }
}
