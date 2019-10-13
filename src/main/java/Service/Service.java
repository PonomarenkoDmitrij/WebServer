package Service;

import Service.ObjService.Service_Costs;
import Service.ObjService.Service_User;
import org.json.JSONObject;

public class Service {

    private static int value;

    public static class NAME_PROCESS{

        public static final byte create = 0;
        public static final byte update = 1;
        public static final byte delete = 2;
        public static final byte show = 3;
        public static final byte show_all = 4;
    }


    public static class NAME_OBJECT{

        public static final byte user     = 1;
        public static final byte costs    = 2;
    }

    public static void func(JSONObject obj){
        if(obj == null || obj.isEmpty()) return;

        value = obj.getInt("enum_object");
        switch (value){
            case NAME_OBJECT.user:
                Service_User s = new Service_User();
                s.setObject(obj);
                break;
            case NAME_OBJECT.costs:
                Service_Costs costs = new Service_Costs();
                costs.setObject(obj);
                break;
        }

    }
}
