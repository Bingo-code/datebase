$().ready(function(){
	$("#loginRegister span").mouseover(function(){
		if(!$(this).hasClass("chosen")){
			$(this).addClass("hover");
		}
	})
	$("#loginRegister span").mouseout(function(){
		if(!$(this).hasClass("chosen")){
			$(this).removeClass("hover");
		}
	})
	$("#loginRegister .left").click(function() {
		if (!$("#loginRegister .left").hasClass("chosen")) {
			$("#loginRegister span").removeClass("chosen hover");
			$("#loginRegister .left").addClass("chosen");
			$("#login")[0].setAttribute("style", "display: block;");
			$("#register")[0].setAttribute("style", "display: none;");
		}
	});
	$("#loginRegister .right").click(function() {
		if (!$("#loginRegister .right").hasClass("chosen")) {
			$("#loginRegister span").removeClass("chosen hover");
			$("#loginRegister .right").addClass("chosen");
			$("#login")[0].setAttribute("style", "display: none;");
			$("#register")[0].setAttribute("style", "display: block;");
		}
	});
	var n = 1;
	var timer = setInterval(function() {
		$("#loginRegister").css("background-image",
		  "url(./image/" + (n % 6 + 1) + "_lr.png)");
		n++;
	}, 3000);
	var onceTimer = setTimeout(function(){
		$("#loginRegister label").css("display","none");
	},1500);
})


function checkPwd(){
	var name = $(".name").val();
	var pwd = $(".pwd").val();
	$("#loginRegister .lInfo").css("display","block");
	setTimeout(function(){
		$("#loginRegister .lInfo").css("display","none");
	},1000);
	if(name == "" || name.indexOf(" ") != -1){
		$("#loginRegister .lInfo").html("账号名不可以为空");
		return false;
	}else{
		if(pwd == ""){
			$("#loginRegister .lInfo").html("密码不可以为空");
			return false;
		}else{
			$("#loginRegister .lInfo").html("");
			return true;
		}
	}
	
}
function checkRPwd(){
	var name = $(".rName").val();
	var pwd = $(".rPwd").val();
	var againPwd = $(".rAgainPwd").val();
	$("#loginRegister .rInfo").css("display","block");
	setTimeout(function(){
		$("#loginRegister .rInfo").css("display","none");
	},1000);
	if(name == "" || name.indexOf(" ") != -1){
		$("#loginRegister .rInfo").html("账号名不可以为空");
		return false;
	}else{
		if(pwd == ""){
			$("#loginRegister .rInfo").html("密码不可以为空");
			return false;
		}else if(pwd!=againPwd){
			$("#loginRegister .rInfo").html("两次密码不相同");
			return false;
		}else{
			return true;
		}
	}
}