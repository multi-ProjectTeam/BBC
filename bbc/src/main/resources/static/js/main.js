'use strict'

function sendSearch(){
	let search = $("input[name=searching]").val();
	let nation = $("#selectnation").val();
	let region = $("#selectregion").val();
	let place = $("#selectplace").val();
	if((search == "" || search.trim().length==0)&&
		(nation == "" || nation.trim().length==0)&&
		(region == "" || region.trim().length==0)&&
		(place == "" || place.trim().length==0)){
		alert('검색어를 입력해주세요');
	} else{
		$("form[name=forms]").submit();
	}
}

$(function() {
    $("#selectnation").change(function() {
		$('#selectregion').children('option:not(:first)').remove();
		$('#selectplace').children('option:not(:first)').remove();
        let nation = $("#selectnation").val();

		$.ajax({
			type:"post",
			dataType:"json",
			aync:true,
			url:"selectregion",
			data: {nation: nation},
			success: function (data){
				console.log(data.length);
				for(let i=0; i<data.length; i++){
					$("#selectregion").append('<option value="' + data[i] +'">' + data[i] +'</option>')
				}
			},
			error:function(data,textStatus){
				alert('오류');
			},
			complete:function(data,textStatus){
			}
		});
    });
});

$(function() {
    $("#selectregion").change(function() {
		$('#selectplace').children('option:not(:first)').remove();
        let nation = $("#selectnation").val();
		let region = $("#selectregion").val();

		$.ajax({
			type:"post",
			dataType:"json",
			aync:true,
			url:"selectplace",
			data: {nation: nation, region: region},
			success: function (data){
				console.log(data.length);
				for(let i=0; i<data.length; i++){
					$("#selectplace").append('<option value="' + data[i] +'">' + data[i] +'</option>')
				}
			},
			error:function(data,textStatus){
				alert('오류');
			},
			complete:function(data,textStatus){
			}
		});
    });
});