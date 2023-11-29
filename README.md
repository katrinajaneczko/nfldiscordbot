# NFL Discord Bot
Users on the Discord platform can enjoy the benefits of a server bot that effortlessly provides them with information from the National Football League (NFL), including scores and statistics all within their own servers. Discord is a popular platform where users can create communities to communicate and share media. For the bot to work, a user can write a command that the bot knows, and the bot will display its response to the user. With this bot, NFL communities in Discord can view desired data in a fast and efficient manner.

# How to Use It
These instructions are for Discord admin users.

## How to Add the Bot to Your Server:
1. Ensure that you are logged into your Discord account.
2. Click [here](https://discord.com/api/oauth2/authorize?client_id=1153482870980083763&permissions=68608&scope=bot) to access the bot's invite link.
3. On the webpage that opens, choose the server where you want to add the bot from the dropdown menu.
4. Click "Continue," and the bot will be added to your server.

## How to Run the Bot (Will Be Updated Later to Include an Executable)
1. Download this repository to your local machine.
2. Using an IDE (preferably IntelliJ), open the project and navigate to the **MyBot.java** file. You can find it here: **NFL_Discord_Server_Bot/src/main/java/org/example/MyBot.java**
3. Run the file until the console output stops.
4. Open the Discord server where you've added the bot.
5. In any channel (e.g., the #general channel), type ***!Hey bot*** or ***!hey bot*** in the text field.
6. The bot should respond with a message, indicating that it's working correctly.
7. Additional instructions will be provided as the project progresses.

## How to Deploy
1. Sync the fork with the original repository to retrieve new code changes. 
2. Click [here](https://discord.com/api/oauth2/authorize?client_id=1172214896553898074&permissions=68608&scope=bot) to access the production bot's invite link.
   * A separate bot and bot token is used for deployments (as opposed to local development).  
3. The code is deployed using a GitHub Action workflow which creates a JAR file, containerizes it using Docker, pushes the image to an Amazon ECR registry, and pulls the image to deploy an Amazon ECS service.
   * The workflow runs each time there is a push to the `main` branch.
   * If there is a green icon as opposed to red, then it's deploying correctly.
4. See the Wiki page for more info on how this all works and how to add this to the original repository.
   * To add/change repository secrets in the original repo, you'd go to `Settings > Secrets` and `variables > Actions` and hit the `New repository secret button`. The following 4 secrets are necessary for CI/CD: 
      * AWS_ACCESS_KEY_ID
      * AWS_SECRET_ACCESS_KEY
      * AWS_ACCOUNT_ID
      * BOT_TOKEN
    * The BOT_TOKEN you should already have, and you can copy/paste it here. But the AWS account info will need to be obtained from an active account. 

   

