package learnjava.final_homework.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "subject_name")
	private String subjectName;

	@ManyToMany(mappedBy = "subject")
	// @JoinTable(name = "score", joinColumns = @JoinColumn(name = "id"),
	// inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private Set<Score> score;

	public Set<Score> getScore() {
		return score;
	}

	public void setScore(Set<Score> score) {
		this.score = score;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}
