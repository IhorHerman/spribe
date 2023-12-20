package api.utils;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;

import java.util.List;

@Log4j
public class Mapper {
    public static <T> T map(Response response, Class<T> type) {
        log.info("map " + type.getName());
        return response.as(type);
    }

    public static <T> List<T> map(Response response, String path, Class<T> tClass) {
        log.info("map list of  " + tClass.getName());
        return response.jsonPath().getList(path, tClass);
    }
}
