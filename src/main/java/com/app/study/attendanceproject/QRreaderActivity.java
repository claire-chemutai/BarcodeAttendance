package com.app.study.attendanceproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QRreaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrreader);
    }
    public void HandleClick(View arg0) {
        try {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            switch (arg0.getId()) {
                case R.id.butQR:
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                    break;
                case R.id.butProd:
                    intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
                    break;
                case R.id.butOther:
                    intent.putExtra("SCAN_FORMATS", "CODE_39,CODE_93,CODE_128,DATA_MATRIX,ITF,CODABAR");
                    break;
            }
            startActivityForResult(intent, 0); //Barcode Scanner to scan for us
        } catch (ActivityNotFoundException e) {
            showDialog(QRreaderActivity.this, "No scanner found", "Download Scanner code Activity?", " Yes", "No").show();

        }
    }
    private Dialog showDialog ( final Activity act, CharSequence title,CharSequence message, CharSequence yes, CharSequence no ) {

        // a subclass of dialog that can display buttons and message
        AlertDialog.Builder download = new AlertDialog.Builder( act );
        download.setTitle( title );
        download.setMessage ( message );
        download.setPositiveButton ( yes, new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick( DialogInterface dialog, int i ) {
                // TODO Auto-generated method stub
                //uri to download barcode scanner
                Uri uri = Uri.parse( "market://search?q=pname:" + "com.google.zxing.client.android" );
                Intent in = new Intent ( Intent.ACTION_VIEW, uri );
                try {
                    act.startActivity ( in );
                } catch ( ActivityNotFoundException e) {

                }
            }
        });
        download.setNegativeButton ( no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick ( DialogInterface dialog, int i ) {
                // TODO Auto-generated method stub
            }
        });
        return download.show();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            TextView tvStatus=(TextView)findViewById(R.id.tvStatus);
            TextView tvResult=(TextView)findViewById(R.id.tvResult);
            if (resultCode == RESULT_OK) {
                tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
                tvResult.setText(intent.getStringExtra("SCAN_RESULT"));
            } else if (resultCode == RESULT_CANCELED) {
                tvStatus.setText("Press a button to start a scan.");
                tvResult.setText("Scan cancelled.");
            }
        }
    }
}
//import android.app.Activity;
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;
//
//public class QRreaderActivity extends AppCompatActivity {
//    static final String SCAN = "com.google.zxing.client.android.SCAN";
//    Button scan_btn;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_qrreader);
//        final Activity activity = this;
//        scan_btn= findViewById(R.id.scan_btn);
//
//        scan_btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
////                IntentIntegrator integrator=new IntentIntegrator(activity);
////                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
////
////                integrator.setPrompt("Scan");
////                integrator.setCameraId(0);
////                integrator.setBeepEnabled(false);
////                integrator.setBarcodeImageEnabled(false);
////                integrator.initiateScan();
//                Intent intent = new Intent(SCAN);
//                intent.putExtra("SCA")
//                //intent.setPackage("com.google.zxing.client.android");
//                intent.putExtra("SCAN_MODE", "ONE");
//                startActivityForResult(intent, 0);
//            }
//        });
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultcode, Intent data){
////        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultcode,data);
////        if(result!=null){
////            if(result.getContents()==null){
////                Toast.makeText(this, "you cancelled the scanning", Toast.LENGTH_LONG).show();
////            }
////            else{
////                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
////            }
////        }
////        else {
////            super.onActivityResult(requestCode,resultcode,data);
////        }
//        if (requestCode == 0) {
//            if (resultcode == RESULT_OK) {
//                String contents = data.getStringExtra("SCAN_RESULT");
//                String format = data.getStringExtra("SCAN_RESULT_FORMAT");
//                Toast.makeText(this, contents, Toast.LENGTH_LONG).show();
//            } else if (resultcode == RESULT_CANCELED) {
//                Toast.makeText(this, "you cancelled the scanning", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//}
