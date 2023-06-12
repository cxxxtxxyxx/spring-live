package shop.mtcoding._core;

import com.google.gson.Gson;

public class MessageConverter {

    public static String convert(Object object) {
        Gson gson = new Gson();
        String result = gson.toJson(object);
        return result;
    }
}
