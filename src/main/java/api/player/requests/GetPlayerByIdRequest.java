package api.player.requests;

import api.BaseRequest;
import api.RequestDto;
import api.player.PlayerEndpoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;

@Log4j
public class GetPlayerByIdRequest extends BaseRequest {

    public GetPlayerByIdRequest() {
        super(PlayerEndpoints.GET.getValue());
    }

    @Step("Get player by id")
    @Override
    public Response send(RequestDto requestModel) {
        log.info("Get player by id");
        return requestConfig()
                .body(requestModel)
                .when()
                .log().all()
                .post();
    }
}
