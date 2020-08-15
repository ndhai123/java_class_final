package learnjava.final_homework.service;

import java.util.Date;
import java.util.List;
import learnjava.final_homework.model.Student;


public interface StudentService {
	void save (Student student);
	List<Student> findAll();
	List<Student> searchByName(String fullName);
	Student findById(Long id);
	void deleteById(Long id);
	void upDateById(Long id, String fullName, String sex, Date birthday, Long classId);
	

}
