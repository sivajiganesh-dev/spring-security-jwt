package xyz.housie.app.securityjwtservice.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.housie.app.securityjpaservice.user.model.User;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    public User findUserByUsername(String username);
}
