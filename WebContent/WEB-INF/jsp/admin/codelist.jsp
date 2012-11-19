<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<div id="page">
	<div id="mainarea">
	<div id="sidebar">
		<div id="sidebarnav">
		<a class="active" href="#">Home</a>
		<a href="#">Services</a>
		<a href="#">Pricing</a>
		<a href="#">Contact Us</a>
		</div>
		
		<h2>TESTIMONIALS</h2>
		<div class="widget">
		Lorem ipsum dolor sit amet, consectetur. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
		<br/>
		<strong>Joe Bloggs</strong>
		<br/><br/>
		Lorem ipsum dolor sit amet, consectetur. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
		<br/>
		<strong>Joe Bloggs</strong>
	</div></div>
	
	<div id="contentarea">
		<h2>WELCOME</h2>
		Blueshine is a CSS template that is free and fully standards compliant. <a href="http://www.free-css-templates.com/">Free CSS templates</a> designed this template.
		This template is allowed for all uses, including commercial use, as it is released under the <strong>Creative Commons Attributions 2.5</strong> license. The only stipulation to the use of this free template is that the links appearing in the footer remain intact. Beyond that, simply enjoy and have fun with it!	
		<br><br>
		Lorem ipsum dolor sit amet, consectetur. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit. 
		<br><br>
		Anim id est laborum adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
		<br><br>
		Lorem ipsum <a href="#">link</a> dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
		<br><br>
		Lorem ipsum dolor sit amet, consectetur. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
		
	</div>
	
	
	</div>

	
	<s:form theme="simple" name="frm" id="frm" action="searchCode">
		<s:iterator value="codeMasterList" id="codeMasterList" status="outerStat">
			<s:property value="%{code}" />&nbsp;<s:property value="%{name}" />
		</s:iterator>
	</s:form>
</div>
</t:putAttribute>
</t:insertDefinition>