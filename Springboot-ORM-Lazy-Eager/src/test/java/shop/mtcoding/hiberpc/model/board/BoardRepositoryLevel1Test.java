package shop.mtcoding.hiberpc.model.board;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.hiberpc.model.MyDummyEntity;
import shop.mtcoding.hiberpc.model.user.User;
import shop.mtcoding.hiberpc.model.user.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import({UserRepository.class, BoardRepository.class})
@DataJpaTest
public class BoardRepositoryLevel1Test extends MyDummyEntity {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp(){
        em.createNativeQuery("ALTER TABLE user_tb ALTER COLUMN id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE board_tb ALTER COLUMN id RESTART WITH 1").executeUpdate();
    }

    // 컨트롤러에서 유저에게 받은 정보는 신뢰할 수 없다
    // 모든 정보는 DB에서 조회해서 확인해야 한다.
    @Test
    public void save_test(){ // @ManyToOne(User, Eager)
        // given
        userRepository.save(newUser("ssar"));
        em.clear();

        User userPS = userRepository.findById(1); // 연관관계에서 insert시에는 조회(영속화)된 것을 입력해야 한다
        Board board = newBoard("제목1", userPS);
        // when
        Board boardPS = boardRepository.save(board);
        System.out.println("테스트 : "+boardPS);

        // then
        assertThat(boardPS.getId()).isEqualTo(1);
        assertThat(boardPS.getUser().getId()).isEqualTo(1);
    }

    @Test
    public void findById_test(){ // @ManyToOne(User, Eager)
        // given 1
        User userPS = userRepository.save(newUser("ssar"));
        boardRepository.save(newBoard("제목1", userPS));

        // given 2
        int id = 1;

        em.clear();
        // when
        Board boardPS = boardRepository.findById(id);
//        System.out.println("테스트" + boardPS.getUser()); // 지연로딩 없어서 select 해줌)

        System.out.println("테스트" + boardPS.getUser().getId());
//        System.out.println("테스트" + boardPS.getUser().getUsername()); // 지연로딩 없어서 select 해줌)



        // then
        assertThat(boardPS.getTitle()).isEqualTo("제목1");
    }

    @Test
    public void update_test(){ // @ManyToOne(User, Eager)
        // given 1
        User userPS = userRepository.save(newUser("ssar"));
        boardRepository.save(newBoard("제목1", userPS));
        em.clear();

        // given 2
        String title = "제목12";
        String content = "내용12";

        // when
        Board boardPS = boardRepository.findById(1);
        boardPS.update(title, content);
        em.flush();
        em.clear();

        // then
        Board findBoardPS = boardRepository.findById(1);
        assertThat(findBoardPS.getContent()).isEqualTo("내용12");
    }

    @Test
    public void delete_test(){ // @ManyToOne(User, Eager)
        // given 1
        User userPS = userRepository.save(newUser("ssar"));
        boardRepository.save(newBoard("제목1", userPS));

        // given 2
        int id = 1;
        Board findBoardPS = boardRepository.findById(id);

        // when
        boardRepository.delete(findBoardPS);

        // then
        Board deleteBoardPS = boardRepository.findById(1);
        Assertions.assertThat(deleteBoardPS).isNull();
    }



    @Test
    public void findAll_test(){ // @ManyToOne(User, Eager)
        // given
        findAll_given();

        // when
        List<Board> boardListPS = boardRepository.findAll();
        System.out.println("테스트 : "+boardListPS);

        // then
        assertThat(boardListPS.size()).isEqualTo(3);
    }

    private void findAll_given(){
        User ssarPS = userRepository.save(newUser("ssar"));
        User cosPS = userRepository.save(newUser("cos"));
        User lovePS = userRepository.save(newUser("love"));
        List<Board> boardList = Arrays.asList(newBoard("제목1", ssarPS), newBoard("제목2", cosPS),newBoard("제목3", lovePS));
        boardList.stream().forEach((board)->{
            boardRepository.save(board);
        });
        em.clear();
    }
}
