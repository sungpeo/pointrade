function myBoard(){
    global.myBoard = {};
    global.myBoard.points = [];
    console.log('my-board');

    //내 계좌번호(일단 USER_KEY) 전역 변수를 같이 쓰는걸로?!
    //이름 조회
    $('#myAccount').text(global.userKey);
    $('#myName').text(global.name);

    //해피 포인트 기준
    global.criteria = {};
    function changeCriteria(key){
        if(key==1){
            global.criteria.key = 1; //해피포인트
            global.criteria.name = '해피포인트'; //해피포인트
        }else{
            global.criteria.key = 2; //
            global.criteria.name = 'CJ-ONE포인트'; //
        }
    }

    if(global.name=='테스터'){
        changeCriteria(2);
    }else{
        changeCriteria(1);
    }



    //내가 가진 포인트 전부 조회하면서 기준 대비 currentRate까지
    $.get('/basket/list',
        { "userKey":global.userKey, "criteria":global.criteria.key },
        function(data){
            global.basketList = data.basketList;
            makePointTable();
    });

    //내가 가진 포인트 그대로 화면에 보여주기
    //        <tr id="pointInfo1" class="pointTr">
    //            <td width="40%" class="mh1">총금액<div id="tblRate1">(0:0)</div></td>
    //            <td width="60%" id="tblPoint1" class="mh1">0</td>
    //        </tr>

    function makePointTable() {
        var absoluteSum=0, relativeSum=0;
        for( var rowNum=0; rowNum<global.basketList.length; rowNum++ ){
            var eachPointName = global.basketList[rowNum].pointName,
                left = global.basketList[rowNum].pointKey==global.criteria.key ? 1 : global.basketList[rowNum].currentRateMom,
                right = global.basketList[rowNum].pointKey==global.criteria.key ? 1 : global.basketList[rowNum].currentRateSon,
                eachPoint = global.basketList[rowNum].balance,
                relativePoint = Math.floor(eachPoint/right);

            absoluteSum += eachPoint;
            relativeSum += relativePoint;

            $('#point-table > tbody:last').append('<tr id="pointInfo'+rowNum+'" class="point-tr">'
                + '<td width="40%" class="mh1">'+eachPointName
                + '<div id="tblRate'+rowNum+'">('+left+':'+right+')</div></td>'
                + '<td width="60%" id="tblPoint'+rowNum+'" class="mh1">'+eachPoint+' P'
                + '<div id="tblRelativePoint'+rowNum+'">('+relativePoint+' P)</div>'+'</td>'
                + '</tr>');

            var p = {};
            p.pointName = eachPointName;
            p.criteria = global.criteria.key;
            p.pointKey = global.basketList[rowNum].pointKey;
            p.rate = right;
            p.point = eachPoint;
            p.relativePoint = relativePoint;
            global.myBoard.points.push(p);
        }

        //비율 계산해서 총금액 보여주기
        $('#mytotal').html(absoluteSum+' P<div id="criteria">('+relativeSum+' P)</div>');
        createEventPointTable();
    }

    //포인트 리스트 중 하나(from_point)를 클릭 시 무엇과 교환할지
    //대상 포인트(to_point)와 현재 배율을 보여줌

    //대상 포인트(to_point) 클릭 시,
    //from_point 대비 to_point 화면으로 넘어감
    function createEventPointTable(){
        console.log('point table click event will be created.')
        $('#point-table tr').click(function(event){
            var trId = event.currentTarget.id; //pointInfo0
            var trNum = trId.substring('pointInfo'.length);
            global.myBoard.clickedRowNum = trNum;

            $(location).attr('href', '#/trade-board');
        })
    }




}
