package hello.myproject.domain.chatgpt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChatGptService {

    @Value("${chat-gpt}")
    private String api_key;

    public HttpEntity<String> getGptRequest(String question){
        //헤더 지정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(api_key);

        String requestBody = "{"
                + "\"model\": \"gpt-3.5-turbo\","
                + "\"messages\": ["
                + "    {\"role\": \"system\", \"content\": \"You are a helpful assistant.\"},"
                + "    {\"role\": \"user\", \"content\": \"" + question + "\"}"
                + "],"
                + "\"temperature\": 0"
                + "}";

        return new HttpEntity<>(requestBody, headers);
    }
}
