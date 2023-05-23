package com.MahmmoudOsama.inheretance;
import java.util.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.MahmmoudOsama.inheretance.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding b;
    static LinkedHashMap<String, Integer> heirs_map;
   static   LinkedHashMap<String, String> heirsMapA;
   static LinkedHashMap<String, String> heirsMapE;
   static LinkedHashMap<String,Integer>heirsImages;
   static ArrayList<Person>persons;
   static FirestAdapter myCustomAdapter;
int wifes=0;
   int y;
   static boolean osba;
    static double totalMoney;
   static boolean reclc;
   static boolean inc;
    public static final String SON = "son";
    public static final String SON_SON = "son\'s son";
    public static final String FATHER = "father";
    public static final String GRANDFATHER = "grandfather";
    public static final String HUSBAND = "husband";
    public static final String BROTHER = "brother";
    public static final String BROTHER_SON = "brother\'s son";
    public static final String FATHER_BROTHER = "father\'s brother";
    public static final String FATHER_BROTHER_SON = "father\'s brother\'s son";
    public static final String MOTHERS_RELATIVES = "mother\'s brother & mother\'s sister";
    public static final String SISTER = "sister";
    public static final String FATHER_SISTER = "father\'s sister";
    public static final String UNCLE = "uncle";
    public static final String FATHER_UNCLE = "father\'s uncle";
    public static final String UNCLE_SON = "uncle\'s son";
    public static final String FATHER_UNCLE_SON = "father's uncle\'s son";
    public static final String DAUGHTER = "daughter";
    public static final String SON_DAUGHTER = "son\'s daughter";
    public static final String MOTHER = "mother";
    public static final String GRANDMOTHER = "grandmother";
    public static final String WIFE = "wife";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        LinkedHashMap<Integer, String> fractionMap = new LinkedHashMap<Integer, String>();

        fractionMap.put(3, "الثمن"); // انصبة كل وارث في هيئة كسور عشرية (المفتاح هنا هو رقم البسط على المقام 24)
        fractionMap.put(4, "السدس");
        fractionMap.put(6, "الربع");
        fractionMap.put(8, "الثلث");
        fractionMap.put(12, "النصف");
        fractionMap.put(16, "الثلثين");
        fractionMap.put(0, "محجوب");
        fractionMap.put(-1, "الباقي تعصيبا");
        fractionMap.put(-3, "السدس + الباقي");
        fractionMap.put(-2, "الباقي للذكر مثل حظ الانثيين");
        fractionMap.put(-4, "الباقي للذكر مثل حظ الانثيين");
        heirs_map = new LinkedHashMap<>();
        heirs_map.put(SON, 0);
        heirs_map.put(SON_SON, 0);
        heirs_map.put(FATHER, 0);
        heirs_map.put(GRANDFATHER, 0);
        heirs_map.put(HUSBAND, 0);
        heirs_map.put(BROTHER, 0);
        heirs_map.put(BROTHER_SON, 0);
        heirs_map.put(FATHER_BROTHER, 0);
        heirs_map.put(FATHER_BROTHER_SON, 0);
        heirs_map.put(MOTHERS_RELATIVES, 0);
        heirs_map.put(SISTER, 0);
        heirs_map.put(FATHER_SISTER, 0);
        heirs_map.put(UNCLE, 0);
        heirs_map.put(FATHER_UNCLE, 0);
        heirs_map.put(UNCLE_SON, 0);
        heirs_map.put(FATHER_UNCLE_SON, 0);
        heirs_map.put(DAUGHTER, 0);
        heirs_map.put(SON_DAUGHTER, 0);
        heirs_map.put(MOTHER, 0);
        heirs_map.put(GRANDMOTHER, 0);
        heirs_map.put(WIFE, 0);

        heirsImages =new LinkedHashMap<>();

