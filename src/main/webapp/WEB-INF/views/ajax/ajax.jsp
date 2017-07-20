<script type="text/javascript">
    $(document).ready(function(){
        //当点击页面导航的超链接时发送异步请求
        $("#pageNav a").click(function(){
            var idx = $("#pageNav a").index($(this));//返回当前元素在导航的超链接集合中索引
            var curPageNo=parseInt($("#pageNo").val());//当前页数,因为要计算，注意类型转换
            var totalPage=$("#totalPage").val();//总页数
            var pageNo=1;//要请求的页面

            switch(idx){
                case 0:
                    pageNo=1;
                    break;
                case 1:
                    pageNo=curPageNo-1;
                    break;
                case 2:
                    pageNo=curPageNo+1;
                    break;
                case 3:
                    pageNo=totalPage;
                    break;
            }

            $.getJSON("${pageContext.request.contextPath }/student/getPageJson",{pageNo:pageNo},function(json){
                //更新当前页面数
                $("#pageNo").val(json.pageNo);
                //更新表格中的数据
                //删除旧数据
                $("#studentData tr:gt(0)").remove();//删除非第一行的元素
                //添加新查询的数据
                //遍历json中data集合
                var str;
                $.each(json.data,function(idx,stu){
                    str="";
                    str+="<tr>";
                    str+="<td>"+stu.id+"</td>"
                    str+="<td>"+stu.name+"</td>"
                    str+="<td>"+stu.birth+"</td>"
                    str+="<td><a href=\"student/updateStudent?studentId="+stu.id+"\">编辑</a></td>";
                    str+="<td><input type=\"checkbox\" value=\""+stu.id+"\" name=\"delIds\"/></td>";
                    str+="</tr>";
                    $("#studentData").append(str);//将集合中的每一个元素转换为一个tr元素，并附加到表格中
                });
            })
        });
        $("#selectAll").click(function(){
            $("#studentData :checkbox").prop("checked","true");
        });
    });
</script>
</head>
<body>
    <div class="page">
        <!-- 页头开始 -->
    <div id="header">
        <jsp:include page="header.jsp"></jsp:include>
    </div>
    <!-- 页头结束 -->
    <!--内容开始 -->
    <div id="content">
        <div id="menu" style="float: left">
            <jsp:include page="menu.jsp"></jsp:include>
        </div>
        <div id="workspace" style="float: left;margin-left: 50px;">
            <form action="student/deleteSelectedStudent">
    <table id="studentData">
        <tr>
            <td>ID</td>
            <td>姓名</td>
            <td>生日</td>
            <td>编辑</td>
            <td>删除</td>
        </tr>
        <c:forEach items="${page.data }" var="student">
            <tr>
                <td>${student.id }</td>
                <td>${student.name }</td>
                <td><fmt:formatDate value="${student.birth }" pattern="yyyy-MM-dd"/> </td>
                <td><a href="student/updateStudent/${student.id }">编辑</a></td>
                <td><input type="checkbox" value="${student.id }" name="delIds"/></td>
            </tr>
        </c:forEach>
    </table>
        <input type="button" value="全选" id="selectAll"/>
        <input type="submit" value="删除所选">
    </form>
    <%--
    <div>
        第${page.pageNo}页
        共${page.totalPage}页
        <a href="student/listStudent?pageNo=1">第一页</a>
        <c:if test="${!page.firstPage }">
        <a href="student/listStudent?pageNo=${page.pageNo-1 }">上一页</a>
        </c:if>
        <c:if test="${!page.lastPage }">
        <a href="student/listStudent?pageNo=${page.pageNo+1 }">下一页</a>
        </c:if>
        <a href="student/listStudent?pageNo=${page.totalPage }">最后一页</a>     
    </div>
    --%>
    <!-- 使用jquery及json实现基于异步请求的分页 -->
    <input type="hidden" id="pageNo" value="${page.pageNo}"/>
    <input type="hidden" id="totalPage"value="${page.totalPage}"/>

    <div id="pageNav">
        <a href="javascript:void(0)">第一页</a>
        <a href="javascript:void(0)">上一页</a>
        <a href="javascript:void(0)">下一页</a>
        <a href="javascript:void(0)">最后一页</a>     
    </div>
        </div>
    </div>
    <!--内容结束 -->
    <!-- 页脚开始 -->
    <div id="footer">
        <jsp:include page="footer.jsp"></jsp:include>
    </div>
    <!-- 页脚结束 -->
    </div>
