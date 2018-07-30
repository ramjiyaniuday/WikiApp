package com.test.moneytap.wikiapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 30-07-2018.
 */

public class WikiAdapter extends RecyclerView.Adapter<WikiAdapter.MyViewHolder> {
    private Context context;
    private List<Page> modelList;
    private WikiAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView query, desc;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            query = view.findViewById(R.id.query);
            desc = view.findViewById(R.id.desc);
            thumbnail = view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                       listener.onItemSelected(modelList.get(getAdapterPosition()));
                }
            });
        }
    }


    public WikiAdapter(Context context, List<Page> modelList, WikiAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.modelList = modelList;
    }

    public void clearAll(){
        modelList.clear();
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wiki_row_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Page page = modelList.get(position);
        if(page!=null) {
            holder.query.setText(page.getTitle());

            if (page.getTerms() != null && page.getTerms().getDescription() != null && page.getTerms().getDescription().size() > 0)
                holder.desc.setText(page.getTerms().getDescription().get(0));

            if (page.getThumbnail() != null && page.getThumbnail().getSource() != null) {
                Glide.with(context)
                        .load(page.getThumbnail().getSource())
                    /*.apply(RequestOptions.circleCropTransform())*/
                        .into(holder.thumbnail);
            }
        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public interface WikiAdapterListener {
        void onItemSelected(Page page);
    }
}
