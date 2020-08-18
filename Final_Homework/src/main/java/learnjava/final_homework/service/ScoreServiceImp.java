package learnjava.final_homework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learnjava.final_homework.model.Score;
import learnjava.final_homework.repository.ScoreRepository;

@Service
public class ScoreServiceImp implements ScoreService {
	@Autowired
	private ScoreRepository scoreRepository;

	@Override
	public List<Score> findAll() {
		// TODO Auto-generated method stub
		return scoreRepository.findAll();
	}

	@Override
	public List<Score> getListScoreByClassAndSubjet(long classId, long subjectId, double minScore, double maxScore) {
		// TODO Auto-generated method stub
		return scoreRepository.getListScoreByClassAndSubjet(classId, subjectId, minScore, maxScore);
	}

	@Override
	public List<Score> getListScoreByClassAndSubjetNoScore(long classId, long subjectId) {
		// TODO Auto-generated method stub
		return scoreRepository.getListScoreByClassAndSubjetNoScore(classId, subjectId);
	}

}
