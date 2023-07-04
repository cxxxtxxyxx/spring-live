package shop.mtcoding.hiberpc.model.board;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.hiberpc.model.MyDummyEntity;
import shop.mtcoding.hiberpc.model.user.User;
import shop.mtcoding.hiberpc.model.user.UserRepository;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import({UserRepository.class, BoardRepository.class})
@DataJpaTest
public class BoardRepositoryLevel2Test extends MyDummyEntity {

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

    @Test
    public void findByIdLazy_test(){ // @ManyToOne(User, Lazy)
        // given 1
        User userPS = userRepository.save(newUser("ssar"));
        boardRepository.save(newBoard("제목1", userPS));
        em.clear();

        // given 2
        int id = 1;

        // when
        System.out.println("테스트 : board만 조회 ======================");
        Board boardPS = boardRepository.findById(id);

        // then
        assertThat(boardPS.getTitle()).isEqualTo("제목1");
    }

    @Test
    public void findByIdLazyLoading_test(){ // @ManyToOne(User, Lazy)
        // given 1
        User userPS = userRepository.save(newUser("ssar"));
        boardRepository.save(newBoard("제목1", userPS));
        em.clear();

        // given 2
        int id = 1;

        // when
        System.out.println("테스트 : board만 조회 ======================");
        Board boardPS = boardRepository.findById(id);
        System.out.println("테스트 : username Lazy Loading ======================");
        String username = boardPS.getUser().getUsername();
        System.out.println("테스트 : "+username);

        // then
        assertThat(boardPS.getTitle()).isEqualTo("제목1");
    }

    // 조인을 해서 가져오면, JPA가 제공해주는 paging이라는 강력한 라이브러리를 활용하지 못함
    // 직접 구현해야함
    @Test
    public void findAllJoin_test(){ // @ManyToOne(User, Eager)
        // given
        findAll_given();

        // when
        List<Board> boardListPS = boardRepository.findAllJoin();
        System.out.println("테스트 : "+boardListPS);

        // then
        assertThat(boardListPS.size()).isEqualTo(3);
    }

    @Test
    public void findAllJoinFetch_test(){ // @ManyToOne(User, Eager)
        // given
        findAll_given();

        // when
        List<Board> boardListPS = boardRepository.findAllJoinFetch();
        System.out.println("테스트 : "+boardListPS);

        // then
        assertThat(boardListPS.size()).isEqualTo(3);
    }

    // 1 + 1 (100개까지는)
    // 1000개는 1 + 10
    @Test
    public void findAllBatchFetchSize_test(){ // // @ManyToOne(User, Eager), default_batch_fetch_size: 100
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
