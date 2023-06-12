package shop.mtcoding;

import shop.mtcoding._core.ComponentScanner;
import shop.mtcoding._core.DispatcherServlet;

import java.util.Scanner;
import java.util.Set;

public class Main {

    /**
     * 요청1 : /joinForm
     * 요청2 : /login
     * 요청3 : /
     */
    public static void main(String[] args) throws Exception {
        // 1. URI 입력 받기
        Scanner sc = new Scanner(System.in);
        String uri = sc.nextLine();
        // String uri = "/joinForm";

        // 2. 컴포넌트 스캔
        Set<Class> classes = ComponentScanner.scanClass("shop.mtcoding");

        // 3. 입력받은 URI로 메소드 호출
        String response = DispatcherServlet.findUri(classes, uri);
        System.out.println(response);
    }

}