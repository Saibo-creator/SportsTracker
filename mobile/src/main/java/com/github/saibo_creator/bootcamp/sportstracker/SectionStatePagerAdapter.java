package com.github.saibo_creator.bootcamp.sportstracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionStatePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();


    /**
     * Constructor for {@link FragmentStatePagerAdapter} that sets the fragment manager for the
     * adapter. This is the equivalent of calling
     * {@link #FragmentStatePagerAdapter(FragmentManager, int)} and passing in
     * {@link #BEHAVIOR_SET_USER_VISIBLE_HINT}.
     *
     * <p>Fragments will have {@link Fragment#setUserVisibleHint(boolean)} called whenever the
     * current Fragment changes.</p>
     *
     * @param fm fragment manager that will interact with this adapter
     * @deprecated use {@link #FragmentStatePagerAdapter(FragmentManager, int)} with
     * {@link #BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT}
     */
    public SectionStatePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public int getPositionByTitle(String title) {
        return mFragmentTitleList.indexOf(title);
    }



    /**
     * Return the Fragment associated with a specified position.
     *
     * @param i
     */
    @NonNull
    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);

    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mFragmentList.size();

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return  mFragmentTitleList.get(position);

    }
}
