package JavaProject.Semester.Services;

import JavaProject.Semester.Models.Judge;
import JavaProject.Semester.Exception.ResourceNotFoundException;
import JavaProject.Semester.Repository.JudgeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgeService {

    private static final Logger logger = LoggerFactory.getLogger(JudgeService.class);

    private final JudgeRepository judgeRepository;

    @Autowired
    public JudgeService(JudgeRepository judgeRepository) {
        this.judgeRepository = judgeRepository;
    }

    public Judge registerJudge(Judge judge) {
        if (judgeRepository.existsByEmail(judge.getEmail())) {
            throw new IllegalArgumentException("A judge with email '" + judge.getEmail() + "' already exists");
        }
        logger.info("Registering new judge: '{}' ({})", judge.getName(), judge.getEmail());
        return judgeRepository.save(judge);
    }

    public List<Judge> getAllJudges() {
        return judgeRepository.findAll();
    }

    public Judge getJudgeById(Long id) {
        return judgeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Judge not found with ID: " + id));
    }

    public Judge updateJudge(Long id, Judge details) {
        Judge existing = getJudgeById(id);
        existing.setName(details.getName());
        existing.setEmail(details.getEmail());
        existing.setExpertise(details.getExpertise());
        logger.info("Updated judge details for ID: {}", id);
        return judgeRepository.save(existing);
    }

    public void deleteJudge(Long id) {
        Judge existing = getJudgeById(id);
        judgeRepository.delete(existing);
        logger.info("Deleted judge with ID: {}", id);
    }
}
