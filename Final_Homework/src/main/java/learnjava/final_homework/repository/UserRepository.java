package learnjava.final_homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import learnjava.final_homework.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String username);
}
