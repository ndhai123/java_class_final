package learnjava.final_homework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import learnjava.final_homework.model.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
	@Query("SELECT s from Score s inner join s.student st inner join s.subject sb where st.classId =:class_id AND sb.id =:subject_id AND s.score > :min_score AND s.score <= :max_score")
	public List<Score> getListScoreByClassAndSubjet(@Param("class_id") Long class_id,
			@Param("subject_id") Long subject_id, @Param("min_score") double min_score,
			@Param("max_score") double max_score);

	@Query("SELECT s from Score s inner join s.student st inner join s.subject sb where st.classId =:class_id AND sb.id =:subject_id")
	public List<Score> getListScoreByClassAndSubjetNoScore(@Param("class_id") Long class_id,
			@Param("subject_id") Long subject_id);

}
