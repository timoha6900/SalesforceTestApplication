package com.test.salesforcetestapplication.ui.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.salesforcetestapplication.R;
import com.test.salesforcetestapplication.ui.adapters.AccountsAdapter;
import com.test.salesforcetestapplication.utils.AccountsDynamicLoaderViewModel;
import com.test.salesforcetestapplication.utils.Constants;
import com.test.salesforcetestapplication.utils.DataStatusViewModel;

public class MainActivity extends AppCompatActivity {

	private Toolbar mToolbar;
	private DrawerLayout mNavigationDrawer;
	private RecyclerView mRecyclerView;

	private ContentLoadingProgressBar mProgressBar;
	private LinearLayout mErrorLayout;
	private TextView mErrorTxt;

	private DataStatusViewModel mDataStatusViewModel;
	private AccountsDynamicLoaderViewModel mAccountsLoader;
	private AccountsAdapter mAccountsAdapter;
	private LinearLayoutManager mLinearLayoutManager;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setTitle(R.string.drawer_menu_item_account);

		initViews();
		setupToolbar();
		setupDrawer();

		initInput();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home) {
			mNavigationDrawer.openDrawer(GravityCompat.START);
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if(mNavigationDrawer.isDrawerOpen(GravityCompat.START)) {
			mNavigationDrawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	private void initViews() {
		mToolbar = findViewById(R.id.toolbar);
		mNavigationDrawer = findViewById(R.id.navigation_drawer);
		mProgressBar = findViewById(R.id.progressbar);
		mErrorLayout = findViewById(R.id.error_layout);
		mErrorTxt = findViewById(R.id.error_txt);
		mRecyclerView = findViewById(R.id.accounts_recycler_view);

		findViewById(R.id.error_btn).setOnClickListener(v -> {
			mErrorLayout.setVisibility(View.GONE);
			mDataStatusViewModel.restart(getApplication());
		});
	}

	private void initInput() {
		mDataStatusViewModel = ViewModelProviders.of(this).get(DataStatusViewModel.class);
		mDataStatusViewModel.getStatus().observe(this, new Observer<Constants.DataProcessingStatus>() {
			@Override
			public void onChanged(@Nullable Constants.DataProcessingStatus status) {
				if(status == null){
					return;
				}
				switch (status) {
					case Loading:
						mProgressBar.setVisibility(View.VISIBLE);
						break;

					case Success:
						mProgressBar.setVisibility(View.GONE);
						setupAccountList();
						mDataStatusViewModel.getStatus().removeObserver(this);
						break;

					case NetworkError:
						mErrorTxt.setText(R.string.main_activity_error_msg_network_error);
						mProgressBar.setVisibility(View.GONE);
						mErrorLayout.setVisibility(View.VISIBLE);
						break;

					case DbError:
						mErrorTxt.setText(R.string.main_activity_error_msg_db_error);
						mProgressBar.setVisibility(View.GONE);
						mErrorLayout.setVisibility(View.VISIBLE);
						break;
				}
			}
		});
	}

	private void setupAccountList(){
		mLinearLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(mLinearLayoutManager);

		mAccountsLoader = ViewModelProviders.of(this).get(AccountsDynamicLoaderViewModel.class);
		mAccountsLoader.getAccountsData().observe(this, accounts -> {
			if(accounts != null){
				if(mAccountsAdapter == null){
					mAccountsAdapter = new AccountsAdapter(accounts);
					mRecyclerView.setAdapter(mAccountsAdapter);
				}else{
					mAccountsAdapter.updateData(accounts);
				}
			}
			mProgressBar.setVisibility(View.GONE);
		});
		mAccountsLoader.loadAccounts(Constants.DEF_ITEM_LOADING_AMOUNT);

		mRecyclerView.setOnScrollListener(mRecyclerViewScrollListener);
	}

	private void setupDrawer() {
		NavigationView navigationView = findViewById(R.id.navigation_view);
		navigationView.setCheckedItem(R.id.accounts_menu);
		navigationView.setNavigationItemSelectedListener(item -> {
			item.setChecked(true);
			mNavigationDrawer.closeDrawer(GravityCompat.START);
			if(item.getItemId() == R.id.exit_menu){
				finish();
			}
			return false;
		});
	}

	private void setupToolbar() {
		setSupportActionBar(mToolbar);
		ActionBar actionBar = getSupportActionBar();

		if(actionBar != null) {
			actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
	}

	RecyclerView.OnScrollListener mRecyclerViewScrollListener = new RecyclerView.OnScrollListener() {
		@Override
		public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
			super.onScrolled(recyclerView, dx, dy);
			int visibleItemCount = mLinearLayoutManager.getChildCount();
			int totalItemCount = mLinearLayoutManager.getItemCount();
			int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
			if(!mAccountsLoader.isLoading()){
				if((visibleItemCount + firstVisibleItem) >= totalItemCount) {
					mAccountsLoader.loadAccounts(Constants.DEF_ITEM_LOADING_AMOUNT);
					mProgressBar.setVisibility(View.VISIBLE);
				}
			}
		}
	};

}
