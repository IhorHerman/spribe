package api.pojo.delete;

import lombok.Data;

@Data
public class PlayerDeleteResponseDto {
    private Body body;
    private String statusCode;
    private Integer statusCodeValue;
}
