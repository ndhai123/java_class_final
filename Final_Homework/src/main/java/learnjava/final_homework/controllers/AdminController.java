package learnjava.final_homework.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import learnjava.final_homework.form.StudentForm;
import learnjava.final_homework.model.Student;
import learnjava.final_homework.service.ClassService;
import learnjava.final_homework.service.StudentService;
import learnjava.final_homework.model.Class;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private ClassService classService;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String showAdmin(Model model) {
		System.out.print("Test");
		return "admin";
	}

	@RequestMapping(value = { "/studentList" }, method = RequestMethod.GET)
	public String studentList(Model model) {
		List<Student> list = studentService.findAll();
		model.addAttribute("students", list);
		return "studentList";
	}

	@RequestMapping(value = { "/addStudent" }, method = RequestMethod.GET)
	public String showAddStudentPage(Model model) {

		StudentForm studentForm = new StudentForm();
		model.addAttribute("studentForm", studentForm);

		List<Class> listClass = classService.findAll();
		model.addAttribute("listClass", listClass);
		return "addStudent";
	}

	@Value("${error.message}")
	private String errorMessage;

	@RequestMapping(value = { "/addStudent" }, method = RequestMethod.POST)
	public String saveStudent(Model model, //
			@ModelAttribute("studentForm") StudentForm studentForm) throws ParseException {

		String fullName = studentForm.getFullName();
		String sex = studentForm.getSex();
		Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(studentForm.getBirthday());
		Long classId = studentForm.getClassId();

		Student newStudent = new Student(fullName, sex, birthday, classId);
		studentService.save(newStudent);

		model.addAttribute("errorMessage", errorMessage);
		return "redirect:/admin/studentList";
	}

	@RequestMapping(value = { "/deleteStudent" }, method = RequestMethod.GET)
	public String deleteStudent(Model model, @RequestParam(name = "id") long id) {
		studentService.deleteById(id);
		return "redirect:/admin/studentList";
	}

	@RequestMapping(value = { "/show_edit_page" }, method = RequestMethod.GET)
	public String showEditStudent(Model model, @RequestParam(name = "id") Long id) throws ParseException {

		Student student = studentService.findById(id);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String birthday = simpleDateFormat.format(student.getBirthday());
		
		StudentForm stForm = new StudentForm();
		stForm.setFullName(student.getFullName());
		stForm.setSex(student.getSex());
		stForm.setBirthday(birthday);
		stForm.setClassId(student.getClassId());
		
		List<Class> listClass = classService.findAll();
		model.addAttribute("listClass", listClass);
		
		model.addAttribute("studentForm", stForm);
		model.addAttribute("studentId", id);
		return "editStudent";
	}
	
	@RequestMapping(value = { "/editStudent" }, method = RequestMethod.POST)
	public String saveEditStudent(Model model, //
			@ModelAttribute("studentForm") StudentForm studentForm, @RequestParam(name = "id") String  id)  throws ParseException {

		String fullName = studentForm.getFullName();
		String sex = studentForm.getSex();
		Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(studentForm.getBirthday());
		Long classId = studentForm.getClassId();

		studentService.upDateById(Long.valueOf(id), fullName, sex, birthday, classId);

		model.addAttribute("errorMessage", errorMessage);
		return "redirect:/admin/studentList";
	}
	

}