try {
    heirsImages.put(SON, R.drawable.son);
    heirsImages.put(SON_SON, R.drawable.grandson);
    heirsImages.put(FATHER, R.drawable.father);
    heirsImages.put(GRANDFATHER, R.drawable.grandfather);
    heirsImages.put(HUSBAND, R.drawable.husbend);
    heirsImages.put(BROTHER, R.drawable.brother);
    heirsImages.put(BROTHER_SON, R.drawable.brotherson);
    heirsImages.put(FATHER_BROTHER, R.drawable.fatherbrother);
    heirsImages.put(FATHER_BROTHER_SON, R.drawable.fatherbrotherson);
    heirsImages.put(MOTHERS_RELATIVES, R.drawable.brotherandsister);
    heirsImages.put(SISTER, R.drawable.sister);
    heirsImages.put(FATHER_SISTER, R.drawable.fathersister);
    heirsImages.put(UNCLE, R.drawable.uncle);
    heirsImages.put(FATHER_UNCLE, R.drawable.fatheruncle);
    heirsImages.put(UNCLE_SON, R.drawable.uncleson);
    heirsImages.put(FATHER_UNCLE_SON, R.drawable.fatheruncleson);
    heirsImages.put(DAUGHTER, R.drawable.daughter);
    heirsImages.put(SON_DAUGHTER, R.drawable.sondaugther);
    heirsImages.put(MOTHER, R.drawable.mohter);
    heirsImages.put(GRANDMOTHER, R.drawable.grandmother);
    heirsImages.put(WIFE, R.drawable.wife);



    heirsMapA = new LinkedHashMap<String, String>();
    heirsMapE = new LinkedHashMap<>();

    String[] arabicHeirs = {"ابن", "ابن الابن", "أب", "جد", "زوج", "أخ", "ابن الأخ", "أخ لأب", "ابن الأخ لأب", "أخوة لأم", "أخت", "أخت لأب", "عم", "عم لأب", "ابن العم", "ابن العم لأب", "بنت", "بنت الابن", "أم", "جدة", "زوجة"};


    String[] englishHeirs = {"son", "son's son", "father", "grandfather", "husband", "brother", "brother's son", "father's brother", "father's brother's son", "mother's brother & mother's sister", "sister", "father's sister", "uncle", "father's uncle", "uncle's son", "father's uncle's son", "daughter", "son's daughter", "mother", "grandmother", "wife"};

    for (int i = 0; i < arabicHeirs.length; i++) {
        heirsMapA.put(arabicHeirs[i], englishHeirs[i]);
        heirsMapE.put(englishHeirs[i], arabicHeirs[i]);
    }



    persons = new ArrayList<>();
    myCustomAdapter = new FirestAdapter(persons);
    b.listView.setAdapter(myCustomAdapter);
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {


        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();

            removeItem(position);
            
            // Call your delete item method with the position to delete the item from your data source.
            // Also, you need to notify your RecyclerView adapter to remove the item from the UI.
        }
    };
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
    itemTouchHelper.attachToRecyclerView(b.listView);
}catch (Exception e){
    b.title.setText(e.toString());
}
        y=0;
        b.son.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(SON));
                myCustomAdapter.notifyDataSetChanged();
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });
        b.sonOfSon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(SON_SON));
                myCustomAdapter.notifyDataSetChanged();
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);

            }
        });b.father.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(FATHER));
                myCustomAdapter.notifyDataSetChanged();
                b.father.setVisibility(View.GONE);
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });b.grandfather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(GRANDFATHER));
                myCustomAdapter.notifyDataSetChanged();
                b.grandfather.setVisibility(View.GONE);
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });b.hasbend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(HUSBAND));
                myCustomAdapter.notifyDataSetChanged();
                b.hasbend.setVisibility(View.GONE);
                b.wife.setVisibility(View.GONE);
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });b.brohter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(BROTHER));
                myCustomAdapter.notifyDataSetChanged();
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });b.BrotherSon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(BROTHER_SON));
                myCustomAdapter.notifyDataSetChanged();
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });b.fatherBrother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(FATHER_BROTHER));
                myCustomAdapter.notifyDataSetChanged();
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });b.fatherBrotherSon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(FATHER_BROTHER_SON));
                myCustomAdapter.notifyDataSetChanged();
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });b.MotherSons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(MOTHERS_RELATIVES));
                myCustomAdapter.notifyDataSetChanged();
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });b.sister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(SISTER));
                myCustomAdapter.notifyDataSetChanged();
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });b.fatherSister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(FATHER_SISTER));
                myCustomAdapter.notifyDataSetChanged();
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });b.uncle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(UNCLE));
                myCustomAdapter.notifyDataSetChanged();
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });b.fatherUncle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(FATHER_UNCLE));
                myCustomAdapter.notifyDataSetChanged();
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);

            }
        });b.uncleSon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(UNCLE_SON));
                myCustomAdapter.notifyDataSetChanged();
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });b.fatherUncleSon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(FATHER_UNCLE_SON));
                myCustomAdapter.notifyDataSetChanged();
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });b.daughter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(DAUGHTER));
                myCustomAdapter.notifyDataSetChanged();
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });b.sonDaughter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(SON_DAUGHTER));
                myCustomAdapter.notifyDataSetChanged();
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });b.mother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(MOTHER));
                myCustomAdapter.notifyDataSetChanged();
                b.mother.setVisibility(View.GONE);
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });b.grandMother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(GRANDMOTHER));
                myCustomAdapter.notifyDataSetChanged();
                b.grandMother.setVisibility(View.GONE);
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });b.wife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.add(new Person(WIFE));
                myCustomAdapter.notifyDataSetChanged();
                wifes++;
                b.hasbend.setVisibility(View.GONE);
                if(wifes>=4)
                    b.wife.setVisibility(View.GONE);
                if(persons.size()>0)
                    b.hint.setVisibility(View.VISIBLE);
                else
                    b.hint.setVisibility(View.INVISIBLE);
            }
        });

