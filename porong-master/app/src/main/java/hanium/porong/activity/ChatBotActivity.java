//package hanium.porong.activity;
//
//import android.content.Intent;
//import android.os.Build;
//import android.os.Bundle;
//import android.speech.RecognizerIntent;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ScrollView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//import java.util.UUID;
//
//import ai.api.AIServiceContext;
//import ai.api.AIServiceContextBuilder;
//import ai.api.android.AIConfiguration;
//import ai.api.android.AIDataService;
//import ai.api.model.AIRequest;
//import ai.api.model.AIResponse;
//import hanium.porong.MainActivity;
//import hanium.porong.R;
////import com.google.firebase.auth.FirebaseAuth;
////import com.google.firebase.auth.FirebaseUser;
//
//public class ChatBotActivity extends AppCompatActivity {
//    /*
//        This activity is the heart of the Chatbot and handles all the request and reply from AI agent.
//     */
//    private static final int USER = 10001;
//    private static final int BOT = 10002;
//
//    private String uuid = UUID.randomUUID().toString();
//    private LinearLayout chatLayout;
//    private EditText queryEditText;
//    //Android Client to access the Dialogflow
//
//    private AIRequest aiRequest;
//    private AIDataService aiDataService;
//    private AIServiceContext customAIServiceContext;
//    //Speech to text
//    private FloatingActionButton speech;
//
//    FirebaseUser currentUser;//used to store current user of account
//    FirebaseAuth mAuth;//Used for firebase authentication
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chat);
//        final ScrollView scrollview = findViewById(R.id.chatScrollView);
//        scrollview.fullScroll(View.FOCUS_DOWN);
//        chatLayout = findViewById(R.id.chatLayout);
//        final ImageView sendBtn = findViewById(R.id.sendBtn);
//        queryEditText = findViewById(R.id.queryEditText);
//
//        sendBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sendMessage(sendBtn);
//            }
//        });
//
//        queryEditText.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
//                    switch (i) {
//                        case KeyEvent.KEYCODE_DPAD_CENTER:
//                        case KeyEvent.KEYCODE_ENTER:
//                            sendMessage(sendBtn);
//                            return true;
//                        default:
//                            break;
//                    }
//                }
//                return false;
//            }
//        });
//
//        initChatbot();
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//    private void closeApplication() {
//        finishAffinity();
//        System.exit(0);
//    }
//
//
//    /*
//            Initialize the chat bot using this function.
//     */
//    private void initChatbot() {
//
//        //Configure the AI agent from Dialogfow access code
//        final AIConfiguration config = new AIConfiguration("aa737be0a2594514981af6aaa9eb1431",
//                AIConfiguration.SupportedLanguages.Korean,
//                AIConfiguration.RecognitionEngine.System);
//        //Sending the request to Agent.
//        aiDataService = new AIDataService(this, config);
//        customAIServiceContext = AIServiceContextBuilder.buildFromSessionId(uuid);
//        aiRequest = new AIRequest();
//    }
//
//    /*
//        To get the result of the Query.
//     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
//            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//            queryEditText.setText(result.get(0));
//        }
//    }
//
//    /*
//        To send message to our AI agent.
//     */
//    private void sendMessage(View view) {
//        String msg = queryEditText.getText().toString();
//
//        if (msg.trim().isEmpty()) {
//            //If the message is empty
//            Toast.makeText(MainActivity.this, "Please enter your query!", Toast.LENGTH_LONG).show();
//        } else {
//            // If message is non empty string
//            showTextView(msg, USER);
//            queryEditText.setText("");
//            aiRequest.setQuery(msg);
//            //Send Request
//            RequestTask requestTask = new RequestTask(MainActivity.this, aiDataService, customAIServiceContext);
//            requestTask.execute(aiRequest);
//        }
//    }
//
//    /*
//        Handle the reply from bot.
//    */
//    public void callback(AIResponse aiResponse) {
//        if (aiResponse != null) {
//            // process aiResponse here
//            String botReply = aiResponse.getResult().getFulfillment().getSpeech();
//            Log.d("MyBOT", "Bot Reply: " + botReply);
//            showTextView(botReply, BOT);
//
//        } else {
//            Log.d("MyBOT", "Bot Reply: Null");
//            showTextView("There was some communication issue. Please Try again!", BOT);
//
//        }
//    }
//
//    /*
//        Show the reply of the AI agent.
//     */
//    private void showTextView(String message, int type) {
//        FrameLayout layout;
//        switch (type) {
//            case USER:
//                layout = getUserLayout();
//                break;
//            case BOT:
//                layout = getBotLayout();
//                break;
//            default:
//                layout = getBotLayout();
//                break;
//        }
//        layout.setFocusableInTouchMode(true);
//        chatLayout.addView(layout); // move focus to text view to automatically make it scroll up if softfocus
//        TextView tv = layout.findViewById(R.id.chatMsg);
//        tv.setText(message);
//        layout.requestFocus();
//        queryEditText.requestFocus(); // change focus back to edit text to continue typing
//    }
//
//    /*
//        To return user Layout design to display message.
//     */
//    FrameLayout getUserLayout() {
//        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//        return (FrameLayout) inflater.inflate(R.layout.user_msg_layout, null);
//    }
//
//    /*
//        To return the Bot Layout design to display the message.
//     */
//    FrameLayout getBotLayout() {
//        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//        return (FrameLayout) inflater.inflate(R.layout.bot_msg_layout, null);
//    }
//}