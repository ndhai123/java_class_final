package learnjava.final_homework.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import learnjava.final_homework.model.Student;

//  JpaRepository<Student, Long>  có sẵn một số phương thức thao tác với database, nếu cái nào chưa có thì viết câu lệnh thao tác với databasse ở 
public interface StudentRepository extends JpaRepository<Student, Long> { 
	@Modifying // KHi thao tac voi database
	@Transactional // Khi thao tac vs list
	@Query("UPDATE Student s SET s.fullName =:fullName,  s.sex =:sex, s.birthday =:birthday,s.classId =:classId WHERE s.id =:id")
	public void upDateById(@Param("id") Long id, @Param("fullName") String fullName, @Param("sex") String sex,  @Param("birthday") Date birthday, @Param("classId")Long classId);
	@Query("SELECT s FROM Student s WHERE s.fullName LIKE CONCAT('%',:fullName,'%')")
	public List<Student> searchByName(@Param("fullName") String fullName);
}

