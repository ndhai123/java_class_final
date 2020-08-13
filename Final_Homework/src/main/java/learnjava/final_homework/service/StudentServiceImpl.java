package learnjava.final_homework.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learnjava.final_homework.model.Student;
import learnjava.final_homework.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired // Khai bien o trong service phai dung
	private StudentRepository studentRepository; // Khai bao bien de su dung ham trong repository
	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		studentRepository.save(student);
		
	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public Student findById(Long id) {
		// TODO Auto-generated method stub
		return studentRepository.getOne(id);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(id);
		
	}

	@Override
	public void upDateById(Long id, String fullName, String sex, Date birthday,  Long classId) {
		// TODO Auto-generated method stub
		studentRepository.upDateById(id, fullName, sex, birthday, classId);
	}


}
