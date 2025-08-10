import java.io.*;

public class FileDoName {
    public static void main(String[] args) throws InterruptedException {
        String inputFolder = "D:\\project\\japan\\japan\\NHK新闻，日语播报\\";
        File folder = new File(inputFolder);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        int start = 134;
        int end = 134;

        for (int i = start; i <= end; i++) {
            String originalFileName = i + ".txt";
            File originalFile = new File(folder, originalFileName);

            if (!originalFile.exists()) {
                System.out.println("文件不存在，跳过：" + originalFileName);
                continue;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(originalFile))) {
                String firstLine = br.readLine();
                if (firstLine == null || firstLine.trim().isEmpty()) {
                    System.out.println("文件 " + originalFileName + " 第一行为空，无法用作文件名，跳过");
                    continue;
                }

                String safeName = sanitizeFileName(firstLine.trim());
                String newFileName = i + " " + safeName + ".txt";
                File newFile = new File(folder, newFileName);

                if (newFile.exists()) {
                    System.out.println("目标文件名已存在，跳过：" + newFileName);
                    continue;
                }

                // 读取原文件全部内容
                StringBuilder content = new StringBuilder();
                String line;
                // 已经读过第一行了，如果想把第一行也写入新文件，要加回来
                content.append(firstLine).append(System.lineSeparator());
                while ((line = br.readLine()) != null) {
                    content.append(line).append(System.lineSeparator());
                }

                // 创建新文件并写入内容
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(newFile))) {
                    bw.write(content.toString());
                }
            } catch (IOException e) {
                System.err.println("处理文件 " + originalFileName + " 出错：" + e.getMessage());
            }

            // 删除原文件
            if (originalFile.delete()) {
                System.out.println("文件 " + originalFileName + " 删除成功，已创建新文件 ");
            } else {
                System.err.println("文件 " + originalFileName + " 删除失败！");
            }

        }
    }

    private static String sanitizeFileName(String name) {
        return name.replaceAll("[\\\\/:*?\"<>|]", "_");
    }
}
