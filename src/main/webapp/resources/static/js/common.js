/*
$(document).ready(function(){

	$('header .name a').click(function() {
		layer_popup('#membermod');
	});

	var fileTarget = $('.filebox .upload-hidden');
	fileTarget.on('change', function(){
		if(window.FileReader){
			var filename = $(this)[0].files[0].name;
		} else {
			var filename = $(this).val().split('/').pop().split('\\').pop();
		}

		$(this).siblings('.upload-name').val(filename);
	});
});
*/

/*
 * 좌측 메뉴 이벤트 바인딩
 * */
function fn_pageInit(){

}
function fn_bindLnbEvent(){
	$("#lnb .1dps a").on("click",function(e){
//		console.log("TEST",$(e.target));
//		if(!$(e.target).hasClass("1dps")) return;
		$(this).parent("li").toggleClass("active");
	});
}

function fn_layer_popup(el){

	var $el = $(el);
	var isDim = $el.prev().hasClass('back');

	isDim ? $('.popCont').fadeIn() : $el.fadeIn();
	$('.back').fadeIn();

	var $elWidth = ~~($el.outerWidth()),
		$elHeight = ~~($el.outerHeight()),
		docWidth = $(document).width(),
		docHeight = $(document).height();

	if ($elHeight < docHeight || $elWidth < docWidth) {
		$el.css({
			marginTop: -$elHeight /2,
			marginLeft: -$elWidth/2
		})
	} else {
		$el.css({top: 0, left: 0});
	}

	$el.find('a.popClose').click(function(){
		isDim ? $('.popCont').fadeOut() : $el.fadeOut();
		$('.back').fadeOut();
		return false;
	});

	$('.back').click(function(){
		$('.back').fadeOut();
		return false;
	});

}

/*******************************[ DEV ]****************************************************************************************** */

Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";

    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;

    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};

String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};

var korean = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
var space = /\s/gi;
var eng = /^[a-zA-Z]*$/gi;
var password = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/gi;
var sameWord = /(\w)\1\1/gi;
var emailList = ["daum.net","gmail.com","nate.com","naver.com"];

var chkIncreMent = function(str){
    for(var i=0; i<str.length-2; i++){
        var code1 = Number(str[i].charCodeAt());
        var code2 = Number(str[i+1].charCodeAt());
		var code3 = Number(str[i+2].charCodeAt());
		var incrementFlag = false;

		if(code1 > 47 && code1 < 58){
			if((code1+1) == code2 && (code1+2) == code3){
                incrementFlag = true;
            }
		}else if(code1 > 64 && code1 < 91){ /* 대문자 */
            if((code1+1) == code2 && (code1+2) == code3){
                incrementFlag = true;
            }
        }else if(code1 > 96 && code1 < 123){ /* 소문자 */
            if((code1+1) == code2 && (code1+2) == code3){
				incrementFlag = true;
            }
		}
		if(incrementFlag) break;
    }
    return incrementFlag;
}

var chkPwd = function(pwdStr){
	var code = 0;
    if(!pwdStr.match(password)){
        // alert("비밀번호는 영어,특수문자를 포함한 8글자 이상으로 입력 해 주세요");
        code = 1;
    }
    else if(pwdStr.match(sameWord)){
        // alert("비밀번호는 동일한 문자 3개 이상 사용 할 수 없습니다.");
        code = 2;
	}
    else if(chkIncreMent(pwdStr)){
        // alert("비밀번호는 3자리 이상 연속된 문자를 사용 할 수 없습니다.");
        code = 3;
	}
	return code;
}

var inputPwdChk = function(e){
    $(e.target).keyup(function(){
        var inputVal = $(this).val();

    });
}

var phoneFomatter = function (num,type){
	var formatNum = '';
	num = num.replace(/-/gi,"");
    if(num.length==11){
        if(type==0){
            formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-****-$3');
        }else{
            formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
        }
    // }else if(num.length==8){
    //     formatNum = num.replace(/(\d{4})(\d{4})/, '$1-$2');
    }else{
        if(num.indexOf('02')==0){
            if(type==0){
                formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-****-$3');
            }else{
                formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
            }
        }else{
            if(type==0){
                formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-***-$3');
            }else{
                formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
            }
        }
    }
    return formatNum;
}

var dateValid = function(fDate,tDate){
	var flag = true;
	var _fDate = new Date(fDate);
	var _tDate = new Date(tDate);
	if(_fDate > _tDate){
		alert("날짜를 확인 해 주세요");
		flag = false;
	}
	return flag;
}


var validation = function(){
	var rslt = {code:0 , msg:""};
	$(".valid").each(function(i,item){
		var msg = $(item).attr("data-msg");
		var value = $(item).val();

		if(!value){
			msg+=" 입력해주세요.";
			$(item).focus();
			rslt = {code:1,msg:msg};
			return false;
		};

		if($(item).attr("data-type") == "ph"){
			value = value.replace(/-/gi,"");
			console.log(value.length);
			if((!value.match(/(\d{3})(\d{4})(\d{4})/) && !value.match(/(\d{3})(\d{3})(\d{4})/) ) || value.length > 11){
				msg = "핸드폰번호를 확인해주세요.";
				$(item).focus();
				rslt = {code:1,msg:msg};
				return false;
			}
		}
	});
	return rslt;
}