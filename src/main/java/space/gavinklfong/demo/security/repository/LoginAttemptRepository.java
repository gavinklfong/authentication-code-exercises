package space.gavinklfong.demo.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import space.gavinklfong.demo.security.model.LoginAttempt;

import java.util.List;

@Repository
public interface LoginAttemptRepository extends CrudRepository <LoginAttempt, Long> {

    List<LoginAttempt> findByIpAddress(String ipAddress);

    List<LoginAttempt> findByUsername(String username);
}
