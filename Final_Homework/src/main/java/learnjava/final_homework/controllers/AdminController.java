package learnjava.final_homework.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import learnjava.final_homework.form.StudentForm;
import learnjava.final_homework.model.Class;
import learnjava.final_homework.model.Student;
import learnjava.final_homework.service.ClassService;
import learnjava.final_homework.service.StudentService;

class ChartItemObject {
	String label = "Student Quantity";
	String backgroundColor = "rgba(60,141,188,0.9)";
	String borderColor = "rgba(60,141,188,0.8)";
	String pointRadius = "false";
	String pointColor = "#3b8bba";
	String minBarLength = "0";
	String barPercentage = "1";
	String barThicknes = "50";
	String maxBarThickness = "150";
	String pointStrokeColor = "rgba(60,141,188,1)";
	String pointHighlightFill = "#fff";
	String pointHighlightStroke = "rgba(60,141,188,1)";
	//
	// so luong hoc sinh tuong duong voi tung diem so tu 0-10
	// query tu DB
	//
	String[] data = { "1", "5", "0", "12", "7", "5", "8", "7", "11", "4", "11" };

}

class ChartAreaObject {
	String[] labels;
	ChartItemObject[] datasets;

}

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
			@ModelAttribute("studentForm") StudentForm studentForm, @RequestParam(name = "id") String id)
			throws ParseException {

		String fullName = studentForm.getFullName();
		String sex = studentForm.getSex();
		Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(studentForm.getBirthday());
		Long classId = studentForm.getClassId();

		studentService.upDateById(Long.valueOf(id), fullName, sex, birthday, classId);

		model.addAttribute("errorMessage", errorMessage);
		return "redirect:/admin/studentList";
	}

	@RequestMapping(value = { "/chart" }, method = RequestMethod.GET)
	public String showChart(Model model) {

		return "chart";
	}

	@RequestMapping(value = { "/load_chart" }, method = RequestMethod.GET)
	public @ResponseBody String getChartValue(HttpServletRequest request) throws JsonProcessingException {

		// Get gia tri duoc gui tu ajax
		String cSubject = request.getParameter("cSubject");
		String cClass = request.getParameter("cClass");

		System.out.println(cSubject);
		System.out.println(cClass);

		//
		/*
		 * lay duoc gia tri cua subject va class sau do query data tuong ung tu DB
		 * SELECT count(score.id) from score, student where score.subject_id = 1 and
		 * student.class_id = 1 and score.student_id = student.id
		 */
		//

		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

		//
		// label diem so tu 1-10
		//
		String[] labels = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

		// Tao cau truc tuong duong voi cau truc ma chart js su dung
		// Refer chartjs.html line 895
		//
		ChartItemObject datasetsItem = new ChartItemObject();
		ChartItemObject[] datasets = { datasetsItem };

		ChartAreaObject chartAreaObject = new ChartAreaObject();
		chartAreaObject.labels = labels;
		chartAreaObject.datasets = datasets;

		String data = mapper.writeValueAsString(chartAreaObject);
		System.out.println(data);

		// tra gia tri ve cho ajax
		return data;
	}

	@RequestMapping(value = { "/search" }, method = RequestMethod.GET)
	public String searchByName(Model model, @RequestParam(name = "condition") String condition) {
		System.out.println("search");

		List<Student> list = studentService.searchByName(condition);
		model.addAttribute("students", list);
		return "studentList";
	}

}
