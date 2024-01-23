document.addEventListener("DOMContentLoaded", ()=>{
	document.querySelector("#withdrawLi").addEventListener("click", showWithdraw)

})

const showWithdraw = function() {
	document.querySelector(".content").remove()
	const content = document.createElement("section");
	content.class = "content";
	content.innerHTML = `
		<h3>회원탈퇴</h3>
		<hr/>
		<form method='post' action='withdraw' id = "withdrawForm">
		<p>회원탈퇴를 하시려면 비밀번호를 입력하십시오.</p>
		<table>
			<tr><td>비밀번호</td><td><input type="password" name="password"></td><td><button>회원탈퇴</button></td></tr>
		</table>
		</form>
	`
	document.querySelector("body>div.container").appendChild(content);
}