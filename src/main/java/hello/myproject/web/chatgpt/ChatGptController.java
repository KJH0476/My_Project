package hello.myproject.web.chatgpt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.myproject.domain.chatgpt.ChatGptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatGptController {

    private final ChatGptService chatGptService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();   //JSON 읽기, 쓰기, 변환 기능 제공 객체
    private String endPoint = "https://api.openai.com/v1/chat/completions";

    @GetMapping("/question-to-ai")
    public String chatGptForm(@RequestParam(required = false) String answer, Model model){
        if(answer != null){
            model.addAttribute("answer", answer);
        }
        return "page/questionToAi";
    }

    @PostMapping("/question-to-ai")
    public String chatGptResponse(@RequestParam String question, RedirectAttributes redirectAttributes){

        HttpEntity<String> entity = chatGptService.getGptRequest(question);

        try {
            //RestTemplate -> rest api 서비스 요청후 응답을 받을 수 있는 템플릿
            ResponseEntity<String> response = restTemplate.postForEntity(endPoint, entity, String.class);
            log.info("Success Make GPT response");

            String jsonResponse = response.getBody();

            //jackson 라이브러리의 JsonNode 사용
            //형태: {key:value}
            //응답 json 에서 필요한 content 값만 가져옴
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            String answer = jsonNode.path("choices").get(0).path("message").path("content").asText();

            redirectAttributes.addAttribute("answer", answer);

            return "redirect:/question-to-ai";

        } catch (Exception e) {
            e.printStackTrace();
            return "page/questionToAi";
        }
    }
}
