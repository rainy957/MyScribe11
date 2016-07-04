package app.com.example.android.myscribe;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AadhaarNotLinked extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhaar_not_linked);

        /*
        String message_string = "Mobile number not linked with aadhaar.To link your mobile number ";
        TextView message = (TextView) findViewById(R.id.message_text_view);
        message.setText(message_string);

         */

        SpannableString message = new SpannableString("Mobile number not linked with aadhaar.To link your mobile number Click here ");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://uidai.gov.in/update-your-aadhaar-data.html"));
                startActivity(i);
            }
            @Override
            public void updateDrawState(TextPaint drawstate) {
                super.updateDrawState(drawstate);
                drawstate.setUnderlineText(false);
            }
        };

        message.setSpan(clickableSpan,65,75, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView textView = (TextView) findViewById(R.id.message_text_view);
        textView.setText(message);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }
}
