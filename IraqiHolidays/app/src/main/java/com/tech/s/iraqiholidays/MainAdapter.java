package com.tech.s.iraqiholidays;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tech.s.iraqiholidays.model.PackInfo;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyHolder> {
    private Context context;
    private List<PackInfo> list;

    MainAdapter(Context context, List<PackInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.packages_list, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        PackInfo info = list.get(position);
        holder.pName.setText(info.getpFrom() + " - " + info.getpTo());
        holder.pCompany.setText(info.getSupplierName());
        if (info.getpDetail().length() > 110) {
            holder.pDesc.setText(Html.fromHtml(info.getpDetail().substring(0, 110).trim() + " ..."));
        } else {
            holder.pDesc.setText(Html.fromHtml(info.getpDetail().trim()));
        }
        //holder.pRange.setText(info.getStartDate() + " " + info.getEndDate());
        holder.pPrice.setText("USD " + info.getpPrice() + "\n" + "افضل الاسعار");

        Picasso.with(context).load("http://www.visamarket.net/ihm/" + info.getImage()).error(R.drawable.error_logo).into(holder.pImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView pName, pCompany, pDesc, pPrice;
        ImageView pImage;
        Button addToCart, details;

        public MyHolder(View itemView) {
            super(itemView);
            pName = (TextView) itemView.findViewById(R.id.p_name);
            pCompany = (TextView) itemView.findViewById(R.id.p_company);
            pDesc = (TextView) itemView.findViewById(R.id.p_desc);
            //pRange = (TextView) itemView.findViewById(R.id.p_range);
            pPrice = (TextView) itemView.findViewById(R.id.p_price);

            pImage = (ImageView) itemView.findViewById(R.id.p_image);

            addToCart = (Button) itemView.findViewById(R.id.add_to_cart);
            details = (Button) itemView.findViewById(R.id.detials);

            addToCart.setOnClickListener(this);
            details.setOnClickListener(this);

            ChangeTypeface.setTypeface(context, pName);
            ChangeTypeface.setTypeface(context, pCompany);
            ChangeTypeface.setTypeface(context, pDesc);
            ChangeTypeface.setTypeface(context, pPrice);
            ChangeTypeface.setTypeface(context, addToCart);
            ChangeTypeface.setTypeface(context, details);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.add_to_cart:
                    break;
                case R.id.detials:
                    Intent intent = new Intent(context, Details.class);
                    intent.putExtra("pid", list.get(getLayoutPosition()).getpId());
                    intent.putExtra("p_image", list.get(getLayoutPosition()).getImage());
                    intent.putExtra("p_name", list.get(getLayoutPosition()).getpTo());
                    intent.putExtra("p_detail", list.get(getLayoutPosition()).getpDetail());
                    intent.putExtra("p_not_include", list.get(getLayoutPosition()).getNotInclude());
                    intent.putExtra("p_include", list.get(getLayoutPosition()).getInclude());
                    intent.putExtra("p_req", list.get(getLayoutPosition()).getRequirements());
                    intent.putExtra("p_price", list.get(getLayoutPosition()).getpPrice());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }
}
