package com.iam.bitcoin;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.widget.Toolbar;

import com.iam.bitcoin.MyNewApp.AppModel;
import com.iam.bitcoin.MyNewApp.AppModelAdapter;

import java.util.ArrayList;
import java.util.List;


public class AA_My_Apps extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        setContentView(R.layout.fragment_a_a__my__apps);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(0f);
        }

        getSupportActionBar().setTitle(getString(R.string.my_apps));


        context = this;

        setupActionBar();
        setupStatusBar();

        // Back button
//        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Apply status bar inset to top bar
//        ViewCompat.setOnApplyWindowInsetsListener(
//                findViewById(R.id.top_bar),
//                (v, insets) -> {
//
//                    int top = insets.getInsets(
//                            WindowInsetsCompat.Type.statusBars()
//                    ).top;
//
//                    v.setPadding(
//                            v.getPaddingLeft(),
//                            top,
//                            v.getPaddingRight(),
//                            v.getPaddingBottom()
//                    );
//
//                    return insets;
//                }
//        );

        RecyclerView rv = findViewById(R.id.myappNew);

//        rv.setHasFixedSize(true);
        rv.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.VERTICAL,
                        false
                )
        );

        List<AppModel> models1 = new ArrayList<>();

        // =========================================================
        // 1. KALI LINUX
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/X23bnr4M7EQEFN26u_IaqadPjGgVODiv18ZUPsww-UWjA_m7YkIyQvhwDS3RhfrDh0WU=w480-h960",
                getString(R.string.app_kali_title),
                getString(R.string.app_kali_tagline),
                getString(R.string.label_downloads),
                "1500000 +",
                getString(R.string.label_rating),
                "4.5",
                getString(R.string.label_size),
                "21MB",
                getString(R.string.label_install),
                getString(R.string.app_kali_bullet1),
                getString(R.string.app_kali_bullet2),
                getString(R.string.app_kali_bullet3),
                getString(R.string.app_kali_bullet4),
                getString(R.string.app_kali_tag1),
                getString(R.string.app_kali_tag2),
                getString(R.string.app_kali_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 2. BUG BOUNTY HUNTER
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/HjpZhN_rQ9yEuvJY9qQMThRbfW-9jXEHzNhVLZw305hfw7QtuIAzyX5IMO5oAthEcEM=w480-h960",
                getString(R.string.app_bugbounty_title),
                getString(R.string.app_bugbounty_tagline),
                getString(R.string.label_downloads),
                "90000 +",
                getString(R.string.label_rating),
                "4.0",
                getString(R.string.label_size),
                "21MB",
                getString(R.string.label_install),
                getString(R.string.app_bugbounty_bullet1),
                getString(R.string.app_bugbounty_bullet2),
                getString(R.string.app_bugbounty_bullet3),
                getString(R.string.app_bugbounty_bullet4),
                getString(R.string.app_bugbounty_tag1),
                getString(R.string.app_bugbounty_tag2),
                getString(R.string.app_bugbounty_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 3. DORKS - HACK
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/SvK47goNL4_LP-rPzoHRZjJGjCqeaW722Gh1i4AVSZoXSNpHqIB6l3sA2F3ASc6fYA=w480-h960",
                getString(R.string.app_dorks_title),
                getString(R.string.app_dorks_tagline),
                getString(R.string.label_downloads),
                "450000 +",
                getString(R.string.label_rating),
                "4.1",
                getString(R.string.label_size),
                "15MB",
                getString(R.string.label_install),
                getString(R.string.app_dorks_bullet1),
                getString(R.string.app_dorks_bullet2),
                getString(R.string.app_dorks_bullet3),
                getString(R.string.app_dorks_bullet4),
                getString(R.string.app_dorks_tag1),
                getString(R.string.app_dorks_tag2),
                getString(R.string.app_dorks_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 4. DARK WEB GUIDE
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/KC2bxYNBG9MRyEFmvY-LNok8Pgz4UAm8vD6lhJoYS99jQkMZTQSPoNNeQ5WLsnRY9w=w480-h960",
                getString(R.string.app_darkweb_title),
                getString(R.string.app_darkweb_tagline),
                getString(R.string.label_downloads),
                "495000 +",
                getString(R.string.label_rating),
                "4.2",
                getString(R.string.label_size),
                "11MB",
                getString(R.string.label_install),
                getString(R.string.app_darkweb_bullet1),
                getString(R.string.app_darkweb_bullet2),
                getString(R.string.app_darkweb_bullet3),
                getString(R.string.app_darkweb_bullet4),
                getString(R.string.app_darkweb_tag1),
                getString(R.string.app_darkweb_tag2),
                getString(R.string.app_darkweb_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 5. WIRESHARK TUTORIAL
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/6lQpAWQSFdZkuaLiY_PZDCOULqb2188IMiNQFvF-W22aEGh78TYKnZH0zOWZe5H_BUI=w480-h960",
                getString(R.string.app_wireshark_title),
                getString(R.string.app_wireshark_tagline),
                getString(R.string.label_downloads),
                "475000 +",
                getString(R.string.label_rating),
                "4.4",
                getString(R.string.label_size),
                "22MB",
                getString(R.string.label_install),
                getString(R.string.app_wireshark_bullet1),
                getString(R.string.app_wireshark_bullet2),
                getString(R.string.app_wireshark_bullet3),
                getString(R.string.app_wireshark_bullet4),
                getString(R.string.app_wireshark_tag1),
                getString(R.string.app_wireshark_tag2),
                getString(R.string.app_wireshark_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 6. METASPLOIT
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/PjfzpTbZMKywkKDtX1dLkzZroAZCLTwGrwIL3acVg_-DGeP4dYkKt_Z6R8bpaOReLQ=w480-h960",
                getString(R.string.app_metasploit_title),
                getString(R.string.app_metasploit_tagline),
                getString(R.string.label_downloads),
                "425000 +",
                getString(R.string.label_rating),
                "4.2",
                getString(R.string.label_size),
                "12MB",
                getString(R.string.label_install),
                getString(R.string.app_metasploit_bullet1),
                getString(R.string.app_metasploit_bullet2),
                getString(R.string.app_metasploit_bullet3),
                getString(R.string.app_metasploit_bullet4),
                getString(R.string.app_metasploit_tag1),
                getString(R.string.app_metasploit_tag2),
                getString(R.string.app_metasploit_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 7. LEARN LINUX
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/YAjsilnbrOYVjic1TTupvwuccuhRayJ9Jy8j53U6AQNcPawKHc6PB30ZkzOaC9egPdbJ=w480-h960",
                getString(R.string.app_learnlinux_title),
                getString(R.string.app_learnlinux_tagline),
                getString(R.string.label_downloads),
                "95000 +",
                getString(R.string.label_rating),
                "4.0",
                getString(R.string.label_size),
                "23MB",
                getString(R.string.label_install),
                getString(R.string.app_learnlinux_bullet1),
                getString(R.string.app_learnlinux_bullet2),
                getString(R.string.app_learnlinux_bullet3),
                getString(R.string.app_learnlinux_bullet4),
                getString(R.string.app_learnlinux_tag1),
                getString(R.string.app_learnlinux_tag2),
                getString(R.string.app_learnlinux_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 8. NMAP
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/GVGjqG712WYktKWPkA3K0r4xABGvPyN4tFUsvsPwuAktDp0AA9uqaT0hnnXovE2S9JU=w480-h960",
                getString(R.string.app_nmap_title),
                getString(R.string.app_nmap_tagline),
                getString(R.string.label_downloads),
                "95000 +",
                getString(R.string.label_rating),
                "4.1",
                getString(R.string.label_size),
                "16MB",
                getString(R.string.label_install),
                getString(R.string.app_nmap_bullet1),
                getString(R.string.app_nmap_bullet2),
                getString(R.string.app_nmap_bullet3),
                getString(R.string.app_nmap_bullet4),
                getString(R.string.app_nmap_tag1),
                getString(R.string.app_nmap_tag2),
                getString(R.string.app_nmap_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 9. INFORMATION GATHERING
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/-SigorY27uLIH_AMB07z14cFLzM9XxEGsuX1KWa7wdZkJMT7hSYDuSPcTaG0KlDthQ=w480-h960",
                getString(R.string.app_infogathering_title),
                getString(R.string.app_infogathering_tagline),
                getString(R.string.label_downloads),
                "90000 +",
                getString(R.string.label_rating),
                "4.7",
                getString(R.string.label_size),
                "14MB",
                getString(R.string.label_install),
                getString(R.string.app_infogathering_bullet1),
                getString(R.string.app_infogathering_bullet2),
                getString(R.string.app_infogathering_bullet3),
                getString(R.string.app_infogathering_bullet4),
                getString(R.string.app_infogathering_tag1),
                getString(R.string.app_infogathering_tag2),
                getString(R.string.app_infogathering_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 10. BURP SUITE
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/irwTFDPMVceceN0Df0UoXbnNY5CfX1JH3Tdxa4T1raoy6gMA3_r1r8a-_enNAiay-Q=w480-h960",
                getString(R.string.app_burpsuite_title),
                getString(R.string.app_burpsuite_tagline),
                getString(R.string.label_downloads),
                "97000 +",
                getString(R.string.label_rating),
                "4.0",
                getString(R.string.label_size),
                "17MB",
                getString(R.string.label_install),
                getString(R.string.app_burpsuite_bullet1),
                getString(R.string.app_burpsuite_bullet2),
                getString(R.string.app_burpsuite_bullet3),
                getString(R.string.app_burpsuite_bullet4),
                getString(R.string.app_burpsuite_tag1),
                getString(R.string.app_burpsuite_tag2),
                getString(R.string.app_burpsuite_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 11. JAVA
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/PIiSitzYa3YxjPKW1BD4UYC79o1xLVu3a4XpDbQnQPMN_MtGfMY32E3Kw4MGwL8o-Q=w480-h960",
                getString(R.string.app_java_title),
                getString(R.string.app_java_tagline),
                getString(R.string.label_downloads),
                "75000 +",
                getString(R.string.label_rating),
                "3.7",
                getString(R.string.label_size),
                "17MB",
                getString(R.string.label_install),
                getString(R.string.app_java_bullet1),
                getString(R.string.app_java_bullet2),
                getString(R.string.app_java_bullet3),
                getString(R.string.app_java_bullet4),
                getString(R.string.app_java_tag1),
                getString(R.string.app_java_tag2),
                getString(R.string.app_java_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 12. PYTHON
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/Y4ntLRYxYoWP2s6lvvV2l_izsqwKewJUo5wCrbAnlyuS_RO025h-2Z10O60xuR9180Hg=w480-h960",
                getString(R.string.app_python_title),
                getString(R.string.app_python_tagline),
                getString(R.string.label_downloads),
                "90000 +",
                getString(R.string.label_rating),
                "4.7",
                getString(R.string.label_size),
                "12MB",
                getString(R.string.label_install),
                getString(R.string.app_python_bullet1),
                getString(R.string.app_python_bullet2),
                getString(R.string.app_python_bullet3),
                getString(R.string.app_python_bullet4),
                getString(R.string.app_python_tag1),
                getString(R.string.app_python_tag2),
                getString(R.string.app_python_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 13. WEB DEVELOPMENT
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/vXsNSl7bC7yFPWNIgVEhnmUHXSm-_KqP81c1Zno_S4VMgNn-3CxuCPv3RY_CFWoEmII=w480-h960",
                getString(R.string.app_webdev_title),
                getString(R.string.app_webdev_tagline),
                getString(R.string.label_downloads),
                "89000 +",
                getString(R.string.label_rating),
                "4.2",
                getString(R.string.label_size),
                "14MB",
                getString(R.string.label_install),
                getString(R.string.app_webdev_bullet1),
                getString(R.string.app_webdev_bullet2),
                getString(R.string.app_webdev_bullet3),
                getString(R.string.app_webdev_bullet4),
                getString(R.string.app_webdev_tag1),
                getString(R.string.app_webdev_tag2),
                getString(R.string.app_webdev_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 14. SWIFT
        // =========================================================
//
        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/CqmLBtDLxuXSl9VDGg3lw8C3tfp2WXvKnv2i4aWWLlBxnnp9vDT_oCToL7KQhmBs_Q=w480-h960",
                getString(R.string.app_swift_title),
                getString(R.string.app_swift_tagline),
                getString(R.string.label_downloads),
                "50000 +",
                getString(R.string.label_rating),
                "4.9",
                getString(R.string.label_size),
                "12MB",
                getString(R.string.label_install),
                getString(R.string.app_swift_bullet1),
                getString(R.string.app_swift_bullet2),
                getString(R.string.app_swift_bullet3),
                getString(R.string.app_swift_bullet4),
                getString(R.string.app_swift_tag1),
                getString(R.string.app_swift_tag2),
                getString(R.string.app_swift_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 15. KOTLIN
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/UCE7GKDU6EMHxjW_K0X8g6ABD_UUSrbvKF0OrOAdz8WS2ooBhS-Gi9w4A-lyW7nhvY8=w480-h960",
                getString(R.string.app_kotlin_title),
                getString(R.string.app_kotlin_tagline),
                getString(R.string.label_downloads),
                "70000 +",
                getString(R.string.label_rating),
                "4.3",
                getString(R.string.label_size),
                "11MB",
                getString(R.string.label_install),
                getString(R.string.app_kotlin_bullet1),
                getString(R.string.app_kotlin_bullet2),
                getString(R.string.app_kotlin_bullet3),
                getString(R.string.app_kotlin_bullet4),
                getString(R.string.app_kotlin_tag1),
                getString(R.string.app_kotlin_tag2),
                getString(R.string.app_kotlin_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 16. ANDROID UI DESIGN
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/2noLVPJ5wq2nuUyBjdddzLrEvwDCmS4s_eZiPcVrQuD_aLCJs4YW34wdikgSNKMeaYI=w480-h960",
                getString(R.string.app_androidui_title),
                getString(R.string.app_androidui_tagline),
                getString(R.string.label_downloads),
                "87000 +",
                getString(R.string.label_rating),
                "4.1",
                getString(R.string.label_size),
                "29MB",
                getString(R.string.label_install),
                getString(R.string.app_androidui_bullet1),
                getString(R.string.app_androidui_bullet2),
                getString(R.string.app_androidui_bullet3),
                getString(R.string.app_androidui_bullet4),
                getString(R.string.app_androidui_tag1),
                getString(R.string.app_androidui_tag2),
                getString(R.string.app_androidui_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 17. ANDROID APP DEVELOPMENT
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/FeIGNopfZg8EK4Qu8nIxg6LTjf2n8ZnSe55VN1U_HpEuy58yH4QShcWl4SnbRFFM5pIw=w480-h960",
                getString(R.string.app_androiddev_title),
                getString(R.string.app_androiddev_tagline),
                getString(R.string.label_downloads),
                "95000 +",
                getString(R.string.label_rating),
                "3.9",
                getString(R.string.label_size),
                "21MB",
                getString(R.string.label_install),
                getString(R.string.app_androiddev_bullet1),
                getString(R.string.app_androiddev_bullet2),
                getString(R.string.app_androiddev_bullet3),
                getString(R.string.app_androiddev_bullet4),
                getString(R.string.app_androiddev_tag1),
                getString(R.string.app_androiddev_tag2),
                getString(R.string.app_androiddev_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 18. ARCH LINUX
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/xOLD5oveiVyHSpFzu-I3c0yWhYzx3i6EhiASOSor7j1yYlDvoXiAunUhby8oHGIQpHM=w480-h960",
                getString(R.string.app_archlinux_title),
                getString(R.string.app_archlinux_tagline),
                getString(R.string.label_downloads),
                "95000 +",
                getString(R.string.label_rating),
                "4.1",
                getString(R.string.label_size),
                "24MB",
                getString(R.string.label_install),
                getString(R.string.app_archlinux_bullet1),
                getString(R.string.app_archlinux_bullet2),
                getString(R.string.app_archlinux_bullet3),
                getString(R.string.app_archlinux_bullet4),
                getString(R.string.app_archlinux_tag1),
                getString(R.string.app_archlinux_tag2),
                getString(R.string.app_archlinux_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 19. BITCOIN
        // =========================================================
//
//        models1.add(new AppModel(
//                "https://play-lh.googleusercontent.com/bnsVzM4hkXgsIBvBgX2Trj5qD-yODMkuHAXMnV-ZPJnpba-dAsCdJ2xk9x6aTzZs25M=w480-h960",
//                getString(R.string.app_bitcoin_title),
//                getString(R.string.app_bitcoin_tagline),
//                getString(R.string.label_downloads),
//                "45000 +",
//                getString(R.string.label_rating),
//                "4.0",
//                getString(R.string.label_size),
//                "21MB",
//                getString(R.string.label_install),
//                getString(R.string.app_bitcoin_bullet1),
//                getString(R.string.app_bitcoin_bullet2),
//                getString(R.string.app_bitcoin_bullet3),
//                getString(R.string.app_bitcoin_bullet4),
//                getString(R.string.app_bitcoin_tag1),
//                getString(R.string.app_bitcoin_tag2),
//                getString(R.string.app_bitcoin_tag3),
//                getString(R.string.label_free)
//        ));

        // =========================================================
        // 20. COMPUTER SHORTCUT KEYS
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/LQHkCNp6MDPf4txbLiDYWhrWwCfMN0Z3pPLyrhrNxhU2vtvJ0rDOm2u16mAnNBe7DCk=w480-h960",
                getString(R.string.app_shortcuts_title),
                getString(R.string.app_shortcuts_tagline),
                getString(R.string.label_downloads),
                "70000 +",
                getString(R.string.label_rating),
                "4.8",
                getString(R.string.label_size),
                "22MB",
                getString(R.string.label_install),
                getString(R.string.app_shortcuts_bullet1),
                getString(R.string.app_shortcuts_bullet2),
                getString(R.string.app_shortcuts_bullet3),
                getString(R.string.app_shortcuts_bullet4),
                getString(R.string.app_shortcuts_tag1),
                getString(R.string.app_shortcuts_tag2),
                getString(R.string.app_shortcuts_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 21. IT INTERVIEW QUESTIONS
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/fh3uMjqGHeW4S6I3FBwn5jIU_ninOlj6kjiXrcexoS_wBvKPmLij70g2u8JsgKazjg=w480-h960",
                getString(R.string.app_itinterview_title),
                getString(R.string.app_itinterview_tagline),
                getString(R.string.label_downloads),
                "10000 +",
                getString(R.string.label_rating),
                "4.2",
                getString(R.string.label_size),
                "22MB",
                getString(R.string.label_install),
                getString(R.string.app_itinterview_bullet1),
                getString(R.string.app_itinterview_bullet2),
                getString(R.string.app_itinterview_bullet3),
                getString(R.string.app_itinterview_bullet4),
                getString(R.string.app_itinterview_tag1),
                getString(R.string.app_itinterview_tag2),
                getString(R.string.app_itinterview_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 22. DAILY ENGLISH SPEAK
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/ItYPMtGqav4EdiuBV8e6mMhOdxbqBptoMDFLeHyHUXVQ7s_7gFwflCd9t2S_Y9sLpF8=w480-h960",
                getString(R.string.app_englishspeak_title),
                getString(R.string.app_englishspeak_tagline),
                getString(R.string.label_downloads),
                "45000 +",
                getString(R.string.label_rating),
                "5.0",
                getString(R.string.label_size),
                "15MB",
                getString(R.string.label_install),
                getString(R.string.app_englishspeak_bullet1),
                getString(R.string.app_englishspeak_bullet2),
                getString(R.string.app_englishspeak_bullet3),
                getString(R.string.app_englishspeak_bullet4),
                getString(R.string.app_englishspeak_tag1),
                getString(R.string.app_englishspeak_tag2),
                getString(R.string.app_englishspeak_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // 23. SHORT ENGLISH STORY
        // =========================================================

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/TelSVUeMQdD6_o1ksLJwvze07md1U4Au4dv4WqLV-TWk5EWJZACgNXQiXqfwnPwP1iY=w480-h960",
                getString(R.string.app_englishstory_title),
                getString(R.string.app_englishstory_tagline),
                getString(R.string.label_downloads),
                "10000 +",
                getString(R.string.label_rating),
                "4.7",
                getString(R.string.label_size),
                "12MB",
                getString(R.string.label_install),
                getString(R.string.app_englishstory_bullet1),
                getString(R.string.app_englishstory_bullet2),
                getString(R.string.app_englishstory_bullet3),
                getString(R.string.app_englishstory_bullet4),
                getString(R.string.app_englishstory_tag1),
                getString(R.string.app_englishstory_tag2),
                getString(R.string.app_englishstory_tag3),
                getString(R.string.label_free)
        ));

        // =========================================================
        // ADAPTER
        // =========================================================

        AppModelAdapter appModelAdapter =
                new AppModelAdapter(context, models1);

        rv.setAdapter(appModelAdapter);


//        setupActionBar();
//        setupStatusBar();
    }

    private void setupActionBar() {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(
                getResources().getColor(R.color.background_color)));
    }

    private void setupStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.background_color));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.background_color));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        Context context = this; // or use getContext() if inside a fragment

        // Call the animateSlideRight method directly on the class
        return super.onOptionsItemSelected(item);
    }
}