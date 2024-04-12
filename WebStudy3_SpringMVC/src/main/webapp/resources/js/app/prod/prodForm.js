/*
$(":input") : 모든 인풋태그 대상

 */
$(function(){
	const $prodBuyer = $('[name="prodBuyer"]');
	$('[name="prodLgu"]').on("change", function(){
		let lgu = $(this).val();
		$prodBuyer.find(`option`).each(function(index, opt){
//			if(opt.classList.contains(lgu))
			if(index==0 || !lgu || (lgu && $(opt).hasClass(lgu)) ){
				$(opt).show();
			}else{
				$(opt).hide();
			}
		});
		
		$prodBuyer.find(`option:first`).prop(`selected`, true);
//		if(lgu){
//			$prodBuyer.find(`option`).hide();
//			$prodBuyer.find(`option.${lgu}`).show();
//		}else{
//			$prodBuyer.find(`option`).show();
//		}
	});
	$(":input[data-init-value]").each(function(index, ipt){ // select Box 요소 선택
		let initValue = $(ipt).data("initValue"); // 
		$(ipt).val(initValue);
		$(ipt).trigger("change");
	});
	
});