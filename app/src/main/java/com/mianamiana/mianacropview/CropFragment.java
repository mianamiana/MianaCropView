package com.mianamiana.mianacropview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.mianamiana.view.CopyType;
import com.mianamiana.view.MianaCropView;
import com.mianamiana.view.ICropView;

/**
 * Created by Mianamiana on 15/11/20.
 */
public class CropFragment extends Fragment implements AdapterView.OnItemSelectedListener,View.OnClickListener, CompoundButton.OnCheckedChangeListener, ICropView.OnFillableChangeListener {


    private MianaCropView mMianaCropView;
    private CheckBox mCheckBox;
    private Button mBtnFill;
    private final CopyType[] types = {CopyType.SQUARE, CopyType.SCALE_2_3
            , CopyType.SCALE_3_4, CopyType.SCALE_9_16, CopyType.SCALE_3_2, CopyType.SCALE_4_3, CopyType.SCALE_16_9};
    private String[] typeNames = {"SQUARE", "2:3", "3:4", "9:16", "3:2", "4:3", "16:9"};
    private SampleActivity mActivity;
    private int mSampleNumber = 0;
    private Button mBtnSample;
    private Spinner mSpinner;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (SampleActivity) getActivity();
        setHasOptionsMenu(true);

    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_crop, null);
    }

    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
        mMianaCropView = (MianaCropView) rootView.findViewById(R.id.cropView);
        mMianaCropView.setImageResource(R.drawable.sample1);
        mMianaCropView.setOnFillableChangeListener(this);

//        mMianaCropView.setCropType(mCopyType);

        mCheckBox = (CheckBox) rootView.findViewById(R.id.checkbox);
        mCheckBox.setOnCheckedChangeListener(this);
        mBtnFill = (Button) rootView.findViewById(R.id.btnFill);
        rootView.findViewById(R.id.btnFill).setOnClickListener(this);
        mBtnSample = (Button) rootView.findViewById(R.id.btnBitmap);
        mBtnSample.setOnClickListener(this);
        mBtnSample.setText(mSampleNumber % 2 == 0 ? "sample1" : "sample2");
        mSpinner = (Spinner) mActivity.findViewById(R.id.spinner);
        mSpinner.setAdapter(new ArrayAdapter<String>(mActivity,android.R.layout.simple_list_item_1,typeNames));
        mSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mMianaCropView.setCropType(types[position]);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mMianaCropView.setIsAdvancedMode(isChecked);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBitmap:
                mSampleNumber++;
                mMianaCropView.setImageResource(mSampleNumber % 2 == 0 ? R.drawable.sample1 : R.drawable.sample2);
                mBtnSample.setText(mSampleNumber % 2 == 0 ?"sample1":"sample2");
                break;
            case R.id.btnFill:
                mMianaCropView.setFillMode(!mMianaCropView.isFillMode());
                break;
        }
    }


    @Override
    public void onFillableChange(boolean isFillable) {
        mBtnFill.setEnabled(isFillable);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mActivity.setCropBitmap(mMianaCropView.getCroppedBitmap());
        mActivity.getSupportFragmentManager().beginTransaction()
                .hide(this).add(SampleActivity.CONTAINER, new PreviewFragment())
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        menu.add("preview").setIcon(R.drawable.ic_arrow_forward_white_24dp)
                .setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(!hidden)
        {
            mActivity.setTitle(R.string.app_name);
            mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //NO-OP
    }
}


