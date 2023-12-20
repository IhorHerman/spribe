package api.player.requests;

import api.BaseRequest;
import api.RequestDto;
import api.player.PlayerEndpoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;

@Log4j
public class GetAllPlayersRequest extends BaseRequest {

    public GetAllPlayersRequest() {
        super(PlayerEndpoints.GET_ALL.getValue());
    }

    @Step("Get players")
    @Override
    public Response send(RequestDto requestModel) {
        log.info("Get players");
        return requestConfig()
                .when()
                .log().all()
                .get();
    }

    public Response send() {
        return send(null);
    }
}
