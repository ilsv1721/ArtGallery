<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="w3-card-4">

	<div class="w3-container w3-cyan ilya-smc w3-center">
		<h2>Sign in</h2>
	</div>

	<sf:form action="login" method="post" class="w3-container">
		<p>
			<label class="ilay-smc">Email:</label>
		</p>
		<p>
			<input type="text" name="username" value=""
				class="w3-input w3-border w3-round" />
		</p>
		<p>
			<label class="ilay-smc">Password:</label>
		</p>
		<p>
			<input type="password" name="password" value=""
				class="w3-input w3-border w3-round" />
		</p>

		<p>
			<label class="ilay-smc">Remember me:</label> <input type="checkbox"
				name="remember-me" id="remember_me" class="w3-check" />
		</p>

		<p>
			<input type="submit" name="submit" value="Sign in"
				class="w3-btn w3-blue-grey" />
		</p>
	</sf:form>
</div>

