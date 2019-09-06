package ir.greencode.contactnumberproject.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import ir.greencode.contactnumberproject.R;

;

/**
 * Created by alireza on 5/18/18.
 */

public class QuestionDialog extends Dialog {

    QuestionListener myInterface;
    Context context;
    TextView yesBtn;
    TextView noBtn;
    String title;
    String desc;
    TextView header;
    TextView txtDesc;

    public QuestionDialog(Context context, String title, String desc) {
        super(context);
        this.context = context;
        this.title = title;
        this.desc = desc;
    }

    public void setListener(QuestionListener listener) {
        this.myInterface = listener;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        View view = View.inflate(context, R.layout.dialog_question, null);

        setContentView(view);
        setCancelable(false);

        yesBtn = view.findViewById(R.id.yesBtn);
        noBtn = view.findViewById(R.id.noBtn);
        header  = view.findViewById(R.id.header);
        txtDesc = view.findViewById(R.id.txtDesc);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myInterface.onSuccess();
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myInterface.onReject();
            }
        });

        WindowManager.LayoutParams params = getWindow().getAttributes();

        params.width = -1;
        params.x = 100;

        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 70);
        getWindow().setBackgroundDrawable(inset);
        getWindow().setAttributes(params);

        if(this.desc.equals("")){
            txtDesc.setVisibility(View.GONE);
        }else {
            txtDesc.setText(this.desc);
            txtDesc.setVisibility(View.VISIBLE);
        }

        header.setText(this.title);

    }


    @Override
    public void onBackPressed() {
        myInterface.onReject();
        super.onBackPressed();
    }


}
