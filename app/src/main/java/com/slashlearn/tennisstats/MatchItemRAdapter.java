package com.slashlearn.tennisstats;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;

public class MatchItemRAdapter extends RecyclerView.Adapter<MatchItemRAdapter.ViewHolder> {

    private ArrayList<String> mfileNames;
    private Context mContext;

    public MatchItemRAdapter(Context context, ArrayList<String> fileNames){
        mfileNames = fileNames;
        mContext = context;
    }

    private void deleteItem(String matchTitleIn) {
        File file = new File(mContext.getFilesDir(), matchTitleIn);
        file.delete();

        //updateMatchTitleList
        String matchList = loadMatchString("MatchTitleList");
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(matchList.split(",")));
        tokens.remove(matchTitleIn);

        StringBuilder sb = new StringBuilder();
        for (String s : tokens) {
            System.out.println(s);
            sb.append(s + ",");
        }

        FileOutputStream fileOS = null;
        try {
            fileOS = mContext.openFileOutput("MatchTitleList", MODE_PRIVATE);
            fileOS.write(sb.toString().getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOS != null) {
                try {
                    fileOS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("successful delete");
    }

    private String loadMatchString(String matchTitleIn) {
        FileInputStream fileIS = null;
        String returnVal = "";

        try {
            fileIS = mContext.openFileInput(matchTitleIn);
            InputStreamReader inputStreamReader = new InputStreamReader(fileIS);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = bufferedReader.readLine()) != null) {
                sb.append(text);
            }
            returnVal = sb.toString();

        } catch (FileNotFoundException e ) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileIS != null) {
                try {
                    fileIS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return returnVal;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_listview_detail, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final int i = position;
        String fileName = mfileNames.get(position);
        String[] tokens = fileName.split("-~~-");
        for (String s : tokens) {
            System.out.println("Token:" +  s);
        }


        holder.matchDescriptionView.setText(tokens[1]);
        holder.matchDateView.setText(tokens[0]);
        //try catch in case the title is empty
        try {
            holder.matchTitleView.setText(tokens[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            holder.matchTitleView.setText(" ");
        }

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String matchLoadString = loadMatchString(mfileNames.get(i));
                Match currentMatch = new Match(matchLoadString);
                //take to StartPoint screen
                Intent loadMatchIntent = new Intent(mContext, StartPoint.class);
                loadMatchIntent.putExtra("currentMatch", currentMatch);
                mContext.startActivity(loadMatchIntent);
            }
        });
        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.deleteConfirmButton.setVisibility(View.VISIBLE);
                holder.cancelConfirmButton.setVisibility(View.VISIBLE);
            }
        });

        holder.deleteConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem(mfileNames.get(position));
                mfileNames.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mfileNames.size());
            }
        });

        holder.cancelConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.deleteConfirmButton.setVisibility(View.GONE);
                holder.cancelConfirmButton.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mfileNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView matchTitleView;
        private TextView matchDescriptionView;
        private TextView matchDateView;
        private ImageView deleteIcon;
        private Button deleteConfirmButton;
        private Button cancelConfirmButton;
        private RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            matchTitleView = itemView.findViewById(R.id.matchTitleTextView);
            matchDescriptionView = itemView.findViewById(R.id.matchDescripTextView);
            matchDateView = itemView.findViewById(R.id.matchDateTextView);
            deleteIcon = itemView.findViewById(R.id.deleteIconImageView);
            deleteConfirmButton = itemView.findViewById(R.id.deleteConfirmButton);
            cancelConfirmButton = itemView.findViewById(R.id.deleteCancelButton);
            parentLayout = itemView.findViewById(R.id.recycler_view_detail);
        }
    }
}
