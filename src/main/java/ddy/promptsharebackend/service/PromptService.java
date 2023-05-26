package ddy.promptsharebackend.service;

import ddy.promptsharebackend.domain.Prompt;
import ddy.promptsharebackend.repository.PromptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromptService {

    public final PromptRepository promptRepository;

    public List<Prompt> getPromptList() {
        return promptRepository.findAll();
    }

    public Prompt getPrompt(Long promptId) {
        Optional<Prompt> byId = promptRepository.findById(promptId);
        return byId.orElse(null); // TODO: 예외 따로 구현하기
    }

    public Prompt savePrompt(Prompt prompt) {
        promptRepository.save(prompt);
        return getPrompt(prompt.getId());
    }

    public Prompt updatePrompt(Long promptId, Prompt updatePrompt) {
        Optional<Prompt> byId = promptRepository.findById(promptId);
        if (byId.isPresent()) {
            Prompt findPrompt = byId.get();
            findPrompt.setTitle(updatePrompt.getTitle());
            findPrompt.setDetail(updatePrompt.getDetail());
            findPrompt.setUserInput(updatePrompt.getUserInput());
            findPrompt.setGptOutput(updatePrompt.getGptOutput());
            promptRepository.save(findPrompt);
            return getPrompt(promptId);
        } else {
            return null; // TODO: 예외 따로 구현하기
        }
    }

    public Long deletePrompt(Long promptId) {
        promptRepository.deleteById(promptId); // TODO: 삭제가 안되었을 경우 생각하기
        return promptId;
    }
}