reclc=false;
b.calculate.setOnClickListener(new View.OnClickListener() {


        @Override
    public void onClick(View view) {
            if (persons.size()>0) {
                reclc = false;
                inc = false;
                if (b.money.getText().toString().equals(""))
                    totalMoney = 1000000;
                else
                    totalMoney = Double.parseDouble(b.money.getText().toString());
//try{
                for (int i = 0; i < persons.size(); i++) {
                    heirs_map.put(persons.get(i).name, heirs_map.get(persons.get(i).name) + 1);


                }

                LinkedHashMap<String, Integer> amount;

                amount = all_amounts();

                for (int i = 0; i < persons.size(); i++) {
                    persons.get(i).fraction = fractionMap.get(amount.get(persons.get(i).name));

                }

                amount = dev(amount, heirs_map);
                if (Math.round(sum(amount)) < 24) {
                    reclc = true;

                    amount = recalc(amount);
                }

                if (sum(amount) > 24) {
                    inc = true;
                    for (int i = 0; i < persons.size(); i++) {
                        persons.get(i).amount = (amount.get(persons.get(i).name) / heirs_map.get(persons.get(i).name)) * totalMoney /
                                (double) sum(amount);

                        persons.get(i).amount = Math.round(persons.get(i).amount * 100.0) / 100.0;
                    }
                } else {
                    for (int i = 0; i < persons.size(); i++) {
                        persons.get(i).amount = (amount.get(persons.get(i).name) / heirs_map.get(persons.get(i).name)) * totalMoney / 24;

                        persons.get(i).amount = Math.round(persons.get(i).amount * 100.0) / 100.0;
                    }

                }
                Intent in = new Intent(getBaseContext(), TransActivity.class);
                in.putExtra("reclc", reclc);
                in.putExtra("inc", inc);
                startActivity(in);
            }else
                Toast.makeText(getBaseContext(),"الرجاء ادخال بعض الورثة",Toast.LENGTH_LONG).show();

    }
//    catch(Exception e){
//        b.title.setText(e.toString());
//    }}
});
    }

    public void removeItem(int position) {
        switch (persons.get(position).name){
            case HUSBAND:
                b.hasbend.setVisibility(View.VISIBLE);
                b.wife.setVisibility(View.VISIBLE);
                break;
            case WIFE:
                if(wifes==1) {
                    b.hasbend.setVisibility(View.VISIBLE);
                    wifes--;
                }
               else{
                   b.wife.setVisibility(View.VISIBLE);
                   wifes--;
                }
                break;
            case FATHER:
                b.father.setVisibility(View.VISIBLE);
                break;
            case MOTHER:
                b.mother.setVisibility(View.VISIBLE);
                break;
            case GRANDFATHER:
                b.grandfather.setVisibility(View.VISIBLE);
                break;
            case GRANDMOTHER:
                b.grandMother.setVisibility(View.VISIBLE);
                break;


        }
        persons.remove(position);
        myCustomAdapter.notifyItemRemoved(position);
        myCustomAdapter.notifyItemRangeChanged(position, persons.size());
        if(persons.size()>0)
            b.hint.setVisibility(View.VISIBLE);
        else
            b.hint.setVisibility(View.INVISIBLE);
    }

interface Heir {

    boolean isblocked();

    int amount();
}

static class Son implements Heir {

    @Override
    public boolean isblocked() {
        return false;
    }

    @Override
    public int amount() {
        if (isblocked() == true) {
            return 0;
        } else {
            if (heirs_map.get(DAUGHTER) > 0) {
                return -2;
            } else {
                return -1;
            }
        }
    }

}

