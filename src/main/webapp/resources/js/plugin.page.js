(function($){
    $.fn.paging = function(options){
        var curThis = this;
        var goPageFnName = null;

        var defalts = {
            inputSearch : '',
            selectSearch : '',
        };


        var subOption = $.extend(true, defalts, options);



        if(subOption.goPageFnName == undefined || subOption.goPageFnName == null || subOption.goPageFnName == ""){
            goPageFnName = "goPage";
        }else{
            goPageFnName = subOption.goPageFnName;
        }
        return this.each(function(){

            var curRange = subOption.curRange*1;
            var prevPage = subOption.prevPage*1;
            var nextPage = subOption.nextPage*1;
            var startPage = subOption.startPage*1;
            var endPage = subOption.endPage*1;
            var rangeCnt = subOption.rangeCnt*1;
            var pageCnt = subOption.pageCnt*1;
            var curPage = subOption.curPage*1;


            var inputSearch = ",'"+subOption.inputSearch+"'";
            var selectSearch = ",'"+subOption.selectSearch+"'";

            var html = "";

            if (pageCnt > 1){
                if(curRange != 1){
                    html += '<a href="#" onClick="' + goPageFnName + '(1' + selectSearch + inputSearch + ')">[처음]</a>';
                }


                if(curPage != 1){
                    html += '<a href="#" onClick="' + goPageFnName + '(' + prevPage + selectSearch + inputSearch + ')">[이전]</a>';
                }
            }

            for(var i = startPage ; i <= endPage ; i++){
                if (i == curPage){
                    html +=  '<span style="font-weight: bold;"><a href="#" >'+i+'</a></span>'
                }else{
                    html +=  '<span><a href="#" onClick=" '+ goPageFnName + '(' + i + selectSearch + inputSearch + ')">'+i+'</a></span>'
                }
            }

            if (pageCnt > 1){

                if (curPage != pageCnt){
                    html += '<a href="#" onClick="' + goPageFnName + '(' + nextPage + selectSearch + inputSearch + ')">[다음]</a>';
                }

                if(curRange != rangeCnt){
                    html += '<a href="#" onClick="' + goPageFnName + '(' + pageCnt + selectSearch + inputSearch + ')">[끝]</a>';
                }
            }


            html += '';
            $(curThis).empty().append(html);
        });
    };
})(jQuery);