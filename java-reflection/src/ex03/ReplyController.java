package ex03;


@Controller
public class ReplyController {

    @RequestMapping(uri = "/reply/write")
    public void write() {
        System.out.println("ReplyController.write");
    }
}
