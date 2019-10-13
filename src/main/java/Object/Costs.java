package Object;

import org.hibernate.annotations.Entity;
import org.json.JSONObject;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "\"Costs\"", schema = "public")
public class Costs extends AbsObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column(name = "userID")
    private int userID;         // ID user
    @Column(name = "sum_money")
    private double sum_money;   // total amount of money
    // supposed costs
    @Column(name = "one_day")
    private double one_day;
    @Column(name = "seven_day")
    private double seven_day;
    @Column(name = "month")
    private double month;
    // costs:
    @Column(name = "travel")
    private double travel;      // travel expenses
    @Column(name = "food")
    private double food;        // food expenses
    @Column(name = "clothes")
    private double clothes;     // clothes expenses
    @Column(name = "other")
    private double other;       // other expenses
    @Column(name = "sum_costs")
    private double sum_costs;   // general cost per month
    @Column(name = "create_entry")
    private Date create_entry;  // date create entry

    public Costs(){}

    public Costs(int id, int userID, double sum_money, double one_day,double seven_day, double month,
                 double travel, double food, double clothes, double other, double sum_costs, Date create_entry) {
        this.id = id;
        this.userID = userID;
        this.sum_money = sum_money;
        this.one_day = one_day;
        this.seven_day = seven_day;
        this.month = month;
        this.travel = travel;
        this.food = food;
        this.clothes = clothes;
        this.other = other;
        this.sum_costs = sum_costs;
        this.create_entry = create_entry;
    }

    public void JSONObject(JSONObject obj) {
        id = obj.getInt("id");
        userID = obj.getInt("userID");
        sum_money = obj.getDouble("sum_money");
        one_day = obj.getDouble("one_day");
        seven_day = obj.getDouble("seven_day");
        month = obj.getDouble("month");
        travel = obj.getDouble("travel");
        food = obj.getDouble("food");
        clothes = obj.getDouble("clothes");
        other = obj.getDouble("other");
        sum_costs = obj.getDouble("sum_costs");
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("userID", userID);
        obj.put("sum_money", sum_money);
        obj.put("one_day", one_day);
        obj.put("seven_day", seven_day);
        obj.put("month", month);
        obj.put("travel", travel);
        obj.put("food", food);
        obj.put("clothes", clothes);
        obj.put("other", other);
        obj.put("sum_costs", sum_costs);
        obj.put("create_entry", create_entry.toString());
        return null;
    }

    public void date(){
        create_entry = new Date(new java.util.Date().getTime());
    }

    public String toString() {
        return "id: "+id+"; sum money: " + sum_money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
