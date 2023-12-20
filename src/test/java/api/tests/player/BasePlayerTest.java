package api.tests.player;

import api.player.PlayerRole;
import api.pojo.get.PlayerItemResponseDto;
import api.pojo.create.PlayerResponseDto;
import api.pojo.update.PlayerUpdateResponseDto;
import api.player.requests.GetAllPlayersRequest;
import api.player.requests.GetPlayerByIdRequest;
import api.pojo.get.PlayerIdRequestDto;
import api.pojo.create.PlayerRequestDto;
import api.utils.Mapper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;


@Log4j
public class BasePlayerTest {
    @Step("Assert player")
    protected static void softAssert(PlayerRequestDto expected, PlayerResponseDto actual) {
        log.info("assert player");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actual.getAge(), expected.getAge(), "Age is incorrect");
        softAssert.assertEquals(actual.getGender(), expected.getGender(), "Gender is incorrect");
        softAssert.assertEquals(actual.getLogin(), expected.getLogin(), "Login is incorrect");
        softAssert.assertEquals(actual.getPassword(), expected.getPassword(), "Password is incorrect");
        softAssert.assertEquals(actual.getRole(), expected.getRole(), "Role is incorrect");
        softAssert.assertEquals(actual.getScreenName(), expected.getScreenName(), "ScreenName is incorrect");

        softAssert.assertAll();
    }

    @Step("Assert player")
    public static void softAssert(PlayerRequestDto expected, PlayerItemResponseDto actual) {
        log.info("assert player response");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actual.getAge(), expected.getAge(), "Age is incorrect");
        softAssert.assertEquals(actual.getGender(), expected.getGender(), "Gender is incorrect");
        softAssert.assertEquals(actual.getRole(), expected.getRole(), "Role is incorrect");
        softAssert.assertEquals(actual.getScreenName(), expected.getScreenName(), "ScreenName is incorrect");

        softAssert.assertAll();
    }

    @Step("Assert player")
    public static void softAssert(PlayerRequestDto expected, PlayerUpdateResponseDto actual) {
        log.info("assert player update response");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actual.getAge(), expected.getAge(), "Age is incorrect");
        softAssert.assertEquals(actual.getGender(), expected.getGender(), "Gender is incorrect");
        softAssert.assertEquals(actual.getLogin(), expected.getLogin(), "Login is incorrect");
        softAssert.assertEquals(actual.getRole(), expected.getRole(), "Role is incorrect");
        softAssert.assertEquals(actual.getScreenName(), expected.getScreenName(), "ScreenName is incorrect");

        softAssert.assertAll();
    }

    protected PlayerResponseDto findSupervisor() {
        log.info("find supervisor");
        Response playersResponse = new GetAllPlayersRequest().send();
        var players = Mapper.map(playersResponse, "players", PlayerItemResponseDto.class);

        var supervisor = findPlayer(players, p -> p.getRole().equals(PlayerRole.SUPERVISOR.getValue()));

        PlayerIdRequestDto request = new PlayerIdRequestDto(supervisor.getId());
        Response supervisorResponse = new GetPlayerByIdRequest().send(request);

        return Mapper.map(supervisorResponse, PlayerResponseDto.class);
    }

    protected PlayerItemResponseDto findPlayer(List<PlayerItemResponseDto> players, Predicate<PlayerItemResponseDto> predicate) {

        return players
                .stream()
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Player not found"));
    }

    protected PlayerRequestDto generatePlayerRequest() {
        return new PlayerRequestDto(18, "female",
                "Login" + System.currentTimeMillis(),
                "Password" + System.currentTimeMillis(),
                PlayerRole.USER.getValue(),
                "ScreenName" + System.currentTimeMillis());
    }
}
