
function onInput(event) {
    // alert ("The new content: " + event.target.value);
    var type = $("#selectNameOrField").val();
    var inputContent = event.target.value;
    if(inputContent == ""){
        $("#searchAssociation").css("display","none");

        return;
    }
    var recommend = 5;
    if(type == "field"){ //按研究领域查询
        $.ajax({
            type: 'GET',
            url: "/api/expert/recommend",
            dataType: "json",
            data: {
                recommend: recommend,
                field: inputContent
            },
            success: function(res){
                if(res.code == 1){
                    $("#searchAssociation").empty();
                    $("#searchAssociation").css("display","block");
                    var resultList = res.data;
                    $.each(resultList,function(i,row){
                        $("#searchAssociation").append('<li><a href="/expert?type=field&searchContent='+ encodeURI(encodeURI(row.expertName)) +'">'+ row.expertName +'</a></li>')
                    })
                }else{
                    layer.msg("查询失败",{time:1000})
                }
            }

        })
    }else{
        $.ajax({
            type: 'GET',
            url: "/api/expert/recommend",
            dataType: "json",
            data: {
                recommend: recommend,
                expertName: inputContent
            },
            success: function(res){
                if(res.msg == "success"){
                    $("#searchAssociation").empty();
                    $("#searchAssociation").css("display","block");
                    var resultList = res.data;
                    $.each(resultList,function(i,row){
                        $("#searchAssociation").append('<li><a href="/expert?type=expertName&searchContent='+ encodeURI(encodeURI(row.expertName)) +'">'+ row.expertName +'</a></li>')
                    })
                }else{
                    layer.msg("查询失败",{time:1000})
                }
            }

        })
    }

}