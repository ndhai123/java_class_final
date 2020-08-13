package learnjava.final_homework.service;

import java.util.List;

import learnjava.final_homework.model.User;

public interface UserService {
	void save(User user);

	List<User> findAll();

	User findById(Long id);

	User findByUserName(String userName);

}
