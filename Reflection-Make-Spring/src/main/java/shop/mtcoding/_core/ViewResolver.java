package shop.mtcoding._core;


import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ViewResolver {

    public static String convert(String fileName) throws Exception {
        // 프로젝트의 root 경로를 찾음 (자바 소스 파일을 찾을 때는 ClassLoader 사용)
        String projectRootPath = System.getProperty("user.dir");
        // mac 과 window 와 linux 파일 시스템이 달라서 / or \ 등을 운영체제 시스템에 맞추는 방법 : File.separator
        String resourcePath = projectRootPath + File.separator+"build"+ File.separator+"resources"+ File.separator+"main"+ File.separator+"templates"+File.separator;
        fileName = fileName.replace("/", File.separator);
        return htmlFileRead(resourcePath+fileName+".html");
    }

    private static String htmlFileRead(String HTMLFilePath) throws Exception {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(HTMLFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        return contentBuilder.toString();
    }
}
