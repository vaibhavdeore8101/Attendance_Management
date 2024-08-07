<!DOCTYPE html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Forfot Password</title>
<link rel="stylesheet" href="regstyles.css">
<script>
	function validate() {
		var user = document.forgotform.user.value;	
		var question = document.forgotform.question.value;
		var ans = document.forgotform.ans.value;
		var newpass = document.forgotform.newpass.value;
		var confpass = document.forgotform.confpass.value;

		if (user == null || user == "") {
			alert("Plz Give Valid UserName");
			return false;
		}

		else if (question == null || question == "") {
			alert("Plz Select Question");
			return false;
		}

		else if (ans == null || ans == "") {
			alert("Plz Give Correct Answer");
			return false;
		}

		else if (newpass == null || newpass == "") {
			alert("Plz Give New Password");
			return false;
		}

		else if (confpass == null || confpass == "") {
			alert("Plz Give Confirm Password");
			return false;
		}

		else if (newpass != confpass) {
			alert("New And Confirm Password Must Be Same");
			return false;
		}
	}
</script>
</head>

<body>


	<%
		String msg = (String) request.getAttribute("msg");

		if (msg != null) {
	%>
	<script type="text/javascript">
		alert("${msg}");
	</script>
	<%
		}
	%>
	<div class="container">
		<section class="register">
			<h3>Forgot Form</h3>
			<br>

			<form name="forgotform" action="forgot" method="post" 
				onsubmit="validate()">
				<%--       <h1 style="color:red">${msg}</h1>  
 --%>

				<div class="reg_section password">
					<h3>Question And Answer</h3>
					<input type="text" name="user" placeholder="Username"> <select
						name="question">
						<option value="" name="question">Select Question</option>

						<option value="WHAT IS YOUR NICKNAME ?" name="question">WHAT
							IS YOUR NICKNAME ?</option>
						<option value="WHAT IS YOUR BIRTH YEAR ?" name="question">WHAT
							IS YOUR BIRTH YEAR ?</option>
					</select> <input type="text" name="ans" value="" placeholder="Answer">
				</div>

				<div class="reg_section personal_info">
					<h3>New And Confirm Password</h3>

					<input type="text" name="newpass" value=""
						placeholder="New Password"> <input type="text"
						name="confpass" value="" placeholder="Confirm Password">

				</div>

				<p class="submit">
					<input type="submit" name="action" value="Reset Password">
				</p>
			</form>
		</section>
	</div>
</body>
</html>