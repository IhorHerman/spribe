package api.pojo.create;

import api.RequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PlayerRequestDto implements RequestDto {

    private Integer age;
    private String gender;
    private String login;
    private String password;
    private String role;
    private String screenName;
}