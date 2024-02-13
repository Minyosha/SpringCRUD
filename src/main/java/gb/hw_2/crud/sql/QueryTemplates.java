package gb.hw_2.crud.sql;

import lombok.Getter;
import lombok.Setter;



import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "db")
@Getter
@Setter
public class QueryTemplates {
    private String findAllUsers;
    private String deleteUserById;

    // геттеры и сеттеры для полей
}
