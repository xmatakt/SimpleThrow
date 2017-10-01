package majapp.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    private int numOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.numOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MainFragment fragment1 = new MainFragment();
                return fragment1;
            case 1:
                ListFragment fragment2 = new ListFragment();
                return fragment2;
            case 2:
                GraphFragment fragment3 = new GraphFragment();
                return fragment3;
            case 3:
                AnimationFragment fragment4 = new AnimationFragment();
                return fragment4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