static class Sons_son implements Heir {

    String[] blockers = {SON};

    @Override
    public boolean isblocked() {
        for (String blocker : blockers) {
            if (heirs_map.get(blocker) > 0) {
                return true;
            }

        }
        return false;
    }

    @Override
    public int amount() {
        if (isblocked() == true) {
            return 0;
        }
        if (heirs_map.get(SON_DAUGHTER) > 0) {
            return -2;
        } else {
            return -1;
        }
    }

}

static class Father implements Heir {

    @Override
    public boolean isblocked() {
        return false;
    }

    @Override
    public int amount() {
        if (heirs_map.get(SON) > 0 || heirs_map.get(SON_SON) > 0) {
            return 4;
        } else {
            return -3;
        }
    }
}

static class GrandFather implements Heir {

    String[] blockers = {FATHER};

    @Override
    public boolean isblocked() {
        for (String blocker : blockers) {
            if (heirs_map.get(blocker) > 0) {
                return true;
            }

        }
        return false;
    }

    @Override
    public int amount() {
        if (isblocked() == true) {
            return 0;
        }
        if (heirs_map.get(SON) > 0 || heirs_map.get(SON_SON) > 0) {
            return 4;
        } else {
            return -3;
        }
    }
}

static class Husband implements Heir {

    @Override
    public boolean isblocked() {
        return false;
    }

    @Override
    public int amount() {
        if (heirs_map.get(SON) > 0 || heirs_map.get(SON_SON) > 0
                || heirs_map.get(DAUGHTER) > 0 || heirs_map.get(SON_DAUGHTER) > 0) {
            return 6;
        }
        return 12;
    }
}

static class Wife implements Heir {

    @Override
    public boolean isblocked() {
        return false;
    }

    @Override
    public int amount() {
        if  (heirs_map.get(SON) > 0 || heirs_map.get(SON_SON) > 0
                || heirs_map.get(DAUGHTER) > 0 || heirs_map.get(SON_DAUGHTER) > 0) {
            return 3;
        }
        return 6;
    }
}

static class Brother implements Heir {

    String[] blockers = {FATHER, SON, GRANDFATHER, SON_SON};

    @Override
    public boolean isblocked() {
        for (String blocker : blockers) {
            if (heirs_map.get(blocker) > 0) {
                return true;
            }

        }
        return false;

    }

    @Override
    public int amount() {
        if (isblocked() == true) {
            return 0;
        }
        if (heirs_map.get(SISTER) > 0) {
            return -2;
        } else {
            return -1;
        }
    }
}

static class Brother_by_father implements Heir {

    String[] blockers = {FATHER, SON, GRANDFATHER, SON_SON,BROTHER};

    @Override
    public boolean isblocked() {
        for (String blocker : blockers) {
            if (heirs_map.get(blocker) > 0) {
                return true;
            }

        }
        return false;

    }

    @Override
    public int amount() {
        if (isblocked() == true) {
            return 0;
        }
        if ((heirs_map.get(SISTER) > 0) && (heirs_map.get(SON_DAUGHTER) > 0 || heirs_map.get(DAUGHTER) > 0)) {
            return 0;
        } else if (heirs_map.get(FATHER_SISTER) > 0) {
            return -2;
        } else {
            return -1;
        }

    }
}

static class Brother_son implements Heir {

    String[] blockers = {FATHER, SON,GRANDFATHER,
            SON_SON, BROTHER, FATHER_BROTHER};

    @Override
    public boolean isblocked() {
        for (String blocker : blockers) {
            if (heirs_map.get(blocker) > 0) {
                return true;
            }

        }
        return false;
    }

    @Override
    public int amount() {
        if (isblocked() == true) {
            return 0;
        }
        if ((heirs_map.get(FATHER_SISTER) > 0 || heirs_map.get(SISTER) > 0) && (heirs_map.get(SON_DAUGHTER) > 0 || heirs_map.get(DAUGHTER) > 0)) {
            return 0;
        } else {
            return -1;
        }

    }
}

static class Brother_by_father_son implements Heir {

    String[] blockers = {FATHER, SON, GRANDFATHER, SON_SON,
            BROTHER, FATHER_BROTHER, BROTHER_SON};

    @Override
    public boolean isblocked() {
        for (String blocker : blockers) {
            if (heirs_map.get(blocker) > 0) {
                return true;
            }

        }
        return false;
    }

