package Object;

import javax.persistence.*;

import org.json.JSONObject;

import java.sql.Date;

@Entity
@Table(name = "\"Users\"", schema = "public")
public class Users extends AbsObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String full_name;
    @Column(name = "email")
    private String email;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "birthday")
    private Date birthday;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users(){}

    public Users(String full_name, String email, String login, String password, Date birthday) {
        this.full_name = full_name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.birthday = birthday;
    }

    public void JSONObject(JSONObject obj) {
        if(obj.has("id")) id = obj.getInt("id");
        if(obj.has("full_name")) full_name = obj.getString("full_name");
        if(obj.has("email")) email = obj.getString("email");
        if(obj.has("login")) login = obj.getString("login");
        if(obj.has("password")) password = obj.getString("password");
        if(obj.has("birthday")) birthday = Date.valueOf( obj.getString("birthday"));
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("full_name", full_name);
        obj.put("email", email);
        obj.put("login", login);
        obj.put("password", password);
        obj.put("birthday", birthday.toString());
        return obj;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
