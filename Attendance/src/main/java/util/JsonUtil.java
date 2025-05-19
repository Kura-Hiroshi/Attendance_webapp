package util;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    
    //クライアントから受け取ったJSONを任意の型に変換するメソッド
    public static <T> T parseJson(HttpServletRequest request, Class<T> clazz) throws IOException {
        return mapper.readValue(request.getReader(), clazz);
    }
    
    //クライアントから受け取ったJSONをJsonNodeに変換するメソッド
    public static JsonNode parseJson(HttpServletRequest request) throws IOException {
        return mapper.readTree(request.getReader());
    }
}
