<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <main>

        <div class="row g-5">

            <div>
                <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <c:choose>
                        <c:when test="${loginMember eq null}">
                            <span class="text-primary">회원가입</span>
                        </c:when>
                        <c:otherwise>
                            <span class="text-primary">정보수정</span>
                        </c:otherwise>

                    </c:choose>
                </h4>
            </div>


                <form class="needs-validation" action="/joinGo" method="post">
                    <div class="row g-3">
                        <div class="col-sm-6">
                            <label for="mi_name" class="form-label">회원 이름</label>
                            <input type="text" class="form-control" id="mi_name" name="mi_name" placeholder="성함" value="${loginMember.mi_name}" required>
                            <div class="invalid-feedback">
                                필수 조건입니다.
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <label for="mi_birthday" class="form-label">생년월일</label>
                            <input type="text" class="form-control" id="mi_birthday" name="mi_birthday" placeholder="" value="${loginMember.mi_birthday}" required>
                            <div class="invalid-feedback">
                                필수 조건입니다.
                            </div>
                        </div>


                        <div class="col-12">
                            <c:choose>
                                <c:when test="${loginMember eq null}">
                                    <label for="mi_id" class="form-label">아이디</label>
                                    <div class="input-group has-validation">
                                        <input type="text" class="form-control" id="mi_id" name="mi_id" placeholder="ID" oninput="checkId()" required>
                                        <div class="invalid-feedback">
                                            필수 조건입니다.
                                        </div>
                                    </div>
                                    <div class ="check_font" id ="id_check"></div>
                                </c:when>
                                <c:otherwise>
                                    <label for="mi_id" class="form-label">아이디</label>
                                    <div class="input-group has-validation">
                                        <input type="text" class="form-control" id="mi_id" name="mi_id" placeholder="ID" readonly value="${loginMember.mi_id}" required>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>




                        <div class="col-12">
                            <label for="mi_pw" class="form-label">비밀번호</label>
                            <div class="input-group has-validation">
                                <input type="password" class="form-control" id="mi_pw"  name="mi_pw" placeholder="password" value="${loginMember.mi_pw}" required>
                                <div class="invalid-feedback">
                                    필수 조건입니다.
                                </div>
                            </div>
                        </div>

                        <div class="col-12">
                            <label for="mi_email" class="form-label">이메일 <span class="text-muted"></span></label>
                            <input type="email" class="form-control" id="mi_email" name="mi_email" placeholder="you@example.com" value="${loginMember.mi_email}">
                            <div class="invalid-feedback">
                                필수 조건입니다.
                            </div>
                        </div>


                        <div class="col-12">
                            <label for="mi_tell" class="form-label">전화번호</label>
                            <div class="input-group has-validation">
                                <input type="text" class="form-control" id="mi_tell" name="mi_tell" placeholder="Tell" value ="${loginMember.mi_tell}" >
                            </div>
                        </div>


                        <div class="col-12">
                            <label for="mi_mobile" class="form-label">휴대전화</label>
                            <div class="input-group has-validation">
                                <input type="text" class="form-control" id="mi_mobile" name="mi_mobile" placeholder="Mobile" value="${loginMember.mi_mobile}" required>
                                <div class="invalid-feedback">
                                    필수 조건입니다.
                                </div>
                            </div>
                        </div>


                        <div class="col-12">
                            <label for="address" class="form-label">주소</label>
                            <input type="text" class="form-control" id="address" name="address" placeholder="클릭하세요"  onclick="Postcode()" value="${arr[0]}"  required readonly>
                            <div class="invalid-feedback">
                                필수 조건입니다.
                            </div>
                        </div>

                        <div>
                            <input type="text" id="postcode" name="postcode" placeholder="우편번호" value="${arr[1]}" required readonly>
                            <div class="invalid-feedback">
                                필수 조건입니다.
                            </div>
                        </div>

                        <div class="col-12">
                            <label for="address2" class="form-label">상세주소 </label>
                            <input type="text" class="form-control" id="address2" name="address2" placeholder="상세주소" value="${arr[2]}" required>
                            <div class="invalid-feedback">
                                필수 조건입니다.
                            </div>
                        </div>


                    </div>

                    <hr class="my-4">
                    <c:choose>
                        <c:when test="${loginMember eq null}">
                            <button class="w-100 btn btn-primary btn-lg" type="submit" > 회원가입 시작</button>
                        </c:when>

                        <c:otherwise>
                            <button class="w-100 btn btn-primary btn-lg"  type="submit" >정보 수정</button>
                            <button class="w-100 btn btn-primary btn-lg" type="button" onclick="deleteId()" >회원 탈퇴</button>
                        </c:otherwise>
                    </c:choose>


                </form>
            </div>

    </main>

</div>>

<script>
    function Postcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }

                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("address2").focus();
            }
        }).open();
    }
</script>
<script type="text/javascript" src="lib.js"></script>
<script>


    function deleteId(){
        var id = $('#mi_id').val(); //id값이 "id"인 입력란의 값을 저장

        $.ajax({
            url: "/deleteMember", //Controller에서 인식할 주소
            type:'POST', //POST 방식으로 전달
            data:{ "mi_id" : id},

            //신호 받음
            error:function(){
                location.href = "/"
                alert("에러입니다");
            },
            success:function(data){
                if (data == 'y' ){
                    location.href = "/"
                    alert("회원탈퇴 성공입니다.")
                }else{
                    location.href = "/"
                    alert("회원탈퇴 실패입니다.");
                }
            }
        });

    }



    function checkId(){
        var id = $('#mi_id').val(); //id값이 "id"인 입력란의 값을 저장
        var idJ = /^[a-z0-9]{4,12}$/;

        if(idJ.test(id)){
            $.ajax({
                url: "/checkID", //Controller에서 인식할 주소
                type:'POST', //POST 방식으로 전달
                data:{ "mi_id" : id},

                success:function(data){
                    if (data == 'y' ){
                        $('#id_check').text('사용 가능한 아이디입니다.');
                        $('#id_check').css('color', 'red');
                        $('#reg_submit').attr("disabled", false);

                    }else{
                        $('#id_check').text('이미 사용중인 아이디입니다.');
                        $('#id_check').css('color', 'red');
                        $('#reg_submit').attr("disabled", true);
                    }


                },
                error:function(){
                    alert("에러입니다");
                }
            });
        }else{
            $('#id_check').text('양식에 맞지 않습니다.');
            $('#id_check').css('color', 'red');
            $("#reg_submit").attr("disabled", true);
        }


    };
</script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>