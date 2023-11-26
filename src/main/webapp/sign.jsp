<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>LuxurySpa a Spa Template</title>
    <link rel="stylesheet" href="css/login.css">
  </head>
  <body>
            <div class="container right-panel-active">
              <!-- Sign Up -->
              <div class="container__form container--signup">
                <form method="POST" action="register" class="form" id="form1">
                  <h2 class="form__title">Sign Up</h2>
                  <label for="reg-user"></label><input type="text" name="reg-user" id="reg-user" placeholder="User" class="input" required>
                  <label for="reg-email"></label><input type="email" name="reg-email" id="reg-email" placeholder="Email" class="input" required>
                  <label for="reg-password"></label><input type="password" name="reg-password" id="reg-password" placeholder="Password" class="input" required>
                  <button href="#" type="submit" class="btn" value="submit">Sign Up</button>
                </form>
              </div>

              <!-- Sign In -->
              <div class="container__form container--signin">
                <form method="POST" action="login" class="form" id="form2">
                  <h2 class="form__title">Sign In</h2>
                  <label for="log-email"></label><input type="email" name="log-email" id="log-email" placeholder="Email" class="input" required>
                  <label for="log-password"></label><input type="password" name="log-password" id="log-password" placeholder="Password" class="input" required>
                  <a href="#" class="link">Forgot your password?</a>
                  <button href="#" type="submit" class="btn" value="submit">Sign In</button>
                </form>
              </div>

              <!-- Overlay -->
              <div class="container__overlay">
                <div class="overlay">
                  <div class="overlay__panel overlay--left">
                    <button class="btn" id="signIn">Sign In</button>
                  </div>
                  <div class="overlay__panel overlay--right">
                    <button class="btn" id="signUp">Sign Up</button>
                  </div>
                </div>
              </div>
            </div>
    <script src="js/login.js"></script>
  </body>

  <jsp:include page="/include/backButton.jsp" />

</html>