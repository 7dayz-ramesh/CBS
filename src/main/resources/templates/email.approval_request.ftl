<html>
<head>
<style> 

.div1 {
margin-left: 25%;
    margin-right: 25%;
  width: 600px;
  height: 75px;
  border: 2px solid black;
}


.div2 {
margin-left: 25%;
    margin-right: 25%;
  width: 500px;
  height: 450px;  
  padding: 50px;
  border: 1px solid black;
}


.boxed {
background-color: #C0C0C0;
  border: 1px solid black;

}

  .flex-container {
        
        align-items: center; 
      
margin-right:25%;
        background-color: #A9A9A9;
  margin-left: 25%;
        
 
      }
</style>
</head>
<body>

<div class="div1" >

<center><p style="font-size:20px;">CONTRACTOR BOOKING SYSTEM</p></div></center>
<div class="div2">
<div class="boxed">
  

<h1 style="font-size: 10px;"><center>Summary Of Answers</center></h1>
<table style="    font-size: 10px;   margin-left: 15%;">

<tr><th>
<td><b>Discipline and Role</b></td></th></tr>
<tr><th>
<td><img style="height:15px;width:15px"src="https://img.icons8.com/wired/64/000000/edit-property.png"/> ${discipline}, ${role}</td>
</th></tr>

<tr><th>
<td><b>Contractor</b></td></th></tr>
<tr><th>
<td><img style="height:15px;width:15px"src="https://img.icons8.com/wired/64/000000/edit-property.png"/> ${contractorEmployee}-'${contractor}'</td>
</th></tr>

<tr><th>
<td><b>Supplier Type</b></td></th></tr>
<tr><th>
<td><img style="height:15px;width:15px"src="https://img.icons8.com/wired/64/000000/edit-property.png"/> ${supplierType}</td>
</th></tr>

<tr><th>
<td><b>Start and End Date</b></td></th></tr>
<tr><th>
<td><img style="height:15px;width:15px"src="https://img.icons8.com/wired/64/000000/edit-property.png"/> ${startDate} - ${endDate}</td>
</th></tr>

<tr><th>
<td><b>Assignment Work Locations</b></td></th></tr>
<tr><th>
<td><img style="height:15px;width:15px"src="https://img.icons8.com/wired/64/000000/edit-property.png"/> ${workLocations}</td>
</th></tr>

<tr><th>
<td><b>Reason for recruiting</b></td></th></tr>
<tr><th>
<td><img style="height:15px;width:15px"src="https://img.icons8.com/wired/64/000000/edit-property.png"/> ${reasonForRecruiting}</td>
</th></tr>
<tr><th><td><b>Milestones</b></td></th></tr>
</table>

<div>${workTasks}</div>

<br>
 <div class="flex-container">
 <div >Total Cost            ${totalCost}</div>

</div>
<style>

.button {

  background-color: black;
  border: none;
  color: white;
  padding: 5px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 14px;
  margin: 6px 2px;
  cursor: pointer;
}
</style>

<center><button class="button"><a style="text-decoration:none" href="${redirectUrl}">Review Document</a></button></center>
<p style="font-size:12px;">  Please review and approve documents  <br> </br>Requested by:<br>
<b>${requestedBy}</b>  <br>
${mailAddress}</p>
</div>



</body>
</html>
