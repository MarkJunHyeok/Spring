package com.spring.notice;

import lombok.Data;
@Data
public class PageDTO {


    final private int PAGE_SIZE = 10;
    final private int RANG_SIZE = 10;

    private int curPage; //현재 페이지
    private int curRange; //햔제 블럭

    private int listCnt; // 총 게시글
    private int pageCnt; // 총 페이지
    private int rangeCnt; // 총 블럭

    private int startPage; // 현재 블럭 기준 첫번째 페이지
    private int endPage; // 현재 블럭 기준 마지막 페이지

    private int prevPage; // 현재 페이지 기준 이전 페이지
    private int nextPage; // 현재 페이지 기준 다음 페이지

    private int startIndex; // db index 시작 값

    public PageDTO() {}

    public PageDTO(int listCnt, int curPage){


        setCurPage(curPage);
        setListCnt(listCnt);

        setPageCnt(listCnt);
        setRangeCnt(pageCnt);

        rangeSetting(curPage);

        setStartIndex(curPage);
    }

    //최대 페이지와 블럭 수 구하기.
    public void setPageCnt(int listCnt) {
        this.pageCnt = (int) Math.ceil(listCnt*1.0/PAGE_SIZE);
    }
    public void setRangeCnt(int pageCnt) {
        this.rangeCnt = (int) Math.ceil(pageCnt*1.0/RANG_SIZE);
    }

    //현재 블럭 위치 구하기
    public void setCurRange(int curPage) {
        this.curRange = (int)((curPage-1)/RANG_SIZE) + 1;
    }

    //현재 블럭 위치 기반으로 페이지 숫자 버튼 세팅해주기
    public void rangeSetting(int curPage){

        setCurRange(curPage);//현재 블럭 구하기

        this.startPage = (curRange - 1) * RANG_SIZE + 1;
        this.endPage = startPage + RANG_SIZE - 1;

        //페이지가 10보다 적을수도 있으니 적다면 총 페이지로 설정
        if(endPage > pageCnt){
            this.endPage = pageCnt;
        }

        this.prevPage = curPage - 1;
        this.nextPage = curPage + 1;
    }




    public void setStartIndex(int curPage) {
            this.startIndex = (curPage - 1) * PAGE_SIZE;
    }


}
