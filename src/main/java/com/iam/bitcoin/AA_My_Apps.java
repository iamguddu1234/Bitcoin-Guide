package com.iam.bitcoin;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class AA_My_Apps extends Fragment {

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_a_a__my__apps, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.myapp);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false));
        List<MyAppModel> models = new ArrayList<>();
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/X23bnr4M7EQEFN26u_IaqadPjGgVODiv18ZUPsww-UWjA_m7YkIyQvhwDS3RhfrDh0WU=w480-h960", "Kali Linux Master"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/HjpZhN_rQ9yEuvJY9qQMThRbfW-9jXEHzNhVLZw305hfw7QtuIAzyX5IMO5oAthEcEM=w480-h960", "Bug Bounty Hunter"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/vXsNSl7bC7yFPWNIgVEhnmUHXSm-_KqP81c1Zno_S4VMgNn-3CxuCPv3RY_CFWoEmII=w480-h960", "Learn HTML, CSS, JS, PHP"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/GVGjqG712WYktKWPkA3K0r4xABGvPyN4tFUsvsPwuAktDp0AA9uqaT0hnnXovE2S9JU=w480-h960", "Nmap Commands"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/ZwVVCs2MlaYfHmNikQ9PVWdCtX51YpibO4w3yeZ8ykTsWK2GgLdaDh1yKEnsoF2nmg=w480-h960", "Python Programs For Practice"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/UCE7GKDU6EMHxjW_K0X8g6ABD_UUSrbvKF0OrOAdz8WS2ooBhS-Gi9w4A-lyW7nhvY8=w480-h960", "Kotlin Programs for practice"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/6lQpAWQSFdZkuaLiY_PZDCOULqb2188IMiNQFvF-W22aEGh78TYKnZH0zOWZe5H_BUI=w480-h960", "Wireshark Tutorial"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/KC2bxYNBG9MRyEFmvY-LNok8Pgz4UAm8vD6lhJoYS99jQkMZTQSPoNNeQ5WLsnRY9w=w480-h960", "Dark Web Guide"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/PIiSitzYa3YxjPKW1BD4UYC79o1xLVu3a4XpDbQnQPMN_MtGfMY32E3Kw4MGwL8o-Q=w480-h960", "Java Programs For Practice"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/-SigorY27uLIH_AMB07z14cFLzM9XxEGsuX1KWa7wdZkJMT7hSYDuSPcTaG0KlDthQ=w480-h960", "Information Gather tools Guide"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/SvK47goNL4_LP-rPzoHRZjJGjCqeaW722Gh1i4AVSZoXSNpHqIB6l3sA2F3ASc6fYA=w480-h960", "Dorks - Hack"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/PjfzpTbZMKywkKDtX1dLkzZroAZCLTwGrwIL3acVg_-DGeP4dYkKt_Z6R8bpaOReLQ=w480-h960", "Master In Metasploit"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/fh3uMjqGHeW4S6I3FBwn5jIU_ninOlj6kjiXrcexoS_wBvKPmLij70g2u8JsgKazjg=w480-h960", "IT Interview Questions"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/ttkrnOo6-GKFQsCO7ZSfRF3L_3o7g5TfWZR1-4_ZrSpjOR4jt5_0zto-4mAJWFI0FOCI=w480-h960", "Bitcoin - Basic To Advance"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/xOLD5oveiVyHSpFzu-I3c0yWhYzx3i6EhiASOSor7j1yYlDvoXiAunUhby8oHGIQpHM=w480-h960", "Arch Linux Tutorial"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/G8sZFBYvBTdaJ7W1eM_KqlE2i5Nf-I0N3mQklzyQHakI39XUKXlSzPUXwYEEID2gzA=w480-h960", "Learn Android App Development"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/LQHkCNp6MDPf4txbLiDYWhrWwCfMN0Z3pPLyrhrNxhU2vtvJ0rDOm2u16mAnNBe7DCk=w480-h960", "Computer Shortcut Keys"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/TelSVUeMQdD6_o1ksLJwvze07md1U4Au4dv4WqLV-TWk5EWJZACgNXQiXqfwnPwP1iY=w480-h960", "Short English Story"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/ryhWKbIwBwADPXH3JnHtx-rbjrs6txEeDbTAmVFU-F1SNkTXz4AKCl6a6gO-0HXV3sk=w480-h960", "Learn Android App UI Design"));
        models.add(new MyAppModel("https://play-lh.googleusercontent.com/ItYPMtGqav4EdiuBV8e6mMhOdxbqBptoMDFLeHyHUXVQ7s_7gFwflCd9t2S_Y9sLpF8=w480-h960", "Tutorial - Daily English Speak"));
        MyAppAdapter myAppAdapter = new MyAppAdapter(context, models);
        recyclerView.setAdapter(myAppAdapter);

        return view.getRootView();



    }
}