package com.example.hospitalized.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.hospitalized.MainActivity;
import com.example.hospitalized.R;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

import ss.anoop.awesometextinputlayout.AwesomeTextInputLayout;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    EditText edtfullname, edtgender, edtcontact, edtdate, edtmonth, edtyear, edtaddress, edtcity, edtstate, edtemail, edtproblem;
    Button uploadbtn, takeimg, btnsubmit;
    RadioButton radioButtonyes, radioButtonNo;
    Spinner spinner, genderspinner;
    ImageView img;
    AwesomeTextInputLayout datespinner, monthspinner, yearspinner, genderinput;
    Animation animationup, animationdown;
    private final int requestcode = 1;
    int count = 1;
    private Bitmap bitmap;
    DatePickerDialog.OnDateSetListener dt = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int Date) {

            edtdate.setText(String.format(" " + Date));
            edtmonth.setText(String.format(" " + month + 1));
            edtyear.setText(String.format(" " + year));

        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_home_fragment, container, false);
        edtdate = root.findViewById(R.id.Date);
        edtmonth = root.findViewById(R.id.month);
        edtyear = root.findViewById(R.id.year);
        edtfullname = root.findViewById(R.id.name);
        spinner = root.findViewById(R.id.spinner);
        genderspinner = root.findViewById(R.id.genderspinner);
        genderinput = root.findViewById(R.id.inputgender);
        btnsubmit = root.findViewById(R.id.submit);
        datespinner = root.findViewById(R.id.inputldate);
        monthspinner = root.findViewById(R.id.inputlmonth);
        yearspinner = root.findViewById(R.id.inputlyear);
        takeimg = root.findViewById(R.id.Take);
        uploadbtn = root.findViewById(R.id.upload);
        img = root.findViewById(R.id.photo);

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        edtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                new DatePickerDialog(getContext(), dt, calendar.get(Calendar.DATE),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)).show();

            }
        });

        edtmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                new DatePickerDialog(getContext(), dt, calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.DATE)).show();

            }
        });

        edtyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();

                new DatePickerDialog(getContext(), dt, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)).show();

            }
        });


        String[] disease = {"Disease -", "Nose", "Eye", "Ear", "Hair"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, disease);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        String[] gender = {"Gender -", "Female", "Male"};

        ArrayAdapter arrayAdapterg = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, gender);
        arrayAdapterg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderspinner.setAdapter(arrayAdapterg);

        animationup = AnimationUtils.loadAnimation(getContext(), R.anim.buttonanimup);
        animationdown = AnimationUtils.loadAnimation(getContext(), R.anim.buttonanimdown);


        btnsubmit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnsubmit.startAnimation(animationup);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {

                    btnsubmit.startAnimation(animationdown);
                }
                return false;
            }
        });




        /*spinner.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });*/

        takeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(i3, count);

            }
        });


        return root;


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            Bundle b = data.getExtras();
            Bitmap btp = (Bitmap) b.get("data");
            img.setImageBitmap(btp);

            count++;

        }

        super.onActivityResult(requestCode, resultCode, data);


    }
}