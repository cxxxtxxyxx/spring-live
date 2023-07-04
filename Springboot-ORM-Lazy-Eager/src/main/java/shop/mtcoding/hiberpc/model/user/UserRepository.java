package shop.mtcoding.hiberpc.model.user;

import org.springframework.stereotype.Repository;
import shop.mtcoding.hiberpc.model.MyRepository;

import javax.persistence.EntityManager;

@Repository
public class UserRepository extends MyRepository<User> {

    public UserRepository(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected String getEntityName() {
        return "User";
    }
}
