package com.spring;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PageComponent {



    public Map<String, Object> setPage(int listCnt, int curPage, int pageSize, int rangeSize,
                                       String inputSearch, String selectSearch){
        Map<String, Object> pageComponent = new HashMap<String, Object>();
        int pageCnt = setPageCnt(listCnt, pageSize);
        int rangeCnt = setrangeCnt(pageCnt, rangeSize);

        int curRange = setCurRange(curPage, rangeSize);

        int startPage = (curRange - 1) * rangeSize + 1;
        int endPage = startPage + rangeSize - 1;

        //페이지가 10보다 적을수도 있으니 적다면 총 페이지로 설정
        if(endPage > pageCnt){
            endPage = pageCnt;
        }
        int prevPage = curPage - 1;
        int nextPage = curPage + 1;

        int startIndex = setStartIndex(curPage, pageSize);

        pageComponent.put("INPUT",inputSearch);
        pageComponent.put("SELECT",selectSearch);
        pageComponent.put("START_INDEX", startIndex);

        pageComponent.put("PAGE_SIZE", pageSize);
        pageComponent.put("curRange", curRange);
        pageComponent.put("prevPage", prevPage);
        pageComponent.put("nextPage", nextPage);
        pageComponent.put("startPage", startPage);
        pageComponent.put("endPage", endPage);
        pageComponent.put("pageCnt", pageCnt);
        pageComponent.put("rangeCnt", rangeCnt);


        return pageComponent;

    }

    //최대 페이지 수 구하기.
    private int setPageCnt(int listCnt, int pageSize) {
        return (int) Math.ceil(listCnt*1.0/pageSize);
    }

    //최대 블럭 수 구하기.
    private int setrangeCnt(int pageCnt, int rangeSize) {
        return (int) Math.ceil(pageCnt*1.0/rangeSize);
    }

    //현재 블럭 위치 구하기
    private int setCurRange(int curPage, int rangeSize) {
        return (int)((curPage-1)/rangeSize) + 1;
    }


    //디비 인덱스 수 구하기.
    private int setStartIndex(int curPage, int pageSize) {
       return  (curPage - 1) * pageSize;
    }


}
