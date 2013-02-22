<%@ page language="java" import="org.slf4j.*,java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<form action="show" method="post">
<TABLE style="margin-top:20px;" BORDER="0" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" HEIGHT="100%">
  <TR>
    <TD WIDTH="100%" HEIGHT="100%" align="center" valign="center">
      <TABLE height="0%" width="400" BORDER="1" CELLPADDING="0" CELLSPACING="0" CLASS="iframe_table" bordercolor="#CCCCCC" bgcolor="white">
        <TR>
          <td WIDTH="100%" height="30px" bgcolor="#245178" align="center"><font size="2" color="white"><b>Adapt debugging levels:</b></font></td>
        </TR>
        <TR>
          <TD HEIGHT="100%" valign="top"><span class="subtitle">
            <TABLE width="100%" BORDER="0" CELLSPACING="1" CELLPADDING="">
              <tr>
                <td width="100%" nowrap><font size="2" color="#000000">Name</font></td>
                <td width="100%" nowrap><font size="2" color="#000000">Current Priority</font></td>
                <td width="100%" nowrap><font size="2" color="#000000">New Priority</font></td>
              </tr>
              <c:forEach items="${loggers }" var="logger">
	              <tr>
	                <td width="100%"><span class="subtitle">${logger.name}</span></td>
	                <td width="100%"><span class="subtitle">${logger.level} <bean:write name='logger' property='level' /></span></td>
	                <td width="100%">
		                <span class="subtitle">
			                <select name='cat.${logger.name}' >
			                    <option value='leave'>leave</option>
			                    <option value='none'>inherit</option>
			                    <option value='DEBUG'>DEBUG</option>
			                    <option value='INFO'>INFO</option>
			                    <option value='WARN'>WARN</option>
			                    <option value='ERROR'>ERROR</option>
			                    <option value='FATAL'>FATAL</option>
			                </select>
		                </span>
	                </td>
	              </tr>              
              </c:forEach>

            </TABLE>
          </TD>
        </TR>
        <TR>
        	<td colspan="3" align="center">
        		<input type="submit" value="Submit" ></input>
        	</td>
        </TR>
      </TABLE>
    </TD>
  </TR>
</TABLE>

</form>
