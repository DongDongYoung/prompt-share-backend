package ddy.promptsharebackend.api;

import ddy.promptsharebackend.domain.Prompt;
import ddy.promptsharebackend.dto.CreatePromptDto;
import ddy.promptsharebackend.service.PromptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: 로깅 추가하기
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PromptApiController {

    private final PromptService promptService;

    @GetMapping("/prompts")
    public ResponseEntity<List<Prompt>> viewPromptList() {
        List<Prompt> promptList = promptService.getPromptList();
        return ResponseEntity.ok(promptList);
    }

    @GetMapping("/prompt/{promptId}")
    public ResponseEntity<Prompt> viewPrompt(@PathVariable Long promptId) {
        Prompt findPrompt = promptService.getPrompt(promptId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (findPrompt != null) {
            return ResponseEntity.ok(findPrompt);
        } else {
            // TODO: ExceptionHandler 또는 ExceptionResponseBody 구현하기
            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/prompt")
    public ResponseEntity<Prompt> createPrompt(@RequestBody CreatePromptDto promptDto) {
        Prompt prompt = Prompt.of(
                promptDto.getTitle(),
                promptDto.getDetail(),
                promptDto.getUserInput(),
                promptDto.getGptOutput()
        );
        Prompt savedPrompt = promptService.savePrompt(prompt);
        return ResponseEntity.ok(savedPrompt);
    }

    @PutMapping("/prompt/{promptId}")
    public ResponseEntity<Prompt> updatePrompt(
            @PathVariable Long promptId,
            @RequestBody CreatePromptDto promptDto
    ) {
        Prompt updatePrompt = Prompt.of(
                promptDto.getTitle(),
                promptDto.getDetail(),
                promptDto.getUserInput(),
                promptDto.getGptOutput()
        );
        Prompt updatedPrompt = promptService.updatePrompt(promptId, updatePrompt);
        return ResponseEntity.ok(updatedPrompt);
    }

    @DeleteMapping("/prompt/{promptId}")
    public ResponseEntity<Long> removePrompt(@PathVariable Long promptId) {
        Long deletedPromptId = promptService.deletePrompt(promptId);
        return ResponseEntity.ok(deletedPromptId);
    }
}
