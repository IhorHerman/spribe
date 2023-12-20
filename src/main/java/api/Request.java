package api;

import io.restassured.response.Response;

public interface Request {
    Response send(RequestDto requestModel);
}
