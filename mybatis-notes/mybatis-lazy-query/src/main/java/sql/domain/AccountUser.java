package sql.domain;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/16 16:19
 * @Version 1.0
 * @Description
 */
public class AccountUser extends Account {

    private String username;
    private String address;



    @Override
    public String toString() {
        return "AccountUser{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                "} " + super.toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
