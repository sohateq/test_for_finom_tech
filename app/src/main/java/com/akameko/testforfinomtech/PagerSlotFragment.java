package com.akameko.testforfinomtech;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.akameko.testforfinomtech.eventbus.EventEnter;
import com.akameko.testforfinomtech.eventbus.EventWalletUpdate;
import com.akameko.testforfinomtech.repository.PagerItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

public class PagerSlotFragment extends Fragment {

    PagerItem pagerItem;
    private TextView currencyName;
    private TextView currencyRate;
    private TextView currencyCount;
    private EditText currencyEditText;

    public PagerSlotFragment(PagerItem pagerItem) {
        this.pagerItem = pagerItem;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pager_slot, container, false);
        currencyName = root.findViewById(R.id.text_view_currency_name_slot);
        currencyRate = root.findViewById(R.id.text_view_currency_rate_slot);
        currencyCount = root.findViewById(R.id.text_view_currency_count_slot);
        currencyEditText = root.findViewById(R.id.edit_text_currency_slot);

        currencyName.setText(pagerItem.getCurrencyName());
        currencyRate.setText("1EUR = " + pagerItem.getCurrencyRate().toString() + pagerItem.getCurrencyName());
        currencyCount.setText(String.format(Locale.US, "We have: %.2f", pagerItem.getCurrencyCount()));

        currencyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (currencyEditText.isFocused()) {
                    EventBus.getDefault().postSticky(new EventEnter(currencyName.getText().toString(), s.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventEnter event) {


        //currencyCount.setText(event.getCalculateCount());
        if (!currencyEditText.isFocused()) {

            if (!event.getCalculateCount().equals("")) {
                Double numberToShow = ((MainActivity) getActivity()).presenter.convert(event.getCurrencyName(), currencyName.getText().toString(), Double.parseDouble(event.getCalculateCount()));
                //Log.d("123", event.getCurrencyName() + currencyName.getText().toString() + event.getCalculateCount());
                //currencyEditText.setText(numberToShow.toString());
                currencyEditText.setText(String.format(Locale.US, "%.2f", numberToShow));
            } else {
                currencyEditText.setText("");
            }
        }

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onUpdateEvent(EventWalletUpdate event) {
        //currencyCount.setText("We have: " +  event.getCurrencyCount(currencyName.getText().toString()));
        currencyCount.setText(String.format(Locale.US, "We have: %.2f", event.getCurrencyCount(currencyName.getText().toString())));
        currencyEditText.setText("");
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public String getCurrencyName() {
        return currencyName.getText().toString();
    }

    public Double getCurrencyCount() {
        if (currencyEditText.getText().toString().equals("")) return 0d;
        return Double.parseDouble(currencyEditText.getText().toString());
    }
}
