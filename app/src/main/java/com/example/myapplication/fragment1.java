package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment1 extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private List<recyclermodel> recyclermodels;
    EditText editText;
    Button buttton;

    String Currency_name[];
    String Currency_value[];
    Double input_value;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public fragment1() {
        // Required empty public constructor
    }


    public static fragment1 newInstance(String param1, String param2) {
        fragment1 fragment = new fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    private void call_api(){
        Toast.makeText(getActivity(), "Begin API Call", Toast.LENGTH_SHORT).show();


         final String BASE_URL= "https://api.exchangeratesapi.io/";


            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                        addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call <data_model> call=retrofitInterface.getAllData();


           System.out.println("step 1"+call.request());

        call.enqueue(new Callback<data_model>() {
            @Override
            public void onResponse(Call<data_model> call, Response<data_model> response) {
                parseData(response.body());

            }

            @Override
            public void onFailure(Call<data_model> call, Throwable t) {
                Toast.makeText(getContext(), ""+t.toString(), Toast.LENGTH_SHORT).show();
                System.out.println("step 1"+t.toString());
            }



        });



    }

    private void parseData(data_model body) {
        System.out.println(body.getBase());
        System.out.println(body.getDate());



        populate_recyclerdata(body.getRates());



    }

    private void populate_recyclerdata(Rates rates) {
        System.out.println(rates.toString());
        cleandata(rates.toString());

        for (int i =0; i < Currency_value.length;i++){
            System.out.println(Currency_name[i] + " | " + Currency_value[i]);


        }


        RecyclerViewAdapter recyclerViewAdapter= new RecyclerViewAdapter(getContext(),Currency_name,input_value,Currency_value);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void cleandata(String s) {
        String[] arrSplit = s.split(",");
        String[] a = new String[arrSplit.length];
        String[] b = new String[arrSplit.length];
        int j=0,k=0;

        for(int i = 0; i< arrSplit.length ; i++){
            System.out.println(i + " : " + arrSplit[i]);
            int temp = arrSplit[i].indexOf('=');
            a[i] = arrSplit[i].substring(0,temp);
            b[i] = arrSplit[i].substring(temp+1,arrSplit[i].length()-1);

            System.out.println("a :" + a[i] + " | b :" + b[i]);

        }




            Currency_name=a;
            Currency_value=b;




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment1, container, false);
        editText=view.findViewById(R.id.input);
        recyclerView= view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        buttton = (Button)view.findViewById(R.id.submit);

        buttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals(""))
                    input_value=0.0;
                else
                input_value = Double.valueOf(editText.getText().toString());



                call_api();
            }
        });



                return view;
    }
}