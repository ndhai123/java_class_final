package learnjava.final_homework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import learnjava.final_homework.model.Class;


import learnjava.final_homework.repository.ClassRepository;

@Service
public class ClassServiceIpml implements ClassService {
	
	@Autowired 
	private ClassRepository classRepository;
	
	@Override
	public List<Class> findAll() {
		// TODO Auto-generated method stub
		return classRepository.findAll();
	}
	

}
