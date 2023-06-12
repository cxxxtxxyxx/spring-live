package ex02;


/**
 * 1. 너가 메서드를 만들고 싶으면 @RequestMapping 어노테이션을 사용해야돼
 * 2. 해당 어노테이션에 uri에 주소 값을 입력해두면, 그대로 작동할거야!!
 */
public class UserController {
    @RequestMapping(uri = "/login")
    public void login(){
        System.out.println("login() 호출됨");
    }

    @RequestMapping(uri = "/join")
    public void join(){
        System.out.println("join() 호출됨");
    }

    @RequestMapping(uri = "/save")
    public void save(){
        System.out.println("save() 호출됨");
    }
}
