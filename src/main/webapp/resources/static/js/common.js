$(document).ready(function(){

    /* 토글버튼 */
    $(".toggle-btn-box .btn-toggle").click(function(){
        $(".toggle-btn-box .btn-toggle").removeClass("active");
        if(!$(this).hasClass("active")) {
            $(this).addClass("active");
        }
    })

    /* 테이블 체크박스 클릭 이벤트 */ 
    // $(".ckAll").click(function(e){
    //     var tbl = $(e.target).parents("table");
    //     if ($(e.target).is(":checked")) tbl.find("input[type='checkbox']").prop("checked", true)
    //     else tbl.find("input[type='checkbox']").prop("checked", false)
    // })
    // $("tbody .onlyOne input[type='checkbox']").click(function(e){
    //     var tbl = $(e.target).parents("table");
    //     if(tbl.find("tbody .onlyOne input[type='checkbox']:checked").length == tbl.find("tbody .onlyOne input[type='checkbox']").length) {
    //         tbl.find(".ckAll").prop("checked", true)
    //     } else {
    //         tbl.find(".ckAll").prop("checked", false)
    //     }
    // })

    $(".ck").on("click",function(){
        var targetTbl  = $(this).parents("table")
        if($(this).hasClass("ckAll")){
            toggleAllCheck($(this).is(":checked"),targetTbl.find(".ck"))
        }else{
            var ckCnt      = targetTbl.find(".ckOther").length;
            var checkedCnt = targetTbl.find(".ckOther:checked").length;
            if(ckCnt == checkedCnt) targetTbl.find(".ckAll").prop("checked",true);
            else targetTbl.find(".ckAll").prop("checked",false);
        }
    });

    function toggleAllCheck(flag , els){
        if(flag){
            $(els).prop("checked",true);
        }else{
            $(els).prop("checked",false);
        }
    }
    
    /* 파일등록 input 체인지 이벤트 */ 
    var fileLst = $(".file-list li");
    var file = fileLst.find("input[type='file']");
    file.on("change",function(e){
        var inputFile = $(this);
        var fileReader = new FileReader();
        var fileName = e.target.files[0].name;
        fileReader.readAsDataURL(e.target.files[0]);
        fileReader.onload = function(e) {
            inputFile.prev("input").val(fileName);
        };
    })
    fileLst.find(".btn-del").click(function(){
        $(this).parents("li").find("input[type='text']").val('');
    })

    /* 데이트 피커 날짜 변경 버튼 이벤트 */
    $(".searchSt .date-btn").click(function(){
        if($(this).hasClass("active")) {
            $(this).removeClass("active");
        } else {
            $(".date-radio-box .date-btn").removeClass("active");
            $(this).addClass("active");
            /*버튼에 따른 날짜 변경*/
            var formValue = $(this).parents("td").find(".from").val();
            var formValueDate = new Date(formValue);
            var yy = formValueDate.format("yyyy");
            var mm = formValueDate.format("MM");
            var dd = formValueDate.format("dd");

            if($(this).hasClass("today")) { // 오늘
                $(this).parents("td").find(".to").val(new Date().format("yyyy.MM.dd"))
                $(this).parents("td").find(".from").val(new Date().format("yyyy.MM.dd"))
            } else if($(this).hasClass("oneWeek")) { // 일주일
                mm = formValueDate.getMonth();
                dd = formValueDate.getDate() + 7;
                $(this).parents("td").find(".to").val(new Date(yy,mm,dd).format("yyyy.MM.dd"))
            } else if
            ($(this).hasClass("oneMonth")) {  // 1개월
                mm = formValueDate.getMonth() + 1;
                $(this).parents("td").find(".to").val(new Date(yy,mm,dd).format("yyyy.MM.dd"))
            } else if($(this).hasClass("threeMonths")) {  // 3개월
                mm = formValueDate.getMonth() + 3;
                $(this).parents("td").find(".to").val(new Date(yy,mm,dd).format("yyyy.MM.dd"))
            }
        }
    })
    $(".datepicker.from, .datepicker.to").on("input, change", function (){
        $(this).parents("td").find(".date-btn").removeClass("active");
    })

}); // $ function 


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