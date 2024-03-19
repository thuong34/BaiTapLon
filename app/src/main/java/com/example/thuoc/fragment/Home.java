package com.example.thuoc.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.thuoc.MainActivity;
import com.example.thuoc.R;
import com.example.thuoc.adapter.productAdapter;
import com.example.thuoc.model.product;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View myview;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    SearchView searchView;

    RecyclerView rvproduct;
    ArrayList<product> list;
    productAdapter productadapter;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.fragment_home, container, false);


        rvproduct = myview.findViewById(R.id.rvproduct);

        searchView= myview.findViewById(R.id.search_item);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        list =getListproduct();
        productadapter = new productAdapter(list, this.getContext());
        rvproduct.setAdapter(productadapter);
        return myview;
    }
    public void reloadData(){
        Toast.makeText(getActivity(),"Reload Data",Toast.LENGTH_SHORT).show();
    }
    private void filterList(String newText) {
        ArrayList<product> filterList = new ArrayList<product>();
        for(product itemproduct : list){
            if(itemproduct.getName().toLowerCase().contains(newText.toLowerCase())){
                filterList.add(itemproduct);
            }
        }
        if(filterList.isEmpty()){
            Toast.makeText(myview.getContext(), "Không tìm thấy dữ liệu",Toast.LENGTH_LONG).show();
        }else{
            productadapter.setFilterList(filterList);
        }

    }

    private ArrayList<product> getListproduct() {
        ArrayList<product> lst = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lst.add(new product(9, "HAPACAL", 30.5F, R.drawable.thuoc9));
            lst.add(new product(8, "PANADOL", 15.6F, R.drawable.thuoc8));
            lst.add(new product(7, "CHELA-FERR FORTE", 48.2F, R.drawable.thuoc7));
            lst.add(new product(6, "GMP-WHO", 35.6F, R.drawable.thuoc6));
            lst.add(new product(5, "MOLMUPIRAVIR", 98.7F, R.drawable.thuoc5));
            lst.add(new product(4, "AZITHROMYCIN", 87.6F, R.drawable.thuoc4));
            lst.add(new product(3, "DEXAMETHASONE", 145.66F, R.drawable.thuoc3));
            lst.add(new product(2, "TERPIN-CODEIN", 120.4F, R.drawable.thuoc2));
            lst.add(new product(1, "POGANIC", 90.5F, R.drawable.thuoc1));
            lst.add(new product(0, "ĐÔNG Y", 80.6F, R.drawable.thuoc0));
        }

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.getContext(), 1);
        rvproduct.setLayoutManager(mLayoutManager);
        productAdapter productadapter = new productAdapter(lst,this.getContext());
        rvproduct.setAdapter(productadapter);
        return lst;
    }
}