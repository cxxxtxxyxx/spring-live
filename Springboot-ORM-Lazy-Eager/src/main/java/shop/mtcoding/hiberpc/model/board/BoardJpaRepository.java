package shop.mtcoding.hiberpc.model.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardJpaRepository extends JpaRepository<Board, Integer> {

    @Query("select b from Board b join b.user")
    List<Board> findAllJoin();

    @Query("select b from Board b join fetch b.user")
    List<Board> findAllJoinFetch();

//    public List<Board> findAllJoin(){
//        return em.createQuery("select b from Board b join b.user", getEntityClass()).getResultList();
//    }
//
//    public List<Board> findAllJoinFetch(){
//        return em.createQuery("select b from Board b join fetch b.user", getEntityClass()).getResultList();
//    }
}
