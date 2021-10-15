window.onload = function() {
	/*alert( typeof document.getElementById("helloworld").value );*/
	/*$.post(
		"refreshment",
		{
			seqlist : document.getElementById("helloworld").value,
			stat : "init"
			
		},
		function(data){
			AsyncImageUpdate(data);
		}
	);*/
	AsyncImageUpdate();
}



function hello(){
	alert("HELLO");
}

var min_Board_seq = 0;
var max_Board_seq = 0;

var region = null;
var spot = null;
var resaurant = null;


function AsyncImageUpdate(){
	$.post(
		"refreshment",
		{
			seqlist : document.getElementById("helloworld").value,
		},
		function(data){
			var boards = data.boards;
			var images = data.images;
			
			if(data.boards != null){
				var len = (boards.length < 18 ? boards.length : 18);
				
				if(len>0){
					for(i = 0; i< len; i++){
						if(i==0){
							min_Board_seq = boards[i].board_seq;
							nation = boards[i].nation;
							region = boards[i].region;
							place = boards[i].place;
						}else if(i==len-1){
							max_Board_seq = boards[i].board_seq;
						}
						try{
							document.getElementById("test"+(i+1)).innerText = boards[i].title;
							document.getElementById("test"+(i+1)).href= "datil?board_seq=" + boards[i].board_seq;
							document.getElementById("image"+(i+1) ).src = images[i].path;
						}catch(error){
							alert(error);
						}
					}
				}
			}
		}
	);
}

function nextPage(stat, board_seq){
	$.post(
		"nextPage",
		{
			board_seq: board_seq,
			stat: stat,
			nation: nation,
			region: region,
			place: place
		},
		function(data){
			var boards = data.boards;
			var images = data.images;
			var len = (boards.length < 18 ? boards.length : 18);
			if(len > 0){
				if(stat == "right"){
					min_Board_seq = boards[0].board_seq
					max_Board_seq = boards[len-1].board_seq
				}else{
					min_Board_seq = boards[len-1].board_seq
					max_Board_seq = boards[0].board_seq
				}
				for(i = 0; i< len; i++){
					
					try{
						document.getElementById("test"+(i+1)).innerText = boards[i].title;
						document.getElementById("test"+(i+1)).href= "datil?board_seq=" + boards[i].board_seq;
						document.getElementById("image"+(i+1) ).src = images[i].path;
					}catch(error){
						alert(error);
					}
				}
			}
		}
	);
}

function goRight(){
	nextPage("right", max_Board_seq);
}

function goLeft(){
	nextPage("left", min_Board_seq);
}