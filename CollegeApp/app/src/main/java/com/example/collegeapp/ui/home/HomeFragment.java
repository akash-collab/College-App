package com.example.collegeapp.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.collegeapp.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ImageSlider imageSlider;
    private ImageView map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Correct the ID reference here
        imageSlider = view.findViewById(R.id.imageSlider);
        setSliderViews();

        map = view.findViewById( R.id.map );
        map.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();

            }
        } );

        return view;

    }

    private void openMap() {
        Uri uri = Uri.parse( "geo:0, 0?q=Techno India University kolkata" );
        Intent intent =new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage( "com.google.android.apps.maps" );
        startActivity( intent );

    }

    private void setSliderViews() {
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.image1, "   TIU", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image2, "   Campus", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image3, "   Campus", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image4, "   Techno India", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image5, "   Fest", ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
    }
}
