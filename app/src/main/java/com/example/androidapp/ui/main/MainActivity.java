package com.example.androidapp.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.androidapp.AndroidApplication;
import com.example.androidapp.R;
import com.example.androidapp.commons.models.Developer;
import com.example.androidapp.databinding.MainBinding;
import com.example.androidapp.di.components.DaggerMainComponent;
import com.example.androidapp.di.modules.MainModule;

import java.util.List;

import javax.inject.Inject;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MainActivity extends AppCompatActivity implements MainContract.View {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private MainBinding binding;
    private DevRecyclerAdapter recyclerAdapter;

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectMembers();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_list);
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitle(getTitle());
        recyclerAdapter = new DevRecyclerAdapter(presenter);
        binding.itemList.itemList.setAdapter(recyclerAdapter);
        if (binding.itemList.itemDetailContainer != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        presenter.getDevelopers();
    }


    @Override
    public void loadDevelopers(List<Developer> developerList) {
        recyclerAdapter.setDevs(developerList);
    }

    @Override
    public void showDev(Developer item) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(ItemDetailFragment.ARG_ITEM_ID, item);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        } else {
            startActivity(ItemDetailActivity.makeIntent(this, item));
        }
    }

    private void injectMembers() {
        DaggerMainComponent.builder()
                .appComponent(AndroidApplication.getInstance().getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }
}
