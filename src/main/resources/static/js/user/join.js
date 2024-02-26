// 전화번호 입력 필드에 숫자만 입력되도록 처리하는 함수
function PhoneNumber(input) {
    // 입력된 값에서 숫자만 추출
    var phoneNumber = input.value.replace(/[^0-9]/g, "");
    // 숫자만 입력된 값으로 입력 필드의 값을 설정
    input.value = phoneNumber;
    // 입력된 전화번호의 길이가 10자리 이상인 경우 전송 버튼을 활성화
    if (phoneNumber.length >= 11) {
        document.getElementById("phoneToken").disabled = false;
    } else {
        // 전화번호가 10자리 미만인 경우 전송 버튼을 비활성화
        document.getElementById("phoneToken").disabled = true;
    }
}

// 전화번호 입력 필드의 입력 이벤트에 함수 연결
document.getElementById("user_phone").addEventListener("input", function () {
    PhoneNumber(this);
});

function phone(e) {
    var number = e.value.replace(/[^0-9]/g, "");
    var phone = "";
    if (number.length < 4) {
        return number;
    } else {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3, 4);
        phone += "-";
        phone += number.substr(7, 4);
    }
    e.value = phone;
}

let timer2;
let phoneTimer = false; //타이머 작동여부
let phoneAuth = () => {
    if (phoneTimer === false) {
        // 타이머가 작동 중이 아닐 때
        phoneTimer = true; // 타이머 작동 상태로 변경
        document.getElementById("phoneFinish").disabled = false; // 인증 확인 버튼 활성화
        const token = Math.floor(100000 + Math.random() * 900000); // 6자리 랜덤 숫자 생성
        document.getElementById("phoneAuthKey").value = token; // 생성된 랜덤 숫자를 입력 필드에 설정
        document.getElementById("phoneConfirmNum").style.display = "block"; // 인증번호 입력 영역 표시
        let time = 60;
        timer2 = setInterval(function () {
            if (time >= 0) {
                // 시간이 0초 이상이면
                const min = Math.floor(time / 60);
                const sec = String(time % 60).padStart(2, "0");
                document.getElementById("timer2").innerText = min + ":" + sec;
                time = time - 1;
            } else {
                // 시간이 0초 이하이면
                time += 1;
                const min = Math.floor(time / 60);
                const sec = String(time % 60).padStart(2, "0");
                document.getElementById("phoneFinish").disabled = true; // 인증 확인 버튼 비활성화
                phoneTimer = false;
                clearInterval(timer2); // 타이머 중지
                document.getElementById("phoneAuthKey").value = ""; // 인증번호 입력 필드 초기화
            }
        }, 1000);
    } else {
        // 타이머가 작동 중일 때
    }
};
let isAuthCompleted = false;
let phoneAuthFinish = () => {
    if (phoneTimer) {
        //phoneTimer true 일 때(타이머가 작동 중일 때)
        alert("인증이 완료되었습니다");
        clearInterval(timer2);
        document.getElementById("phoneToken").disabled = true;
        document.getElementById("phoneFinish").textContent = "인증 완료"; //인증 확인 버튼을 인증 완료 버튼으로 변경
        document.getElementById("phoneFinish").disabled = true;
        isAuthCompleted = true; // 인증 완료 상태로 변경
        checkJoinButton(); // 회원가입 버튼 상태를 확인하여 변경
    }
};

function checkJoinButton() {
    if (isAuthCompleted) {
        // 인증이 완료되었을 때
        document.getElementById("joinButton").disabled = false; // 회원가입 버튼 활성화
        document.getElementById("joinButton").classList.remove("btn-outline-secondary"); // btn-outline-secondary 클래스 제거
        document.getElementById("joinButton").classList.add("btn-outline-primary"); // btn-outline-primary 클래스 추가
    } else {
        // 인증이 완료되지 않았을 때
        document.getElementById("joinButton").disabled = true; // 회원가입 버튼 비활성화
        document.getElementById("joinButton").classList.remove("btn-outline-primary"); // btn-outline-primary 클래스 제거
        document.getElementById("joinButton").classList.add("btn-outline-secondary"); // btn-outline-secondary 클래스 추가
    }
}

function confirmed() {
    const userResponse = window.confirm("회원가입을 정말 취소하시겠습니까?");
    if (userResponse) {
        window.location.href = "/user/login";
    } else {
    }
}

function setJoinDate() {
    var currentDate = new Date();
    var year = currentDate.getFullYear();
    var month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
    var day = ('0' + currentDate.getDate()).slice(-2);
    var formattedDate = year + '-' + month + '-' + day;
    // user_joindate 필드의 값을 설정
    document.getElementById('user_joindate').value = formattedDate;
}

let isPasswordVisible = false;

function togglePassword() {
    const passwordInput = document.getElementById("pw");
    const toggleButton = document.getElementById("pwToggle");

    if (isPasswordVisible) {
        passwordInput.type = "password";
        toggleButton.innerHTML = '<i class="bi bi-eye"></i>';
    } else {
        passwordInput.type = "text";
        toggleButton.innerHTML = '<i class="bi bi-eye-slash"></i>';
    }
    isPasswordVisible = !isPasswordVisible;
}

function togglerePassword() {
    const passwordInput = document.getElementById("repw");
    const toggleButton = document.getElementById("repwToggle");

    if (isPasswordVisible) {
        passwordInput.type = "password";
        toggleButton.innerHTML = '<i class="bi bi-eye"></i>';
    } else {
        passwordInput.type = "text";
        toggleButton.innerHTML = '<i class="bi bi-eye-slash"></i>';
    }
    isPasswordVisible = !isPasswordVisible;
}

