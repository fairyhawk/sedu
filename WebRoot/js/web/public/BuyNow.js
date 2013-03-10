	function BuyNow(pid){
				$.ajax({  
					url : baselocation+"/cou/sellwayweb!BuyNow.action",  
					data : {sellId : pid},  // 参数  
					type : "post",  
					cache : false,    
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:NowBuy 
					});
					function NowBuy(result){
						var sellWay=result.entity;
						if(sellWay.subjuectId==1){
						
						}
						if(sellWay.subjuectId==2){
						
						}
						if(sellWay.subjuectId==3){
						
						}
						if(sellWay.subjuectId==5){
						
						}
						if(sellWay.subjuectId==7){
						
						}
						if(sellWay.subjuectId==8){
						
						}
						if(sellWay.subjuectId==9){
						
						}
						if(sellWay.subjuectId==10){
						
						}
						if(sellWay.subjuectId==11){
						
						}
						
						var s="23,司法考试-重点班,"+sellWay.teacher+","+sellWay.lessonNumber+",990,198,1,10000";
						addbao(sellWay.sellId,sellWay.sellName,sellWay.sellPrice,sellWay.subjectKey,s,baselocation,true);
						
					}
			}
	