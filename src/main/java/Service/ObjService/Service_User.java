package Service.ObjService;

import DAO.CostsDAO;
import Main.Server_Socket;
import Object.Users;
import Object.Message;
import Service.Service;
import org.json.JSONArray;
import org.json.JSONObject;
import Service.Service.NAME_PROCESS;
import DAO.UserDAO;

public class Service_User {
    private int value;
    private static UserDAO udao = new UserDAO();
    private JSONObject obj;
    private Users user;

    private static class unique_numb {
        public static final byte verification = 10;
    }

    public void setObject(JSONObject obj) {
        System.out.println(obj.toString());
        value = obj.getInt("code");
        user = new Users();
        user.JSONObject((JSONObject) obj.get("user"));
        boolean result;
        switch (value) {
            case NAME_PROCESS.create:
//                result = udao.operation(user, value);
//                getMess(result, value);
                break;
            case NAME_PROCESS.show:
                messUserShow(udao.findById(user.getId()));
                break;
            case unique_numb.verification:
                user = udao.verification(user.getLogin(), user.getPassword());
                result = user != null;
                getMess(result, unique_numb.verification, user);
                break;
        }
    }

    private void getMess(boolean result, int code, Users user) {
        obj = new JSONObject();
        obj.put("object", Service.NAME_OBJECT.user);
        obj.put("code", value);
        obj.put("result", result);
        obj.put("user",user.toJSON());
        if(code == unique_numb.verification){
            JSONArray ar = Service_Costs.list(user.getId());
            obj.put("costs", ar);
        }
        Server_Socket.getMessage(obj);
    }

    private void messUserShow(Users user){
        obj = new JSONObject();
        obj.put("code", Service.NAME_OBJECT.user);
        Server_Socket.getMessage(obj.put("user", user.toJSON()));
    }
}
