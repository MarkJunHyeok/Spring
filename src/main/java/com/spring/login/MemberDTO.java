package com.spring.login;

import lombok.Data;

@Data

public class MemberDTO {

    private String mi_id;
    private String mi_pw;
    private String mi_name;
    private String mi_birthday;
    private String mi_email;
    private String mi_tell;
    private String mi_mobile;
    private String mi_addr;

    public MemberDTO() {
    }

    public MemberDTO(String mi_id, String mi_pw, String mi_name, String mi_birthday, String mi_email, String mi_tell, String mi_mobile, String mi_addr) {
        this.mi_id = mi_id;
        this.mi_pw = mi_pw;
        this.mi_name = mi_name;
        this.mi_birthday = mi_birthday;
        this.mi_email = mi_email;
        this.mi_tell = mi_tell;
        this.mi_mobile = mi_mobile;
        this.mi_addr = mi_addr;
    }
}
