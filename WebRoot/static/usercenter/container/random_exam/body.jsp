<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<div class="contenter">
			<form name="startExam" action="<%=contextPath %>/exam/qstManager!startRandomExam.action" method="post">
				<input type="hidden" name="difficult_set" value="1"/>
				
				
			      <div class="exam_random_con">
			        <img src="<%=importURL%>/images/usercenter/randomExamResult/e_random_bg.png" alt="自由做题" />
				        <div class="exam_random_part">
				          <h4 class="exam_random_tit">选择一个专业开始做题：</h4>
				          <ul class="exam_random_range">
				           <s:iterator value="subjectList" id="subj" status="subId">
				<li>
				   <label><input type="radio" name="subjectIdweb" value='<s:property value="#subj.subjectId"/>' checked='<s:property value="#subId.index==1?'checked':''" />' >
				   <s:property value="#subj.subjectName"/></label></li>
				</s:iterator>
				          </ul>
					     </div>
				        <div class="exam_random_part">
				          <h4 class="exam_random_tit">选择覆盖题型范围</h4>
				          <ul class="exam_random_range">
				             <li><label><input type="checkbox" name="qst_type" value="1" checked="true"/>单选</label></li>
				         	 <li><label><input type="checkbox" name="qst_type" value="2" checked="true"/>多选</label></li>
				        	 <li><label><input type="checkbox" name="qst_type" value="3" checked="true"/>判断</label></li>
				        	 <!-- <li><label><input type="checkbox" name="qst_type" value="6" checked="true"/>问答</label></li>
				       	     <li><label><input type="checkbox" name="qst_type" value="4" checked="true"/>材料分析</label></li> --> 
				          </ul>
					     </div>
				        <div class="exam_random_part">
				          <h4 class="exam_random_tit">出现的题目</h4>
				          <ul class="exam_random_topic">
				          	 <li><label><input type="radio" name="scope" value="1" checked="true"/>出现所有题目</label></li>
				             <li><label><input type="radio" name="scope" value="2"/>仅出现我没有做过的题目</label></li>
				             <li><label><input type="radio" name="scope" value="3"/>仅出现我曾做过的题目</label></li>
				          </ul>
					     </div>
			        <div class="exam_random_part">
			          <h4 class="exam_random_tit">选择题型难度</h4>
					          <div class="exam_random_con1"><span class="exam_random_d1">30%</span><span class="exam_random_d2">70%</span></div>
					          
					            <table id="loading_table" style="broder:0;borderStyle:none;width:580px;line-height:4px;background:#C2C2C2;">
					           	 <tr id="loading_tr">
					        	  <td id="grade_1" name="1" >&nbsp;</td>
					        	  <td id="grade_2" name="2">&nbsp;</td>
					        	  <td id="grade_3" name="3">&nbsp;</td>
					             </tr>
					            </table>     
					         
					</div>
					          <div class="exam_random_con3"><span class="fl"><input name="grade" type="radio" value="">30%的人做错</span><span class="fr"><input name="grade" type="radio" value="">70%的人做错</span>
					          </div>
					          <div class="exam_random_con4">快速设定：
					          <a name="1" href="javascript:void(0);" style="TEXT-DECORATION: none" onclick="quick_select('1');">&nbsp;菜鸟&nbsp;</a>
					          <a name="2" href="javascript:void(0);" style="TEXT-DECORATION: none" onclick="quick_select('2');">&nbsp;进阶&nbsp;</a>
							  <a name="3" href="javascript:void(0);" style="TEXT-DECORATION: none" onclick="quick_select('3');">&nbsp;高手&nbsp;</a>
					          </div>
			        <div class="exam_random_part">
			          <h4 class="exam_random_tit">答案显示模式</h4>
			          <ul class="exam_random_topic">
			          	<li><label><input name="result_type" type="radio" value="1" checked="checked">最后一次性显示做题结果</label></li>
          			<li><label><input name="result_type" type="radio" value="2">每做一题立即显示答案</label></li>
          			</ul>
           			 <input  type="submit" onclick="return subm();" value="" class="examRandom_startbtn"/></input>
            		</div>  
            		 </div> 	
       		</form>
		</div>   
		<div class="clear"></div>
	</div> 
