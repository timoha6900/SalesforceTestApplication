package com.test.salesforcetestapplication.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.salesforcetestapplication.R;
import com.test.salesforcetestapplication.data.storage.AccountRecordModel;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.AccountsViewHolder> {

	private List<AccountRecordModel> mAccounts;

	public AccountsAdapter(List<AccountRecordModel> accounts) {
		mAccounts = accounts;
	}

	public void updateData(List<AccountRecordModel> accounts) {
		mAccounts = accounts;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public AccountsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accounts_list, parent, false);
		return new AccountsViewHolder(convertView);
	}

	@Override
	public void onBindViewHolder(@NonNull AccountsViewHolder holder, int position) {
		TreeMap<String, String> account = mAccounts.get(position).getRecord();
		Set<String> keySet = account.keySet();

		StringBuilder sb = new StringBuilder();
		for(String key : keySet){
			sb.append(key);
			sb.append(": ");
			sb.append(account.get(key));
			sb.append("\n");
		}
		holder.textView.setText(sb.toString());
	}

	@Override
	public int getItemCount() {
		return mAccounts.size();
	}


	public static class AccountsViewHolder extends RecyclerView.ViewHolder {

		protected TextView textView;

		public AccountsViewHolder(@NonNull View itemView) {
			super(itemView);
			textView = itemView.findViewById(R.id.account_txt);
		}
	}
}