    @Override
    public int amount() {
        if (isblocked() == true) {
            return 0;
        }
        if ((heirs_map.get(FATHER_SISTER) > 0 || heirs_map.get(SISTER) > 0) && (heirs_map.get(SON_DAUGHTER) > 0 || heirs_map.get(DAUGHTER) > 0)) {
            return 0;
        } else {
            return -1;
        }

    }
}

static class Uncle implements Heir {

    String[] blockers = {FATHER, SON, GRANDFATHER, SON_SON, BROTHER,
            FATHER_BROTHER, BROTHER_SON, FATHER_BROTHER_SON};

    @Override
    public boolean isblocked() {
        for (String blocker : blockers) {
            if (heirs_map.get(blocker) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int amount() {
        if (isblocked() == true) {
            return 0;
        }
        if ((heirs_map.get(FATHER_SISTER) > 0 || heirs_map.get(SISTER) > 0) && (heirs_map.get(SON_DAUGHTER) > 0 || heirs_map.get(DAUGHTER) > 0)) {
            return 0;
        } else {
            return -1;
        }

    }

}

static class Uncle_by_father implements Heir {

    String[] blockers = {FATHER,SON, GRANDFATHER, SON_SON,BROTHER,
            FATHER_BROTHER, BROTHER_SON,FATHER_BROTHER_SON, UNCLE};

    @Override
    public boolean isblocked() {
        for (String blocker : blockers) {
            if (heirs_map.get(blocker) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int amount() {
        if (isblocked() == true) {
            return 0;
        }
        if ((heirs_map.get(FATHER_SISTER) > 0 || heirs_map.get(SISTER) > 0) && (heirs_map.get(SON_DAUGHTER) > 0 || heirs_map.get(DAUGHTER) > 0)) {
            return 0;
        } else {
            return -1;
        }

    }

}

static class Uncle_son implements Heir {

    String[] blockers = {FATHER,SON, GRANDFATHER, SON_SON,BROTHER,
            FATHER_BROTHER, BROTHER_SON,FATHER_BROTHER_SON, UNCLE ,FATHER_UNCLE};

    @Override
    public boolean isblocked() {
        for (String blocker : blockers) {
            if (heirs_map.get(blocker) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int amount() {
        if (isblocked() == true) {
            return 0;
        }
        if ((heirs_map.get(FATHER_SISTER) > 0 || heirs_map.get(SISTER) > 0) && (heirs_map.get(SON_DAUGHTER) > 0 || heirs_map.get(DAUGHTER) > 0)) {
            return 0;
        } else {
            return -1;
        }

    }

}

static class Uncle_by_father_son implements Heir {

    String[] blockers = {FATHER,SON, GRANDFATHER, SON_SON,BROTHER,
            FATHER_BROTHER, BROTHER_SON,FATHER_BROTHER_SON, UNCLE, FATHER_UNCLE, UNCLE_SON};

    @Override
    public boolean isblocked() {
        for (String blocker : blockers) {
            if (heirs_map.get(blocker) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int amount() {
        if (isblocked() == true) {
            return 0;
        }
        if ((heirs_map.get(FATHER_SISTER) > 0 || heirs_map.get(SISTER) > 0) && (heirs_map.get(SON_DAUGHTER) > 0 || heirs_map.get(DAUGHTER) > 0)) {
            return 0;
        } else {
            return -1;
        }

    }

}

static class Daughter implements Heir {

    @Override
    public boolean isblocked() {
        return false;
    }

    @Override
    public int amount() {
        if (heirs_map.get(SON) == 0 && heirs_map.get(DAUGHTER) == 1) {
            return 12;
        } else if (heirs_map.get(SON) == 0 && heirs_map.get(DAUGHTER) > 1) {
            return 16;
        } else if (heirs_map.get(SON) >= 0 && heirs_map.get(DAUGHTER) >= 1) {
            return -4;
        }
        return -1;

    }
}

static class Daughter_of_sons implements Heir {

    String[] blockers = {SON};

    @Override
    public boolean isblocked() {
        for (String blocker : blockers) {
            if (heirs_map.get(blocker) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int amount() {
        if (isblocked() == true) {
            return 0;
        }
        if (heirs_map.get(DAUGHTER) >= 2 && heirs_map.get(SON_SON) == 0) {
            return 0;
        } else if (heirs_map.get(SON) == 0 && heirs_map.get(DAUGHTER) == 0 && heirs_map.get(SON_SON) == 0 && heirs_map.get(SON_DAUGHTER) == 1) {
            return 12;
        } else if (heirs_map.get(SON) == 0 && heirs_map.get(DAUGHTER) == 0 && heirs_map.get(SON_SON) == 0 && heirs_map.get(SON_DAUGHTER) > 1) {
            return 16;
        } else if (heirs_map.get(SON) == 0 && heirs_map.get(DAUGHTER) == 1 && heirs_map.get(SON_SON) == 0
                && (heirs_map.get(SON_DAUGHTER) == 1 || heirs_map.get(SON_DAUGHTER) > 1)) {
            return 4;
        } else if (heirs_map.get(SON) == 0 && heirs_map.get(SON_SON) >= 1 && heirs_map.get(SON_DAUGHTER) >= 1) {
            return -4;
        }
        return -1;
    }
}

static class Mother implements Heir {

    @Override
    public boolean isblocked() {
        return false;
    }

    @Override
    public int amount() {
        if (heirs_map.get(SON) >= 1 || heirs_map.get(DAUGHTER) >= 1 || heirs_map.get(SON_SON) >= 1
                || heirs_map.get(SON_DAUGHTER) >= 1
                || (heirs_map.get(BROTHER) + heirs_map.get(SISTER) + heirs_map.get(FATHER_BROTHER) + heirs_map.get(MOTHERS_RELATIVES) + heirs_map.get(FATHER_SISTER) > 1)) {
            return 4;
        } else if (heirs_map.get(SON) == 0 && heirs_map.get(DAUGHTER) == 0 && heirs_map.get(SON_SON) == 0 && heirs_map.get(SON_DAUGHTER) == 0
                && ((heirs_map.get(BROTHER) == 0 && heirs_map.get(SISTER) == 0) || (heirs_map.get(BROTHER) == 1 && heirs_map.get(SISTER) == 0) || (heirs_map.get(BROTHER) == 0 && heirs_map.get(SISTER) == 1))) {
            return 8;
        }
        return -1;
    }
}

static class Grand_mother implements Heir {

    String[] blockers = {MOTHER};

    @Override
    public boolean isblocked() {
        if (heirs_map.get(blockers[0]) > 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int amount() {
        if (isblocked() == true) {
            return 0;
        }
        if (sum(heirs_map) == heirs_map.get(GRANDMOTHER)) {
            return -1;
        }
        return 4;
    }
}

static class Sister implements Heir {

    String[] blockers = {FATHER, SON, GRANDFATHER, SON_SON};

    @Override
    public boolean isblocked() {
        for (String blocker : blockers) {
            if (heirs_map.get(blocker) > 0) {
                return true;
            }
        }
        return false;

    }

    @Override
    public int amount() {
        if (isblocked() == true) {
            return 0;
        }
        if (heirs_map.get(SON) == 0
                && heirs_map.get(DAUGHTER) == 0
                && heirs_map.get(SON_SON) == 0
                && heirs_map.get(SON_DAUGHTER) == 0
                && heirs_map.get(BROTHER) == 0
                && heirs_map.get(SISTER) == 1) {
            return 12;
        } else if (heirs_map.get(SON) == 0
                && heirs_map.get(DAUGHTER) == 0
                && heirs_map.get(SON_SON) == 0
                && heirs_map.get(SON_DAUGHTER) == 0
                && heirs_map.get(BROTHER) == 0
                && heirs_map.get(SISTER) > 1) {
            return 16;
        } else if (heirs_map.get(BROTHER) >= 1) {
            return -4;
        } else {
            return -1;
        }
    }
}

static class Sister_by_father implements Heir {

    String[] blockers = {FATHER, SON, GRANDFATHER, SON_SON,BROTHER};

    @Override
    public boolean isblocked() {
        for (String blocker : blockers) {
            if (heirs_map.get(blocker) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int amount() {
        if (isblocked() == true) {
            return 0;
        }
        if ((heirs_map.get(SISTER) > 0)
                && (heirs_map.get(SON_DAUGHTER) > 0 || heirs_map.get(DAUGHTER) > 0)) {
            return 0;
        } else if (heirs_map.get(SON) == 0
                && heirs_map.get(DAUGHTER) == 0
                && heirs_map.get(SON_SON) == 0
                && heirs_map.get(SON_DAUGHTER) == 0
                && heirs_map.get(BROTHER) == 0
                && heirs_map.get(SISTER) == 0
                && heirs_map.get(FATHER_BROTHER) == 0
                && heirs_map.get(FATHER_SISTER) == 1) {
            return 12;
        } else if (heirs_map.get(SON) == 0
                && heirs_map.get(DAUGHTER) == 0
                && heirs_map.get(SON_SON) == 0
                && heirs_map.get(SON_DAUGHTER) == 0
                && heirs_map.get(BROTHER) == 0
                && heirs_map.get(SISTER) == 0
                && heirs_map.get(FATHER_BROTHER) == 0
                && heirs_map.get(FATHER_SISTER) > 1) {
            return 16;
        } else if (heirs_map.get(SON) == 0
                && heirs_map.get(SON_SON) == 0
                && heirs_map.get(FATHER) == 0
                && heirs_map.get(GRANDFATHER) == 0
                && heirs_map.get(SON_DAUGHTER) == 0
                && heirs_map.get(DAUGHTER) == 0
                && heirs_map.get(BROTHER) == 0
                && heirs_map.get(SISTER) == 1
                && heirs_map.get(FATHER_BROTHER) == 0) {
            return 4;
        } else if (heirs_map.get(FATHER_BROTHER) > 0) {
            return -4;
        } else {
            return -1;
        }
    }
}

static class Mother_sons implements Heir {

    String[] blockers = {SON, SON_SON, DAUGHTER, SON_DAUGHTER, FATHER, GRANDFATHER};

    @Override
    public boolean isblocked() {
        for (String blocker : blockers) {
            if (heirs_map.get(blocker) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int amount() {
        if (isblocked() == true) {
            return 0;
        }
        if (sum(heirs_map) == heirs_map.get(MOTHERS_RELATIVES)) {
            return -1;
        } else if (heirs_map.get(MOTHERS_RELATIVES) > 1) {
            return 8;
        } else {
            return 4;
        }
    }
}

    static LinkedHashMap<String, Integer> all_amounts() {
        LinkedHashMap<String, Integer> am = new LinkedHashMap<String, Integer>();
        Collection c = (heirs_map.keySet());
        ArrayList<String> al = new ArrayList<String>(c);
        for (int i = 0; i < al.size(); i++) {
            if (heirs_map.get(al.get(i)) > 0) {
                if (i == 0) {
                    Son son = new Son();
                    am.put(al.get(i), son.amount());
                } else if (i == 1) {
                    Sons_son son_son = new Sons_son();
                    am.put(al.get(i), son_son.amount());
                } else if (i == 2) {
                    Father father = new Father();
                    am.put(al.get(i), father.amount());
                } else if (i == 3) {
                    GrandFather grand = new GrandFather();
                    am.put(al.get(i), grand.amount());
                } else if (i == 4) {
                    Husband husband = new Husband();
                    am.put(al.get(i), husband.amount());
                } else if (i == 5) {
                    Brother brother = new Brother();
                    am.put(al.get(i), brother.amount());
                } else if (i == 6) {
                    Brother_son b_son = new Brother_son();
                    am.put(al.get(i), b_son.amount());
                } else if (i == 7) {
                    Brother_by_father b_father = new Brother_by_father();
                    am.put(al.get(i), b_father.amount());
                } else if (i == 8) {
                    Brother_by_father_son bfs = new Brother_by_father_son();
                    am.put(al.get(i), bfs.amount());
                } else if (i == 9) {
                    Mother_sons ms = new Mother_sons();
                    am.put(al.get(i), ms.amount());
                } else if (i == 10) {
                    Sister sister = new Sister();
                    am.put(al.get(i), sister.amount());
                } else if (i == 11) {
                    Sister_by_father sf = new Sister_by_father();
                    am.put(al.get(i), sf.amount());
                } else if (i == 12) {
                    Uncle uncle = new Uncle();
                    am.put(al.get(i), uncle.amount());
                } else if (i == 13) {
                    Uncle_by_father uf = new Uncle_by_father();
                    am.put(al.get(i), uf.amount());
                } else if (i == 14) {
                    Uncle_son us = new Uncle_son();
                    am.put(al.get(i), us.amount());
                } else if (i == 15) {
                    Uncle_by_father_son ufs = new Uncle_by_father_son();
                    am.put(al.get(i), ufs.amount());
                } else if (i == 16) {
                    Daughter daughter = new Daughter();
                    am.put(al.get(i), daughter.amount());
                } else if (i == 17) {
                    Daughter_of_sons ds = new Daughter_of_sons();
                    am.put(al.get(i), ds.amount());
                } else if (i == 18) {
                    Mother mother = new Mother();
                    am.put(al.get(i), mother.amount());
                } else if (i == 19) {
                    Grand_mother gm = new Grand_mother();
                    am.put(al.get(i), gm.amount());
                } else if (i == 20) {
                    Wife wife = new Wife();
                    am.put(al.get(i), wife.amount());
                }
            }

        }
        return am;
    }

    static LinkedHashMap<String, Integer> dev(LinkedHashMap<String, Integer> am, LinkedHashMap<String, Integer> dict) {
        boolean bool = true;
        Collection dc = (dict.keySet());
        ArrayList<String> dal = new ArrayList<String>(dc);
        Collection ac = (am.keySet());
        ArrayList<String> aal = new ArrayList<String>(ac);
        for (int i = 0; i < aal.size(); i++) {
            if (am.get(aal.get(i)) < 0) {
                bool = false;
            }
        }
        if (bool == true) {
            osba=false;
            return am;
        } else {
            osba=true;
            boolean b = true;
            for (int i = 0; i < aal.size(); i++) {
                if (am.get(aal.get(i)) < -1) {
                    b = false;
                }
            }
            if (b) {
                for (int i = 0; i < aal.size(); i++) {
                    if (am.get(aal.get(i)) == -1) {
                        if (24 - sum(am) - 1 < 0) {
                            am.put(aal.get(i).toString(), 0);
                        } else {
                            am.put(aal.get(i).toString(), 24 - sum(am) - 1);
                        }
                    }
                }
            } else if (am.containsValue(-3)) {
                if (sum(am) + 7 >= 24) {
                    if (dict.get(FATHER) == 1) {
                        am.put(FATHER, 4);
                    } else {
                        am.put(GRANDFATHER, 4);
                    }
                } else {
                    if (dict.get(FATHER) == 1) {
                        am.put(FATHER, 24 - sum(am) - 3);
                    } else {
                        am.put(GRANDFATHER, 24 - sum(am) - 3);
                    }
                }
            } else {
                int remain = 24 - sum(am) - 6;
                if (remain < 0) {
                    remain = 0;
                }
                int male = 0;
                int female = 0;
                for (int i = 0; i < aal.size(); i++) {
                    if (am.get(aal.get(i)) == -2) {
                        male = dict.get(aal.get(i));
                    }
                    if (am.get(aal.get(i)) == -4) {
                        female = dict.get(aal.get(i));
                    }

                }
 //#########################################################################################################################################################################################
                int arrow = remain / (male * 2 + female);
                for (int i = 0; i < aal.size(); i++) {
                    if (am.get(aal.get(i)) == -2) {
                        am.put(aal.get(i), 2 * arrow * dict.get(aal.get(i)));
                    }

                    if (am.get(aal.get(i)) == -4) {
                        am.put(aal.get(i), arrow * dict.get(aal.get(i)));
                    }
                }
            }

        }
        return am;
    }

    static LinkedHashMap<String, Integer> recalc(LinkedHashMap<String, Integer> am) {
        int n = 24;
        int s = sum(am);
        Collection c = (am.values());
        ArrayList<String> al = new ArrayList<String>(c);

            if (am.containsKey(WIFE)) {
                n = 24 - am.get(WIFE);
                s = s - am.get(WIFE);
            }
            if (am.containsKey(HUSBAND)) {
                n = 24 - am.get(HUSBAND);
                s = s - am.get(HUSBAND);

        }
        for (LinkedHashMap.Entry<String, Integer> entry : am.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(key!=WIFE&&key!=HUSBAND)
                am.put(key,am.get(key)*n/s);
        }
//        for (int i = 0; i < al.size(); i++) {
//            if (al.get(i) != WIFE && al.get(i) != HUSBAND) {
//                am.put(al.get(i), am.get(al.get(i)) * n / s);
//            }
//        }
        return am;
    }

    static int indx(LinkedHashMap hash, int element) {

        Collection c = (hash.values());
        ArrayList al = new ArrayList(c);

        if (al.contains(element) == false) {
            return -1;
        }
        return al.indexOf(element);
    }

    static int sum(LinkedHashMap<String, Integer> hash) {
        Collection c = (hash.values());
        ArrayList al = new ArrayList(c);
        int sum = 0;
        for (int i = 0; i < al.size(); i++) {
            sum += (int) al.get(i);
        }
        return sum;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
