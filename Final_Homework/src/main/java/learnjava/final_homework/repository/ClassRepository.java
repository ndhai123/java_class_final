package learnjava.final_homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import learnjava.final_homework.model.Class;


public interface ClassRepository extends JpaRepository<Class, Long>{

}
