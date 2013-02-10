<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> 
<t:insertDefinition name="rest.layout">
<t:putAttribute name="main_rest">
<link rel="stylesheet" href="../css/admin_message.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../css/jquery.combobox.css" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="../fancybox/source/jquery.fancybox.css?v=2.1.3" media="screen" />
<script src="../js/jquery.combobox.js"></script>
<script src="../fancybox/source/jquery.fancybox.pack.js?v=2.1.3"></script>
<style>
    ul#categoryTree, ul#categoryTree ul { list-style-type:none; margin: 0; padding: 0; width:300px;}
	ul#categoryTree li a { display: block; text-decoration: none; color: #fff; line-height:30px; text-indent:20px; height:30px; background-image:url('../images/admin/menu.gif'); background-repeat:no-repeat;cursor:pointer;}
	ul#categoryTree li a:hover { background-image:url('../images/admin/menu_over.gif');}
	ul#categoryTree li ul li a { background: #eaf1f6; color: #000; margin-top:1px; height:25px;}
	ul#categoryTree li ul li a:hover { text-indent:15px; background: #cadded; border-left: 5px #217fa4 solid;}
	
</style>
    
<script>
<!--	
	// Save menu
	function save(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	// Copy menu
	function copy(){
		var menuid = document.getElementById("selectedMenuID").value;
		if (menuid == 0) {
			// Show Error Message
			errorMessage.innerHTML = "Please save Menu Information first or select a menu from the menu category.";
			$( "#errorMessagePanel" ).dialog( "open" );
			return;
		}
		document.getElementById("frm").action = "copyMenu";
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	// Delete menu
	function del(){
		var menuid = document.getElementById("selectedMenuID").value;
		if (menuid == 0) {
			// Show Error Message
			errorMessage.innerHTML = "Please save Menu Information first or select a menu from the menu category.";
			$( "#errorMessagePanel" ).dialog( "open" );
			return;
		}
		if(confirm("Do you want to delete the menu?")) {
			document.getElementById("frm").action = "delMenu";
			$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
			document.getElementById("frm").submit();
		}
	}
	
	// Save changes of category and menu sort 
	function saveTree() {
		document.getElementById("categorySortStr").value = $("#categoryTree").sortable('toArray').toString();
		var categoryArray = $("#categoryTree").sortable('toArray');
		var menuArray;
		var arrayStr;
		var isFirst = true;

		for (var i=0; i<categoryArray.length; i++) {
			menuArray = $("#menuTree"+categoryArray[i]).sortable('toArray');
			if (menuArray.length>0) {
				if (isFirst) {
					arrayStr = menuArray.toString();
					isFirst = false;
				} else {
					arrayStr = arrayStr + "," + menuArray.toString();
				}
			}
		}

		document.getElementById("menuSortStr").value = arrayStr;

		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").action = "saveMenuTree";
		document.getElementById("frm").submit();
	}
	
	// Save options
	function saveOption(){
		var menuid = document.getElementById("selectedMenuID").value;
		if (menuid == 0) {
			// Show Error Message
			errorMessage.innerHTML = "Please save Menu Information first or select a menu from the menu category.";
			$( "#errorMessagePanel" ).dialog( "open" );
			return;
		}
		
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").action = "saveMenuOption";
		document.getElementById("frm").submit();
	}
	

	
	function search(){
		$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		document.getElementById("frm").submit();
	}
	
	// Instruction textcounter
	function countText(obj, label) {
		var content = obj.value;
		document.getElementById(label).innerHTML = content.length ;
	}
	
	// Initialize menu data to edit
	function initEdit(index) {
		var restaurantID = document.getElementById("selectedID").value;
		var menuID = document.getElementById("menuList[" + index + "].menuID").value;
		//$.blockUI({ message: '<h4><img src="../images/admin/busy.gif" /> Please wait...</h4>' });
		
		$.ajax({
			type: "POST",
	        url: "initRestaurantMenuEdit",
	        dataType: "json",
	        data: { "selectedID": restaurantID, "selectedMenuID": menuID},
	    })
        // callback handler that will be called on success
        .done(function(response){
            // Set menu data
            setMenuData(response);
            // Open menu info accordion
            $( "#menuInfo" ).accordion('activate', 0 );
            
        })
        .fail(function(request, status, error){
        	// In case of request session time out, show login page
        	document.getElementById("frm").action = "initLogin";
        	document.getElementById("frm").submit();
        })   
	    ;
	}
	
	function setMenuData(response) {
		var menuvo = response.vo;
				
		//Set up menu info
		document.getElementById("restaurantID").value = menuvo.restaurantID;
		document.getElementById("selectedMenuID").value = menuvo.menuID;
		document.getElementById("labelMenuID").innerHTML = menuvo.menuID;
		document.getElementById("menuID").value = menuvo.menuID;
		document.getElementById("categoryID").value = menuvo.categoryID;
		document.getElementById("name").value = menuvo.name;
		document.getElementById("price").value = menuvo.price;
		document.getElementById("minUnit").value = menuvo.minUnit;
		document.getElementById("sort").value = menuvo.sort;
		var image = menuvo.imagePath;
		if (image == "") {
			document.getElementById("imagePathImg").src="../images/admin/noimage.jpg";
			document.getElementById("imagePathAnchor").href="../images/admin/noimage.jpg";
		} else {
			document.getElementById("imagePathImg").src="../pictures/menu/thumb_" + image;
			document.getElementById("imagePathAnchor").href="../pictures/menu/" + image;
		}
		document.getElementById("imagePath").value = image;
		document.getElementById("description").value = menuvo.description;
		document.getElementById("taxRate").value = menuvo.taxRate;
		document.getElementById("status").value = menuvo.status;
		document.getElementById("naFlag").value = menuvo.naFlag;
		
		var optionList = response.menuOptionList;
	    //Set up Options 
		setOption(optionList);
	}
	
	// Clear menu data
	function clearMenu() {
		document.getElementById("restaurantID").value = "";
		document.getElementById("labelMenuID").innerHTML = "";
		document.getElementById("menuID").value = "";
		document.getElementById("selectedMenuID").value = 0;
		document.getElementById("categoryID").value = 0;
		document.getElementById("name").value = "";
		document.getElementById("price").value = 0.00;
		document.getElementById("minUnit").value = 1;
		document.getElementById("sort").value = 1;
		document.getElementById("imagePath").value = "";
		document.getElementById("imagePathImg").src ="../images/admin/noimage.jpg";
		document.getElementById("description").value = "";
		document.getElementById("taxRate").value = 0.00;
		document.getElementById("status").value = "";
		document.getElementById("naFlag").value = "0";
		
	    //Clear Options 
	    var tbody = document.getElementById('optionTbl').getElementsByTagName("tbody")[0];
		var rowCount = tbody.rows.length;
		// Clear all rows
		for(var i=0; i<rowCount; i++) {
			tbody.deleteRow(0);
		}
	}
	
	// Copy category name to change
	function copyCatName() {
		var name = document.getElementById("categoryEditName").value;
		var index = document.getElementById("categoryEditIndex").value;
		document.getElementById("categoryName["+ index +"]").innerHTML = name ;
		document.getElementById("categoryList[" + index + "].name").value = name;
	}
	
	// Open Edit category name
	function openEditPanel(index) {
		document.getElementById("categoryEditName").value = document.getElementById("categoryList[" + index + "].name").value;
		document.getElementById("categoryEditIndex").value = index;
		$( "#categoryEditPanel" ).dialog( "open" );
	}
	
	// Close Edit category name
	function closeEditPanel() {
		$( "#categoryEditPanel" ).dialog( "close" );
	}
	
	// Add category row
	function addCategory() {
		var category = document.getElementById("categoryTree");
		var categoryNodes = document.getElementById("categoryTree").childNodes;
		var cnt = 0;
		var maxID =0;
		var tempID =1;
		for (var i=0; i<categoryNodes.length; i++) {
			// Count categorys
			if (categoryNodes[i].nodeName == "LI") {
				tempID = document.getElementById("categoryList[" + cnt + "].categoryID").value;
				if (parseInt(tempID) > parseInt(maxID)) {
					maxID = tempID;
				}
				cnt++;
			}
		}
		maxID++;
		
		var li_cat = document.createElement("LI");
		li_cat.setAttribute("id", cnt);
		category.appendChild(li_cat);
		
		var a_cat = document.createElement("A");
		a_cat.setAttribute("href", "#");
		a_cat.setAttribute("ondblclick", "javascript:openEditPanel('" + cnt +"');");
		li_cat.appendChild(a_cat);
		
		var span_cat = document.createElement("SPAN");
		span_cat.setAttribute("id", "categoryName[" + cnt + "]");
		a_cat.appendChild(span_cat);
		
		var txt_cat = document.createTextNode("Category Name" + maxID);
		span_cat.appendChild(txt_cat);
		
		// Add hidden elements
		var restid_cat = document.createElement("INPUT");
		restid_cat.setAttribute("type", "hidden");
		restid_cat.setAttribute("name", "categoryList[" + cnt + "].restaurantID");
		restid_cat.setAttribute("value", document.getElementById("selectedID").value);
		a_cat.appendChild(restid_cat);
		
		var catid_cat = document.createElement("INPUT");
		catid_cat.setAttribute("type", "hidden");
		catid_cat.setAttribute("name", "categoryList[" + cnt + "].categoryID");
		catid_cat.setAttribute("id", "categoryList[" + cnt + "].categoryID");
		catid_cat.setAttribute("value", maxID);
		a_cat.appendChild(catid_cat);
		
		var name_cat = document.createElement("INPUT");
		name_cat.setAttribute("type", "hidden");
		name_cat.setAttribute("name", "categoryList[" + cnt + "].name");
		name_cat.setAttribute("id", "categoryList[" + cnt + "].name");
		name_cat.setAttribute("value", "Category Name" + maxID);
		a_cat.appendChild(name_cat);
	}
	
	// Delete Category
	function delCategory() {
		
		try {
			// Category index to delete
			var index = document.getElementById("categoryEditIndex").value;
			var hasMenu = false;
			// Check if the category has menus
			var menuNodes = document.getElementById("menuTree"+index).childNodes;
			for (var i=0; i<menuNodes.length; i++) {
				// Check if the category has menus
				if (menuNodes[i].nodeName == "LI") {
					hasMenu = true;
					break;
				}
			}
		} catch(err) {
			// Just add category doesn't have menuTree which cause exception
			// New category doen't have menus
			hasMenu = false;
		}
			
		if (hasMenu) {
			// if the category has menus
			// Close category edit panel
			$( "#categoryEditPanel" ).dialog( "close" );
			
			// Show Error Message
			errorMessage.innerHTML = "Please change the category's menus to another menu. <br>Category containing menus can't be deleted.";
			$( "#errorMessagePanel" ).dialog( "open" );
		} else {
			var categoryNodes = document.getElementById("categoryTree").childNodes;
			for (var i=0; i<categoryNodes.length; i++) {
				if (categoryNodes[i].nodeName == "LI" && categoryNodes[i].id == index) {
					categoryNodes[i].parentNode.removeChild(categoryNodes[i]);
					break;
				}
			}
			// Close category edit panel
			$( "#categoryEditPanel" ).dialog( "close" );
		} 
		
	}
	
	// Add option
	function addOption() {
		var menuid = document.getElementById("selectedMenuID").value;
		if (menuid == 0) {
			// Show Error Message
			errorMessage.innerHTML = "Please save Menu Information first or select a menu from the menu category.";
			$( "#errorMessagePanel" ).dialog( "open" );
			return;
		}
		var tbody = document.getElementById('optionTbl').getElementsByTagName("tbody")[0];
		var rowCount = tbody.rows.length;
		
		var obj_row;
		if(/*@cc_on!@*/true){
			obj_row = document.createElement("TR");
			tbody.appendChild(obj_row);
		}else{
			obj_row = tbody.insertRow();
		}
		
		var td_del = document.createElement("TD");
		td_del.setAttribute("align", "center");
		obj_row.appendChild(td_del);
		
		var input_del = document.createElement("input"); 
		input_del.setAttribute("type", "checkbox");
		input_del.setAttribute("name", "chk");
		td_del.appendChild(input_del);
		
		var td_name = document.createElement("TD");
		obj_row.appendChild(td_name);
		
		var optionid_cat = document.createElement("input");
		optionid_cat.setAttribute("type", "hidden");
		optionid_cat.setAttribute("name", "menuOptionList[" + rowCount + "].optionID");
		optionid_cat.setAttribute("value", rowCount + 1);
		td_name.appendChild(optionid_cat);
			
		var input_name = document.createElement("input"); 
		input_name.setAttribute("type", "text");
		input_name.setAttribute("name", "menuOptionList[" + rowCount + "].name");
		input_name.setAttribute("size", "40");
		input_name.setAttribute("maxlength", "50");
		td_name.appendChild(input_name);
		 
		var td_charge = document.createElement("TD");
		td_charge.setAttribute("align", "center");
		obj_row.appendChild(td_charge);
		
		var input_charge = document.createElement("input"); 
		input_charge.setAttribute("type", "text");
		input_charge.setAttribute("name", "menuOptionList[" + rowCount + "].extraCharge");
		input_charge.setAttribute("value", "0.00");
		input_charge.setAttribute("size", "6");
		input_charge.setAttribute("maxlength", "6");
		td_charge.appendChild(input_charge);
		
		var td_na = document.createElement("TD");
		td_na.setAttribute("align", "center");
		obj_row.appendChild(td_na);
		
		var select_na = document.createElement("select"); 
		select_na.setAttribute("name", "menuOptionList[" + rowCount + "].naFlag");
		td_na.appendChild(select_na);
		
		var option1_na = document.createElement("option"); 
		option1_na.setAttribute("value", 0);
		option1_na.setAttribute("selected", "selected");
		select_na.appendChild(option1_na);
		
		var txt_option1 = document.createTextNode("Service");
		option1_na.appendChild(txt_option1);
		
		var option2_na = document.createElement("option"); 
		option2_na.setAttribute("value", 1);
		select_na.appendChild(option2_na);
		
		var txt_option2 = document.createTextNode("N/A");
		option2_na.appendChild(txt_option2);
	}
	
	// delete option list rows
	function delRow(){
		var tbody = document.getElementById("optionTbl").getElementsByTagName("tbody")[0];
		var rowCount = tbody.rows.length;
		
        for(var i=0; i<rowCount; i++) {
            var row = tbody.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            if(null != chkbox && undefined == chkbox.checked) {
                // In case menuOptionList, checkbox is node[1]
                chkbox = row.cells[0].childNodes[1];
                if(null != chkbox && true == chkbox.checked) {
                	tbody.deleteRow(i);
                    rowCount--;
                    i--;
                }
            } else {
                if(null != chkbox && true == chkbox.checked) {
                	tbody.deleteRow(i);
                    rowCount--;
                    i--;
                }
            }
        }
	}
	
	// Set up option
	function setOption(optionList) {
		
		var tbody = document.getElementById('optionTbl').getElementsByTagName("tbody")[0];
		var rowCount = tbody.rows.length;
		// Clear all rows
		for(var i=0; i<rowCount; i++) {
			tbody.deleteRow(0);
		}
		
		rowCount = optionList.length;
		var obj_row;
		var td_name;
		var input_optionid;
		var input_restid;
		var input_menuid;
		var input_name;
		var td_charge;
		var input_charge;
		var td_na;
		var select_na;
		var option1_na;
		var option2_na;
		var txt_option1;
		var txt_option2;
		var option;
		var naflag;
		
		for(var i=0; i<rowCount; i++) {
			option = optionList[i];
			if(/*@cc_on!@*/true){
				obj_row = document.createElement("TR");
				tbody.appendChild(obj_row);
			}else{
				obj_row = tbody.insertRow();
			}
			
			var td_del = document.createElement("TD");
			td_del.setAttribute("align", "center");
			obj_row.appendChild(td_del);
			
			var input_del = document.createElement("input"); 
			input_del.setAttribute("type", "checkbox");
			input_del.setAttribute("name", "chk");
			td_del.appendChild(input_del);
			
			td_name = document.createElement("TD");
			obj_row.appendChild(td_name);
			
			input_restid = document.createElement("INPUT");
			input_restid.setAttribute("type", "hidden");
			input_restid.setAttribute("name", "menuOptionList[" + i + "].restaurantID");
			input_restid.setAttribute("value", option.restaurantID);
			td_name.appendChild(input_restid);
				
			
			input_menuid = document.createElement("INPUT");
			input_menuid.setAttribute("type", "hidden");
			input_menuid.setAttribute("name", "menuOptionList[" + i + "].menuID");
			input_menuid.setAttribute("value", option.menuID);
			td_name.appendChild(input_menuid);
			
			input_optionid = document.createElement("INPUT");
			input_optionid.setAttribute("type", "hidden");
			input_optionid.setAttribute("name", "menuOptionList[" + i + "].optionID");
			input_optionid.setAttribute("value", option.optionID);
			td_name.appendChild(input_optionid);
				
			input_name = document.createElement("input"); 
			input_name.setAttribute("type", "text");
			input_name.setAttribute("name", "menuOptionList[" + i + "].name");
			input_name.setAttribute("value", option.name);
			input_name.setAttribute("size", "40");
			input_name.setAttribute("maxlength", "50");
			td_name.appendChild(input_name);
			 
			td_charge = document.createElement("TD");
			td_charge.setAttribute("align", "center");
			obj_row.appendChild(td_charge);
			
			input_charge = document.createElement("input"); 
			input_charge.setAttribute("type", "text");
			input_charge.setAttribute("name", "menuOptionList[" + i + "].extraCharge");
			input_charge.setAttribute("value", option.extraCharge);
			input_charge.setAttribute("size", "6");
			input_charge.setAttribute("maxlength", "6");
			td_charge.appendChild(input_charge);
			
			td_na = document.createElement("TD");
			td_na.setAttribute("align", "center");
			obj_row.appendChild(td_na);
			
			select_na = document.createElement("select"); 
			select_na.setAttribute("name", "menuOptionList[" + i + "].naFlag");
			td_na.appendChild(select_na);
			
			naflag = option.naFlag;
			option1_na = document.createElement("option"); 
			option1_na.setAttribute("value", 0);
			if (naflag != 1) {
				option1_na.setAttribute("selected", "selected");
			}
			select_na.appendChild(option1_na);
			
			txt_option1 = document.createTextNode("Service");
			option1_na.appendChild(txt_option1);
			
			option2_na = document.createElement("option"); 
			option2_na.setAttribute("value", 1);
			if (naflag == 1) {
				option2_na.setAttribute("selected", "selected");
			}
			select_na.appendChild(option2_na);
			
			txt_option2 = document.createTextNode("N/A");
			option2_na.appendChild(txt_option2);
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
        	active: -2
        });
		
		$( "#categoryTree, #categoryTree ul" ).sortable();
        $( "#categoryTree, #categoryTree ul" ).disableSelection();
        
		$('#categoryTree ul').hide();
		$('#categoryTree li a').click(
			function() {
				var openMe = $(this).next();
				var mySiblings = $(this).parent().siblings().find('ul');
				if (openMe.is(':visible')) {
					openMe.slideUp('normal');  
				} else {
					mySiblings.slideUp('normal');  
					openMe.slideDown('normal');
				}
		      }
		);

		// Category Edit Panel
        $( "#categoryEditPanel" ).dialog({
            autoOpen: false,
            width: 300,
            height: 150
        });
		
     	// Set up accordion
        $( "#menuInfo" ).accordion({
        	active: <s:property value="active"/>,
        	collapsible: true,
        	alwaysOpen: false,
            heightStyle: "content"
        });
		
		// Set up spinner
        $( "#price, #extraCharge" ).spinner({
            min: 0.00,
            max: 999.99,
            step: 0.01,
            numberFormat: "n"
        });
        $( "#sort" ).spinner({
            min: 1,
            max: 999,
            step: 1,
            numberFormat: "n"
        });
        
        $( "#minUnit" ).spinner({
            min: 1,
            max: 99,
            step: 1,
            numberFormat: "n"
        });
        
     	// Set up image
        $(".fancybox").fancybox();
        
        // Set up button
        $("input[type=button]").button();
        
        // Hide instruction after 5 sec
        $("#instruction").delay(5000).hide(1000);

	} );
