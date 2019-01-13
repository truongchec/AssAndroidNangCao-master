package com.example.dung.ass.model;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.dung.ass.R;
import com.example.dung.ass.database.DatabaseHelper;
import com.example.dung.ass.database.SqlDao.UserDAO;
import com.example.dung.ass.database.adapter.AdapterUser;
import com.example.dung.ass.database.model.User;

import java.util.List;

public class DangKyFragment extends Fragment {

    private EditText edname;
    private EditText edSdt;
    private Button btXacNhan;
    private EditText edMonHoc;
    private RecyclerView faNguoiDung;

    private View dangky;

    private AdapterUser adapterUser;
    private List<User> typeUsers;
    private DatabaseHelper databaseHelper;
    private UserDAO typeUserDAO;
    private LinearLayoutManager linearLayoutManager;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dangky = inflater.inflate( R.layout.dangkyfragment, container, false );

        edname =  dangky.findViewById(R.id.edname);
        edSdt = dangky.findViewById(R.id.edSdt);
        edMonHoc = dangky.findViewById( R.id.edmonhoc );
        btXacNhan =  dangky.findViewById(R.id.btXacNhan);






        btXacNhan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              // User user = typeUserDAO.getUserByID( edMonHoc.getText().toString().trim() );
                User user=typeUserDAO.getUserByID(edMonHoc.getText().toString().trim());

                if (user == null) {

                    String monhoc = edMonHoc.getText().toString().trim();
                    String name = edname.getText().toString().trim();
                    String sdt = edSdt.getText().toString().trim();
                    user = new User(monhoc,name,sdt);
                    user.id = monhoc;
                    user.name = name;
                    user.phone = sdt;
                    long result = typeUserDAO.insertUser(user);
                    if (name.equals( "" ) && monhoc.equals( "" )) {
                        edname.setError( "Không Được Bỏ Trống" );
                        edMonHoc.setError( "Không Được Bỏ Trống" );
                        return;
                    } else if (sdt.length() != 10) {
                        edSdt.setError( "Số Điện Thoại Yêu Cầu 10 số" );
                        return;
                    } else if ( result >0 ){
                        adapterUser.notifyDataSetChanged();
                        typeUsers.add( 0, new User( monhoc, name, sdt ) );
                    }else {
                        Toast.makeText( getActivity(), "Không Có Khối", Toast.LENGTH_SHORT ).show();
                    }
                }
                else {
                    Toast.makeText( getActivity(), "Đã Được Đăng Ký Rồi", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
        databaseHelper = new DatabaseHelper(getActivity());
        typeUserDAO = new UserDAO(databaseHelper);

        typeUsers = typeUserDAO.getAllUser();
        faNguoiDung= dangky.findViewById(R.id.faNguoiDung);

        adapterUser = new AdapterUser(getActivity(), typeUsers, typeUserDAO);

        faNguoiDung.setAdapter(adapterUser);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        faNguoiDung.setLayoutManager(linearLayoutManager);
        return dangky;
    }
}
