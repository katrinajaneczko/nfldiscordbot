import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class MyBotTest {

    @Mock
    private MessageReceivedEvent mockEvent;

    @Mock
    private MessageChannel mockChannel;

    @Mock
    private Message mockMessage;

    @Mock
    private User mockUser;

    private MyBot myBot;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        myBot = new MyBot();
    }

    @Test
    public void testOnMessageReceived() {
        // Set up the mock objects
        when(mockEvent.getMessage()).thenReturn(mockMessage);
        when(mockEvent.getChannel()).thenReturn(mockChannel);
        when(mockMessage.getContentRaw()).thenReturn("!Hey bot");
        when(mockMessage.getAuthor()).thenReturn(mockUser);

        // Call the onMessageReceived method
        myBot.onMessageReceived(mockEvent);

        // Verify that the bot responds as expected
        verify(mockChannel).sendMessage("Hey there! How you are doing?").queue();
    }
}
