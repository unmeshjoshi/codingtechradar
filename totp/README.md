This is a sample project to try TOTP authentication. 

1. Build the project 
  ./gradlew clean build shadowJar
2. Run 
  java -jar build/libs/totp-1.0-all.jar
3. Create a new key
  curl --data "" -i http://localhost:8080/key
4. Install Authenticator app in your android phone.
5. Setup a key returned to you in the Authenticator app.
6. Try logging in with the verification code in your app.
curl --data "key=<YOUR SECRET KEY>&verificationCode=<YOUR VERIFICATION CODE>" -i http://localhost:8080/login

Note that YOU DO NOT pass secret key while loggin in. The server needs to have a mechanism to store secret key per user.
This is just for experimentation purpose
