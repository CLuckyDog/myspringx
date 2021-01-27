package com.webflux.pojo;

import com.webflux.enumeration.SexEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author ：pan_zhongjian
 * @version :
 * @date ：Created in 2020/5/18 16:54
 * @description:
 * @modified By:
 */
public class User implements Serializable {

    private static final long serialVersionUID = -480576301526341000L;
    @Id
    private String id;
    // 性别
    private String sex;
    // 在MongoDB中使用user_name保存属性
    @Field("user_name")
    private String userName;
    private String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