const idCheck = () => {
    const user_id = document.getElementById("user_id").value;
    const id_info = document.getElementById("id_info");
    if (user_id.trim() === "") {
        alert("아이디를 입력하세요.")
        return;
    }
    console.log("입력값: ", user_id);
    $.ajax({
        type: "post",
        url: "/user/id-check",
        data: {
            "user_id": user_id
        },
        success: function (res) {
            console.log("요청성공", res);
            if (res == "ok") {
                console.log("사용 가능한 아이디");
                id_info.innerHTML = "사용 가능한 아이디입니다.";
                id_info.style.color = "blue"
                id_info.style.fontSize = "13px"

            } else {
                console.log("이미 사용중인 아이디입니다");
                id_info.innerHTML = "이미 사용중인 아이디입니다.";
                id_info.style.color = "red"
                id_info.style.fontSize = "13px"
            }
        },
        error: function (err) {
            console.log("에러발생", err);
        }
    });
}
const nickCheck = () => {
    const user_nick = document.getElementById("user_nick").value;
    const nick_info = document.getElementById("nick_info");
    if (user_nick.trim() === "") {
        alert("닉네임을 입력하세요.")
        return;
    }
    console.log("입력값: ", user_nick);
    $.ajax({
        type: "post",
        url: "/user/nick-check",
        data: {
            "user_nick": user_nick
        },
        success: function (res) {
            console.log("요청성공", res);
            if (res == "ok") {
                console.log("사용 가능한 닉네임");
                nick_info.innerHTML = "사용 가능한 닉네임입니다.";
                nick_info.style.color = "blue"
                nick_info.style.fontSize = "13px"
            } else {
                console.log("이미 사용중인 닉네임입니다");
                nick_info.innerHTML = "이미 사용중인 닉네임입니다.";
                nick_info.style.color = "red"
                nick_info.style.fontSize = "13px"
            }
        },
        error: function (err) {
            console.log("에러발생", err);
        }
    });
}

let timer1;
let emailTimer = false; //타이머 작동여부

function mailCheck() {
    // 이메일 입력 필드에서 이메일 주소 가져오기
    let user_email = document.getElementById("user_email").value;

    // 이메일 주소가 비어 있는지 확인
    if (user_email.trim() === "") {
        alert("이메일 주소를 입력해주세요.");
        return;
    }

    console.log(user_email);
    // 여기에 이메일 주소의 유효성을 검사하는 코드를 추가할 수 있습니다.
    // 예를 들어 정규 표현식을 사용하여 이메일 형식을 확인할 수 있습니다.

    // 유효한 이메일 주소인 경우, 서버로 이메일 전송 요청을 보냅니다.
    $.ajax({
        type: "POST",
        url: "/send-email", // 이메일 전송을 처리하는 서버의 엔드포인트 주소
        data: {
            user_email: user_email // 이메일 주소를 서버에 전송합니다.
        },
        success: function (response) {
            // 이메일 전송 요청이 성공한 경우, 서버에서의 응답에 따라 처리할 작업을 추가할 수 있습니다.
            alert("이메일이 성공적으로 전송되었습니다. 인증번호를 확인해주세요.");
            if (emailTimer === false) {
                document.getElementById("emailConfirmNum").style.display = "block"; // 인증번호 입력 영역 표시
                document.getElementById("emailFinish").disabled = false; // 인증 확인 버튼 활성화
                let time = 180;
                timer1 = setInterval(function () {
                    if (time >= 0) {
                        // 시간이 0초 이상이면
                        const min = Math.floor(time / 60);
                        const sec = String(time % 60).padStart(2, "0");
                        document.getElementById("timer1").innerText = min + ":" + sec;
                        time = time - 1;
                    } else {
                        // 시간이 0초 이하이면
                        time += 1;
                        const min = Math.floor(time / 60);
                        const sec = String(time % 60).padStart(2, "0");
                        document.getElementById("emailFinish").disabled = true; // 인증 확인 버튼 비활성화
                        emailTimer = false;
                        clearInterval(timer1); // 타이머 중지
                        document.getElementById("emailAuthKey").value = ""; // 인증번호 입력 필드 초기화
                    }
                }, 1000);
            } else {
                // 타이머가 작동 중일 때
            }
        },
        error: function (err) {
            // 이메일 전송 요청이 실패한 경우, 오류 메시지를 출력할 수 있습니다.
            alert("이메일 전송에 실패했습니다. 다시 시도해주세요.");
            console.error("이메일 전송 오류:", err);
        }
    });
}


// "이메일 인증 확인" 버튼 클릭 시 실행되는 함수
function emailAuthFinish() {
    // 사용자가 입력한 값
    let emailAuthKey = document.getElementById("emailAuthKey").value;
    console.log(emailAuthKey);

    $.ajax({
        type: "post",
        url: "/authKey-check",
        data: {
            "emailAuthKey" : emailAuthKey
        },
        success: function (res){
            console.log("요청성공", res);
            console.log("입력한 인증번호"+emailAuthKey);
            if(res == "ok"){
                console.log("인증 성공");
                alert("인증이 왼료되었습니다.");
                clearInterval(timer1); // 타이머 중지
                document.getElementById("emailToken").disabled = true;
                document.getElementById("emailFinish").textContent = "인증 완료"; //인증 확인 버튼을 인증 완료 버튼으로 변경
                document.getElementById("emailFinish").disabled = true;
                document.getElementById("timer1").innerText = ""; // 인증번호 입력 필드 초기화

            }else {
                console.log("인증 실패");
                alert("인증번호가 맞지 않습니다.");

            }
        },
        error: function (err){
            console.log("에러발생", err);
        }

    });

}