//-->
</script>
<div id="page">
	<div id="mainarea">
		<div id="sidebar">
			<div id="sidebarnav">
				<a href="javascript:goPage('R201');">Restaurant List</a>
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
				        <li><a href="#Info" onClick ="javascript:goPageParam('R202','<s:property value="selectedID"/>');">Information</a></li>
				        <li><a href="#Menu" onClick ="javascript:goPageParam('R204','<s:property value="selectedID"/>');">Menu</a></li>
				        <li><a href="#Photo" onClick ="javascript:goPageParam('R203','<s:property value="selectedID"/>');">Photo</a></li>
				    </ul>
				    <div id="Info">
					</div>
				    <div id="Menu" style="width:auto; height:auto; margin:10,0,0,0;">

						<h3><font color="#555555">Menu Detail</font></h3>
						
						<s:form theme="simple" name="frm" id="frm" action="saveMenu" method="POST" enctype="multipart/form-data">
						<s:hidden name="selectedID" id="selectedID" />
						<s:hidden name="restVO.name"/>
						<s:hidden name="selectedMenuID" id="selectedMenuID" />
						<s:hidden name="categorySortStr" id="categorySortStr" />
						<s:hidden name="menuSortStr" id="menuSortStr" />
						
						<s:if test='%{!"".equals(selectedID)}'>
						<!--  If there is restaurant info -->
							<table>
								<tr><td width="320" valign="top">
								<!-- Category -->
								<div style="width:auto; height:auto; margin-left:10px; margin-top:0px; margin-right:10px;">
									<h5>Menu Category</h5>
									<ul id="categoryTree">
										<s:iterator value="categoryList" id="categoryList" status="outerStat">
											<li id ="<s:property value='%{#outerStat.index}'/>">
												<a ondblclick = "javascript:openEditPanel('<s:property value="%{#outerStat.index}"/>');" title="Drag&Drop to change sort. Double click to change name.">
													<span id="categoryName[<s:property value='%{#outerStat.index}'/>]"><s:property value='%{name}'/></span>
													<s:hidden name ="categoryList[%{#outerStat.index}].restaurantID" />
													<s:hidden name ="categoryList[%{#outerStat.index}].categoryID" id ="categoryList[%{#outerStat.index}].categoryID" />
													<s:hidden name ="categoryList[%{#outerStat.index}].name" id ="categoryList[%{#outerStat.index}].name" />
													<s:hidden name ="categoryList[%{#outerStat.index}].sort" />
												</a>
												<ul id="menuTree<s:property value='%{#outerStat.index}'/>">
													<s:set var="catID" value="categoryID" />
													<s:iterator value="menuList" id="menuList" status="outerStat2">
														<s:if test='%{#catID == categoryID}'>
															<li id ="<s:property value='%{#outerStat2.index}'/>">
																<a href="javascript:initEdit('<s:property value="%{#outerStat2.index}"/>');"><s:property value='%{name}'/>
																<s:hidden name ="menuList[%{#outerStat2.index}].restaurantID" id ="menuList[%{#outerStat2.index}].restaurantID" />
																<s:hidden name ="menuList[%{#outerStat2.index}].menuID" id ="menuList[%{#outerStat2.index}].menuID" />
																<s:hidden name ="menuList[%{#outerStat2.index}].categoryID" id ="menuList[%{#outerStat2.index}].categoryID" />
																<s:hidden name ="menuList[%{#outerStat2.index}].name" id ="menuList[%{#outerStat2.index}].name" />
																<s:hidden name ="menuList[%{#outerStat2.index}].price" id ="menuList[%{#outerStat2.index}].price" />
																<s:hidden name ="menuList[%{#outerStat2.index}].description" id ="menuList[%{#outerStat2.index}].description" />
																<s:hidden name ="menuList[%{#outerStat2.index}].imagePath" id ="menuList[%{#outerStat2.index}].imagePath" />
																<s:hidden name ="menuList[%{#outerStat2.index}].sort" id ="menuList[%{#outerStat2.index}].sort" />
																<s:hidden name ="menuList[%{#outerStat2.index}].taxRate" id ="menuList[%{#outerStat2.index}].taxRate" />
																<s:hidden name ="menuList[%{#outerStat2.index}].status" id ="menuList[%{#outerStat2.index}].status"  />
																<s:hidden name ="menuList[%{#outerStat2.index}].minUnit" id ="menuList[%{#outerStat2.index}].minUnit" />
																<s:hidden name ="menuList[%{#outerStat2.index}].naFlag" id ="menuList[%{#outerStat2.index}].naFlag" />
																</a>
															</li>
														</s:if>
													</s:iterator>
												</ul>
											</li>
										</s:iterator>
									</ul>
									<div style="width:320; height:auto; margin-top:5px;">
										<input type="button" value="Save" onClick="javascript:saveTree();" />
										<input type="button" value="Add Category" onClick="javascript:addCategory();" />
									</div>
									<div id="instruction" class="ui-state-highlight ui-corner-all" style="width:320; margin-top: 20px; padding: 0 .7em;">
										<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
											<strong>Drag&Drop</strong> to change sort. <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Doubleclick</strong> to change name.</p>
									</div>
								</div>
	
								<!-- Category Edit Panel -->
								<div id="categoryEditPanel" title="Edit Category">
									<p>Category Name:<input type="text" id="categoryEditName" onkeyup="javascript:copyCatName();"/><input type="hidden" id="categoryEditIndex" /></p>	
									<p align="center"><input type="button" value="Delete Category" onClick="javascript:delCategory();" />&nbsp;<input type="button" value="Close" onClick="javascript:closeEditPanel();" /></p>
								</div>
								
								<!-- Menu -->
								<td valign="top" width="670">
								<div style="width:auto; height:auto; margin-left:10px; margin-top:0px; margin-right:10px;">
								<h5>Menu Information</h5>
									<div id="menuInfo" >
										<h3>Menu Info</h3>
											<div>
												<table>
													<tr>
														<th>Menu ID : </th>
														<td>
															<span id="labelMenuID">
																<s:if test='%{vo.menuID != 0}'>
																	<s:property value="vo.menuID" />
																</s:if>
															</span>
															<s:hidden name="vo.menuID" id="menuID" />
														</td>
													</tr>
													<tr>
														<th width="150">Category<FONT color=red>*</FONT>:</th>
														<td>
															<s:select name="vo.categoryID" id="categoryID" list="categoryList" listKey="categoryID" listValue="name" headerKey="0" headerValue="" />
															<s:hidden name="vo.restaurantID" id="restaurantID"/>
															<s:hidden name="vo.naFlag" id="naFlag"/>
														</td>
													</tr>
													<tr>
														<th>Name<FONT color=red>*</FONT>:</th>
														<td>
															<s:textfield name="vo.name" id="name" size="50" maxlength="50" />
														</td>
													</tr>
													<tr>
														<th>Price($)<FONT color=red>*</FONT>:</th>
														<td>
															<s:textfield name="vo.price" id="price" size="6" maxlength="6" />
														</td>
													</tr>
													<tr>
														<th>Min. Unit:</th>
														<td>
															<s:textfield name="vo.minUnit" id="minUnit" size="2" maxlength="2" />
														</td>
													</tr>
													<tr>
														<th>Sort:</th>
														<td>
															<s:textfield name="vo.sort" id="sort" size="6" maxlength="6" />
														</td>
													</tr>
													<tr>
														<th>Image :</th>
														<td>
															<s:if test='%{!"".equals(vo.imagePath)}'>
																<a class="fancybox" id="imagePathAnchor" href="../pictures/menu/<s:property value='%{vo.imagePath}'/>">
																	<img id="imagePathImg" src="../pictures/menu/thumb_<s:property value='%{vo.imagePath}'/>">
																</a>
															</s:if>
															<s:else>
																<a class="fancybox" id="imagePathAnchor" href="../images/admin/noimage.jpg">
																	<img id="imagePathImg" src="../images/admin/noimage.jpg">
																</a>
															</s:else>
															<s:hidden name="vo.imagePath" id="imagePath"/>
															<p><s:file name="imgfile" id="imgfile" accept="image/*" /></p>
														</td>
													</tr>
													<tr>
														<th>Description:<br><span style="font-size: 10px;">Text Length: <span id="len_info">0</span><br><font color="#f00">(Max 100 char)</font></span></th>
														<td>
															<s:textarea rows="3" cols="50" name="vo.description" id="description" onkeyup="javascript:countText(this, 'len_info');"></s:textarea>
														</td>
													</tr>
													<tr>
														<th>Tax Rate<FONT color=red>*</FONT>:</th>
														<td>
															<s:select name="vo.taxRate" id="taxRate" list="taxRateList" listKey="code" listValue="shortName" />
														</td>
													</tr>
													<tr>
														<th>Status<FONT color=red>*</FONT>:</th>
														<td>
															<s:select name="vo.status" id="status" list="statusList" listKey="code" listValue="shortName" />
														</td>
													</tr>
													<tr>
														<td colspan=2><hr></td>
													</tr>
													<tr>
														<td>
															<input type="button" value="Save" onClick="javascript:save();" />
															<input type="button" value="Delete" onClick="javascript:del();" />
														</td>
														<td align="right">
															<input type="button" value="Copy" onClick="javascript:copy();" />
															<input type="button" value="New" onClick="javascript:clearMenu();" />
														</td>
													</tr>
												</table>
											</div>
											
										<!-- Menu Option -->
										<h3>Menu Option</h3>
											<div>
											    <div>
													<div>
														<table>
															<tr>
																<td>
																	<input type="button" value="Add Row" onClick ="javascript:addOption();"/>
																	<input type="button" value="Del Row" onClick ="javascript:delRow();"/>
															</tr>
														</table>
												    
														<div>
														<table class="tableborder" id="optionTbl" width="580">
															<thead>
															<tr>
																<th></th>
																<th width="330">Option Name</th>
																<th width="100">Extra Charge</th>
																<th width="100">Service</th>
															</tr>
															</thead>
															<tbody>
															<s:iterator value="menuOptionList" id="menuOptionList" status="outerStat">
															<tr>
																<td align="center">
																	<s:checkbox name="chk" id="chk"/>
																</td>
																<td>
																	<s:textfield name="menuOptionList[%{#outerStat.index}].name" size="40" maxlength="50" />
																	<s:hidden name="menuOptionList[%{#outerStat.index}].restaurantID"/>
																	<s:hidden name="menuOptionList[%{#outerStat.index}].menuID"/>
																</td>
																<td align="center">
																	<s:textfield name="menuOptionList[%{#outerStat.index}].extraCharge" size="6" maxlength="6" />
																</td>
																<td align="center">
																	<s:select name="menuOptionList[%{#outerStat.index}].naFlag" list="naFlagList" listKey="code" listValue="shortName"/>
																</td>
															</tr>
															</s:iterator>
															</tbody>
														</table>
														</div>
													</div>
													<table width="100%">
														<tr>
															<td><hr></td>
														</tr>
														<tr>
															<td>
																<input type="button" value="Save" onClick="javascript:saveOption();" />
															</td>
														</tr>
													</table>
						
											    </div>
											    
											
											</div>
									</div>
								</div>
								</td></tr>
							</table>
						</s:if>
						<s:else>
						<!--  If there is no restaurant info -->
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
					<div id="Photo">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</t:putAttribute>
</t:insertDefinition>