
function fn_pageInit(){
	fn_getAccIpList();
}

function fn_getAccIpList(){
	$.ajax({
		url:"/admin/getAccIpList"
		,type:"post"
		,data:{}
		,success:function(r){
			if(r.code == 0){
				fn_mkRow(r.result);
			}else{
				alert(r.msg);
			}
		}
	})
}

function fn_mkRow(data){
	var html =``;
	if(data.length != 0){
		for(var i in data){
			html += `
				<tr>
					<td>${data[i].seq}</td>
					<td>${data[i].ip}</td>
					<td>${data[i].reqDate.replace(DATE_PATTERN,'$1-$2-$3')}</td>
					<td>${data[i].status}</td>
					<td>${data[i].regUser}</td>
					<td>${data[i].regDate.replace(DATE_PATTERN,'$1-$2-$3')}</td>
				</tr>
			`;
		}
	}else{

	}
	$("#accIpTbl tbody").append(html);
}
