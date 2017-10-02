package majapp.myapplication;

/**
 * TabLayout with SwipeView and Fragments: http://www.truiton.com/2015/06/android-tabs-example-fragments-viewpager/
 * sending data between fragments: - https://www.journaldev.com/14207/android-passing-data-between-fragments
 *                                 - https://stackoverflow.com/questions/27484245/pass-data-between-two-fragments-without-using-activity
 * simple animation: http://opensourceforgeeks.blogspot.sk/2015/05/creating-bouncing-ball-animation-in.html
 * animation interpolation methods: https://www.codota.com/android/methods/android.view.animation.Animation/setInterpolator
 * icons: http://www.iconarchive.com/show/classy-media-icons-by-uiconstock/play-icon.html
* */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements MainFragment.DataSender{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.setValuesText));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.showValuesText));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.showGraphText));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.showAnimationText));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void sendData(ThrowTrajectory trajectory){

        ListFragment fragment = (ListFragment)getSupportFragmentManager().getFragments().get(1); //getSupportFragmentManager().findFragmentById(R.id.pager);
        if(fragment != null)
            fragment.displayReceivedData(trajectory);
    }
}
