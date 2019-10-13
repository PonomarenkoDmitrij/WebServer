package Service.ObjService;

import DAO.CostsDAO;
import Main.Server_Socket;
import Object.Costs;
import Service.Service;
import Service.Service.NAME_PROCESS;
import org.json.JSONArray;
import org.json.JSONObject;
import Object.Message;

import java.util.List;

public class Service_Costs {
    private int value;
    private static CostsDAO cdao = new CostsDAO();
    private Message message;
    private JSONObject obj;

    public void setObject(JSONObject obj) {
        value = obj.getInt("code");
        Costs costs = new Costs();
        costs.JSONObject((JSONObject) obj.get("costs"));
        boolean result;
        switch (value) {
            case NAME_PROCESS.create:
                cdao.operation(costs, value);
                createMessage(true, value);
                break;
            case NAME_PROCESS.show:
                cdao.findById(costs.getId());
                break;
//            case NAME_PROCESS.show_all:
//                List<Costs> l = cdao.findAll();
//                list(l);
//                break;
        }
    }

    private void createMessage(boolean result, int operation) {
        obj = new JSONObject();
        obj.put("result", result);
        obj.put("code", operation);
        obj.put("object",Service.NAME_OBJECT.costs);
        Server_Socket.getMessage(obj);
    }

    private void list(List<Costs> l) {
        obj = new JSONObject();
        JSONArray ar = new JSONArray();
        for (Costs c : l) {
            ar.put(c.toJSON());
        }
        obj.put("costs", ar);
        obj.put("code", Service.NAME_OBJECT.costs);
        Server_Socket.getMessage(obj);
    }

    public static JSONArray list(int userID){
        List<Object[]> l = cdao.findAll(userID);
        JSONArray ar = new JSONArray();
        if(l != null) {
            for (Object[] o : l) {
                ar.put(o);
            }
        }
        return null;
    }

}
