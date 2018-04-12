//写cookies
function setCookies(name,value)
{
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

//读取cookies
function getCookies(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

    if(arr=document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}

//删除cookies
function delCookies(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}
  //取得url参数值
 function getUrlParam(name) {
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); //构造一个含有目标参数的正则表达式对象
      var r = window.location.search.substr(1).match(reg); //匹配目标参数
      if (r != null) return unescape(r[2]); return null; //返回参数值
     }

     /*
     vpageSize:
     vpageIndex:
      */
function page_list(vpageSize,vpageIndex,vtotalCount,vcentSize,linkurl,searchContent,type)
               {

                   var pageSize = parseInt(vpageSize);
                   var pageIndex = parseInt(vpageIndex);
                   var totalCount = parseInt(vtotalCount);
                   var centSize = parseInt(vcentSize);


                       //计算页数
                    if (totalCount < 1 || pageSize < 1)
                    {
                        return "";
                    }
                    var pageCount = parseInt(totalCount / pageSize);
                    if (pageCount < 1)
                    {
                        return "";
                    }
                    if (totalCount % pageSize > 0)
                    {
                        pageCount += 1;
                    }
                    if (pageCount <= 1)
                    {
                        return "";
                    }
                    var pageStr = "";
                    if( searchContent ==null && type == null){
                        var firstBtn = '<a href="'+linkurl+'?page=' +parseInt(pageIndex - 1)+ '">«上一页</a>';
                        var lastBtn = '<a href="'+linkurl+'?page=' +parseInt(pageIndex +1)+  '">下一页»</a>';
                        var firstStr = '<a href="'+linkurl+'?page=1">1</a>';
                        var lastStr = '<a href="'+linkurl+'?page='+pageCount+'">'+pageCount+'</a>';
                    }else if(searchContent != null && type ==null){
                        var firstBtn = '<a href="'+linkurl+'?page=' +parseInt(pageIndex - 1)+ '&searchContent='+ searchContent +'">«上一页</a>';
                        var lastBtn = '<a href="'+linkurl+'?page=' +parseInt(pageIndex +1)+  '&searchContent='+ searchContent +'">下一页»</a>';
                        var firstStr = '<a href="'+linkurl+'?page=1&searchContent='+ searchContent +'">1</a>';
                        var lastStr = '<a href="'+linkurl+'?page='+pageCount+'&searchContent='+ searchContent +'">'+pageCount+'</a>';
                    }else if(searchContent != null && type != null){
                        var firstBtn = '<a href="'+linkurl+'?page=' +parseInt(pageIndex - 1)+ '&searchContent='+ searchContent +'&type='+ type +'">«上一页</a>';
                        var lastBtn = '<a href="'+linkurl+'?page=' +parseInt(pageIndex +1)+  '&searchContent='+ searchContent +'&type='+ type +'">下一页»</a>';
                        var firstStr = '<a href="'+linkurl+'?page=1&searchContent='+ searchContent +'&type='+ type +'">1</a>';
                        var lastStr = '<a href="'+linkurl+'?page='+pageCount+'&searchContent='+ searchContent +'&type='+ type +'">'+pageCount+'</a>';
                    }


                    if (pageIndex <= 1)
                    {
                        firstBtn = "<span class=\"disabled\">«上一页</span>";
                    }
                    if (pageIndex >= pageCount)
                    {
                        lastBtn = "<span class=\"disabled\">下一页»</span>";
                    }
                    if (pageIndex == 1)
                    {
                        firstStr = "<span class=\"current\">1</span>";
                    }
                    if (pageIndex == pageCount)
                    {
                        lastStr = "<span class=\"current\">" + pageCount + "</span>";
                    }
                    var firstNum = parseInt(pageIndex - (centSize / 2)); //中间开始的页码
                    if (pageIndex < centSize)
                        firstNum = 2;
                    var lastNum = parseInt(pageIndex + centSize - ((centSize / 2) + 1)); //中间结束的页码
                    if (lastNum >= pageCount)
                        lastNum = pageCount - 1;
                    pageStr = pageStr + "<span>共" + totalCount + "记录</span>";
                    pageStr = pageStr + firstBtn + firstStr;
                    if (pageIndex >= centSize)
                    {
                        //pageStr.Append("<span>...</span>\n");
                          pageStr = pageStr + "<span>...</span>\n";
                    }
                    for (var i = firstNum; i <= lastNum; i++)
                    {
                        if (i == pageIndex)
                        {
                            //pageStr.Append("<span class=\"current\">" + i + "</span>");
                             pageStr = pageStr + "<span class=\"current\">" + i + "</span>";
                        }
                        else
                        {
                            //pageStr.Append("<a href=\"" + ReplaceStr(linkUrl, pageId, i.ToString()) + "\">" + i + "</a>");
                            //pageStr = pageStr + "<a href=\"" + "\">" + i + "</a>";
                            if( searchContent ==null && type == null){
                                pageStr = pageStr +'<a href="'+linkurl+'?page='+i+'">'+i+'</a>'
                            }else if(searchContent != null && type == null){
                                pageStr = pageStr +'<a href="'+linkurl+'?page='+i+'&searchContent='+ searchContent +'">'+i+'</a>'
                            }else if(searchContent != null && type != null){
                                pageStr = pageStr +'<a href="'+linkurl+'?page='+i+'&searchContent='+ searchContent +'&type='+ type +'">'+i+'</a>'
                            }

                        }
                    }
                    if (pageCount - pageIndex > centSize - ((centSize / 2)))
                    {
                        //pageStr.Append("<span>...</span>");
                        pageStr = pageStr + "<span>...</span>";
                    }
                    //pageStr.Append(lastStr + lastBtn);
                    pageStr = pageStr + lastStr + lastBtn;

                     return pageStr;
               }

function getGender(gender){
    if(gender == 1){
        return "男";
    }else if(gender == 2){
        return "女";
    }else {
        return null;
    }
}

function getTags(tags) {
    if(tags == null){
        return "";
    }
    var tagsArr=tags.split(";");
    var str = "";
    for(var i=0;i < tagsArr.length;i++){
         str = str + "<span>" + tagsArr[i] + "</span>";
    }
    return str;
}