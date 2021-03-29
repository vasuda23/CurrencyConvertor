package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment2 extends Fragment {

    Spinner spinner;
    View view;

    Button Bday,Bmonth,Byear;
    String [] Currencies = {"CAD", "HKD", "ISK", "PHP", "DKK", "HUF", "CZK", "AUD", "RON", "SEK", "IDR", "INR", "BRL", "RUB", "HRK", "JPY", "THB", "CHF", "SGD", "PLN", "BGN", "TRY", "CNY", "NOK", "NZD", "ZAR", "USD", "MXN", "ILS", "GBP", "KRW", "MYR ", "EUR"};

    public String CurrencyName="USD";

    public fragment2() {
        // Required empty public constructor
    }


    public static fragment2 newInstance(String param1, String param2) {
        fragment2 fragment = new fragment2();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment2, container, false);
        spinner= view.findViewById(R.id.spinner);


        //Buttons

        Bday = (Button)view.findViewById(R.id.bDay);
        Bmonth = (Button)view.findViewById(R.id.bMonth);
        Byear = (Button)view.findViewById(R.id.bYear);





        Spinner spin = view.findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),
                        Currencies[position],
                        Toast.LENGTH_SHORT)
                        .show();
                CurrencyName=Currencies[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Bday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    get_data(0,CurrencyName);
                }
            }


        });

        Bmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    get_data(1,CurrencyName);
                }
            }
        });

        Byear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    get_data(2,CurrencyName);
                }
            }
        });

        ArrayAdapter ad   = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,Currencies);

        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spin.setAdapter(ad);


        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);



        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void get_data(Integer i,String CurrencyName){

        System.out.println("Calling for "+i);


        final String BASE_URL= "https://api.exchangeratesapi.io/";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime now = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
        }



        String start_date=now.toString().substring(0,10);

        String end_at=calculate_endDate(now,i);

        String url=BASE_URL+"history?start_at="+start_date+"&end_at="+end_at+"&symbols=EUR,"+CurrencyName;

        System.out.println("URL is"+url);

        url=url.trim();


        try {


            Retrofit retrofit = new Retrofit.Builder().baseUrl(url).
                    addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitInterfaceChart retrofitInterfaceChart = retrofit.create(RetrofitInterfaceChart.class);

            Call<chart_model> call = retrofitInterfaceChart.getChartData();

            System.out.println("step 1" + call.request());

            call.enqueue(new Callback<chart_model>() {
                @Override
                public void onResponse(Call<chart_model> call, Response<chart_model> response) {
                    System.out.println("Succsess" + response.body());
                }

                @Override
                public void onFailure(Call<chart_model> call, Throwable t) {
                    System.out.println("Fail" + t.toString());
                }
            });
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }



    }

    private static void setGraphData(data_model body) {
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private static String calculate_endDate(LocalDateTime now, Integer i) {
        String endDt="";
        LocalDate date;

                switch (i) {

                    case 0:
                    date = LocalDate.now().minusDays(1);
                    endDt=date.toString();
                    break;

                    case 1:
                    date = LocalDate.now().minusMonths(1);
                    endDt=date.toString();
                    break;

                    case 2:
                    date = LocalDate.now().minusYears(1);
                    endDt=date.toString();
                    break;
                                    }
        return endDt;
    }


}