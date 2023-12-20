package api.tests.player;

import api.player.requests.CreatePlayerRequest;
import api.player.requests.GetAllPlayersRequest;
import api.player.requests.GetPlayerByIdRequest;
import api.pojo.get.PlayerIdRequestDto;
import api.pojo.create.PlayerRequestDto;
import api.pojo.get.PlayerItemResponseDto;
import api.pojo.create.PlayerResponseDto;
import api.utils.Mapper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static api.TestGroups.*;

public class GetPlayerTests extends BasePlayerTest {

    @Step("Get player")
    @Test(groups = {GENERAL, PLAYER_GET, POSITIVE})
    public void getPlayerByIdTest() {
        PlayerRequestDto playerRequest = generatePlayerRequest();
        var supervisor = findSupervisor();
        Response response = new CreatePlayerRequest(supervisor.getLogin())
                .send(playerRequest)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        PlayerResponseDto playerResponseDto = Mapper.map(response, PlayerResponseDto.class);

        PlayerIdRequestDto playerGetByIdRequestDto = new PlayerIdRequestDto(playerResponseDto.getId());
        Response responseById = new GetPlayerByIdRequest().send(playerGetByIdRequestDto)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        PlayerResponseDto playerResponseDtoById = Mapper.map(responseById, PlayerResponseDto.class);
        softAssert(playerRequest, playerResponseDtoById);
    }

    @Step("Get players")
    @Test(groups = {GENERAL, PLAYER_GET, POSITIVE})
    public void getAllPlayersTest() {
        PlayerRequestDto playerRequest = generatePlayerRequest();
        var supervisor = findSupervisor();
        new CreatePlayerRequest(supervisor.getLogin())
                .send(playerRequest)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        Response response = new GetAllPlayersRequest()
                .send()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        List<PlayerItemResponseDto> players = Mapper.map(response, "players", PlayerItemResponseDto.class);

        var player = findPlayer(players, p -> p.getScreenName().equals(playerRequest.getScreenName()));

        softAssert(playerRequest, player);
    }

    @Step("Get player with invalid id")
    @Test(dataProvider = "invalidIdParameters", groups = {GENERAL, PLAYER_GET, NEGATIVE})
    public void getNonExistentPlayerById(Integer id) {
        PlayerIdRequestDto playerGetByIdRequestDto = new PlayerIdRequestDto(id);

        new GetPlayerByIdRequest().send(playerGetByIdRequestDto)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @DataProvider(name = "invalidIdParameters")
    private Object[][] invalidIdParameters() {
        return new Object[][]{
                {111111111}
        };
    }
}
