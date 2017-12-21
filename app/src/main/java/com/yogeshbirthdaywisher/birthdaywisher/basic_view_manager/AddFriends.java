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
import com.yogeshbirthdaywisher.birthdaywisher.others.Sound;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFriends extends Fragment {
    EditText name,phone,dob;
    Activity mactivity;
    Button save,cancel;
    CheckBox check_sms;
    RadioButton radio_AD,radio_BS;
    Spinner spinner_month,spinner_day;

    public String []array_BS;
    public String []array_AD;
    public String []array_day;

    SQLiteDatabase db;
    DataBase dbhandler;
    private FragmentActivity myContext;

    ImageView button;
    Sound s;



    public AddFriends() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_friends, container, false);
        View myview=inflater.inflate(R.layout.fragment_add_friend_new,container,false);

            name = (EditText) myview.findViewById(R.id.name_layout);
            phone = (EditText) myview.findViewById(R.id.phone_layout);
            //dob = (EditText) myview.findViewById(R.id.editText3);
            save = (Button) myview.findViewById(R.id.save_layout);
            cancel=(Button) myview.findViewById(R.id.cancel_layout);
            check_sms=(CheckBox) myview.findViewById(R.id.checkBox_layout);
            radio_AD=(RadioButton) myview.findViewById(R.id.radioButton_AD_layout);
            radio_BS=(RadioButton) myview.findViewById(R.id.radioButton_BS_layout);
            spinner_day=(Spinner) myview.findViewById(R.id.spinner_day_layout);
            spinner_month=(Spinner) myview.findViewById(R.id.spinner_month_layout);
            button=(ImageView) myview.findViewById(R.id.imageButton_layout);
            check_sms.setChecked(true);
            radio_BS.setChecked(true);

            mactivity = this.getActivity();

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


          // if(radio_BS.isChecked()){
               ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(mactivity, android.R.layout.simple_dropdown_item_1line, array_BS);
               spinner_month.setAdapter(adapter1);

        try {
           /*}
            else{
               ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(mactivity, android.R.layout.simple_spinner_item, array_AD);
               spinner_month.setAdapter(adapter2);
           }*/
            first();



            radio_AD.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){

                        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(mactivity, android.R.layout.simple_dropdown_item_1line, array_AD);
                        spinner_month.setAdapter(adapter3);
                       // first();
                    Sound s=new Sound(mactivity);
                    s.checkSound();


                }
            });
            radio_BS.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(mactivity, android.R.layout.simple_dropdown_item_1line, array_BS);
                        spinner_month.setAdapter(adapter4);
                        //first();
                    Sound s=new Sound(mactivity);
                    s.checkSound();

                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewlist fragment = new viewlist();
                    FragmentTransaction fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                    fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("b").commit();
                    Sound s=new Sound(mactivity);
                    s.checkSound();
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mactivity,"Opening Contacts",Toast.LENGTH_LONG).show();
                    s.checkSound();
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                    startActivityForResult(intent,1);
                }
            });


            //keeping items on spinners





            save.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            Sound s=new Sound(mactivity);
                                            s.checkSound();
                                            try {
                                                int checking = 0,format=0;
                                                int a = 0, get_month = 0, get_day = 0;
                                                checking=check();

                                                if (check_sms.isChecked()) {
                                                    a = -1;
                                                } else a = 0;

                                                if (radio_BS.isChecked()) {
                                                    get_month = spinner_month.getSelectedItemPosition()+1;
                                                    get_day = spinner_day.getSelectedItemPosition()+1;

                                                } else if (radio_AD.isChecked()) {
                                                    get_month = spinner_month.getSelectedItemPosition()+1;
                                                    get_day = spinner_day.getSelectedItemPosition()+1;
                                                    format=1;

                                                }


                                                if (radio_AD.isChecked() || radio_BS.isChecked()) {
                                                    if (checking == 0) {
                                                        String nam = name.getText().toString();
                                                        String phon = phone.getText().toString();
                                                        String birth = "2073/" + get_month  + "/" + get_day ;


                                                        try {
                                                            dbhandler = new DataBase(mactivity, null, null, 1);
                                                            db = dbhandler.getDb();
                                                            String query = "insert into birth_table(name,phone,dob,month,day,format,schedule_id) values('" + nam + "','" + phon + "','" + birth + "'," + get_month + "," + get_day +","+format+ ","+a+");";
                                                            db.execSQL(query);
                                                            Toast.makeText(mactivity, "value successfully saved", Toast.LENGTH_LONG).show();
                                                            db.close();
                                                        } catch (Exception e) {
                                                            Toast.makeText(mactivity, e.toString(), Toast.LENGTH_LONG).show();
                                                        }
                                                        Toast.makeText(mactivity, nam + "\n" + phon + "\n" + birth, Toast.LENGTH_LONG).show();
                                                        name.setText("");
                                                        phone.setText("");
                                                        goBack();
                                                        //dob.setText("");
                                                    } else {
                                                        Toast.makeText(mactivity, "some error occured", Toast.LENGTH_LONG).show();

                                                    }


                                                }
                                                else{
                                                    Toast.makeText(mactivity,"Please sselect Birthday",Toast.LENGTH_LONG).show();
                                                }
                                            }
                                                catch(Exception e){
                                                    Toast.makeText(mactivity, e.toString(), Toast.LENGTH_LONG).show();
                                                }


                }



                                    }

            );
        }catch(Exception e){

        }


        return myview;


    }



    public int check(){
        int year=0;
        int month=0, day=0;
        //try {
            //String getdob = dob.getText().toString();
            if (name.getText().toString().equals("")) {
                Toast.makeText(mactivity, "Name cannot be empty", Toast.LENGTH_LONG).show();
                return 1;
            }/* else if (phone.getText().toString().equals("")) {
                Toast.makeText(mactivity, "Phone cannot be empty", Toast.LENGTH_LONG).show();
                return 1;
            } else if (dob.getText().toString().equals("")) {
                Toast.makeText(mactivity, "Date of Birth cannot be empty", Toast.LENGTH_LONG).show();
                return 1;
            } else if (getdob.length() > 10 || getdob.length() < 8) {
                Toast.makeText(mactivity, "Date Format is incorrect. Please Try Again1", Toast.LENGTH_LONG).show();
                return 1;
            }
            int x[] = new int[10];

            if (getdob.substring(0, 1).equals("1") || getdob.substring(0, 1).equals("2") || getdob.substring(0, 1).equals("3") || getdob.substring(0, 1).equals("4") || getdob.substring(0, 1).equals("5") || getdob.substring(0, 1).equals("6") || getdob.substring(0, 1).equals("7") || getdob.substring(0, 1).equals("8") || getdob.substring(0, 1).equals("9")) {
                Toast.makeText(mactivity, "birth start with 2", Toast.LENGTH_LONG).show();
                x[0] = Integer.parseInt(getdob.substring(0, 1));
                //Toast.makeText(mactivity, "" + x[0], Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(mactivity, "Date Format is incorrect. Please Try Again.2" + "\n" + getdob.substring(0, 1), Toast.LENGTH_LONG).show();
                return 1;
            }
            year=x[0];
            for (int i = 1; i < 4; i++) {
                //Toast.makeText(mactivity, "inside for " + i, Toast.LENGTH_LONG).show();
                if (getdob.substring(i, i + 1).equals("1") || getdob.substring(i, i + 1).equals("2") || getdob.substring(i, i + 1).equals("3") || getdob.substring(i, i + 1).equals("4") || getdob.substring(i, i + 1).equals("5") || getdob.substring(i, i + 1).equals("6") || getdob.substring(i, i + 1).equals("7") || getdob.substring(i, i + 1).equals("8") || getdob.substring(i, i + 1).equals("9") || getdob.substring(i, i + 1).equals("0")) {
                    x[i] = Integer.parseInt(getdob.substring(i, i+1));
                    //Toast.makeText(mactivity, getdob.substring(i,i+ 1), Toast.LENGTH_LONG).show();
                    year = year * 10 + x[i];
                } else {
                    Toast.makeText(mactivity, "Date Format is incorrect. Please Try Again.3", Toast.LENGTH_LONG).show();
                    return 1;
                }
            }
            if (getdob.substring(4, 5).equals("/") || getdob.substring(4, 5).equals(".") || getdob.substring(4, 5).equals("-") || getdob.substring(4, 5).equals("//")) {

            } else {
                Toast.makeText(mactivity, "Date Format is incorrect", Toast.LENGTH_LONG).show();
                return 1;
            }
            if(getdob.substring(6, 7).equals("/") || getdob.substring(6, 7).equals(".") || getdob.substring(6, 7).equals("-") || getdob.substring(6, 7).equals("//")||getdob.substring(7, 8).equals("/") || getdob.substring(7, 8).equals(".") || getdob.substring(7, 8).equals("-") || getdob.substring(7, 8).equals("//")){

            }
            else{
                Toast.makeText(mactivity, "Date Format is incorrect", Toast.LENGTH_LONG).show();
                return 1;

            }

            if (getdob.substring(6, 7).equals("/") || getdob.substring(6, 7).equals(".") || getdob.substring(6, 7).equals("-") || getdob.substring(6, 7).equals("//")) {
                x[5] = 0;
                if (getdob.substring(5, 6).equals("1") || getdob.substring(5, 6).equals("2") || getdob.substring(5, 6).equals("3") || getdob.substring(5, 6).equals("4") || getdob.substring(5, 6).equals("5") || getdob.substring(5, 6).equals("6") || getdob.substring(5, 6).equals("7") || getdob.substring(5, 6).equals("8") || getdob.substring(5, 6).equals("9") || getdob.substring(5, 6).equals("0")) {
                    x[6] = Integer.parseInt(getdob.substring(5, 6));
                } else {
                    Toast.makeText(mactivity, "Date Format is incorrect. Please Try Again.4", Toast.LENGTH_LONG).show();
                    return 1;
                }
                month = x[6];
                if((getdob.substring(7).length()>2)){
                    Toast.makeText(mactivity,"Date Format is Incorrect. Please Try Again.5",Toast.LENGTH_LONG ).show();
                    return 1;
                }
                if((getdob.substring(7).length()==2)){
                    x[8]=Integer.parseInt(getdob.substring(7,8));
                    x[9]=Integer.parseInt(getdob.substring(8));
                }
                if((getdob.substring(7).length()==1)){
                    x[8]=0;
                    x[9]=Integer.parseInt(getdob.substring(7));

                }
                day=x[8]*10+x[9];
                if(day>32){
                    return 1;
                }


            } else if (getdob.substring(7, 8).equals("/") || getdob.substring(7, 8).equals(".") || getdob.substring(7, 8).equals("-") || getdob.substring(7, 8).equals("//")) {

                    if (getdob.substring(5, 6).equals("1") || getdob.substring(5, 6).equals("2") || getdob.substring(5,6).equals("3") || getdob.substring(5, 6).equals("4") || getdob.substring(5,6).equals("5") || getdob.substring(5,6).equals("6") || getdob.substring(5,6).equals("7") || getdob.substring(5, 6).equals("8") || getdob.substring(5, 6).equals("9") || getdob.substring(5, 6).equals("0")) {
                        if (getdob.substring(6, 7).equals("1") || getdob.substring(6, 7).equals("2") || getdob.substring(6,7).equals("3") || getdob.substring(6, 7).equals("4") || getdob.substring(6,7).equals("5") || getdob.substring(6,7).equals("6") || getdob.substring(6,7).equals("7") || getdob.substring(6, 7).equals("8") || getdob.substring(6, 7).equals("9") || getdob.substring(6, 7).equals("0")) {
                           // Toast.makeText(mactivity, "error here", Toast.LENGTH_LONG).show();
                            x[5] = Integer.parseInt(getdob.substring(5, 6));
                            x[6] = Integer.parseInt(getdob.substring(6, 7));
                            //Toast.makeText(mactivity,"error here"+"\n"+x[5]+"\n"+x[6],Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(mactivity,"Date Format is Incorrect. Please Try Again.6",Toast.LENGTH_LONG).show();
                            return 1;

                        }

                    }
                    else {
                        Toast.makeText(mactivity,"Date Format is Incorrect. Please Try Again.6",Toast.LENGTH_LONG).show();
                        return 1;
                    }
                month = x[5] * 10 + x[6];
                if (month > 12) {
                    return 1;
                }
                if((getdob.substring(8).length()>2)){
                    Toast.makeText(mactivity,"Date Format is Incorrect. Please Try Again.5",Toast.LENGTH_LONG ).show();
                    return 1;
                }
                if((getdob.substring(8).length()==2)){
                    x[8]=Integer.parseInt(getdob.substring(8,9));
                    x[9]=Integer.parseInt(getdob.substring(9));
                }
                if((getdob.substring(8).length()==1)){
                    x[8]=0;
                    x[9]=Integer.parseInt(getdob.substring(8));

                }
                day=x[8]*10+x[9];
                if(day>32){
                    return 1;
                }

            }
            Toast.makeText(mactivity,year+"\n"+month+"\n"+day,Toast.LENGTH_LONG).show();



        }catch(Exception e){
            Toast.makeText(mactivity,e.toString(),Toast.LENGTH_LONG).show();
        }*/
        return 0;

    }

    public void first(){
        ArrayAdapter<String> adapter_day = new ArrayAdapter<String>(mactivity, android.R.layout.simple_dropdown_item_1line, array_day);
        spinner_day.setAdapter(adapter_day);
    }

    public void goBack(){
        Toast.makeText(mactivity,"Friend Added Sucessfully",Toast.LENGTH_LONG).show();

        Schedule fragment = new Schedule();
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
                phone.setText(number);
                //Toast.makeText(mactivity,""+number,Toast.LENGTH_LONG).show();
            }
        }

    }





}
