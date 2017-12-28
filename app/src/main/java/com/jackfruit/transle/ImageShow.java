package com.jackfruit.transle;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageShow extends AppCompatActivity {
 ImageView img;
 Button crop,reselct,next;
    Intent intent;
    public static final int PICK_IMAGE = 1;
    Uri selectedImage;
    Uri uri;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        img = (ImageView) findViewById(R.id.glry);
        crop = (Button) findViewById(R.id.cropp);
        next=(Button) findViewById(R.id.toocr);
        reselct = (Button) findViewById(R.id.reselct);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);


        crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent inten = new Intent("com.android.camera.action.CROP");
                inten.setData(getImageUri(getApplicationContext(),bitmap));
                startActivityForResult(inten, 33);


            }
        });
        reselct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(intent,PICK_IMAGE);

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i =new Intent(ImageShow.this,OcrTransl.class);
                startActivity(i);
                finish();
            }
        });
    }


    @Override //for backpreed
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    //for selecting and cropping image
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode ==PICK_IMAGE) {

            if(resultCode==RESULT_OK) {

                selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                bitmap = BitmapFactory.decodeFile(picturePath);

                Constants.bitmapimage=bitmap;

                img.setImageBitmap(bitmap);
            }
            else Toast.makeText(ImageShow.this,"Please select an image !!!!",Toast.LENGTH_SHORT);
        }
        if (requestCode == 33)
        {
            if(resultCode==RESULT_OK) {
                    uri=Uri.parse(data.getData().toString());

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Constants.bitmapimage=bitmap;
                img.setImageBitmap(bitmap);
            }
            else Toast.makeText(ImageShow.this,"Croping Canceld",Toast.LENGTH_SHORT);
        }

    }
    //getting data for croping
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_mod, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
