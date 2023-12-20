package api.tests.player;

import api.player.requests.CreatePlayerRequest;
import api.pojo.create.PlayerRequestDto;
import api.pojo.create.PlayerResponseDto;
import api.utils.Mapper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static api.TestGroups.*;

public class CreatePlayerTests extends BasePlayerTest {

    @Step("Create player")
    @Test(groups = {GENERAL, PLAYER_CREATE, POSITIVE})
    public void createPlayerTest() {
        PlayerRequestDto playerRequest = generatePlayerRequest();

        var supervisor = findSupervisor();
        Response response = new CreatePlayerRequest(supervisor.getLogin())
                .send(playerRequest)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        PlayerResponseDto playerResponseDto = Mapper.map(response, PlayerResponseDto.class);

        softAssert(playerRequest, playerResponseDto);
    }

    @Step("Create with invalid age")
    @Test(dataProvider = "invalidAgeParameters", groups = {GENERAL, PLAYER_CREATE, NEGATIVE})
    public void createPlayerWithInvalidAge(Integer age) {
        PlayerRequestDto playerRequestDto = generatePlayerRequest();
        playerRequestDto.setAge(age);

        var supervisor = findSupervisor();
        new CreatePlayerRequest(supervisor.getLogin())
                .send(playerRequestDto)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @DataProvider(name = "invalidAgeParameters")
    private Object[][] invalidAgeParameters() {
        return new Object[][]{
                {0},
                {15},
                {61}
        };
    }
}
