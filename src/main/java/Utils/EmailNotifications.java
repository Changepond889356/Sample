package Utils;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;  
import javax.mail.internet.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;  


public class EmailNotifications extends GenericSkins{
 
  public static void SendNotification() throws Exception {	
	
	 final String username = emailUsername;
	 final String password = emailPassword;
	
	String sContent="";

	//www.google.com/settings/security/lesssecureapps
	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");	
	props.put("mail.smtp.host", "webmail.changepond.com");
	props.put("mail.smtp.port", "25");

	Session session = Session.getInstance(props,
	  new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	  });

	try {

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(fromEmailID)); //"username"
		message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(sToEmail));
		//Set Email Subject
		//message.setSubject("Notification: "+sRunReference+" - Automation Test Execution Summary");
		
		/*Dear All,

		Regression scripts Run20OCT2020 execution has completed.

		Please find the attached report*/
		
		//Email body content
		//sContent=htmlview();
		//sContent = sRunReference+" execution has completed. Please find attached report!";
		sContent = "Dear All,<br><br> Regression scripts "+sRunReference+" execution has completed. <br><br> Please find attached report! <br><br>"
				+ "<br><br> ******************** Auto Generated Email ******************** ";
		
		//sContent=sContent+"\n\n Regards,\n Faheem Muhammad.";
		//message.setText(sContent);
		
		// Create a default MimeMessage object.
       // Message message = new MimeMessage(session);

        // Set From: header field of the header.
       // message.setFrom(new InternetAddress(from));

        // Set To: header field of the header.
        //message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));

        // Set Subject: header field
        message.setSubject("Notification: "+sRunReference+" - Automation Test Execution Summary");

        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        //messageBodyPart.setText(sContent);
        messageBodyPart.setContent(sContent, "text/html");

        // Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
       
        messageBodyPart = new MimeBodyPart();
        String filename = sTestResultsPath+"ExtentTestReport.html";
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);

        // Send the complete message parts
        message.setContent(multipart);

        // Send message
        Transport.send(message);

		
		//message.setContent(sContent,"text/html");
		
		//Transport.send(message);
		

		System.out.println("Done");

	} 
	
	catch (MessagingException e) 
	{
		e.printStackTrace();;
	}
  }
  
 
  public static String htmlview() throws Exception
  {
	  String sView = "<html><head><h1 style='color:#214663;'>Test Execution Report</h1> "
	  		         + "<meta name='viewport' content='width=device-width, initial-scale=1'>"
	  		         + "<style>table, th, td {border: 1px solid black;border-collapse: collapse;}"
	  		         + "table {   width: 50%;} th{background-color: #C6C7C8 ; color: black;}</style></head>";
	  sView = sView+"<body>"
			      +"<div style='overflow-x:auto;'>"
			      +ProjectDetails()
				  +"</div>"
				  +"<div>"
				  +"<p><font size='5';style='color:#05375C'>High-Level Statistics</font></p>"
				  +"</div>"
                   +"<div style='overflow-x:auto;'>"
                   +"<table style='width:75%'>"
                 
                   +"  <tr>"
					    +"<th>Regression Pack</th>"
					    +"<th>Regression Pack Name</th>"
					    +"<th>Execution Status</th>"
					    +"<th>Passed(%)</th>"
					    +"<th>Failed(%)</th>"
					    +"<th>No Run(%)</th>"
					    +"<th>RAG</th>"
					  +"</tr>"
					 +HighLevelStatistics()
					+"</table>"
					+"</div>"
				  +"<div>"
				  +"<p><font size='5';style='color:#05375C'>In-Depth Statistics</font></p>"
				  +"</div>"
                   +"<div style='overflow-x:auto;'>"
                   +"<table style='width:75%'>"
       
                      +"<tr>"
					    +"<th>Regression Pack</th>"
					    +"<th>Regression Pack Name</th>"
					    +"<th>Total Tests</th>"
					    +"<th>Executed</th>"
					    +"<th>Passed</th>"
					    +"<th>Failed</th>"
					    +"<th>No Run</th>"
					    +"<th>Duration</th>"
					  +"</tr>"
					 +sInDepthstatistics()
					+"</table>"
					+"</div>"
					+"<div>"
					+"<p>Regards,</p>"
					+"<p>Faheem Muhammad.</p>"
					+"</div>"
					+"</body></html>";
	  return sView;
  }
  
  @SuppressWarnings("unused")
