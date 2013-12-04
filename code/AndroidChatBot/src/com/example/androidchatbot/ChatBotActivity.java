package com.example.androidchatbot;

/*
 * Course: CS 2302
 * Section: 03
 * Name: Dylan Northrup
 * Professor: Prof. Shaw
 * Assignment #: Lab 19
 */


import com.example.androidchatbot.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;

import p2p.ChatBot;

public class ChatBotActivity extends Activity {

  private TextView heading;
  private TextView recvTxt;
  private EditText sendTxt;
  private Button sendBtn;
  private ChatBot chatBot;

  private String youSaidStr;
  private String chatBotSaidStr;
  private String headingStr2;
  private boolean firstHeading = true;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chat_bot);

    // Getting the heading element
    heading = (TextView) findViewById(R.id.textviewHead1);
    headingStr2 = getString(R.string.heading1b);
    youSaidStr = getString(R.string.yousaidtext);
    chatBotSaidStr = getString(R.string.chatbotsaidtext);

    // Getting the box where text will be received
    //   and making it scrollable
    recvTxt = (TextView) findViewById(R.id.textviewReceived);
    recvTxt.setMovementMethod(new ScrollingMovementMethod());

    // Getting the box where text will be sent from
    sendTxt = (EditText) findViewById(R.id.edittextSent);

    // Getting the button that will send the text to the server
    sendBtn = (Button) findViewById(R.id.sendButton);
    sendBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String questionStr = sendTxt.getText().toString().trim();
        sendTxt.setText("");
        if (questionStr.trim().length() > 0) {
          addToConversation("[ " + youSaidStr + " " +
              questionStr + " ]");
          if (firstHeading) {
            heading.setText(headingStr2);
            firstHeading = false;
          }
          String response = chatBot.getResponse(questionStr);
          addToConversation(chatBotSaidStr + " " + response);
        }
      }
    });

    chatBot = new ChatBot();
    chatBot.keyResponseAny("Too realistic.", "ISS","mir","spacelab");
    chatBot.keyResponseAny("Too Caprican.", "Armistice Station","Ragnar Anchorage");
    chatBot.keyResponseAny("Too much Federation.", "Deep Space 9","Vanguard Station","Wolf 359");
    chatBot.keyResponseAny("Too much Empire.", "Death Star");
    chatBot.keyResponseAny("Too much Mass Effect.", "citadel","omega");
    chatBot.keyResponseAll("Same universe, but too much time travel.", "Babylon","4");
    chatBot.keyResponseAll("Our last best hope for Victory!", "Babylon", "5");
    chatBot.keyResponseAll("Our last best hope for Victory!", "Babylon5");
    // The responses are all quotes from the character Kosh from the show Babylon 5
    chatBot.noMatchMessage("I will miss this... when it is gone.");
    chatBot.noMatchMessage("Understanding is a three-edged sword.");
    chatBot.noMatchMessage("Ah, you seek meaning? Then listen to the music, not the song.");
    chatBot.noMatchMessage("The avalanche has already started. It is too late for the pebbles to vote.");

    addToConversation("> Hello! Can you guess my favorite space station?");

  }

  // Adds lines of text to the main conversation box
  private void addToConversation(String newLine) {
    String rcvStr = recvTxt.getText().toString();
    if (rcvStr.length() > 0)
      newLine = "\n\n" + newLine;

    recvTxt.append(newLine);

    final Layout layout = recvTxt.getLayout();
    if (layout != null) {
      int scrollDelta = layout.getLineBottom(recvTxt.getLineCount() - 1) 
          - recvTxt.getScrollY() - recvTxt.getHeight();
      if (scrollDelta > 0)
        recvTxt.scrollBy(0, scrollDelta);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.chat_bot, menu);
    return true;
  }

}
