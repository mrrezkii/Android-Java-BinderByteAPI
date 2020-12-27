package com.esd.expeditioninfo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.esd.expeditioninfo.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrackingFragment extends Fragment {

    @BindView(R.id.etAWBNumber)
    EditText etAWBNumber;

    @BindView(R.id.spinCourier)
    NiceSpinner spinCourier;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tracking, container, false);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        etAWBNumber.post(new Runnable() {
            @Override
            public void run() {
                showKeyboard(getContext(), etAWBNumber);
            }
        });

        List<String> dataset = new LinkedList<>(Arrays.asList("JNE", "JNT", "POST", "TIKI", "WAHANA"));
        spinCourier.attachDataSource(dataset);
        spinCourier.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
            }
        });
    }

    void showKeyboard(Context mContext, View view) {
        if (mContext != null && view != null && view.requestFocus()) {
            InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @OnClick(R.id.btnContinue)
    void btnContinue(View view) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(getActivity()).
                inflate(
                        R.layout.bottomsheet_container,
                        view.findViewById(R.id.llBottomSheet)
                );
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
}

