package com.kook.pointrade.domain;

/**
 * Created by Sungpyo on 2016-06-12.
 */
public class UserDTO {

    private int userkey;
    private String name;
    private String birth;
    private String hp;

    public int getUserkey() {
        return userkey;
    }

    public void setUserkey(int userkey) {
        this.userkey = userkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }
}
