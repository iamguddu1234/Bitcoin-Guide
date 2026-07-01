package com.iam.bitcoin;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;

import com.iam.bitcoin.MyNewApp.AppModel;
import com.iam.bitcoin.MyNewApp.AppModelAdapter;

import java.util.ArrayList;
import java.util.List;


public class AA_My_Apps extends Fragment {

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_a_a__my__apps, container, false);





        view.findViewById(R.id.btnBack).setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().popBackStack());


        RecyclerView  rv = view.findViewById(R.id.myappNew);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        List<AppModel> models1 = new ArrayList<>();


        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/X23bnr4M7EQEFN26u_IaqadPjGgVODiv18ZUPsww-UWjA_m7YkIyQvhwDS3RhfrDh0WU=w480-h960",
                "Kali Linux Master",
                "Learn. Hack. Defend",
                "Downloads",
                "980000 +",
                "Rating",
                "4.5",
                "Size",
                "21MB",
                "Install",
                "• Cybersecurity Tools & Ethical Hacking Mastery",
                "• Kali Linux & Pen Testing Skill Development",
                "• Forensics, Malware Analysis & Quiz-Based Learning",
                "• Premium, Fast, Ad-Free with Multilingual Support",
                "Ethical Hacking",
                "Pro Tools",
                "Cybersecurity",
                "Free"));



        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/HjpZhN_rQ9yEuvJY9qQMThRbfW-9jXEHzNhVLZw305hfw7QtuIAzyX5IMO5oAthEcEM=w480-h960",
                "Bug Bounty Hunter",
                "Find Bugs. Earn Rewards",
                "Downloads", "90000 +",
                "Rating", "4.0",
                "Size", "21MB",
                "Install",

                "• 100+ Real-World Bug Bounty Topics & Vulnerability Labs",
                "• Web, API, Mobile & Cloud Security Testing Guides",
                "• Live Exploit Walkthroughs, Quiz Games & Practice Challenges",
                "• Multilingual, Offline Access & Premium Pro Features",

                "Bug Bounty",
                "Hacking",
                "Pentesting",
                "Free"));


        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/SvK47goNL4_LP-rPzoHRZjJGjCqeaW722Gh1i4AVSZoXSNpHqIB6l3sA2F3ASc6fYA=w480-h960",
                "Dorks - Hack",
                "Master Google Dorks & OSINT",
                "Downloads", "450000 +",
                "Rating", "4.1",
                "Size", "15MB",
                "Install",

                "• 1000+ Google Dorks for OSINT & Ethical Security Research",
                "• Website, File, Login, Camera & Data Exposure Queries",
                "• Quiz Games, Practice Tasks & Real Search Scenarios",
                "• Multilingual Support & Premium Pro Features Available",

                "Google Dorks",
                "Recon",
                "Cybersecurity",
                "Free"
        ));

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/KC2bxYNBG9MRyEFmvY-LNok8Pgz4UAm8vD6lhJoYS99jQkMZTQSPoNNeQ5WLsnRY9w=w480-h960",
                "Dark Web Guide",
                "Master Dark Web Knowledge",
                "Downloads", "495000 +",
                "Rating", "4.2",
                "Size", "11MB",
                "Install",

                "• 100+ Dark Web & Privacy Topics from Beginner to Advanced",
                "• Tor Basics, Online Privacy, Risks & Legal Awareness",
                "• Quiz Games, Interactive Lessons & Safety Best Practices",
                "• Multilingual Support & Premium Pro Features Available",

                "Tor",
                "Deep Web",
                "Anonymous Browsing",
                "Free"
        ));
        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/6lQpAWQSFdZkuaLiY_PZDCOULqb2188IMiNQFvF-W22aEGh78TYKnZH0zOWZe5H_BUI=w480-h960",
                "Wireshark Tutorial",
                "Analyze Network Traffic Like a Pro",
                "Downloads", "475000 +",
                "Rating", "4.4",
                "Size", "22MB",
                "Install",

                "• 150+ Wireshark Topics from Beginner to Advanced",
                "• Packet Capture, Filters, Protocol Analysis & TCP/IP Labs",
                "• Quiz Games, Hands-On Practice & Real Traffic Scenarios",
                "• Multilingual Support & Premium Pro Features Available",

                "Network Analysis",
                "Sniffing",
                "Forensics",
                "Free"
        ));

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/PjfzpTbZMKywkKDtX1dLkzZroAZCLTwGrwIL3acVg_-DGeP4dYkKt_Z6R8bpaOReLQ=w480-h960",
                "Master In Metasploit",
                "Exploit. Test. Secure Systems",
                "Downloads", "425000 +",
                "Rating", "4.2",
                "Size", "12MB",
                "Install",

                "• 150+ Metasploit Topics from Beginner to Advanced",
                "• Payloads, Exploits, Post-Exploitation & Automation",
                "• Quiz Games, Interactive Labs & Real Attack Scenarios",
                "• Multilingual Support & Premium Pro Features Available",

                "Penetration Testing",
                "Exploit",
                "Vulnerability",
                "Free"
        ));

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/YAjsilnbrOYVjic1TTupvwuccuhRayJ9Jy8j53U6AQNcPawKHc6PB30ZkzOaC9egPdbJ=w480-h960",
                "Learn Linux",
                "Master Linux from Basics to Pro",
                "Downloads", "95000 +",
                "Rating", "4.0",
                "Size", "23MB",
                "Install",

                "• 100+ Linux Topics from Beginner to Advanced + 1000+ Commands & Practice Tasks",
                "• Linux Commands, Shell Scripting & System Administration",
                "• Projects, Quiz Games, Terminal Labs & Real Use-Cases",
                "• Multilingual Support, Offline Access & Premium Features",

                "Linux OS",
                "Shell Scripting",
                "Commands",
                "Free"
        ));

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/GVGjqG712WYktKWPkA3K0r4xABGvPyN4tFUsvsPwuAktDp0AA9uqaT0hnnXovE2S9JU=w480-h960",
                "Nmap Commands",
                "Master Network Scanning with Nmap",
                "Downloads", "95000 +",
                "Rating", "4.1",
                "Size", "16MB",
                "Install",

                "• 100+ Nmap Commands for Real-World Network Scanning",
                "• 100+ Topics Covered: Recon, Enumeration & Vulnerability Detection",
                "• Scan Types, NSE Scripts, OS Detection & Firewall Evasion",
                "• Multilingual Support, Quiz Games & Premium Pro Features",

                "Hacking",
                "Network Scanning",
                "Tools",
                "Free"
        ));


        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/-SigorY27uLIH_AMB07z14cFLzM9XxEGsuX1KWa7wdZkJMT7hSYDuSPcTaG0KlDthQ=w480-h960",
                "Information Gathering Tools Guide",
                "Master OSINT & Recon Like a Pro",
                "Downloads", "90000 +",
                "Rating", "4.7",
                "Size", "14MB",
                "Install",

                "• Most Important Recon & OSINT Tools with Detailed Tutorials",
                "• Nmap, Whois, DNSenum, theHarvester & Command-Based Guides",
                "• Other Ways to Gather Info: OSINT, Footprinting & Enumeration",
                "• Multilingual Support, Quiz Games & Premium Pro Features",

                "OSINT",
                "Recon Tools",
                "Footprinting",
                "Free"
        ));

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/irwTFDPMVceceN0Df0UoXbnNY5CfX1JH3Tdxa4T1raoy6gMA3_r1r8a-_enNAiay-Q=w480-h960",
                "Burp Suite Guide",
                "Master Web App Security Testing",
                "Downloads", "97000 +",
                "Rating", "4.0",
                "Size", "17MB",
                "Install",

                "• 100+ Burp Suite Topics & Web Security Testing Labs",
                "• Proxy, Repeater, Intruder, Scanner & Extensions Guide",
                "• Live Attack Walkthroughs, Quiz Games & Practice Tasks",
                "• Multilingual Support, Offline Access & Premium Pro Features",


                "Web Security",
                "Bug",
                "Vulnerability Research",
                "Free"
        ));

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/PIiSitzYa3YxjPKW1BD4UYC79o1xLVu3a4XpDbQnQPMN_MtGfMY32E3Kw4MGwL8o-Q=w480-h960",
                "Java Programs For Practice",
                "Learn & Master Java Coding",
                "Downloads", "75000 +",
                "Rating", "3.7",
                "Size", "17MB",
                "Install",

                "• 100+ Java Topics with 1000+ Practice Programs",
                "• Loops, Arrays, OOP, Strings, Collections & File Handling",
                "• Quiz Games, Interactive Exercises & Coding Challenges",
                "• Multilingual Support & Premium Pro Features Available",

                "Java Coding ",
                "Logic Building",
                "Learn Java",
                "Free"
        ));

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/Y4ntLRYxYoWP2s6lvvV2l_izsqwKewJUo5wCrbAnlyuS_RO025h-2Z10O60xuR9180Hg=w480-h960",
                "Python Programs For Practice",
                "Master Python with Hands-On Coding",
                "Downloads", "90000 +",
                "Rating", "4.7",
                "Size", "12MB",
                "Install",

                "• 100+ Python Topics with 1000+ Practice Programs",
                "• Basics, Loops, Functions, OOP, File Handling & Libraries",
                "• Quiz Games, Coding Challenges & Logic-Building Exercises",
                "• Multilingual Support & Premium Pro Features Available",

                "Learn Python",
                "Programming",
                "Python Apps",
                "Free"
        ));



        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/vXsNSl7bC7yFPWNIgVEhnmUHXSm-_KqP81c1Zno_S4VMgNn-3CxuCPv3RY_CFWoEmII=w480-h960",
                "Learn HTML, CSS, JS, PHP",
                "Build Websites. Code Faster",
                "Downloads", "89000 +",
                "Rating", "4.2",
                "Size", "14MB",
                "Install",

                "• 100+ Web Development Topics + 1000+ Coding Practice Programs",
                "• Learn HTML, CSS, JavaScript & PHP with Live Examples",
                "• Projects, Coding Exercises, Quiz Games & Practice Tasks",
                "• Multilingual Support, Offline Access & Premium Features",

                "Web Dev",
                "Build Websites",
                "Full Stack",
                "Free"));

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/CqmLBtDLxuXSl9VDGg3lw8C3tfp2WXvKnv2i4aWWLlBxnnp9vDT_oCToL7KQhmBs_Q=w480-h960",
                "Swift Programming",
                "Build iOS Apps with Swift",
                "Downloads", "50000 +",
                "Rating", "4.9",
                "Size", "12MB",
                "Install",

                "• 100+ Swift Topics from Beginner to Advanced + 1000+ Practice Programs",
                "• Learn Swift, UIKit & SwiftUI with Real iOS App Examples",
                "• Projects, Coding Exercises, Quiz Games & Interview Prep",
                "• Multilingual Support, Offline Access & Premium Features",

                "Learn Swift",
                "iOS Apps",
                "Swift Programs",
                "Free"

        ));

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/UCE7GKDU6EMHxjW_K0X8g6ABD_UUSrbvKF0OrOAdz8WS2ooBhS-Gi9w4A-lyW7nhvY8=w480-h960",
                "Kotlin Programs for Practice",
                "Master Kotlin with Hands‑On Coding",
                "Downloads", "70000 +",
                "Rating", "4.3",
                "Size", "11MB",
                "Install",

                "• 100+ Kotlin Topics with 1000+ Practice Programs",
                "• Basics, OOP, Coroutines, Collections & Android‑Ready Code",
                "• Quiz Games, Coding Challenges & Logic‑Building Exercises",
                "• Multilingual Support & Premium Pro Features Available",

                "Kotlin Coding",
                "Android Kotlin",
                "Learn Kotlin",
                "Free"
        ));













        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/2noLVPJ5wq2nuUyBjdddzLrEvwDCmS4s_eZiPcVrQuD_aLCJs4YW34wdikgSNKMeaYI=w480-h960",
                "Learn Android App UI Design",
                "Design Beautiful Android Interfaces",
                "Downloads", "87000 +",
                "Rating", "4.1",
                "Size", "29MB",
                "Install",

                "• 100+ Android UI/UX Topics + 1000+ Design Practice Tasks",
                "• XML Layouts, Material Design & Jetpack Compose UI",
                "• Live UI Projects, Quiz Games & Hands-On Exercises",
                "• Multilingual Support, Offline Access & Premium Features",

                "Material Design",
                "App Design",
                "UI/UX",
                "Free"
        ));

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/FeIGNopfZg8EK4Qu8nIxg6LTjf2n8ZnSe55VN1U_HpEuy58yH4QShcWl4SnbRFFM5pIw=w480-h960",
                "Learn Android App Development",
                "Build Real Android Apps Step by Step",
                "Downloads", "95000 +",
                "Rating", "3.9",
                "Size", "21MB",
                "Install",

                "• 150+ Android Development Topics from Beginner to Advanced",
                "• Java, Kotlin, XML, Jetpack & Android Studio Guides",
                "• Real App Projects, Code Examples & Best Practices",
                "• Offline Learning with Clean & Simple Interface",

                "Learn Android",
                "Mobile Apps",
                "Java Android",
                "Free"
        ));

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/xOLD5oveiVyHSpFzu-I3c0yWhYzx3i6EhiASOSor7j1yYlDvoXiAunUhby8oHGIQpHM=w480-h960",
                "Arch Linux Tutorial",
                "Install & Master Arch Linux Step by Step",
                "Downloads", "95000 +",
                "Rating", "4.1",
                "Size", "24MB",
                "Install",

                "• 150+ Arch Linux Topics from Beginner to Advanced",
                "• Installation, pacman, systemd, networking & desktop setup",
                "• Hands-On Labs, Command Practice & Real Use-Cases",
                "• Multilingual Support, Quiz Games & Premium Pro Features",

                "Linux Guide",
                "System Admin",
                "Commands",
                "Free"
        ));
        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/bnsVzM4hkXgsIBvBgX2Trj5qD-yODMkuHAXMnV-ZPJnpba-dAsCdJ2xk9x6aTzZs25M=w480-h960",
                "Bitcoin - Basic To Advance",
                "Learn Crypto & Blockchain Smartly",
                "Downloads", "45000 +",
                "Rating", "4.0",
                "Size", "21MB",
                "Install",

                "• 100+ Bitcoin & Blockchain Topics from Beginner to Advanced",
                "• Crypto Basics, Wallets, Mining, Trading & Security",
                "• Quiz Games, Interactive Lessons & Practice Challenges",
                "• Multilingual Support & Premium Pro Features Available",

                "Learn Crypto",
                "Blockchain",
                "Digital Currency",
                "Free"
        ));

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/LQHkCNp6MDPf4txbLiDYWhrWwCfMN0Z3pPLyrhrNxhU2vtvJ0rDOm2u16mAnNBe7DCk=w480-h960",
                "Computer Shortcut Keys",
                "Work Faster with Keyboard Shortcuts",
                "Downloads", "70000 +",
                "Rating", "4.8",
                "Size", "22MB",
                "Install",

                "• 50+ Most Daily Used App Shortcut Keys for Quick Work",
                "• Windows, MS Word, Excel, Chrome & System Shortcuts",
                "• Easy Learning, Practice Mode & Memory Boost Tips",
                "• Offline Access, No Ads & Clean Simple Interface",

                "Shortcut Keys",
                "Smart Work",
                "Keyboard Tips",
                "Free"
        ));


        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/fh3uMjqGHeW4S6I3FBwn5jIU_ninOlj6kjiXrcexoS_wBvKPmLij70g2u8JsgKazjg=w480-h960",
                "IT Interview Questions",
                "Crack Programming & Tech Interviews",
                "Downloads", "10000 +",
                "Rating", "4.2",
                "Size", "22MB",
                "Install",

                "• 100+ Programming Language Interview Topics & Questions",
                "• Java, Python, C, C++, PHP, JS & SQL Interview Guides",
                "• HR, Technical & Coding Interview Preparation",
                "• Offline Access with Clean & Simple Interface",

                "Interview Prep Q&A",
                "Job Prep",
                "Tech Jobs",
                "Free"
        ));

        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/ItYPMtGqav4EdiuBV8e6mMhOdxbqBptoMDFLeHyHUXVQ7s_7gFwflCd9t2S_Y9sLpF8=w480-h960",
                "Tutorial - Daily English Speak",
                "Speak English Confidently Every Day",
                "Downloads", "45000 +",
                "Rating", "5.0",
                "Size", "15MB",
                "Install",

                "• 100+ Spoken English Topics + 1000+ Daily Practice Sentences",
                "• Grammar, Vocabulary, Pronunciation & Conversation Skills",
                "• Speaking Exercises, Quiz Games & Real-Life Dialogues",
                "• Multilingual Support, Offline Access & Premium Features",

                "Spoken English",
                "Daily Practice",
                "Fluency",
                "Free"
        ));
        models1.add(new AppModel(
                "https://play-lh.googleusercontent.com/TelSVUeMQdD6_o1ksLJwvze07md1U4Au4dv4WqLV-TWk5EWJZACgNXQiXqfwnPwP1iY=w480-h960",
                "Short English Story",
                "Fun English Stories for Kids",
                "Downloads", "10000 +",
                "Rating", "4.7",
                "Size", "12MB",
                "Install",

                "• 500+ Short English Stories for Kids with Moral Lessons",
                "• Easy Words, Simple Sentences & Colorful Storytelling",
                "• Reading Practice, Vocabulary Boost & Fun Learning",
                "• Safe for Kids: No Ads, No Premium & Offline Reading",

                "Kids Stories",
                "Learn English",
                "Storybook",
                "Free"
        ));

        AppModelAdapter appModelAdapter = new AppModelAdapter(context, models1);
        rv.setAdapter(appModelAdapter);

        return view.getRootView();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}