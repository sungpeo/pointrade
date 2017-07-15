function tradeBoard(){

    global.tradeBoard = {};
    console.log('trade-board');

    var trNum = global.myBoard.clickedRowNum;
    var pointName = global.criteria.name + '-' + global.myBoard.points[trNum].pointName;

    global.tradeBoard.fromPointKey = global.criteria.key;
    global.tradeBoard.toPointKey = global.myBoard.points[trNum].pointKey;
    $('#trade-title').text(pointName);

    var getTradeList = function(){
        $.get('/trade/list',
            { "fromPoint":global.tradeBoard.fromPointKey, "toPoint":global.tradeBoard.toPointKey },
            function(data){
            var backSize = data.backSize;
            var frontSize = data.frontSize;
            var currentRate = data.currentRate;
            var tradeList = data.tradeList;

            var frontStartLine = 8-frontSize+1;
            var listSize = backSize+frontSize;

            for(var i=0; i<listSize; i++) {
                if(tradeList[i].tradeCode=='01'){
                    $('#' + (frontStartLine+i) + '-1').text(tradeList[i].amount);
                }else{
                    $('#' + (frontStartLine+i) + '-3').text(tradeList[i].amount);
                }
                $('#' + (frontStartLine+i) + '-2').text(tradeList[i].rateSon+'/'+tradeList[i].rateMom);

            }

        });
    };

    $('#buttonSell').click(function(){
        var postBody = {};
        postBody.userKey = global.userKey;
        postBody.fromPoint = global.tradeBoard.fromPointKey;
        postBody.toPoint = global.tradeBoard.toPointKey;
        postBody.rateSon = $('#rateSon').val();
        postBody.rateMom = $('#rateMom').val();
        postBody.amount = $('#amount').val();
        postBody.tradeCode = $('#tradeCode').val();
        $.post('/trade/sell',
            postBody,
            function(){
                getTradeList();
            }
        )
    })


//    p.pointName = eachPointName;
//    p.criteria = global.criteria;
//    p.pointKey = global.basketList[rowNum].pointKey;
//    p.rate = right;
//    p.point = eachPoint;
//    p.relativePoint = relativePoint;


    getTradeList();

}