package learnjava.final_homework.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "sex")
	private String sex;

	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "class_id")
	private long classId;

	@ManyToOne
	@JoinColumn(name = "id", insertable = false, updatable = false, nullable = true)
	@NotFound(action = NotFoundAction.IGNORE)
	private Class pClass;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public Student() {

	}

	public Class getpClass() {
		return pClass;
	}

	public void setpClass(Class pClass) {
		this.pClass = pClass;
	}

	public Student(String fullName, String sex, Date birthday, long classId) {
		this.fullName = fullName;
		this.sex = sex;
		this.birthday = birthday;
		this.classId = classId;
	}

}
