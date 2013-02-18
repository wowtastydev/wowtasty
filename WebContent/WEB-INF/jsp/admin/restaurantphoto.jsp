<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="admin.layout">
<t:putAttribute name="main_admin">
<link rel="stylesheet" type="text/css" href="../css/jquery.combobox.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/admin_message.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../fancybox/source/jquery.fancybox.css?v=2.1.3" media="screen" />
<script src="../js/jquery.combobox.js"></script>
<script src="../fancybox/source/jquery.fancybox.pack.js?v=2.1.3"></script>

<style>
    #photo { width: 100%; height: 100%; background-color:#eaf8f9; list-style-type: none; margin: 0; padding: 0;}
	#photo li { margin: 3px 3px 3px 0; padding: 5px; float: left; width: 120px; height: 120px;}
	
	.image-thumb, .image-thumb img { position:relative; width:120px; height:120px;}
	.image-fav { display:block; position:absolute; top:0; right:0; width:25px; height:25px; background-color:rgba(255,255,255,0.4); background-image:url(../images/admin/trash_s.png); }
	.image-fav:hover { top:0; left:0; width:120px; height:120px; background-color:rgba(255,255,255,0.4); background-image:url(../images/admin/trash_b.png); }
</style>

<script>
<!--	
	function add(frm) {
		if(confirm("Do you want to add photos?")) {
			$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
			frm.submit();
		}
	}
	
	function edit(frm) {
		if(confirm("Do you want to edit photos?")) {
			document.getElementById("sortStr").value = $("#photo").sortable('toArray').toString();
			$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
			frm.action = "editRestaurantPhoto";
			frm.submit();
		}
	}
	
	// Delete Image <li> from Photo list 
	function delImage(value) {
		if(confirm("Do you want to delete the image?")) {
			var photoNodes = document.getElementById("photo").childNodes;
			for (var i=0; i<photoNodes.length; i++) {
				if (photoNodes[i].id == value) {
					photoNodes[i].parentNode.removeChild(photoNodes[i]);
					break;
				}
			}
		}
	}
// -->
</script>

<script>
<!--
    // JQuery
	$(document).ready( function() {
        // Set up tab
        $( "#tabs" ).tabs({
        	active: -1
        });
		
        // Set up button
        $("input[type=button]").button();
        
        // Set up image
        $(".fancybox").fancybox();
        
        $("#photo").sortable({
            placeholder: "ui-state-highlight"
        }).disableSelection();
        
        // Hide instruction after 5 sec
        $("#instruction").delay(5000).hide(1000);
        
	} );
//-->
</script>
<s:url id="contextUrl" action="" namespace="" /> 

