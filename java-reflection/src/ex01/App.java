package ex01;

import java.util.Scanner;


// 1차 개발자 (홍길동)
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();

        // path = "/login" -> uc.login()
        // path = "/join" -> uc.join();
        UserController uc = new UserController();

        if (path.equals("/login")) {
            uc.login();
        } else if (path.equals("/join")) {
            uc.join();
        } else if (path.equals("/save")) {
            uc.save();
        }
    }
}
