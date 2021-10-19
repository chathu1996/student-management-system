package lk.itsei.fx.sms.view.util;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.util.Date;

public class SearchTM {
    private String sid;
    private String sname;
    private Date regDate;
    private String cid;
    private String cname;

    public SearchTM() {
    }

    public SearchTM(String sid, String sname, Date regDate, String cid, String cname) {
        this.sid = sid;
        this.sname = sname;
        this.regDate = regDate;
        this.cid = cid;
        this.cname = cname;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
