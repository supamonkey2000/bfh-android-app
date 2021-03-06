package com.jmoore.bevfacey;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

class CustomListAdapterSubPage_TitleText extends ArrayAdapter<String>{ //This class is the list of Information
    private final Activity context;
    private final String[]title;
    private final String[]text;

    CustomListAdapterSubPage_TitleText(Activity context,String[]title,String[]text){
        super(context,R.layout.mylistsubmenu,title);
        this.context=context;
        this.title=title;
        this.text=text;
    }

    @NonNull
    public View getView(int position,View view,@NonNull ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylistsubpage,null,true);

        String fixDesc=text[position];
        fixDesc=fixDesc.replaceFirst("\n\n","");
        if(fixDesc.contains(title[position])){
            fixDesc=fixDesc.replaceFirst(title[position],"");
        }

        String findStr=".,.";
        int lastIndex=0;
        int h3TitleCount=0;
        while(lastIndex!=-1){
            lastIndex=text[position].indexOf(findStr,lastIndex);
            if(lastIndex!=-1){
                h3TitleCount++;
                lastIndex+=findStr.length();
            }
        }

        TextView titleTV=new TextView(context);
        TextView textTV=new TextView(context);
        textTV.setLinksClickable(true);
        textTV.setAutoLinkMask(Linkify.ALL);
        textTV.setLinkTextColor(ContextCompat.getColor(context,R.color.colorLinks));
        titleTV.setTypeface(MainActivity.typefaceMenuItems);
        textTV.setTypeface(MainActivity.typefaceBody);
        titleTV.setText(title[position]);
        textTV.setText(fixDesc.replaceAll(",,,","\n\n"));

        int dp=10;
        float d=context.getResources().getDisplayMetrics().density;
        int margin=(int)(dp*d);
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(margin,0,margin,0);
        titleTV.setLayoutParams(lp);
        textTV.setLayoutParams(lp);

        LinearLayout ll=rowView.findViewById(R.id.subPageTitleTextLinearLayout);
        ll.addView(titleTV);
        ll.addView(textTV);
        return rowView;
    }
}