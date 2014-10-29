package pojo;

/**
 * Created by xiaoxiao on 14-10-28.
 */
public class UserInfo {

    private String id;
    private String loginId;//登录账号
    private String password;//登录密码

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo(String id, String loginId, String password) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
    }

    public UserInfo() {
        super();
    }

}
