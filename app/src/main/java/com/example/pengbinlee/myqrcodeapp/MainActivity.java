package com.example.pengbinlee.myqrcodeapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView scan_result;
    private EditText content;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button scan_button = (Button) findViewById(R.id.scan_button);
        scan_button.setOnClickListener(this);

        Button create_button = (Button) findViewById(R.id.create_button);
        create_button.setOnClickListener(this);

        scan_result = (TextView) findViewById(R.id.scan_result);

        content = (EditText) findViewById(R.id.content);

        imageView = (ImageView) findViewById(R.id.imageview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scan_button:
                startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
                break;
            case R.id.create_button:
                createQRCode();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            scan_result.setText(result);
        }
    }

    public void createQRCode() {
        String input = content.getText().toString();
        if(TextUtils.isEmpty(input)) {
            Toast.makeText(MainActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
        } else {
            Bitmap bitmap = EncodingUtils.createQRCode(input, 500, 500, null);
            imageView.setImageBitmap(bitmap);
        }
    }
}
