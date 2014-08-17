package gr.steve.notyhide;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NewText extends Activity implements OnClickListener
{
   EditText editbox1;
   EditText editbox2;
   Button cutb;
   Button copyb;
   Button pasteb;
   Button saveb,backb;
   Editable s1,s2;
	
   Spannable str;
   Spannable str2;

   private Long mRowId;
   public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
	setContentView(R.layout.new_text);
	editbox1 =(EditText) findViewById(R.id.title);
	editbox2 =(EditText) findViewById(R.id.insertdata);
	mRowId = null;
	Bundle extras = getIntent().getExtras();
	if (extras != null) {
		String title = extras.getString(NotesDbAdapter.KEY_TITLE);
		String body = extras.getString(NotesDbAdapter.KEY_BODY);
		mRowId = extras.getLong(NotesDbAdapter.KEY_ROWID);
		if (title != null) {
			editbox1.setText(title);
		}
		if (body != null) {
			editbox2.setText(body);
		}
	}
	cutb =(Button) findViewById(R.id.cut);
	cutb.setOnClickListener(this);
	copyb =(Button)findViewById(R.id.copy);
	copyb.setOnClickListener(this);
	pasteb =(Button)findViewById(R.id.paste);
	pasteb.setOnClickListener(this);
	saveb =(Button) findViewById(R.id.save);
	saveb.setOnClickListener(this);
	backb =(Button) findViewById(R.id.back);
	backb.setOnClickListener(this);
   }
   public void onBackPressed() {
	moveTaskToBack(true);
 	return;
   }
   @Override
   public void onClick(View v) {
	// TODO Auto-generated method stub
	switch(v.getId())
	{
   	    case R.id.save :
		Bundle bundle = new Bundle();
		bundle.putString(NotesDbAdapter.KEY_TITLE, editbox1.getText().toString());
		bundle.putString(NotesDbAdapter.KEY_BODY, editbox2.getText().toString());
		if (mRowId != null) {
			bundle.putLong(NotesDbAdapter.KEY_ROWID, mRowId);
		}
		Intent mIntent = new Intent();
		mIntent.putExtras(bundle);
		setResult(RESULT_OK, mIntent);
		finish();
		break;
            case R.id.copy :
		//String selectedText = editbox2.getText().substring(editbox2.getSelectionStart(), editbox2.getSelectionEnd());
		if(editbox2.getSelectionEnd() > editbox2.getSelectionStart()) 
		{
			s1 = (Editable) editbox2.getText().subSequence(editbox2.getSelectionStart(), editbox2.getSelectionEnd());
		}else
		{
			s1 = (Editable) editbox2.getText().subSequence(editbox2.getSelectionEnd(), editbox2.getSelectionStart());
		}
		break;
            case R.id.cut :
		if(editbox2.getSelectionEnd() > editbox2.getSelectionStart()) 
		{
			s1 = (Editable) editbox2.getText().subSequence(editbox2.getSelectionStart(), editbox2.getSelectionEnd());
		}else
		{
			s1 = (Editable) editbox2.getText().subSequence(editbox2.getSelectionEnd(), editbox2.getSelectionStart());
		}
		editbox2.getText().replace(Math.min(editbox2.getSelectionStart(), editbox2.getSelectionEnd()), Math.max(editbox2.getSelectionStart(), editbox2.getSelectionEnd()),"", 0, 0);
		break;
            case R.id.paste :
            editbox2.getText().replace(Math.min(editbox2.getSelectionStart(), editbox2.getSelectionEnd()), Math.max(editbox2.getSelectionStart(), editbox2.getSelectionEnd()),s1, 0, s1.length());
            break;
            
            case R.id.back :
		Intent mIntent2 = new Intent(this,MainActivity.class);
		startActivity(mIntent2);
		finish();
		break;	
	}
    }
}