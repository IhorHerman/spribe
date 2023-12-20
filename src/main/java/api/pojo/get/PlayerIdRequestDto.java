package api.pojo.get;

import api.RequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerIdRequestDto implements RequestDto {
    private Integer playerId;
}
