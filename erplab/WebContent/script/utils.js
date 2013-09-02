function submitForm(formId){
	$("#"+formId).submit();
	return;
}

function jump(url){
	window.location.href = url;
}

//创建cookie
function createCookie(name,value,days)
{
	if (days)
	{
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}
	else var expires = "";
	document.cookie = name+"="+value+expires+"; path=/";
}
 
//读取cookie
function readCookie(name)
{
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++)
	{
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}
 
//清除cookie
function eraseCookie(name)
{
	createCookie(name,"",-1);
}

function switchStylestyle(styleName)
{
	$('link[@rel*=style][title]').each(function(i) 
	{
		this.disabled = true;
		if (this.getAttribute('title') == styleName) this.disabled = false;
	});
	createCookie('style', styleName, 365);
}

//validator
function addCompareLessValidtor(){
	$.tools.validator.fn("[data-compare-less]", "$2 should be more than $1", function(input) {
		var id = input.attr("data-compare-less");
		var field = $("#"+id); 
		var validatorName1 = input.attr("validatorName");
		var validatorName2 = field.attr("validatorName");
		return field.val() < input.val()? true : [validatorName1,validatorName2]; 
	});
}
