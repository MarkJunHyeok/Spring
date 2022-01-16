package com.spring.notice;

import lombok.Data;



@Data
public class NoticeDTO {

    private int ni_num;
    private String ni_title;
    private String ni_text;
    private int ni_views;
    private String ni_updatedt;
    private String ni_insertdt;

    private String mi_id;
    private String mi_name;

    public NoticeDTO(int ni_num, String ni_title, String ni_text, int ni_views, String ni_updatedt, String ni_insertdt, String mi_id, String mi_name) {
        this.ni_num = ni_num;
        this.ni_title = ni_title;
        this.ni_text = ni_text;
        this.ni_views = ni_views;
        this.ni_updatedt = ni_updatedt;
        this.ni_insertdt = ni_insertdt;
        this.mi_id = mi_id;
        this.mi_name = mi_name;
    }

    public NoticeDTO() {
    }
}
