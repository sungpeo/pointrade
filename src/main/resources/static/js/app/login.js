function login(){

    console.log('login-div');

    (function(){
        $('#inputAuth').val('1234');
    }())

    $('#buttonLogin').click(function(){

        var inputName = $('#inputName').val();
        var inputBirth = $('#inputBirth').val();
        var inputHp = $('#inputHp').val();
        var inputAuth = $('#inputAuth').val();
    //    alert('login'
    //        +'\nname='+inputName
    //        +'\nbirth='+inputBirth
    //        +'\ninputHp='+inputHp
    //        +'\ninputAuth='+inputAuth
    //    );

        var body = {};
        body.name = inputName;
        body.birth = inputBirth;
        body.hp = inputHp;
        $.post('/user/signin', body, function(data){
    //        console.log('signin' + data.login);
            if(data.login){
                if(inputAuth!='1234'){
                    alert('인증번호를 확인해주세요');
                    return;
                }
                global.userKey = data.userKey;
                global.name = data.name;
                global.birth = data.birth;
                global.hp = data.hp;


                $(location).attr('href', '#/my-board');
            }else{
                alert('계정정보가 없습니다.');
            }
        })

    })
}
