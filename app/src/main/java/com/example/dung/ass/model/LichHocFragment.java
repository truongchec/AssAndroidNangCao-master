package com.example.dung.ass.model;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.dung.ass.LichHoc;
import com.example.dung.ass.LichHocAdapter;
import com.example.dung.ass.R;

import java.util.ArrayList;
import java.util.List;

public class LichHocFragment extends Fragment {
    private View lichhoc;
    private RecyclerView faLichHoc;

    private List<LichHoc> lichHocList;
    private LichHocAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lichhoc = inflater.inflate( R.layout.lichhocfragment, container, false );

        faLichHoc = lichhoc.findViewById( R.id.faLichhoc );


        lichHocList = new ArrayList<>();

        lichHocList.add(new LichHoc("Thứ 4,Ngày 17/10/2018","D415","Tiếng Anh 2.1","Trần Minh Hằng"));
        lichHocList.add(new LichHoc("Thứ 5,Ngày 18/10/2018","D414","Dự Án Mẫu","Nguyễn Hữu Huy"));
        lichHocList.add(new LichHoc("Thứ 6,Ngày 19/10/2018","D411","Android Nâng Cao","Nguyễn Hữu Huy"));
        lichHocList.add(new LichHoc("Thứ 2,Ngày 21/10/2018","D415","Tiếng Anh 2.1","Trần Minh Hằng"));
        lichHocList.add(new LichHoc("Thứ 3,Ngày 22/10/2018","D414","Dự Án Mẫu","Nguyễn Hữu Huy"));
        lichHocList.add(new LichHoc("Thứ 4,Ngày 23/10/2018","D411","Android Nâng Cao","Nguyễn Hữu Huy"));
        lichHocList.add(new LichHoc("Thứ 5,Ngày 24/10/2018","D415","Tiếng Anh 2.1","Trần Minh Hằng"));
        lichHocList.add(new LichHoc("Thứ 6,Ngày 25/10/2018","D414","Dự Án Mẫu","Nguyễn Hữu Huy"));
        lichHocList.add(new LichHoc("Thứ 7,Ngày 26/10/2018","D411","Android Nâng Cao","Nguyễn Hữu Huy"));
        lichHocList.add(new LichHoc("Thứ 2,Ngày 28/10/2018","D415","Tiếng Anh 2.1","Trần Minh Hằng"));
        lichHocList.add(new LichHoc("Thứ 3,Ngày 29/10/2018","D414","Dự Án Mẫu","Nguyễn Hữu Huy"));
        lichHocList.add(new LichHoc("Thứ 4,Ngày 30/10/2018","D411","Android Nâng Cao","Nguyễn Hữu Huy"));
        adapter = new LichHocAdapter(lichHocList);
        faLichHoc.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        faLichHoc.setLayoutManager(manager);
        return lichhoc;
    }
}
