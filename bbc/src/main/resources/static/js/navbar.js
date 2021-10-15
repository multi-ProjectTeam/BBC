
var prevScrollpos = window.pageYOffset;
window.onscroll = function() {
var currentScrollPos = window.pageYOffset;
  if (prevScrollpos > currentScrollPos) {
    document.getElementById("navbar").style.top = "0";
    document.getElementById("footSlot").style.bottom = "0";
  } else {
    document.getElementById("navbar").style.top = "-60px";
    document.getElementById("footSlot").style.bottom = "-60px";
  }
  prevScrollpos = currentScrollPos;
}

function enterEvent(){
	if(window.event.keyCode==13){
		var keyword = document.getElementById("headerInputBox").value;
		keyword = keyword.trim(); 
		if(keyword != ""){
			alert(keyword);
			var url = "search";
			var data = "?keyword="+keyword;
			location.href = url + data;
		}
		
	}
}

$(function(){ 
	$("#modal").click(function(){
		$(".modal").fadeIn();             
		});           
		$("#close").click(function(){          
		$(".modal").fadeOut();       
	});
});
