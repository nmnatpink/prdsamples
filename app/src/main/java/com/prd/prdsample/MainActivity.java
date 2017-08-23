package com.prd.prdsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nmn.mylibrary.PRDclass;

public class MainActivity extends AppCompatActivity {

    PRDclass prdClass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        prdClass = new PRDclass() ;

        prdClass.uploadViaSFTP("","","","","",0);

        prdClass.writeFileOnInternalStorage(this,"","");


    }
}
