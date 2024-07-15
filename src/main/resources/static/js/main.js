$(document).ready(function() {
    // 학생 삭제
    $(".deleteBtn").click(function() {
        console.log("삭제 버튼 클릭")
        if (confirm("정말 삭제하시겠습니까?")) {
            var row = $(this).closest("tr");
            var studentId = row.find(".studentId").text();
            $.ajax({
                url: '/students/delete',
                type: 'POST',
                data: { id: studentId },
                success: function(response) {
                    if (response === true) {
                        alert("삭제되었습니다.");
                        row.remove();
                    } else {
                        alert("삭제에 실패했습니다.");
                    }
                },
                error: function(error) {
                    alert("삭제 중 오류가 발생했습니다.");
                    console.error("Error:", error);
                }
            });
        }
    });

    // 추가 버튼 클릭 이벤트
    $("#addBtn").click(function() {
        $("#addModal").css("display", "block");
    });

    // 모달 닫기 버튼 클릭 이벤트
    $(".close").click(function() {
        $("#addModal").css("display", "none");
    });

    // 모달 외부 클릭 시 닫기
    $(window).click(function(event) {
        if (event.target == $("#addModal")[0]) {
            $("#addModal").css("display", "none");
        }

        if (event.target == $("#editModal")[0]) {
            $("#editModal").css("display", "none");
        }
    });

    // 학생 이름 클릭 시
    $(".studentName").click(function() {
        var row = $(this).closest("tr");
        var id = row.find(".studentId").text();
        var name = row.find(".studentName").text();
        var address = row.find(".studentAddress").text();
        var school = row.find(".studentSchool").text();
        var major = row.find(".studentMajor").text();

        // 모달 폼에 현재 학생 정보 설정
        $("#editId").val(id);
        $("#editName").val(name);
        $("#editAddress").val(address);
        $("#editSchool").val(school);
        $("#editMajor").val(major);

        // 수정 모달 표시
        $("#editModal").css("display", "block");
    });

    // 수정 모달 닫기 버튼 클릭 이벤트
    $("#editModal .close").click(function() {
        $("#editModal").css("display", "none");
    });
});