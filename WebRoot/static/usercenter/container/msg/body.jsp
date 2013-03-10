<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<div class="contenter">
        <div class="order_con">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
    	   <td height="650" valign="top">
              <div class="switch2">
                  <ul class="switch2T">       
	                  <li> <a href="javascript:void(0)" id="switchT0"  class="current" onclick="swichMsg(0)">系统通知</a></li>
	                  <li> <a href="javascript:void(0)" id="switchT1"   onclick="swichMsg(1)">新手任务</a></li>
                  </ul>
                  <form action="<%=contextPath%>/msg/webmsg!listReceiveMsgs.action" method="post" name="tasklist" id="tasklist">
						<s:hidden name="queryUserMsgCondition.currentPage" value="1" />
                        <div id="switch0" class="switch2D msg_list">
	                        <s:if test="page.pageResult != null && page.pageResult.size() != 0">
		                    	<ul class="ptop-10">
			                        <s:iterator value="page.pageResult" id="msg">
			                        	<li>
			                               <span class="right"><a href="<%=contextPath%>/msg/webmsg!deleteMsg.action?userMsg.id=<s:property value='#msg.id'/>" onclick="return confirm('是否删除？')">删除</a></span>
			                               <img src="<%=importURL%>/images/usercenter/icon_msg.gif" /> <s:property value='%{#msg.msg.msgContent}' escape="false" /> <font>［<s:date name="#msg.sendTime" format="yyyy-MM-dd HH:mm:ss" />］</font>
			                            </li>
			                        </s:iterator>
		                        </ul>
		                        <!-- 分页页码 -->
		                        <div class="pager">
		                        <div class="pager_con">
		                            <jsp:include page="/static/usercenter/jsp/common/showPage.jsp" />
		                            </div>
		                        </div>
		                        <!-- 分页页码 //-->
		                    </s:if>
                        </div>
                  </form>
                  <div id="switch1" class="switch2D" style="display:none;">
                  	<s:iterator value='taskCusList' id='taskCus'>
                    	<div class="rw_box">
			           		<img src="http://import.highso.org.cn/upload/task/<s:property value='#taskCus.task.iconUrl'/>"  />
			                	<div>
                                	<span><font>
                                       <s:if test="#taskCus.isComplete==0">
                                       		未完成
                                       </s:if>     
                                        <s:else>
                                        	完成
                                        </s:else>    
                                            </font><h4><s:property value='#taskCus.task.taskName'/></h4></span>
                                            <p>
                                              <s:property value='#taskCus.task.taskContent'/>  
                                            </p>
                                            <span><font>
                                            <s:if test="#taskCus.isComplete==0">                                            	
                                            	<a class="rw_sub rw_sub2" href="#"  onclick="window.location='<%=contextPath %>/<s:property value='#taskCus.task.executeUrl'/>'">去做任务</a>
                                            </s:if>
                                            <s:else>                                            	
                                            	<a class="rw_sub rw_sub2" href="#"  onclick="getEncourage(<s:property value='#taskCus.id'/>)">领取奖励</a>
                                            </s:else>
                                            
                                            </font><img src="<%=importURL%>/images/usercenter/icon_jl.gif" /> 
                                            	奖励积分：<s:property value='#taskCus.task.jifen'/>
                                            	 </span>
                                        </div>                                
                                    </div>
                                    </s:iterator>
                                </div>                   	
                                <div class="clear"></div>                        
                            </div>
            </td>
  	    </tr>
  	  </table>
  	  </div>
  	  </div>
  	  </div>