public static String sInDepthstatistics() throws Exception
  {
	  String sInDepthStatistics="";
	  String sRegPackID="";
	  String sRegPackName="";
	  int iTotalTests=0;
	  int iExecuted=0;
	  int iPassed=0;
	  int iFailed=0;
	  int iNoRun=0;
	  String sDuration="";
	  String sRAGStatus="";
	  
	  Class.forName(sReportsDBJDBCDriver);
	  conn = DriverManager.getConnection(sReportsDBURL, sReportsDBUserName, sReportsDBPassword);
		
	  stmt = conn.createStatement();
	  System.out.println("sReportsDBName:"+sReportsDBName);
	  //Get ProjetcID
	  stmt.executeUpdate("use "+sReportsDBName);
	  String sProjectIDQuesry = "select ID  from automationprojects where ProjectName = '" + sProjectName + "'";
	  ResultSet rsProject = stmt.executeQuery(sProjectIDQuesry);
	  if (rsProject.next()) {
		  sProjectId = rsProject.getString(1);
		}
	  //Get RunReferenceID
		sRunReferenceIdQuery = "select ID  from runreferencehistory where ProjectId = '" + sProjectId + "' AND RunReferenceName = '" + sRunReference + "'";
		ResultSet rsRRId = stmt.executeQuery(sRunReferenceIdQuery);
		if (rsRRId.next()) {
			sRunReferenceId = rsRRId.getString(1);
		}
		getCountQuery = "SELECT count(*) as count FROM regressionpackshistory WHERE RunReferenceId = '"
				+ sRunReferenceId + "' AND RegressionPackReference = '" + sRegPackID + "'";
		ResultSet rsCount1 = stmt.executeQuery(getCountQuery);

		if (rsCount1.next()) {
			getCount = rsCount1.getString(1);
		}
		System.out.println("ProjectID:"+sProjectId);
		System.out.println("RunReferenceID:"+sRunReferenceId);
		//Get Number of Regression packs executed
		getCountQuery = "SELECT * FROM regressionpackshistory WHERE RunReferenceId = '"
				+ sRunReferenceId +"'";
		ResultSet rs = stmt.executeQuery(getCountQuery);
		while(rs.next())
		{
			sRegPackID = rs.getString("RegressionPackReference");
			sRegPackName = rs.getString("RegressionPackName");
			iTotalTests = Integer.parseInt(rs.getString("NumberofTests"));
			iExecuted = Integer.parseInt(rs.getString("Executed"));
			iPassed = Integer.parseInt(rs.getString("Passed"));
			iFailed = Integer.parseInt(rs.getString("Failed"));
			iNoRun = Integer.parseInt(rs.getString("NoRun"));
			sRAGStatus = rs.getString("RAG");
			sDuration = rs.getString("Duration");
			sInDepthStatistics = sInDepthStatistics +"<tr style='text-align: center'>"
					  +"<td style='text-align: left'>"+sRegPackID+"</td>"
					  +"<td style='text-align: left'>"+sRegPackName+"</td>"
					    +"<td>"+iTotalTests+"</td>"
					    +"<td style='color:#2B6794'>"+iExecuted+"</td>"
					    +"<td style='color:green'>"+iPassed+"</td>"
					    +"<td style='color:red'>"+iFailed+"</td>"
					    +"<td>"+iNoRun+"</td>"
					    +"<td style='text-align: left'>"+sDuration+"</td>"
					  +"</tr>";
		}
		
	  return sInDepthStatistics;
  }
  
  @SuppressWarnings("unused")