<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<a href="javascript:goPage('A203');">Restaurant List</a>
				<s:if test='%{"".equals(selectedID)}'>
					<a href="#" class="active">Add Restaurant</a>
				</s:if>
				<s:else>
					<a href="javascript:goPage('A204');">Add Restaurant</a>
				</s:else>
				<a href="javascript:goPage('A201');">Sign-up List</a>
			</div>
		</div>
	
		<div id="contentarea">
			<s:if test='%{"".equals(selectedID)}'>
			<h2>Add Restaurant</h2>
			</s:if>
			<s:else>
			<h2>Edit Restaurant - <s:property value="restVO.name"/></h2>
			</s:else>
		
			<s:if test="hasFieldErrors()">
				<div class="error">
					<s:fielderror/>
				</div>
			</s:if>
			
			<div id="detailarea">
				<div id="tabs">
					<ul>
				        <li><a href="#Info" onClick ="javascript:goPageParam('A205','<s:property value="selectedID"/>');">Information</a></li>
				        <li><a href="#Menu" onClick ="javascript:goPageParam('A209','<s:property value="selectedID"/>');">Menu</a></li>
				        <li><a href="#Photo" onClick ="javascript:goPageParam('A206','<s:property value="selectedID"/>');">Photo</a></li>
				    </ul>
				    <div id="Info">
					</div>
					<div id="Menu">
					</div>
					<div id="Photo">
						<h3><font color="#555555">Menu Photo</font></h3>
						<s:form theme="simple" name="frm" id="frm" action="insertRestaurantPhoto" method="POST" enctype="multipart/form-data">
						<s:token name="clientToken"></s:token>
						<s:hidden name="selectedID" />
						<s:hidden name="restVO.name"/>
						<s:hidden name="sortStr" id="sortStr" />
						
						<s:if test='%{!"".equals(selectedID)}'>
						<!--  If there is restaurant info -->
						
						<table width="auto">
							<s:if test='%{pictureList.size == 0}'>
								<tr>
									<td colspan=2>
										<img src="../images/admin/noimage.jpg">
									</td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<td colspan=2>
									<ul id="photo" class="connectedSortable">
											<s:iterator value="pictureList" id="pictureList" status="outerStat">
										    <li class="ui-state-default" id ="<s:property value='%{seq}'/>">
										    <div class="image-thumb">
											    <a class="fancybox" rel="group" href="../pictures/restaurant/<s:property value='%{fileName}'/>" title="<s:property value='%{fileName}'/>">
												<img src="../pictures/restaurant/thumb_<s:property value='%{fileName}'/>">
												</a>
												<a href="#" onClick="javascript:delImage('<s:property value="%{seq}"/>');" class="image-fav" title="Delete Image"></a>
											</div>
										    </li>
										    </s:iterator>
									</ul>
									<!-- Pictures' hidden value to edit -->
									<s:iterator value="pictureList" id="pictureList" status="outerStat">
										<s:hidden name="pictureList[%{#outerStat.index}].restaurantID" />
										<s:hidden name="pictureList[%{#outerStat.index}].seq" />
										<s:hidden name="pictureList[%{#outerStat.index}].fileName" />
										<s:hidden name="pictureList[%{#outerStat.index}].filePath" />
										<s:hidden name="pictureList[%{#outerStat.index}].fileSize" />
									</s:iterator>
									</td>
								</tr>
								<tr>
									<td colspan=2>
										<input type="button" value="Edit" onClick="javascript:edit(this.form);" />
									</td>
								</tr>
								<tr>
									<td colspan=2>
										<div id="instruction" class="ui-state-highlight ui-corner-all" style="margin-top: 0px; margin-bottom: 20px; padding: 0 .7em;">
											<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
												<strong>Drag&Drop</strong> to change sort. Click <strong>delete images</strong> to delete.<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;After changing, click <strong>"Edit"</strong> button to save.</p>
										</div>
									</td>
								</tr>
							</s:else>
							<s:if test='%{pictureUploadList.size != 0}'>
								<tr>
									<td>
									<s:iterator value="pictureUploadList" id="pictureUploadList" status="outerStat">
										<s:file name="pictureUploadList[%{#outerStat.index}].file" accept="image/*" />
										<s:hidden name="pictureUploadList[%{#outerStat.index}].restaurantID" />
										<s:hidden name="pictureUploadList[%{#outerStat.index}].seq" /><br>
									</s:iterator>
									</td>
									<td valign="top">
										
									</td>
								</tr>
								<tr>
									<td colspan=2>
										<input type="button" value="Add" onClick="javascript:add(this.form);" />
									</td>
								</tr>
							</s:if>
						</table>
						
						</s:if>
						<s:else>
						<!--  If there is restaurant info -->
							<div id="instruction" class="ui-state-highlight ui-corner-all" style="width:320; margin-top: 20px; padding: 0 .7em;">
								<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
									<strong>Please save Restaurant General Information first.</strong></p>
							</div>
						</s:else>
						</s:form>						
						<table width="100%">
							<tr>
								<td><hr></td>
							</tr>
							<tr>
								<td>
									<input type="button" value="List" onClick="javascript:goPage('A203');" />
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</t:putAttribute>
</t:insertDefinition>