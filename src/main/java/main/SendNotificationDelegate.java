package main;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SendNotificationDelegate implements JavaDelegate {

    public static final String CLIENT_APP_NOTIFICATION_URL = "http://localhost:9090/notify";

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Task completed");
        // Отправляем уведомление
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>("Таска закомпличена", headers);
        NotificationResponse resp = new RestTemplate().postForObject(
                CLIENT_APP_NOTIFICATION_URL, entity, NotificationResponse.class);

    }

}