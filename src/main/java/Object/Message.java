package Object;

import org.json.JSONObject;

public class Message {

    private boolean result;     // true or false operation
    private String text;        // result text

    public Message(boolean result, String text) {
        this.result = result;
        this.text = text;
    }

    public JSONObject toJSON(){
         JSONObject obj = new JSONObject();
         obj.put("result", result);
         obj.put("text", text);
         return obj;
    }
}
