package hello.repositories;

import hello.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByStatus(boolean status);


    @Override
    List<User> findAll();
}
