package fr.esimed.easilyproject.entity.DTO;

public class Messages {

    private String to;
    private String title;
    private String msg;

    public Messages() {
    }

    public Messages(String to) {
        this.to = to;
    }

    public Messages(String to, String title, String msg) {
        this.to = to;
        this.title = title;
        this.msg = msg;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
