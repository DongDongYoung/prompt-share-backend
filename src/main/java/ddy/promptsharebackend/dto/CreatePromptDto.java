package ddy.promptsharebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePromptDto {

    private String title;
    private String detail;
    private String userInput;
    private String gptOutput;
}
