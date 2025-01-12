package org.example;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
//import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyBot extends ListenerAdapter //Simplifies the creation of event listeners for Discord bot events
{
    public static void main(String[] args) throws Exception {

        String bot_token = System.getenv("BOT_TOKEN"); // Retrieve the bot token from an environment variable

        if (bot_token == null || bot_token.isEmpty()) {
            System.err.println("Bot token is missing or empty. Please set the BOT_TOKEN environment variable.");
            System.exit(1);
        }

        JDABuilder.createDefault(bot_token) //creates a new JDABuilder instance and sets it up with your bot's token stored in bot_token.
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT) // Enable the intents
                .addEventListeners(new MyBot())  //adds an instance of the MyBot class as an event listener for the bot.
                .setActivity(Activity.playing("Hello, World!")) //Sets the bot's activity to "Playing Hello, World!"
                // This activity will be displayed next to the bot's name in Discord.
                .build(); //Finalizes the setup and starts the bot, making it connect to Discord and listen for events.

    }

//    @Override
//    public void onReady(ReadyEvent event) {   //an event handler that is called when the bot is ready to operate.
//                                              // It takes a ReadyEvent as a parameter, which contains information about the bot's readiness.
//        System.out.println("Logged in as " + event.getJDA().getSelfUser().getName()); //It prints a message to the console indicating that the bot has logged in and mentions the bot's username.
//    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {  //Another event handler. It is called whenever a message is received in a channel where the bot has access.
        if (event.getMessage().getContentRaw().equalsIgnoreCase("!Hey bot")) { //Checks if the content of the received message. is equal to "!hey bot".
            event.getChannel().sendMessage("Hey there! How you are doing?").queue();  // If it is, the bot responds back to the same channel where the message was received

        }
        if (event.getMessage().getContentRaw().equalsIgnoreCase("!NFLDB help")) {
            String filePath = "NFL_Discord_Server_Bot/src/main/resources/help.txt";

            try {
                // Create a FileReader to read the file
                FileReader fileReader = new FileReader(filePath);

                // Wrap the FileReader in a BufferedReader for efficient reading
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                // Read each line from the file and print it to the console
                String line;
                StringBuilder helpMessage = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                    helpMessage.append(line).append("\n"); // Add a newline character after each line
                }
                event.getChannel().sendMessage(helpMessage.toString()).queue();
                // event.getChannel().sendMessage(helpMessage.toString()).queue();

                // Close the BufferedReader
                bufferedReader.close();
            } catch (IOException e) {
                // Handle exceptions, such as file not found or unable to read
                e.printStackTrace();
            }
        }

        if (event.getMessage().getContentRaw().equalsIgnoreCase("!Hey bot")) { //Checks if the content of the received message. is equal to "!hey bot".
            event.getChannel().sendMessage("Hey there! How you are doing?").queue();  // If it is, the bot responds back to the same channel where the message was received

        }
        if (event.getMessage().getContentRaw().startsWith("!player ")) {
            String userMessage = event.getMessage().getContentRaw();
            String param = userMessage.substring("!player ".length());
            String result = "";
            result = playerHandler(param);
            event.getChannel().sendMessage(result).queue();
        }

        if (event.getMessage().getContentRaw().startsWith("!team ")) {
            String userMessage = event.getMessage().getContentRaw();
            String param = userMessage.substring("!team ".length());
            String result = "";
            result = teamHandler(param);
            event.getChannel().sendMessage(result).queue();
        }
        if (event.getMessage().getContentRaw().startsWith("!league ")) {
            String userMessage = event.getMessage().getContentRaw();
            String param = userMessage.substring("!league ".length());
            String result = "";
            result = leagueHandler(param);
            event.getChannel().sendMessage(result).queue();
        }
        if (event.getMessage().getContentRaw().startsWith("!random player")) {
            String result = "";
            result = RandCurrentPlayer.getPlayer();
            event.getChannel().sendMessage(result).queue();
        }

    }

    public String playerHandler(String param) {
        return "[playerHandler(playerName) result]";
    }

    public String teamHandler(String param) {

        if (param.startsWith("record ")) {
            String teamName = param.substring("record ".length());
            Team t = new Team(teamName);
            return t.getRecord();
        }else if(param.startsWith("stats ")){
            String teamName = param.substring("stats ".length());
            Team t = new Team(teamName);
            return t.getTeamStatistics();
        }else {
            return "Given team name is unknown!";
        }
    }

    public String leagueHandler(String param) {
        League l = new League();
        param.trim();
        if (param.equalsIgnoreCase("standings")){
            String strTable = "";
            strTable = l.getLeagueStandings();
            return strTable;
        }else if (param.equalsIgnoreCase("schedule")){
            String schedule = "";
            schedule = l.getLeagueSchedule();
            return schedule;
        }else{
            return "League stat request unknown. Try again!";
        }
    }

}