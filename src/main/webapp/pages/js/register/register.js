document.addEventListener("DOMContentLoaded", () => {
  document.registerForm.id.addEventListener("focusout", checkId);
  document.registerForm.password2.addEventListener("focusout", checkPassword);
  document.registerForm.nickname.addEventListener("focusout", checkNickname);
});

const checkIdHttpRequest = new XMLHttpRequest();
checkIdHttpRequest.open("post", "/idDuplicateCheck?id=" + this.value);
checkIdHttpRequest.addEventListener("readystatechange", function () {
  if (this.readyState == 4 && this.status == 200) {
    const result = this.response;
    if (result == "true") {
      console.log("id 사용 가능");
    } else if (result == "false") {
      console.log("id 중복");
    }
  }
});

const checkNicknameHttpRequest = new XMLHttpRequest();
checkNicknameHttpRequest.open("post", "/checkNickname?nickname=" + this.value);
checkNicknameHttpRequest.addEventListener("readystatechange", function () {
  if (this.readyState == 4 && this.status == 200) {
    const result = this.response;
    if (result == "true") {
      console.log("id 사용 가능");
    } else if (result == "false") {
      console.log("id 중복");
    }
  }
});

const checkId = function () {
  console.log("checkId focusOut");
  checkIdHttpRequest.send();
};
const checkPassword = function () {
  if (this.value === document.registerForm.password.value) {
    console.log("비밀번호 일치");
  } else {
    console.log("비밀번호 불일치");
  }
};
const checkNickname = function () {
  checkNicknameHttpRequest.send();
};
