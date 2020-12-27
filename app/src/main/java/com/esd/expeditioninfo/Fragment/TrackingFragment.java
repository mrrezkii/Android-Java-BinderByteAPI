package com.esd.expeditioninfo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.esd.expeditioninfo.Network.ApiConstant;
import com.esd.expeditioninfo.Network.ApiService;
import com.esd.expeditioninfo.Network.RetrofitAPIClient;
import com.esd.expeditioninfo.Pojo.BinderByteResponse;
import com.esd.expeditioninfo.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackingFragment extends Fragment {

    @BindView(R.id.etAWBNumber)
    EditText etAWBNumber;

    @BindView(R.id.spinCourier)
    NiceSpinner spinCourier;

    private String strAwb, strItem;
    private String strCourier, strService, strStatus, strDate, strPrice, strWeight, strOrigin, strDestination, strShipper, strReceiver;
    private ApiService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tracking, container, false);
        ButterKnife.bind(this, rootView);
        apiService = RetrofitAPIClient.getRetrofit().create(ApiService.class);
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

        List<String> dataset = new LinkedList<>(Arrays.asList(
                "JNE",
                "POS",
                "JNT",
                "SICEPAT",
                "TIKI",
                "ANTERAJA",
                "WAHANA",
                "NINJA",
                "LION",
                "PCP EXPRESS",
                "JET EXPRESS",
                "REX EXPRESS",
                "FIRST LOGISTICS",
                "ID EXPRESS",
                "SHOPEE EXPRESS",
                "KGXPRESS",
                "SAP EXPRESS"));
        spinCourier.attachDataSource(dataset);
    }

    void showKeyboard(Context mContext, View view) {
        if (mContext != null && view != null && view.requestFocus()) {
            InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @OnClick(R.id.btnContinue)
    void btnContinue(View view) {
        strAwb = etAWBNumber.getText().toString();
        strItem = spinCourier.getSelectedItem().toString();
        checkTrack(strItem, strAwb);
    }

    private void checkTrack(String strItem, String strAwb) {
        Call<BinderByteResponse> call = apiService.tracking(ApiConstant.APIKEY_BINDERBYYE, strItem, strAwb);

        call.enqueue(new Callback<BinderByteResponse>() {
            @Override
            public void onResponse(Call<BinderByteResponse> call, Response<BinderByteResponse> response) {
                Log.d("Data", "" + response);
                if (response.isSuccessful()) {
                    strCourier = response.body().getData().getSummary().getCourier();
                    strStatus = response.body().getData().getSummary().getStatus();
                    strService = response.body().getData().getSummary().getService();
                    strDate = response.body().getData().getSummary().getDate();
                    strPrice = response.body().getData().getSummary().getAmount();
                    strWeight = response.body().getData().getSummary().getWeight();
                    strOrigin = response.body().getData().getDetail().getOrigin();
                    strDestination = response.body().getData().getDetail().getDestination();
                    strShipper = strOrigin = response.body().getData().getDetail().getShipper();
                    strReceiver = response.body().getData().getDetail().getReceiver();
                    BottomSheetAction(strCourier, strStatus, strService, strDate, strPrice, strWeight, strOrigin, strDestination, strShipper, strReceiver);
                } else {
                    Toast.makeText(getActivity(), "No Result",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BinderByteResponse> call, Throwable t) {

            }
        });
    }

    private void BottomSheetAction(String strCourier, String strStatus, String strService, String strDate, String strPrice, String strWeight, String strOrigin, String strDestination, String strShipper, String strReceiver) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(getActivity()).
                inflate(
                        R.layout.bottomsheet_container, getActivity().findViewById(R.id.llBottomSheet)
                );
        TextView tvAwb = bottomSheetView.findViewById(R.id.tvAwb);
        TextView tvCourier = bottomSheetView.findViewById(R.id.tvCourier);
        View tvStatus = bottomSheetView.findViewById(R.id.vStatus);
        TextView tvService = bottomSheetView.findViewById(R.id.tvService);
        TextView tvDate = bottomSheetView.findViewById(R.id.tvDate);
        TextView tvPrice = bottomSheetView.findViewById(R.id.tvPrice);
        TextView tvWeight = bottomSheetView.findViewById(R.id.tvWeight);
        TextView tvOrigin = bottomSheetView.findViewById(R.id.tvOrigin);
        TextView tvDestination = bottomSheetView.findViewById(R.id.tvDestination);
        TextView tvShipper = bottomSheetView.findViewById(R.id.tvShipper);
        TextView tvReceiver = bottomSheetView.findViewById(R.id.tvReceiver);

        tvAwb.setText(strAwb);
        tvCourier.setText(strCourier);
        tvService.setText(strService);
        tvDate.setText(strDate);
        tvPrice.setText(strPrice);
        tvWeight.setText(strWeight);
        tvOrigin.setText(strOrigin);
        tvDestination.setText(strDestination);
        tvShipper.setText(strShipper);
        tvReceiver.setText(strReceiver);

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
}

