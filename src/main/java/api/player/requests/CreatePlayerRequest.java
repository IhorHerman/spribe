package api.player.requests;

import api.BaseRequest;
import api.RequestDto;
import api.player.PlayerEndpoints;
import api.pojo.create.PlayerRequestDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.Map;

@Log4j
public class CreatePlayerRequest extends BaseRequest {

    private final String editor;

    public CreatePlayerRequest(String editor) {
        super(PlayerEndpoints.CREATE.getValue());
        this.editor = editor;
    }

    @Step("Create player")
    @Override
    public Response send(RequestDto requestModel) {
        PlayerRequestDto playerRequestDto = (PlayerRequestDto) requestModel;
        log.info("Create player");

        return requestConfig()
                .pathParam("editor", editor)
                .queryParams(toQueryParameterMap(playerRequestDto))
                .when()
                .log().all()
                .get();
    }

    private Map<String, Object> toQueryParameterMap(PlayerRequestDto playerRequestDto) {

        Map<String, Object> playerQueryParams = new HashMap<>();

        playerQueryParams.put("age", playerRequestDto.getAge());
        playerQueryParams.put("gender", playerRequestDto.getGender());
        playerQueryParams.put("login", playerRequestDto.getLogin());
        playerQueryParams.put("password", playerRequestDto.getPassword());
        playerQueryParams.put("role", playerRequestDto.getRole());
        playerQueryParams.put("screenName", playerRequestDto.getScreenName());

        return playerQueryParams;
    }
}
