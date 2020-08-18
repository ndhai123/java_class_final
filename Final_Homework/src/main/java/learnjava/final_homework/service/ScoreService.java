package learnjava.final_homework.service;

import java.util.List;

import learnjava.final_homework.model.Score;

public interface ScoreService {
	List<Score> findAll();

	List<Score> getListScoreByClassAndSubjet(long classId, long subjectId, double minScore, double maxScore);

	List<Score> getListScoreByClassAndSubjetNoScore(long classId, long subjectId);

}
