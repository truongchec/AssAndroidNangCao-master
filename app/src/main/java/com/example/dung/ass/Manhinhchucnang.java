package com.example.dung.ass;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Manhinhchucnang extends AppCompatActivity {
    private EditText edittexttitle;
    private EditText edittextdescirption;
    private EditText edittexturl;
    private Button buttonShareLink;
    private ImageView imgHinh;
    private Button btnShareImgae;
    ShareDialog shareDialog;
    ShareLinkContent shareLinkContent;
    public static int Select_Img=1;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchucnang);
        edittexttitle = (EditText) findViewById(R.id.edittexttitle);
        edittextdescirption = (EditText) findViewById(R.id.edittextdescirption);
        edittexturl = (EditText) findViewById(R.id.edittexturl);
        buttonShareLink = (Button) findViewById(R.id.buttonShareLink);
        imgHinh = (ImageView) findViewById(R.id.imgHinh);
        btnShareImgae = (Button) findViewById(R.id.btnShareImgae);
        shareDialog = new ShareDialog(Manhinhchucnang.this);
        buttonShareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    shareLinkContent=new ShareLinkContent.Builder().setContentTitle(edittexttitle.getText().toString()).setContentDescription(edittextdescirption.getText().toString()).
                            setContentUrl(Uri.parse(edittexturl.getText().toString())).build();
                }
                shareDialog.show(shareLinkContent);
            }
        });
        imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/+");
                startActivityForResult(intent,Select_Img);
            }
        });
        btnShareImgae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(bitmap)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();
                shareDialog.show(content);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==Select_Img&&resultCode==RESULT_OK){
            try {
                InputStream inputStream=getContentResolver().openInputStream(data.getData());
               bitmap=BitmapFactory.decodeStream(inputStream);
               imgHinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
