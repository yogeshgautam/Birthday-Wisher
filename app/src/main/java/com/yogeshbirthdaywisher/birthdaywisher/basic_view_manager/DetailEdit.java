package com.yogeshbirthdaywisher.birthdaywisher.basic_view_manager;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.yogeshbirthdaywisher.birthdaywisher.R;
import com.yogeshbirthdaywisher.birthdaywisher.database.DataBase;
import com.yogeshbirthdaywisher.birthdaywisher.others.CheckBack;
import com.yogeshbirthdaywisher.birthdaywisher.others.Sound;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailEdit extends Fragment {

    Activity mactivity;
    EditText name_layout,phone_layout;
    CheckBox checkBox;
    RadioButton format_bs,format_ad;
    Spinner spinner_month,spinner_day;
    Button save,cancel;
    DataBase dbHandler;
    SQLiteDatabase db;
    private FragmentActivity myContext;

    String name,phone,dob;
    int idd,format,month,day,schedule_id;

    Sound s;

    public String []array_BS;
    public String []array_AD;
    public String []array_day;

    int id_new;

    ImageView button;



    public DetailEdit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview= inflater.inflate(R.layout.fragment_detail_edit, container, false);
        mactivity=this.getActivity();
        dbHandler=new DataBase(mactivity,null,null,1);
        try {

            //getting references from layout.........................................
            name_layout = (EditText) myview.findViewById(R.id.name_layout);
            phone_layout = (EditText) myview.findViewById(R.id.phone_layout);
            checkBox = (CheckBox) myview.findViewById(R.id.checkBox_layout);
            format_bs = (RadioButton) myview.findViewById(R.id.radioButton_BS_layout);
            format_ad = (RadioButton) myview.findViewById(R.id.radioButton_AD_layout);
            spinner_month = (Spinner) myview.findViewById(R.id.spinner_month_layout);
            spinner_day = (Spinner) myview.findViewById(R.id.spinner_day_layout);
            save = (Button) myview.findViewById(R.id.save_layout);
            cancel = (Button) myview.findViewById(R.id.cancel_layout);
            button = (ImageView) myview.findViewById(R.id.imageButton_layout);
            //........................................................................
            s=new Sound(mactivity);
            this.array_BS = new String[] {
                    "Baisakh", "Jestha", "Ashar", "Shrawan", "Bhadra","Aswin","Karthik","Mangshir","Poush","Magh","Falgun","Chaitra"
            };
            this.array_AD=new String[]{
                    "jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"
            };
            this.array_day=new String[]{
                    "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32"
            };

            getDatabase();
        }catch(Exception e){
            Toast.makeText(mactivity,e.toString(),Toast.LENGTH_LONG).show();
        }

        save.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Toast.makeText(mactivity,"Saved Clicked",Toast.LENGTH_LONG).show();
                savedClicked();
                CheckBack.a=1;
                Sound s=new Sound(mactivity);
                s.checkSound();

            }
        });



        cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Toast.makeText(mactivity,"Cancel Clicked",Toast.LENGTH_LONG).show();
                CheckBack.a=1;
                Details fragment = new Details();
                FragmentTransaction fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("b").commit();
                Sound s=new Sound(mactivity);
                s.checkSound();


            }
        });

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                //startActivityForResult(intent, PICK_CONTACT);
                Toast.makeText(mactivity,"Opening Contacts",Toast.LENGTH_LONG).show();
                s.checkSound();

                //Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                //startActivityForResult(intent, PICK_CONTACT);

                Intent intent = new Intent(Intent.ACTION_PICK,ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent,1);
            }
        });







        format_ad.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(mactivity, android.R.layout.simple_dropdown_item_1line, array_AD);
                spinner_month.setAdapter(adapter);

                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(mactivity, android.R.layout.simple_dropdown_item_1line, array_day);
                spinner_day.setAdapter(adapter2);
                Sound s=new Sound(mactivity);
                s.checkSound();


            }
        });

        format_bs.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(mactivity, android.R.layout.simple_dropdown_item_1line, array_BS);
                spinner_month.setAdapter(adapter);

                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(mactivity, android.R.layout.simple_dropdown_item_1line, array_day);
                spinner_day.setAdapter(adapter2);
                Sound s=new Sound(mactivity);
                s.checkSound();

            }
        });



        return myview;
    }

    public void getDatabase(){
        db=dbHandler.getDb();
        String query = "select * FROM selector where id=1";
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        int id=c.getInt(c.getColumnIndex("idd"));
        second(id);
    }

    public void second(int id){
        id_new=id;
        db=dbHandler.getDb();

        String query = "select * FROM birth_table where id="+id;
        try {
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();

            while (!c.isAfterLast()) {
                int b = 1;
                name = c.getString(c.getColumnIndex("name"));
                phone = c.getString(c.getColumnIndex("phone"));
                dob = c.getString(c.getColumnIndex("dob"));
                idd=c.getInt(c.getColumnIndex("id"));
                format=c.getInt(c.getColumnIndex("format"));
                month=c.getInt(c.getColumnIndex("month"));
                day=c.getInt(c.getColumnIndex("day"));
                schedule_id=c.getInt(c.getColumnIndex("schedule_id"));
                c.moveToNext();

                b++;

            }
            c.close();
            db.close();
            showdata();
        } catch (Exception e) {
            Toast.makeText(mactivity, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void showdata(){
        name_layout.setText(name);
        phone_layout.setText(phone);

        if(schedule_id==0){
            checkBox.setChecked(false);
        }else{
            checkBox.setChecked(true);
        }

        if(format==0){
            format_bs.setChecked(true);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mactivity, android.R.layout.simple_dropdown_item_1line, array_BS);
            spinner_month.setAdapter(adapter);

            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(mactivity, android.R.layout.simple_dropdown_item_1line, array_day);
            spinner_day.setAdapter(adapter2);

        }else{
            format_ad.setChecked(true);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mactivity, android.R.layout.simple_dropdown_item_1line, array_AD);
            spinner_month.setAdapter(adapter);

            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(mactivity, android.R.layout.simple_dropdown_item_1line, array_day);
            spinner_day.setAdapter(adapter2);

        }

        spinner_month.setSelection(month-1);
        spinner_day.setSelection(day-1);


    }

    public void savedClicked(){
        String name_new=name_layout.getText().toString();
        String phone_new=phone_layout.getText().toString();
        int format_new=0;
        if(format_ad.isChecked()){
            format_new=1;
        }
        int month_new=spinner_month.getSelectedItemPosition()+1;
        int day_new=spinner_day.getSelectedItemPosition()+1;

        int schedule_id_new=schedule_id;
        if(checkBox.isChecked()){
            if(schedule_id==0){
                schedule_id_new=-1;
            }

        }else{
            schedule_id_new=0;
        }

        db=dbHandler.getDb();
        String query="UPDATE birth_table SET name='"+name_new+"',phone='"+phone_new+"',schedule_id="+schedule_id_new+",month="+month_new+",day="+day_new+",format="+format_new+" WHERE id="+id_new;
        db.execSQL(query);
        Toast.makeText(mactivity,"Value Updated Successfully",Toast.LENGTH_LONG).show();
        goBack();

    }

    public void goBack(){
        Details fragment = new Details();
        FragmentTransaction fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
        fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("b").commit();
    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity)activity;
        super.onAttach(activity);
    }


    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == 1)
        {
            String number = null;
            String name  = null;

            Uri uri = data.getData();
            Cursor cursor = mactivity.getApplication().getContentResolver().query(uri, null, null, null, null);
            if(cursor.moveToFirst())
            {
                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                number = cursor.getString(phoneIndex);
                name = cursor.getString(nameIndex);
                //name_text.setText(""+name);
                //number_text.setText(""+number);
                phone_layout.setText(number);
                //Toast.makeText(mactivity,""+number,Toast.LENGTH_LONG).show();
            }
        }

    }




}
