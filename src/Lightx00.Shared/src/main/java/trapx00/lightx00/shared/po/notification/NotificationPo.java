package trapx00.lightx00.shared.po.notification;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

@DatabaseTable(tableName = "Notification")
public class NotificationPo implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private Date date;
    @DatabaseField
    private String senderId;
    @DatabaseField
    private String receiverId;
    @DatabaseField
    private NotificationType type;
    @DatabaseField
    private String content;


    public NotificationPo(Date date, String senderId, String receiverId, NotificationType type, String content) {
        this.date = date;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.type = type;
        this.content = content;
    }

    public NotificationPo(int id, Date date, String senderId, String receiverId, NotificationType type, String content) {
        this.id = id;
        this.date = date;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.type = type;
        this.content = content;
    }

    public NotificationPo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NotificationPo{" +
            "id=" + id +
            ", date=" + date +
            ", senderId='" + senderId + '\'' +
            ", receiverId='" + receiverId + '\'' +
            ", type=" + type +
            ", content='" + content + '\'' +
            '}';
    }
}
