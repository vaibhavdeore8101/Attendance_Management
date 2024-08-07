<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;}

input[type=text] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

button:hover {
    opacity: 0.8;
}




.container {
    padding: 16px;
}

span.psw {
    float: right;
    padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
    span.psw {
       display: block;
       float: none;
    }
    .cancelbtn {
       width: 100%;
    }
}
</style>
</head>
<body>
${msg}

<h2>Forgot Form</h2>

<form action="forgot" method="post">
  

  <div class="container">
  
  <label for="user"><b>Username</b></label>
    <input type="text" placeholder="Enter Username To Reset password" name="user" required>
  
  <label for="question"><b>Question</b></label>
  <br><br>
  <select name="question" style="width: 100%">
						<option value="WHAT IS YOUR NICKNAME ?" name="question">WHAT IS YOUR NICKNAME ?</option>
						<option value="WHAT IS YOUR BIRTH YEAR ?" name="question">WHAT IS YOUR BIRTH YEAR ?</option>
				</select>
  
  <br>
    
    <br>

    <label for="ans"><b>Answer</b></label>
    <input type="text" placeholder="Give Your Question Answer" name="ans" required>
    
     <label for="psw"><b>New Password</b></label>
    <input type="text" placeholder="Enter New Password" name="newpass" required>
    
     <label for="psw"><b>Confirm Password</b></label>
    <input type="text" placeholder="ReEnter Password" name="confpass" required>
        
    <button type="submit">Reset</button>
    
  </div>

  
</form>

</body>
</html>