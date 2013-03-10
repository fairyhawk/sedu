<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_order.css" />
<link href="<%=importURL%>/styles/usercenter/popup.css" rel="stylesheet" type="text/css" />
<div class="main">
	<div class="nav">
      <ul class="nav_list">
        <li><a style="cursor:pointer;" onclick="centerLocation(1);" id='uc_left_1'><i class="nav_icon nav_icon_home"></i>个人中心</a></li>
        <li><a style="cursor:pointer;" onclick="centerLocation(2);" id='uc_left_3'><i class="nav_icon nav_icon_order"></i>订&nbsp;&nbsp;&nbsp;&nbsp;单<span class="nav_num" id="order" style="display: none"></span></a></li>
        <li><a style="cursor:pointer;" onclick="centerLocation(3);" id='uc_left_2'><i class="nav_icon nav_icon_message"></i>消&nbsp;&nbsp;&nbsp;&nbsp;息<span class="nav_num" id="msg" style="display: none"></span></a></li>
        <li><a style="cursor:pointer;" onclick="centerLocation(4);" id='uc_left_4'><i class="nav_icon nav_icon_myClass"></i>我的课程<span class="icon_hot"><img src="<%=importURL%>/images/usercenter/hot_icon.png" width="23" height="14" alt="hot" /></span><span class="nav_num" id="course" style="display: none"></span></a>
          <!-- 
          <ul class="sub_nav">
            <li><a style="cursor:pointer;">我看他看</a></li>
          </ul> -->
        </li>
         <li><a style="cursor:pointer;" onclick="centerLocation(6);"  id='uc_left_10' onclick="examcookie()"><i class="nav_icon nav_icon_exam"></i>考试中心<span class="icon_hot"><img src="<%=importURL%>/images/usercenter/hot_icon.png" width="23" height="14" alt="hot" /></span><span class="nav_num" id="examPaper" style="display: none"></span></a>
          <ul class="sub_nav">
            <li><a style="cursor:pointer;" onclick="centerLocation(13);" id='uc_left_6'>随机考试</a></li>
          </ul>
        </li>
        <li><a style="cursor:pointer;" onclick="centerLocation(7)"><i class="nav_icon nav_icon_highsoQA" id='uc_left_7'></i>小Hi问答<span class="icon_hot"><img src="<%=importURL%>/images/usercenter/hot_icon.png" width="23" height="14" alt="hot" /></span></a>
        </li>
        <li><a style="cursor:pointer;" onclick="centerLocation(8);" id='uc_left_9'  ><i class="nav_icon nav_icon_learnPro"></i>学习计划</a>
        </li>
        <li><a style="cursor:pointer;" onclick="centerLocation(10);" id='uc_left_8' ><i class="nav_icon nav_icon_learnAss"></i>学习评估</a>
        </li>
        <li><a style="cursor:pointer;" onclick="centerLocation(5);" id='uc_left_5'><i class="nav_icon nav_icon_test"></i>测&nbsp;&nbsp;&nbsp;&nbsp;评</a></li>
	
      </ul>
    </div>
