package com.MahmmoudOsama.inheretance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.MahmmoudOsama.inheretance.databinding.ActivityResultsBinding;

import java.util.LinkedHashMap;

public class ResultsActivity extends AppCompatActivity {
ActivityResultsBinding rab;
FinalAdapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rab= ActivityResultsBinding.inflate(getLayoutInflater());
        setContentView(rab.getRoot());
        Intent in=getIntent();
        if (in.getBooleanExtra("reclc",false)==true&&MainActivity.osba==false){
            rab.txt.setText("مجموع أنصبة الورثة أقل من مجموع التركة لذا قمنا برد باقي التركة على الورثة عدا الزوجين بنفس أسهمهم");
        }
        else if (in.getBooleanExtra("inc",false)==true){
            rab.txt.setText("مجموع أنصبة الورثة أكبر من مجموع التركة فالمسألة تعول إلى الواحد الصحيح ويتم انقاص أنصبة الورثة على حسب أسهمهم");
        }
        else
            rab.txt.setText("");
        adp =new FinalAdapter(MainActivity.persons);
        rab.recycle.setAdapter(adp);


rab.back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        MainActivity.persons.clear();
        Intent i=new Intent(getBaseContext(),MainActivity.class);
        startActivity(i);
    }
});
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        for (LinkedHashMap.Entry<String, Integer> entry : MainActivity.heirs_map.entrySet()){
            String key = entry.getKey();
            Integer value = entry.getValue();
            MainActivity.heirs_map.put(key,0);
        }
    }
}