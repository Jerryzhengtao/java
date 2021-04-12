package ntp.xxl.egather.po;


import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "t_excels")
public class Excel {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String data;
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    private String nickname;

    @ManyToOne
    private User user;

    public Excel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Excel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                ", time=" + time +
                '}';
    }
}
