package api.tests.player;

import api.player.PlayerRole;
import api.pojo.get.PlayerIdRequestDto;
import api.pojo.create.PlayerRequestDto;
import api.pojo.create.PlayerResponseDto;
import api.player.requests.CreatePlayerRequest;
import api.player.requests.DeletePlayerRequest;
import api.utils.Mapper;
import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static api.TestGroups.*;

public class DeletePlayerTests extends BasePlayerTest {
    @Step("Delete player")
    @Test(groups = {GENERAL, PLAYER_DELETE, NEGATIVE})
    public void deletePlayerWithUserRole() {
        PlayerRequestDto playerRequest = generatePlayerRequest();
        playerRequest.setRole(PlayerRole.USER.getValue());

        var supervisor = findSupervisor();
        var response = new CreatePlayerRequest(supervisor.getLogin())
                .send(playerRequest)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        var createdPlayer = Mapper.map(response, PlayerResponseDto.class);
        var playerIdRequest = new PlayerIdRequestDto(createdPlayer.getId());

        new DeletePlayerRequest(createdPlayer.getLogin())
                .send(playerIdRequest)
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }
}