public static String HighLevelStatistics() throws Exception
  {
	  String sInDepthStatistics="";
	  String sRegPackID="";
	  String sRegPackName="";
	  double iTotalTests=0;
	  double iExecuted=0;
	  double iPassed=0;
	  double iFailed=0;
	  double iNoRun=0;
	  
	  String sDuration="";
	  String sRAGStatus="";
	  String sBackGroundColor="white";
	  
	  float fPassedPercent;
	  float fFailedPercent;
	  float fNoRunPercent;
	  
	  Class.forName(sReportsDBJDBCDriver);
	  conn = DriverManager.getConnection(sReportsDBURL, sReportsDBUserName, sReportsDBPassword);
		
	  stmt = conn.createStatement();
	  System.out.println("sReportsDBName:"+sReportsDBName);
	  //Get ProjetcID
	  stmt.executeUpdate("use "+sReportsDBName);
	  String sProjectIDQuesry = "select ID  from automationprojects where ProjectName = '" + sProjectName + "'";
	  ResultSet rsProject = stmt.executeQuery(sProjectIDQuesry);
	  if (rsProject.next()) {
		  sProjectId = rsProject.getString(1);
		}
	  //Get RunReferenceID
		sRunReferenceIdQuery = "select ID  from runreferencehistory where ProjectId = '" + sProjectId + "' AND RunReferenceName = '" + sRunReference + "'";
		ResultSet rsRRId = stmt.executeQuery(sRunReferenceIdQuery);
		if (rsRRId.next()) {
			sRunReferenceId = rsRRId.getString(1);
		}
		getCountQuery = "SELECT count(*) as count FROM regressionpackshistory WHERE RunReferenceId = '"
				+ sRunReferenceId + "' AND RegressionPackReference = '" + sRegPackID + "'";
		ResultSet rsCount1 = stmt.executeQuery(getCountQuery);

		if (rsCount1.next()) {
			getCount = rsCount1.getString(1);
		}
		System.out.println("ProjectID:"+sProjectId);
		System.out.println("RunReferenceID:"+sRunReferenceId);
		//Get Number of Regression packs executed
		getCountQuery = "SELECT * FROM regressionpackshistory WHERE RunReferenceId = '"
				+ sRunReferenceId +"'";
		ResultSet rs = stmt.executeQuery(getCountQuery);
		
		while(rs.next())
		{
			sRegPackID = rs.getString("RegressionPackReference");
			sRegPackName = rs.getString("RegressionPackName");
			iTotalTests = Integer.parseInt(rs.getString("NumberofTests"));
			iExecuted = Integer.parseInt(rs.getString("Executed"));
			iPassed = Integer.parseInt(rs.getString("Passed"));
			iFailed = Integer.parseInt(rs.getString("Failed"));
			iNoRun = Integer.parseInt(rs.getString("NoRun"));
			sRAGStatus = rs.getString("RAG");
			sDuration = rs.getString("Duration");
			if(sRAGStatus.equalsIgnoreCase("Green") )
			{
				sBackGroundColor="#196F3D";
			}
			else if(sRAGStatus.equalsIgnoreCase("Red"))
			{
				sBackGroundColor="red";
			}
			else if(sRAGStatus.equalsIgnoreCase("Amber"))
			{
				sBackGroundColor="#F5B041";
			}
			else
			{
				sBackGroundColor="white";
			}
			fPassedPercent = (float) ((iPassed*100)/iTotalTests);
			fFailedPercent = (float) ((iFailed*100)/iTotalTests);
			fNoRunPercent = (float) ((iNoRun*100)/iTotalTests);
			
			sInDepthStatistics = sInDepthStatistics + "<tr style='text-align: center'>"
				    +"<td style='text-align: left'>"+sRegPackID+"</td>"
				    +"<td style='text-align: left'>"+sRegPackName+"</td>"
				    +"<td style='text-align: left'>"+"Completed"+"</td>"
				    +"<td style='color:green'>"+String.format ("%.2f", fPassedPercent)+" %</td>"
				    +"<td style='color:red'>"+String.format ("%.2f", fFailedPercent)+" %</td>"
				    +"<td >"+String.format ("%.2f", fNoRunPercent)+" %</td>"
				    +"<td style='text-align: left;background-color: "+sBackGroundColor+";color:white'>"+sRAGStatus+"</td>"
				  +"</tr>";
		}
		
	  return sInDepthStatistics;
  }
  
  @SuppressWarnings("unused")
  public static String ProjectDetails() throws Exception
    {
  	  String sProjectDetails="";
  	  String sRegPackID="";
  	  String sRegPackName="";
  	 
  	  String sRAGStatus="";
  	  String sBackGroundColor="white";
  	  String sIterationInitiatedDate="";
  	  Class.forName(sReportsDBJDBCDriver);
  	  conn = DriverManager.getConnection(sReportsDBURL, sReportsDBUserName, sReportsDBPassword);
  		
  	  stmt = conn.createStatement();
  	  System.out.println("sReportsDBName:"+sReportsDBName);
  	  //Get ProjectID
  	  stmt.executeUpdate("use "+sReportsDBName);
  	  String sProjectIDQuesry = "select ID  from automationprojects where ProjectName = '" + sProjectName + "'";
  	  ResultSet rsProject = stmt.executeQuery(sProjectIDQuesry);
  	  if (rsProject.next()) {
  		  sProjectId = rsProject.getString(1);
  		}
  	  //Get RunReference Inititated Date
  		sRunReferenceIdQuery = "select StartDateTime  from runreferencehistory where ProjectId = '" + sProjectId + "' AND RunReferenceName = '" + sRunReference + "'";
  		ResultSet rsRRId = stmt.executeQuery(sRunReferenceIdQuery);
  		if (rsRRId.next()) {
  			sIterationInitiatedDate = rsRRId.getString(1);
  		}
  		//Get Run Reference RAG Status
  		sRunReferenceIdQuery = "select RAG  from runreferencehistory where ProjectId = '" + sProjectId + "' AND RunReferenceName = '" + sRunReference + "'";
  		ResultSet rs = stmt.executeQuery(sRunReferenceIdQuery);
  		if (rs.next()) {
  			sRAGStatus = rs.getString(1);
  		}
  		if(sRAGStatus.equalsIgnoreCase("Green") )
		{
			sBackGroundColor="#196F3D";
		}
		else if(sRAGStatus.equalsIgnoreCase("Red"))
		{
			sBackGroundColor="red";
		}
		else if(sRAGStatus.equalsIgnoreCase("Amber"))
		{
			sBackGroundColor="#F5B041";
		}
		else
		{
			sBackGroundColor="white";
		}
  		
  		sProjectDetails = sProjectDetails +  "<table>"
												+"<tr >"
												   +"<th style='text-align: left'>Project</th>"
												   +"<td>"+sProjectName+"</td>"
												+"<tr >"
												  +"<th style='text-align: left'>Run Reference</th>"
												  +"<td>"+sRunReference+"</td>"
												+"</tr>"
												+"<tr>"
												  +"<th style='text-align: left'>Inititaed Date</th>"
												  +"<td>"+sIterationInitiatedDate.split(" ")[0]+"</td>"
												+"</tr>"
												+"<tr>"
												  +"<th style='text-align: left'>Execution Status</th>"
												  +"<td>Completed</td>"
												+"</tr>"
												+"<tr>"
												  +"<th style='text-align: left'>RAG Status</th>"
												  +"<td style='background-color: "+sBackGroundColor+";color:white;text-align: center'>"+sRAGStatus+"</td>"
												+"</tr>"
											+"</table>";
  	
  	  return sProjectDetails;
    }
 
}
